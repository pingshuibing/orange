package com.qut.spc.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;



@Path("/panel/")
public class PanelController {

	/**
	 * Returns a XML representation of SolarPanels with a cost between min and max
	 * @param min Minimum cost of Solar Panel
	 * @param max Maximum cost of Solar Panel
	 * @return 
	 */
	@GET
	@Produces("application/xml")
	@Path("/price/{min}/{max}")
	public String getPanels(@PathParam("min") int min, @PathParam("max") int max){
		return "<range><a>"+min+"</a>"+"<b>"+max+"</b>"+"</range>";
	}
	
}
