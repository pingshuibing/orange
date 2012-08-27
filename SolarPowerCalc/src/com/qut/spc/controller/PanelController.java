package com.qut.spc.controller;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qut.spc.model.SolarPanel;
import com.qut.spc.model.converter.PanelConverterWrapper;
import com.qut.spc.model.converter.SolarPanelConverter;
import com.qut.spc.model.db.MockDB;
import com.qut.spc.model.db.PanelDatabase;


@Path("/panel/")
public class PanelController {
	
	private PanelDatabase db;
	
	public PanelController(){
		this.db=new MockDB();
	}
	
	public PanelController(PanelDatabase db){
		this.db=db;
	}

	/**
	 * Returns a XML representation of SolarPanels with a cost between min and max
	 * @param min Minimum cost of Solar Panel
	 * @param max Maximum cost of Solar Panel
	 * @return 
	 */
	@GET
	@Produces("application/xml")
	@Path("/price/{min}/{max}")
	public PanelConverterWrapper getPanelsByPrice(@PathParam("min") double min, @PathParam("max") double max){
		ArrayList<SolarPanelConverter> converters=new ArrayList<SolarPanelConverter>();
		
		for(SolarPanel p: db.getSolarPanelsInPriceRange(min, max)){
			converters.add(new SolarPanelConverter(p));
		}
		
		return new PanelConverterWrapper(converters);
	}
	


}
