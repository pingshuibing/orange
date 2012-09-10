package com.qut.spc.controller;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.qut.spc.api.SystemCalculationAPI;
import com.qut.spc.calculations.SystemCalculationContainer;

@Path("/calculate/")
public class CalculationController {

	private SystemCalculationAPI calculator;

	public CalculationController(){
		calculator=new SystemCalculationContainer();
	}
	public CalculationController(SystemCalculationAPI calculator) {
		this.calculator=calculator;
	}
	
	@GET
	@Produces("application/xml")
	@Path("/electricityProduction")
	public String getElectricityProduction(
			@QueryParam("panelId")@DefaultValue("-1") int panelId,@QueryParam("panelCount")@DefaultValue("1") int panelCount, 
			@QueryParam("batteryId")@DefaultValue("-1")int batteryId,
			@QueryParam("inverterId")@DefaultValue("-1")int inverterId, 
			@QueryParam("postcode")@DefaultValue("")String postcode,
			@QueryParam("timespan")@DefaultValue("12")int timespan) {
		
		calculator.setBatteryId(batteryId);
		calculator.setInverterId(inverterId);
		calculator.setLocation(postcode);
		calculator.setPanelId(panelId);
		calculator.setTimespan(timespan);
		
		return 	"<electricityProduction>"+calculator.getElectricityProduction()+"</electricityProduction>";
	}

}
