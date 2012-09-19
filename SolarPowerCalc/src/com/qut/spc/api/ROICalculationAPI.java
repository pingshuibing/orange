package com.qut.spc.api;

public interface ROICalculationAPI {
	
//	/**
//	 * Set system output energy. E.g. 5kWh
//	 * @param size Output energy in kWh
//	 * @throws IllegalArgumentException if size is less than zero
//	 */
//	void setSystemSize(double size) throws IllegalArgumentException;
	/**
	 * Set Daily electricity generation from system. E.g. 5kWh
	 * @param electricity  daily electricity production in kWh
	 * @throws IllegalArgumentException if size is less than zero
	 */
	void setDailyElectricityProduction(double electricity) throws IllegalArgumentException;
	
	/**
	 * Set feed-in tariff. E.g. $0.23/kwh
	 * @param tariff Tariff in dollars(the price that government buy from individual)
	 * @throws IllegalArgumentException if size is less than zero
	 */
	void setFeedInTariff(double tariff) throws IllegalArgumentException;
	
	/**
	 * Set cost of electricity per kW. E.g. $0.44/kWh
	 * @param cost Cost per kwh (the price that individual buy from government)
	 * @throws IllegalArgumentException if cost is less than zero
	 */
	void setCostOfElectricity(double cost) throws IllegalArgumentException;
	
	/**
	 * Set average daily household energy usage(kwh). E.g. 17kWh
	 * @param usage Average daily usage in kWh
	 * @throws IllegalArgumentException if usage is less than zero
	 */
	void setDailyUsage(double usage) throws IllegalArgumentException;
	
//	/**
//	 * Set daily sun hours
//	 * @param hours Average sun hours per day
//	 * @throws IllegalArgumentException if hours is less than zero
//	 */
//	void setDailySunHours(double hours) throws IllegalArgumentException;
	
	/**
	 * Set total cost(AUD) of the system e.g.$10000  
	 * @param cost System total cost(AUD)
	 * @throws IllegalArgumentException if cost is less than zero
	 */
	void setSystemCost(double cost) ;
	
	/**
	 * Set time span (day) of ROI e.g. 7days, 30 days, 365 days
	 * @param timeSpan The time span of ROI (day)
	 * @throws IllegalArgumentException if cost is less than zero
	 */
	void setTimeSpan(double timeSpan) ;
	
	/**
	 * get the time span ROI - e.g. annual ROI: time span is 365.
	 * @throws IllegalArgumentException if any of parameter haven't been set
	 */
	double getROI() throws IllegalArgumentException;
}
