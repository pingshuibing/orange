package com.qut.spc.calculations;

import com.qut.spc.api.ElectricityCalculationApi;

public class Calculator implements ElectricityCalculationApi{
	
	//from google docs
	private static final double CORRECTION_FACTOR = 1.25;

	@Override
	public double getElectricityProduction(double dailySun,
			double inverterEfficiency, double solarPanelEfficiency,
			double solarPowerOutput, double timespan) {
		
		//formula: (  estimated watt need/timespan*1.25)*(invertefficiency) 
		
		double electricity;
		electricity = (dailySun*timespan*solarPowerOutput)
				*inverterEfficiency*solarPanelEfficiency;
		
		return electricity;
	}
	
	

}
