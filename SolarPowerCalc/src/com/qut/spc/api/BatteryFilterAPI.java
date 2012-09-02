package com.qut.spc.api;

import java.util.List;

import com.qut.spc.model.Battery;

/**
 * APIs for searching batteries
 * @author QuocViet
 */
public interface BatteryFilterAPI extends ComponentFilterAPI {
	
	/**
	 * Get current list of batteries
	 */
	List<Battery> getList();
	
	/**
	 * Get list of batteries from current filter
	 * 
	 * @return List of batteries
	 * @throws Exception if a filter is invalid 
	 */
	List<Battery> search() throws IllegalArgumentException;
}
