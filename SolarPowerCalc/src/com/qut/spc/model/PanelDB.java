package com.qut.spc.model;

import java.util.List;

public interface PanelDB {
	/**
	 * Get list of panels by price range
	 * 
	 * @param min Minimum price (0 to have no lower limitation)
	 * @param max Maximum price (0 to have no upper limitation)
	 * @return List of panel
	 * @throws Exception if min or max is negative or max is less than min
	 */
	public List<Panel> getPanelsInPriceRange(double min, double max)
			throws Exception;
	
	/**
	 * Get list of panels that are available in the provided location
	 * 
	 * @param location Postcode/address
	 * @return List of panel
	 * @throws Exception if location is in invalid format 
	 */
	public List<Panel> getPanelsInLocation(String location)
			throws Exception;
	
	/**
	 * Get list of panels by capacity range
	 * 
	 * @param min Minimum capacity (0 to have no lower limitation)
	 * @param max Maximum capacity (0 to have no upper limitation)
	 * @return List of panel
	 * @throws Exception if min or max is negative or max is less than min
	 */
	public List<Panel> getPanelsInCapacity(double min, double max)
			throws Exception;
}
