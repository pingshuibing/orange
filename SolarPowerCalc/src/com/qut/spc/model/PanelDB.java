package com.qut.spc.model;

import java.util.List;

public interface PanelDB {
	/**
	 * Get list of panel by price range
	 * @param min Minimum price (0 to have no under limitation)
	 * @param max Maximum price (0 to have no upper limitation)
	 * @return List of panel
	 */
	public List<Panel> getPanelsInPriceRange(double min, double max);
	
	public List<Panel> getPanelsInLocation(String location);
	
	public List<Panel> getPanelsInCapacity(double min, double max);
}
