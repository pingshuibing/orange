package com.qut.spc.model;

import java.util.List;

public interface InverterDB {
	/**
	 * Get list of inverters by price range
	 * 
	 * @param min Minimum price (0 to have no lower limitation)
	 * @param max Maximum price (0 to have no upper limitation)
	 * @return List of inverters
	 * @throws Exception if min or max is negative or max is less than min
	 */
	public List<Inverter> getInvertersInPriceRange(double min, double max)
			throws Exception;
	
	/**
	 * Get list of inverters that are available in the provided location
	 * 
	 * @param location Postcode/address
	 * @return List of inverters
	 * @throws Exception if location is in invalid format 
	 */
	public List<Inverter> getInvertersInLocation(String location)
			throws Exception;
	
	/**
	 * Get list of inverters by capacity range
	 * 
	 * @param min Minimum capacity (0 to have no lower limitation)
	 * @param max Maximum capacity (0 to have no upper limitation)
	 * @return List of inverters
	 * @throws Exception if min or max is negative or max is less than min
	 */
	public List<Inverter> getInvertersInCapacity(double min, double max)
			throws Exception;
}
