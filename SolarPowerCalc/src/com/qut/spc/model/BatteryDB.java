package com.qut.spc.model;

import java.util.List;

public interface BatteryDB {
	/**
	 * Get list of batteries by price range
	 * 
	 * @param min Minimum price (0 to have no lower limitation)
	 * @param max Maximum price (0 to have no upper limitation)
	 * @return List of batteries
	 * @throws Exception if min or max is negative or max is less than min
	 */
	public List<Battery> getBatteriesInPriceRange(double min, double max)
			throws Exception;
	
	/**
	 * Get list of batteries that are available in the provided location
	 * 
	 * @param location Postcode/address
	 * @return List of batteries
	 * @throws Exception if location is in invalid format 
	 */
	public List<Battery> getBatteriesInLocation(String location)
			throws Exception;
	
	/**
	 * Get list of panels by capacity range
	 * 
	 * @param min Minimum capacity (0 to have no lower limitation)
	 * @param max Maximum capacity (0 to have no upper limitation)
	 * @return List of batteries
	 * @throws Exception if min or max is negative or max is less than min
	 */
	public List<Battery> getBatteriesInCapacity(double min, double max)
			throws Exception;
}
