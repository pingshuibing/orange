package com.qut.spc.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


@Path("/panel/")
public class PanelController {

	@GET
	@Produces("text/plain")
	@Path("/")
	public String getPanel(){
		return "WHAZZAP WORLD?";
	}
	
}
