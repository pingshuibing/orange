package com.qut.spc.api;

public interface ROICalculationAPI {
	
//	/**
//	 * Set system output energy. E.g. 5kW
//	 * @param size Output energy in kW
//	 * @throws IllegalArgumentException if size is less than zero
//	 */
//	void setSystemSize(double size) throws IllegalArgumentException;
	/**
	 * Set annual electricity generation from system. E.g. 5kW
	 * @param annual electricity production in kW
	 * @throws IllegalArgumentException if size is less than zero
	 */
	void setAnnualElectricityProduction(double electricity) throws IllegalArgumentException;
	
	/**
	 * Set feed-in tariff. E.g. $0.44
	 * @param tariff Tariff in dollars
	 * @throws IllegalArgumentException if size is less than zero
	 */
	void setFeedInTariff(double tariff) throws IllegalArgumentException;
	
	/**
	 * Set cost of electricity per kW. E.g. $0.23/kW
	 * @param cost Cost in dollars
	 * @throws IllegalArgumentException if cost is less than zero
	 */
	void setCostOfElectricity(double cost) throws IllegalArgumentException;
	
	/**
	 * Set average daily household energy usage. E.g. 1750kW 
	 * @param usage Average usage in kW
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
	 * Set cost of the system
	 * @param cost System cost
	 * @throws IllegalArgumentException if cost is less than zero
	 */
	void setSystemCost(double cost) ;
	
	/**
	 * get the annual ROI
	 * 
	 */
	double getAnnualROI();
}
