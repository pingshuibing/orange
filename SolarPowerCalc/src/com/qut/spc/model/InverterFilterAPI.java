package com.qut.spc.model;

import java.util.List;

public interface InverterFilterAPI extends ComponentFilterAPI {
	/**
	 * Get current list of inverters
	 */
	List<Inverter> getList();
	
	/**
	 * Get list of inverters from current filter
	 * 
	 * @return List of inverters
	 * @throws Exception if a filter is invalid 
	 */
	List<Inverter> search() throws Exception;
}
