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
		//assume that capacity(solarPowerOutput) is peak electricity generation per hour
		
		double actualSunPower = (dailySun/CORRECTION_FACTOR)*solarPanelEfficiency; //a sunlight correction factor of southern hemisphere
		double electricity;
		if (solarPowerOutput >= actualSunPower){
			electricity = actualSunPower*timespan*inverterEfficiency;
			return electricity;
		}else {
			electricity = (solarPowerOutput*timespan)*inverterEfficiency;
			return electricity;
		}
		
	}
	
	

}
