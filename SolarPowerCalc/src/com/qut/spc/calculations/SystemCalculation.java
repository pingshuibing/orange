package com.qut.spc.calculations;

//import java.util.ArrayList;

import com.qut.spc.api.SystemCalculationAPI;
import com.qut.spc.model.*;

public class SystemCalculation implements SystemCalculationAPI {
	
	long panelId, inverterId, batteryId, timeSpane;
	String location;
	int panelQuantity;
	
	double dailySun,
		timespan;
	
	Panel objPanel;
	Inverter objInverter;
	Battery objBattery;
	
	
	//ArrayList<Long> arrPanelId = new ArrayList<Long>();
	
	SystemCalculation (long panelId, long inverterId, long batteryId, 
			int timeSpan, String location ) {
		this.panelId = panelId;
		this.inverterId = inverterId;
		this.batteryId = batteryId;
		this.timeSpane  = timeSpan;
		this.location = location;
		
		objPanel = Panel.load(panelId);
		objInverter  = Inverter.load(inverterId);
		objBattery = Battery.load(batteryId);
	}
	

	@Override
	public void setPanelId(long id) {
		this.panelId = id;

	}

	@Override
	public void setPanelId(long[] ids) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setInverterId(long id) {
		this.inverterId = id;

	}

	@Override
	public void setBatteryId(long id) {
		this.batteryId = batteryId;

	}

	@Override
	public void setTimespan(int months) {
		//timeSpan in month or day?

	}

	@Override
	public void setLocation(String postcode) {
		this.location = location;

	}

	@Override
	public double getSystemEfficiency() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTotalCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getElectricityProduction() {
		ElectricityCalculator eleCal = new ElectricityCalculator();
		return eleCal.getElectricityProduction(dailySun, objInverter.getEfficiency(), objPanel.getEfficiencyByYear(0)[0], objPanel.getCapacity()*panelQuantity, timespan);
	}

	@Override
	public double getROI() {
		// TODO Auto-generated method stub
		return 0;
	}

}
