package com.qut.spc.model;

import java.util.List;

public interface BatteryDB {
	/**
	 * Get list of battery by price range
	 * @param min Minimum price (0 to have no under limitation)
	 * @param max Maximum price (0 to have no upper limitation)
	 * @return List of panel
	 */
	public List<Battery> getBatteriesInPriceRange(double batteryMinPrice, double batteryMaxPrice);
	
	public List<Battery> getBatteriesInLocation(String userPostcode);
	
	public List<Battery> getBatteriesInCapacity(double batteryMinCapacity, double batteryMaxCapacity);
}
