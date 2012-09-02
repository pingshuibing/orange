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
	 * @throws IllegalArgumentException if minPrice is negative
	 */
	void setMinPrice(double minPrice) throws IllegalArgumentException;
	
	/**
	 * Set the upper limitation for price
	 * 
	 * @param maxPrice Maximum price (0 to have no upper limitation)
	 * @throws IllegalArgumentException if maxPrice is negative
	 */
	void setMaxPrice(double maxPrice) throws IllegalArgumentException;
	
	/**
	 * Set lower limitation for capacity
	 * 
	 * @param minCapacity Minimum capacity (0 to have no lower limitation)
	 * @throws IllegalArgumentException if minCapacity is negative
	 */
	void setMinCapacity(double minCapacity) throws IllegalArgumentException;
	
	/**
	 * Set the upper limitation for capacity
	 * 
	 * @param maxCapacity Maximum price (0 to have no upper limitation)
	 * @throws IllegalArgumentException if maxPrice is negative
	 */
	void setMaxCapacity(double maxCapacity) throws IllegalArgumentException;
	
	void setPostcode(String postcode) throws IllegalArgumentException;
}
