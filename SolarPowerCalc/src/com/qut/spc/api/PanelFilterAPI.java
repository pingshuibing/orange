package com.qut.spc.api;

import java.util.List;

import com.qut.spc.model.Panel;

/**
 * APIs for searching panels
 * @author QuocViet
 */
public interface PanelFilterAPI extends ComponentFilterAPI {
	
	/**
	 * Get current list of panels
	 */
	List<Panel> getList();
	
	/**
	 * Get list of panels from current filter
	 * 
	 * @return List of panels
	 * @throws IllegalArgumentException if a filter is invalid 
	 */
	List<Panel> search() throws IllegalArgumentException;

}
