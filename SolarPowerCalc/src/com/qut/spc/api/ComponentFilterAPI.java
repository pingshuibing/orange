package com.qut.spc.api;

/**
 * Common APIs for searching component in solar power system
 * @author QuocViet
 */
public interface ComponentFilterAPI {
	
	/**
	 * Set lower limitation for price
	 * 
	 * @param minPrice Minimum price (0 to have no lower limitation)
	 * @throws Exception if minPrice is negative
	 */
	void setMinPrice(double minPrice) throws Exception;
	
	/**
	 * Set the upper limitation for price
	 * 
	 * @param maxPrice Maximum price (0 to have no upper limitation)
	 * @throws Exception if maxPrice is negative
	 */
	void setMaxPrice(double maxPrice) throws Exception;
	
	/**
	 * Set lower limitation for capacity
	 * 
	 * @param minCapacity Minimum capacity (0 to have no lower limitation)
	 * @throws Exception if minCapacity is negative
	 */
	void setMinCapacity(double minCapacity) throws Exception;
	
	/**
	 * Set the upper limitation for capacity
	 * 
	 * @param maxCapacity Maximum price (0 to have no upper limitation)
	 * @throws Exception if maxPrice is negative
	 */
	void setMaxCapacity(double maxCapacity) throws Exception;
	
	void setPostcode(String postcode) throws Exception;
}
