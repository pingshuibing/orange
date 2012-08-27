package com.qut.spc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import com.qut.spc.model.SolarPanel;
import com.qut.spc.model.db.PanelDatabase;


@Path("/panel/")
public class PanelController {

	private PanelDatabase db;
	
	public PanelController(@Context PanelDatabase db) {
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
	public List<SolarPanel> getPanelsByPrice(@PathParam("min") int min, @PathParam("max") int max){
		return new ArrayList<SolarPanel>();
	}

}
