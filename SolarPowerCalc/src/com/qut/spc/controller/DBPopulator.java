package com.qut.spc.controller;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qut.spc.model.Panel;

@Path("/populate/")
public class DBPopulator {

	@GET
	@Produces("text/html")
	@Path("/{num}")
	public String populateDB(@PathParam("num") int num){
		try {
			generatePanels(num);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Database Populated with "+num+ " panels";
	}
	
	@GET
	@Produces("text/html")
	@Path("/")
	public String populateDB(){
		try {
			generatePanels(100);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Database Populated with 100 panels";
		
	}
	
	
	private void generatePanels(int numPanels) throws Exception{
		for(int i=0;i<numPanels;i++){
				Panel p=new Panel();
				p.setManufacturer("M "+((int)Math.random()*10));
				p.setPrice(Math.random()*1337);
				p.setCapacity(Math.random()*1800);
				p.setDescription("Panel number: "+i);
				p.setModel("ABCD"+i%6);
				p.setName("PANEL "+i);
				p.setWarranty(24);
				
				ArrayList<String> pc=new ArrayList<String>();
				double d=Math.random();
				
				if(d<0.33)
					pc.add("1111");
				else if(d>0.66)
					pc.add("2222");
				else
					pc.add("3333");

				
				p.setPostcode(pc);
				
				p.save();
		}
	}
	
}
