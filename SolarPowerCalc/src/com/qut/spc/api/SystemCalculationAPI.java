package com.qut.spc.api;

import javax.persistence.EntityNotFoundException;


public interface SystemCalculationAPI {
	
	/**
	 * Set panel for the system using id
	 */
	void setPanelId(long id) throws EntityNotFoundException;

	/**
	 * Set array of panels for the system
	 */
	void setPanelId(long[] ids);
	
	/**
	 * Set inverter for the system using id
	 */
	void setInverterId(long id)throws EntityNotFoundException;
	
	/**
	 * Set battery for the system using id
	 */
	void setBatteryId(long id)throws EntityNotFoundException;
	
	/**
	 * Set time span in months
	 */
	void setTimespan(int months);
	
	/**
	 * Set user location
	 */
	void setLocation(String postcode) throws IllegalArgumentException;
	
	/**
	 * Get efficiency factor
	 */
	double getSystemEfficiency();
	
	/**
	 * Get cost of the whole system
	 */
	double getTotalCost();
	
	/**
	 * Get total output energy
	 */
	double getElectricityProduction();
	
	/**
	 * Get return of investment
	 */
	double getROI();
}
