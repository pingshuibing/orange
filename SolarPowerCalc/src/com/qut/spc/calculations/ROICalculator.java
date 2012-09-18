package com.qut.spc.calculations;

import com.qut.spc.api.ROICalculationAPI;

/**
 * @author Zuojun Chen
 *
 */
public class ROICalculator implements ROICalculationAPI {
	
	private double electricity, feedInTariff, costOfElectricity, dailyUsage, systemCost;
	

	public ROICalculator(){
		
	}
	
	public ROICalculator (
			double electricity, 
			double feedInTariff, 
			double costOfElectricity, 
			double dailyUsage, 
			double dailySunHours, 
			double systemCost) {
		
		this.electricity = electricity;
		this.feedInTariff = feedInTariff;
		this.costOfElectricity = costOfElectricity;
		this.dailyUsage = dailyUsage;
		this.systemCost = systemCost;
	} 
//	@Override
//	public void setSystemSize(double size) throws IllegalArgumentException {
//		this.systemSize = size;
//	}
	
	@Override
	public void setAnnualElectricityProduction(double electricity) throws IllegalArgumentException {
		
	};

	@Override
	public void setFeedInTariff(double tariff) throws IllegalArgumentException {
		this.feedInTariff = tariff;
	}

	@Override
	public void setCostOfElectricity(double cost)
			throws IllegalArgumentException {
		this.costOfElectricity = cost;
	}

	@Override
	public void setDailyUsage(double usage) throws IllegalArgumentException {
		this.dailyUsage = usage;
	}

//	@Override
//	public void setDailySunHours(double hours) throws IllegalArgumentException {
//		this.dailySunHours = hours;
//	}

	@Override
	public void setSystemCost(double cost) throws IllegalArgumentException {
		this.systemCost = cost;
	}
	
	public double getAnnualROI () {
		double annualGainFromSystem, costOfSystem, annualROI;
		annualGainFromSystem = (dailyUsage*costOfElectricity) + (electricity-dailyUsage)*feedInTariff;
		annualROI = annualGainFromSystem/systemCost;
		return annualROI;
		
	}
	
	

}
