package com.qut.spc.api;

import java.util.List;

import com.qut.spc.model.Inverter;

/**
 * APIs for searching inverters
 * @author QuocViet
 */
public interface InverterFilterAPI extends ComponentFilterAPI {
	/**
	 * Get current list of inverters
	 */
	List<Inverter> getList();
	
	/**
	 * Get list of inverters from current filter
	 * 
	 * @return List of inverters
	 * @throws IllegalArgumentException if a filter is invalid 
	 */
	List<Inverter> search() throws IllegalArgumentException;
}
