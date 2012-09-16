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
import com.qut.spc.exceptions.InvalidArgumentException;

@Path("/calculate/")
public class CalculationController {

	private SystemCalculationAPI calculator;

	public CalculationController(){
		calculator=new SystemCalculationContainer(new ElectricityCalculator());
	}
	public CalculationController(SystemCalculationAPI calculator) {
		this.calculator=calculator;
	}
	
	@GET
	@Produces("application/xml")
	@Path("/electricityProduction")
	public String getElectricityProduction (
			@QueryParam("panelId")@DefaultValue("-1") int panelId,@QueryParam("panelCount")@DefaultValue("1") int panelCount, 
			@QueryParam("batteryId")@DefaultValue("-1")int batteryId,
			@QueryParam("inverterId")@DefaultValue("-1")int inverterId, 
			@QueryParam("postcode")@DefaultValue("")String postcode,
			@QueryParam("timespan")@DefaultValue("12")int timespan) throws InvalidArgumentException{
		
		String errorMessage="";
		if(panelId==-1){
			errorMessage+="PanelId is not defined \n";
		}
		if(batteryId==-1){
			errorMessage+="BatteryId is not defined \n";
		}
		if(inverterId==-1){
			errorMessage+="InverterId is not defined \n";
		}
		
		if(!errorMessage.equals(""))
			throw new InvalidArgumentException(errorMessage);
		
		try{
			calculator.setBatteryId(batteryId);
			calculator.setInverterId(inverterId);
			calculator.setLocation(postcode);
			calculator.setPanelId(panelId);
			calculator.setTimespan(timespan);
		}catch(EntityNotFoundException e){
			throw new InvalidArgumentException(e.getMessage());
		}catch(IllegalArgumentException e){
			throw new InvalidArgumentException(e.getMessage());
		}
		
		DecimalFormat df=new DecimalFormat("#.##");
		return 	"<electricityProduction>"+df.format(calculator.getElectricityProduction())+"</electricityProduction>";
	}

}
