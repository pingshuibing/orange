package com.qut.spc.controller;

import java.text.DecimalFormat;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.qut.spc.api.SystemCalculationAPI;
import com.qut.spc.calculations.ElectricityCalculator;
import com.qut.spc.calculations.SystemCalculationContainer;
import com.qut.spc.calculations.TotalCostCalculator;
import com.qut.spc.exceptions.InvalidArgumentException;
import com.qut.spc.model.Battery;
import com.qut.spc.model.Inverter;
import com.qut.spc.model.Panel;

@Path("/calculate/")
public class CalculationController {
	
	private SystemCalculationAPI calculator;

	public CalculationController(){
		calculator=new SystemCalculationContainer(new ElectricityCalculator(),new TotalCostCalculator());
	}
	
	public CalculationController(SystemCalculationAPI calculator) {
		this.calculator=calculator;
	}
	
	@GET
	@Produces("application/xml")
	@Path("/")
	public String getCalculations(
			@QueryParam("panelId")@DefaultValue("-1") int panelId,@QueryParam("panelCount")@DefaultValue("1") int panelCount, 
			@QueryParam("batteryId")@DefaultValue("-1")int batteryId,
			@QueryParam("inverterId")@DefaultValue("-1")int inverterId, 
			@QueryParam("postcode")@DefaultValue("")String postcode,
			@QueryParam("systemCost")@DefaultValue("-1")double systemCost,
			@QueryParam("inverterEfficiency")@DefaultValue("-1")double inverterEfficiency,
			@QueryParam("panelEfficiency")@DefaultValue("-1")double panelEfficiency,
			@QueryParam("panelOutput")@DefaultValue("-1")double panelOutput) throws InvalidArgumentException{
		
		verifyInputs(panelId, batteryId, inverterId, systemCost,
				inverterEfficiency, panelEfficiency, panelOutput);
		
		setComponents(panelId, batteryId, inverterId, postcode,
				inverterEfficiency, panelOutput, panelCount,panelEfficiency);
		
		
		DecimalFormat df=new DecimalFormat("#.##");
		StringBuilder builder=new StringBuilder();
		
		builder.append("<calculations>");
		calculateThings(builder, df);
		builder.append("</calculations>");
		return 	builder.toString();
	}

	private void setComponents(int panelId, int batteryId, int inverterId,
			String postcode, double inverterEfficiency, double panelOutput, int panelCount,double panelEfficiency) {
		try{
			//If battery isn't specified, then set a default battery
			if(batteryId<0){
				Battery battery=new Battery();
				calculator.setBattery(battery);
			}else{
				calculator.setBatteryId(batteryId);				
			}
			
			//If panel isn't specified, then set a default panel with provided capacity and efficiency
			if(panelId<0){
				Panel panel=new Panel();
				panel.setCapacity(panelOutput);
				panel.setEfficiency(panelEfficiency);
				calculator.setPanel(panel);
			}else{
				calculator.setPanelId(panelId);
				calculator.setpanelCount(panelCount);
			}
			
			//If inverter isn't specified, then set a default inverter with provided efficiency
			if(inverterId<0){
				Inverter inverter=new Inverter();
				inverter.setEfficiency(inverterEfficiency);
				calculator.setInverter(inverter);
			}else{
				calculator.setInverterId(inverterId);				
			}
			
			calculator.setLocation(postcode);
			
		}catch(EntityNotFoundException e){
			throw new InvalidArgumentException(e.getMessage());
		}catch(IllegalArgumentException e){
			throw new InvalidArgumentException(e.getMessage());
		}
	}

	private void verifyInputs(int panelId, int batteryId, int inverterId,
			double systemCost, double inverterEfficiency,
			double panelEfficiency, double panelOutput) {
		String errorMessage="";
		if(panelId<0 && (panelEfficiency<0||panelOutput<0||systemCost<0)){
			errorMessage+="PanelId or panelEfficiency and panelOutput is not defined \n";
		}
		if(batteryId<0&&systemCost<0){
			errorMessage+="BatteryId is not defined \n";
		}
		if(inverterId<0&& (inverterEfficiency<0||systemCost<0)){
			errorMessage+="InverterId or inverterEfficiency is not defined \n";
		}
		if(!errorMessage.equals("")){
			throw new InvalidArgumentException(errorMessage);
		}
	}
	
	private void calculateThings(StringBuilder builder,DecimalFormat df){
		calculator.setTimespan(365);
		double elProduction=calculator.getElectricityProduction();
		double tc=calculator.getTotalCost();
		double roi=calculator.getROI();
		
		calculator.setTimespan(30);
		double elProduction2=calculator.getElectricityProduction();
		double tc2=calculator.getTotalCost();
		double roi2=calculator.getROI();
		
		calculator.setTimespan(7);
		double elProduction3=calculator.getElectricityProduction();
		double tc3=calculator.getTotalCost();
		double roi3=calculator.getROI();
		
		appendParameter(elProduction, elProduction2, elProduction3, "electricityProduction", builder, df);
		appendParameter(tc, tc2, tc3, "totalCost", builder, df);
		appendParameter(roi, roi2, roi3, "returnOnInvestment", builder, df);		
	}
	
	private void appendParameter(double year,double month,double week, String name, StringBuilder builder,DecimalFormat format){
		builder.append("<"+name+">");
		builder.append("<year>"+format.format(year)+"</year>");
		builder.append("<month>"+format.format(month)+"</month>");
		builder.append("<week>"+format.format(week)+"</week>");
		builder.append("</"+name+">");
	}
}
