package com.qut.spc.calculations;


import javax.persistence.EntityNotFoundException;

import com.qut.spc.api.ElectricityCalculationApi;
import com.qut.spc.api.SystemCalculationAPI;
import com.qut.spc.db.Database;
import com.qut.spc.model.Battery;
import com.qut.spc.model.Inverter;
import com.qut.spc.model.Panel;
import com.qut.spc.weather.DailySunProvider;

public class SystemCalculationContainer implements SystemCalculationAPI{

	private Panel panel;
	private String location;
	private int timespan;
	private Battery battery;
	private Inverter inverter;

	@Override
	public void setPanelId(long id) throws EntityNotFoundException{
		if(panel==null || panel.getId()!=id){
			this.panel=Database.loadComponent(id,Panel.class);
		}
	}

	@Override
	public void setPanelId(long[] ids) {
	}

	@Override
	public void setInverterId(long id) {
		if(inverter==null || inverter.getId()!=id){
			this.inverter=Database.loadComponent(id,Inverter.class);
		}
	}

	@Override
	public void setBatteryId(long id) {
		if(battery==null || battery.getId()!=id){
			this.battery=Database.loadComponent(id,Battery.class);
		}	
	}

	@Override
	public void setTimespan(int months) {
		this.timespan=months;
	}

	@Override
	public void setLocation(String postcode) {
		this.location=postcode;
	}

	@Override
	public double getSystemEfficiency() {
		return 0;
	}

	@Override
	public double getTotalCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getElectricityProduction() throws EntityNotFoundException{
		ElectricityCalculationApi calculator = new Calculator();
		
		double dailySun=DailySunProvider.getDailySunByPostcode(location);
		return calculator.getElectricityProduction(dailySun, inverter.getEfficiency(), 100, panel.getCapacity(), timespan);
	}

	@Override
	public double getROI() {
		// TODO Auto-generated method stub
		return 0;
	}

}
