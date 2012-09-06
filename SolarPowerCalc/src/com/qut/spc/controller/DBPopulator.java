package com.qut.spc.controller;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.qut.spc.exceptions.InvalidArgumentException;
import com.qut.spc.marshalling.SolarComponentUnmarshaller;
import com.qut.spc.model.ComponentContainer;
import com.qut.spc.model.InverterContainer;
import com.qut.spc.model.Panel;
import com.qut.spc.model.PanelContainer;
import com.qut.spc.model.SolarComponent;

@Path("/populate/")
public class DBPopulator {

	
	private SolarComponentUnmarshaller um;



	public DBPopulator(){
		um = new SolarComponentUnmarshaller();		
	}
	public DBPopulator(SolarComponentUnmarshaller um){
		this.um=um;
	}
	
	
	@GET
	@Produces("text/html")
	@Path("/")
	public String populateDB(@QueryParam("panel") @DefaultValue("") String panels, @QueryParam("inverter") @DefaultValue("") String inverters, @QueryParam("battery") @DefaultValue("") String batteries){
		String ret="DB populated with:\n ";
		
		if(!panels.equals("")){
			ret+= unmarshal(PanelContainer.class,panels)+"\n";
		}
		
		if(!inverters.equals("")){
			ret+= unmarshal(InverterContainer.class,panels)+"\n";
		}
		
		if(!batteries.equals("")){
			ret+= unmarshal(InverterContainer.class,panels)+"\n";
		}
		return ret;
		

	}
	private String unmarshal(Class<? extends ComponentContainer> class1, String xml) {
		try{
			ComponentContainer p=um.unmarshall(class1, xml);
			p.save();

			return p.getList().toString();
		}catch(Exception e){
			throw new InvalidArgumentException(e.getMessage());
		}
	}
	
}
