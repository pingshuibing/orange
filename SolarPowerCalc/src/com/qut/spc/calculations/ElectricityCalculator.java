 package com.qut.spc.calculations;

import com.qut.spc.api.ElectricityCalculationApi;

/**
 * @author Zuojun Chen
 *
 */

public class ElectricityCalculator implements ElectricityCalculationApi{
	
	//from google docs
	private static final double CORRECTION_FACTOR = 1.25;

	@Override
	public double getElectricityProduction(double dailySun,
			double inverterEfficiency, double solarPanelEfficiency,
			double solarPowerOutput,double dailyHours, double timespan) throws IllegalArgumentException {
		
		
		//formula: (  estimated watt need/timespan*1.25)*(invertefficiency) 
		//assume that capacity(solarPowerOutput) is peak electricity generation per hour
		
		restrictInput(dailySun,inverterEfficiency, solarPanelEfficiency,solarPowerOutput,dailyHours,timespan);
		
		double actualSunPower = (dailySun/CORRECTION_FACTOR)*solarPanelEfficiency; //a sunlight correction factor of southern hemisphere
		double electricity;
		if (solarPowerOutput >= actualSunPower){
			electricity = actualSunPower*dailyHours*timespan*inverterEfficiency;
			return electricity;
		}else {
			electricity = (solarPowerOutput*dailyHours*timespan)*inverterEfficiency;
			return electricity;
		}
		
	}

	private void restrictInput(double dailySun, double inverterEfficiency,
			double solarPanelEfficiency, double solarPowerOutput, double dailyHours,
			double timespan) throws IllegalArgumentException{
		
		if (dailySun < 0) {
			throw new IllegalArgumentException("Invaild dailySun input, " +
					"the daily sun parameter shoulbe be more than zero. ");
		}  else if (inverterEfficiency > 1 || inverterEfficiency < 0) {
			throw new IllegalArgumentException("Invaild inverterEfficiency input, " +
					"the inverter efficiency parameter shoulbe be between 0 and 1. ");
		} else if (solarPanelEfficiency > 1 || solarPanelEfficiency < 0) {
			throw new IllegalArgumentException("Invaild solarPanelEfficiency input," +
					"the solar panel efficiency parameter shoulbe be between 0 and 1. ");
		} else if (solarPowerOutput < 0) {
			throw new IllegalArgumentException("Invaild solarPowerOutPut input, " +
					"the solar power output parameter shoulbe be more than zero. ");
		} else if (dailyHours < 0) {
			throw new IllegalArgumentException("Invaild daily hours input, " +
					"the dailyHours parameter should be more than zero.");
		} else if (timespan < 0) {
			throw new IllegalArgumentException("Invaild time  span input, " +
					"the time span parameter should be more than zero.");
		}
		
	}
	
	

}
