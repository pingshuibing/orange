package com.qut.spc.calculations;


import javax.persistence.EntityNotFoundException;

import com.qut.spc.api.ElectricityCalculationApi;
import com.qut.spc.api.SystemCalculationAPI;
import com.qut.spc.api.TotalCostCalculationAPI;
import com.qut.spc.db.Database;
import com.qut.spc.model.Battery;
import com.qut.spc.model.Inverter;
import com.qut.spc.model.Panel;
import com.qut.spc.postcode.PostcodeUtil;
import com.qut.spc.weather.DailySunProvider;

public class SystemCalculationContainer implements SystemCalculationAPI{

	private Panel panel;
	private String location="";
	private int timespan=12;
	private Battery battery;
	private Inverter inverter;
	
	private ElectricityCalculationApi electricityCalculator;
	private TotalCostCalculationAPI costCalculator;


	public SystemCalculationContainer(ElectricityCalculationApi electricityCalculator,TotalCostCalculationAPI costCalculator){
		this.electricityCalculator=electricityCalculator;
		this.costCalculator=costCalculator;
	}

	@Override
	public void setPanelId(long id) throws EntityNotFoundException{
		if(panel==null || panel.getId()!=id){
			this.panel=Database.loadComponent((int)id,Panel.class);
		}
	}

	@Override
	public void setPanelId(long[] ids) {
	}

	@Override
	public void setInverterId(long id) throws EntityNotFoundException {
		if(inverter==null || inverter.getId()!=id){
			this.inverter=Database.loadComponent((int)id,Inverter.class);
		}
	}

	@Override
	public void setBatteryId(long id) throws EntityNotFoundException{
		if(battery==null || battery.getId()!=id){
			this.battery=Database.loadComponent((int)id,Battery.class);
		}	
	}

	@Override
	public void setTimespan(int months) {
		this.timespan=months;
	}

	@Override
	public void setLocation(String postcode) throws IllegalArgumentException{
		if(PostcodeUtil.validatePostcode(postcode)){
			this.location=PostcodeUtil.transformPostcode(postcode);
		}
	}

	@Override
	public double getSystemEfficiency() {
		return 0;
	}

	@Override
	public double getTotalCost() {		
		return costCalculator.getSystemTotalCost(panel.getPrice(), 1, battery.getPrice(), 1, inverter.getPrice());
	}

	@Override
	public double getElectricityProduction() throws EntityNotFoundException{

		double dailySun=DailySunProvider.getDailySunByPostcode(location);
		double sunIntensity=DailySunProvider.getDailySunLight(location);
		
		double elProd=electricityCalculator.getElectricityProduction(sunIntensity, (double)inverter.getEfficiency()/100, 1, panel.getCapacity(),dailySun, timespan);
		return elProd;
	}

	@Override
	public double getROI() {
		return 0;
	}

	public Panel getPanel() {
		return panel;
	}

	public Inverter getInverter() {
		return inverter;
	}

	public Battery getBattery() {
		return battery;
	}

	public String getLocation() {
		return location;
	}

}
