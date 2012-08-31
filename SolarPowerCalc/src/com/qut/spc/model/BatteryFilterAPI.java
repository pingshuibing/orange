package com.qut.spc.model;

import java.util.List;

public interface BatteryFilterAPI extends ComponentFilterAPI {
	/**
	 * Get list of batteries from current filter
	 * 
	 * @return List of batteries
	 * @throws Exception if a filter is invalid 
	 */
	List<Battery> search() throws Exception;
}
