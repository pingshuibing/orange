package com.qut.spc.model;

import java.util.List;

public interface InverterDB {
	
	/**
	 * Get list of inverter by price range
	 * @param min Minimum price (0 to have no under limitation)
	 * @param max Maximum price (0 to have no upper limitation)
	 * @return List of panel
	 */
	public List<Inverter> getInvertersInPriceRange(double inverterMinPrice, double inverterMaxPrice);
	
	public List<Inverter> getInvertersInLocation(String userPostcode);
	
	public List<Inverter> getInvertersInCapacity(double inverterMinCapacity, double inverterMaxCapacity);
}
