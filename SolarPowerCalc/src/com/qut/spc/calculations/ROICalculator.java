package com.qut.spc.calculations;

import com.qut.spc.api.ROICalculationAPI;

/**
 * @author Zuojun Chen
 *
 */
public class ROICalculator implements ROICalculationAPI {
	
	private double electricity, feedInTariff, costOfElectricity, dailyUsage, systemCost;

	public ROICalculator(){
		this.electricity = -1;
		this.feedInTariff = -1;
		this.costOfElectricity = -1;
		this.dailyUsage = -1;
		this.systemCost = -1;
	}
	
	public ROICalculator (
			double electricity, 
			double feedInTariff, 
			double costOfElectricity, 
			double dailyUsage,  
			double systemCost) throws IllegalArgumentException {
		
		restrictInput(electricity, feedInTariff, costOfElectricity, dailyUsage, systemCost);
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
	
	private void restrictInput(double electricity, double feedInTariff,
			double costOfElectricity, double dailyUsage, double systemCost) throws IllegalArgumentException{
		if (electricity < 0)
			throw new IllegalArgumentException("Invaild input, " +
					"the eletricity production parameter shoulbe be more than zero. ");
		else if (feedInTariff < 0)
			throw new IllegalArgumentException("Invaild input, " +
					"the feed-in tariff parameter shoulbe be more than zero. ");
		else if (costOfElectricity < 0)
			throw new IllegalArgumentException("Invaild input, " +
					"the parameter of cost of electricity per kW.  shoulbe be more than zero. ");
		else if (dailyUsage < 0)
			throw new IllegalArgumentException("Invaild input, " +
					"the parameter of average daily household energy usage shoulbe be more than zero. ");
		else if (systemCost < 0)
			throw new IllegalArgumentException("Invaildass input, " +
					"the parameter of cost of the system shoulbe be more than zero. ");
		
	}

	@Override
	public void setAnnualElectricityProduction(double electricity) throws IllegalArgumentException {
		if (electricity < 0)
			throw new IllegalArgumentException("Invaild input, " +
					"the eletricity production parameter shoulbe be more than zero. ");
		this.electricity = electricity;
	};

	@Override
	public void setFeedInTariff(double tariff) throws IllegalArgumentException {
		if (tariff < 0)
			throw new IllegalArgumentException("Invaild input, " +
					"the feed-in tariff parameter shoulbe be more than zero. ");
		this.feedInTariff = tariff;
	}

	@Override
	public void setCostOfElectricity(double cost) throws IllegalArgumentException {
		if (cost < 0)
			throw new IllegalArgumentException("Invaild input, " +
					"the parameter of cost of electricity per kW.  shoulbe be more than zero. ");
		this.costOfElectricity = cost;
	}

	@Override
	public void setDailyUsage(double usage) throws IllegalArgumentException {
		if (usage < 0)
			throw new IllegalArgumentException("Invaild input, " +
					"the parameter of average daily household energy usage shoulbe be more than zero. ");
		this.dailyUsage = usage;
	}

//	@Override
//	public void setDailySunHours(double hours) throws IllegalArgumentException {
//		this.dailySunHours = hours;
//	}

	@Override
	public void setSystemCost(double cost) throws IllegalArgumentException {
		if (cost < 0)
			throw new IllegalArgumentException("Invaildass input, " +
					"the parameter of cost of the system shoulbe be more than zero. ");
		this.systemCost = cost;
	}
	
	public double getAnnualROI () throws IllegalArgumentException{
		chekcParameter();
		double annualGainFromSystem, costOfSystem, annualROI;
		annualGainFromSystem = 365*((dailyUsage*costOfElectricity) + (electricity-dailyUsage)*feedInTariff);
		annualROI = annualGainFromSystem/systemCost;
		return annualROI;
		
	}

	private void chekcParameter() throws IllegalArgumentException{
		if (electricity == -1)
			throw new IllegalArgumentException("The electricity parameter haven't been set yet. ");
		else if (feedInTariff == -1)
			throw new IllegalArgumentException("The feedInTariff parameter haven't been set yet. ");
		else if (costOfElectricity == -1)
			throw new IllegalArgumentException("The costOfElectricity parameter haven't been set yet. ");
		else if (dailyUsage == -1)
			throw new IllegalArgumentException("The dailyUsage parameter haven't been set yet. ");
		else if (systemCost == -1)
			throw new IllegalArgumentException("The systemCost parameter haven't been set yet. ");
		
	}
	
	

}
