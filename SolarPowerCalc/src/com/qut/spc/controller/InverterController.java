package com.qut.spc.controller;

import java.util.ArrayList;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.qut.spc.api.InverterFilterAPI;
import com.qut.spc.exceptions.InvalidArgumentException;
import com.qut.spc.model.Inverter;
import com.qut.spc.model.InverterContainer;

@Path("/inverter/")
public class InverterController {
	private InverterFilterAPI db;
	
	public InverterController() {
		db = new InverterContainer();
	}
	
	@GET
	@Produces("application/xml")
	@Path("/")
	public InverterFilterAPI getInvertersByPriceCapacityLocation(
			@QueryParam("priceMin") @DefaultValue("0") double minPrice,
			@QueryParam("priceMax") @DefaultValue("0") double maxPrice,
			@QueryParam("capacityMin") @DefaultValue("0") double minCapacity,
			@QueryParam("capacityMax") @DefaultValue("0") double maxCapacity,
			@QueryParam("postcode") @DefaultValue("") String postcode)
					throws InvalidArgumentException{
		try {
			db.setMaxCapacity(maxCapacity);
			db.setMaxPrice(maxPrice);
			db.setMinPrice(minPrice);
			db.setMinCapacity(minCapacity);
			db.setPostcode(postcode);
			db.search();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		InverterContainer container=new InverterContainer();
		Inverter inverter=new Inverter();
		inverter.setBatteryVoltageRange("aa");
		inverter.setCapacity(500);
		inverter.setDimensions("asdjiadsj");
		inverter.setEfficiencyDecrease(10);
		inverter.setManufacturer("a");
		inverter.setModel("AA");
		inverter.setName("aa");
		inverter.setOutputFrequency("aa");
		inverter.setOutputVoltage("aaa");
		
		ArrayList<String> pc=new ArrayList<String>();
		pc.add("1111");
		pc.add("2222");
		
		inverter.setPostcode(pc);
		inverter.setPrice(4242);
		inverter.setVoltage(42);
		inverter.setWarranty(25);
		
		ArrayList<Inverter> ret=new ArrayList<Inverter>();
		ret.add(inverter);
		container.setList(ret);
		return container;
//		return db;
	}
}
