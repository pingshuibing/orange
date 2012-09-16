package com.qut.spc.calculations;

import com.qut.spc.api.ROICalculationAPI;

public class ROICalculator implements ROICalculationAPI {
	
	private double systemSize, feedInTariff, costOfElectricity, dailyUsage, dailySunHours, systemCost;
	

	@Override
	public void setSystemSize(double size) throws IllegalArgumentException {
		this.systemSize = size;
	}

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

	@Override
	public void setDailySunHours(double hours) throws IllegalArgumentException {
		this.dailySunHours = hours;
	}

	@Override
	public void setSystemCost(double cost) throws IllegalArgumentException {
		this.systemCost = cost;
	}
	
	public void getROI () {
		
	}
	
	

}
