package com.qut.spc.controller;


import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.qut.spc.exceptions.InvalidArgumentException;
import com.qut.spc.marshalling.SolarComponentUnmarshaller;
import com.qut.spc.model.BatteryContainer;
import com.qut.spc.model.ComponentContainer;
import com.qut.spc.model.InverterContainer;
import com.qut.spc.model.PanelContainer;

@Path("/populate/")
public class DBPopulator {

	private SolarComponentUnmarshaller um;
	
	private final String panelXmlPath = "panels.xml";
	private final String inverterXmlPath = "inverters.xml";
	private final String batteryXmlPath = "batteries.xml";

	public DBPopulator() {
		um = new SolarComponentUnmarshaller();
	}

	public DBPopulator(SolarComponentUnmarshaller um) {
		this.um = um;
	}

	@GET
	@Produces("text/html")
	@Path("/")
	public String populateDB(
			@QueryParam("panel") @DefaultValue("") String panels,
			@QueryParam("inverter") @DefaultValue("") String inverters,
			@QueryParam("battery") @DefaultValue("") String batteries)
			throws InvalidArgumentException {
		String ret = "";
		try {
			if (!panels.isEmpty()) {
				ret += "Panels: \n";
				ret += unmarshal(PanelContainer.class, panelXmlPath) + "\n";
			}

			if (!inverters.isEmpty()) {
				ret += "Inverters: \n";
				ret += unmarshal(InverterContainer.class, inverterXmlPath) + "\n";
			}

			if (!batteries.isEmpty()) {
				ret += "Batteries:\n";
				ret += unmarshal(BatteryContainer.class, batteryXmlPath) + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidArgumentException(e.getMessage());
		}
		if (!ret.isEmpty())
			return "Database populated with: " + ret;
		return "Database populated with nothing";
	}

	private String unmarshal(Class<? extends ComponentContainer> className,
			String xml) {
		try {
			ComponentContainer p = um.unmarshall(className, xml);
			p.save();

			return p.getList().toString();
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

}
