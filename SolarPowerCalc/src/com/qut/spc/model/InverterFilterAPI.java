package com.qut.spc.model;

import java.util.List;

public interface InverterFilterAPI extends ComponentFilterAPI {
	
	/**
	 * Get list of inverters from current filter
	 * 
	 * @return List of inverters
	 * @throws Exception if a filter is invalid 
	 */
	List<Inverter> search() throws Exception;
}
