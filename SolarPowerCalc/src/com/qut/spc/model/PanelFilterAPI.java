package com.qut.spc.model;

import java.util.List;

public interface PanelFilterAPI extends ComponentFilterAPI {
	
	/**
	 * Get current list of panels
	 */
	List<Panel> getList();
	
	/**
	 * Get list of panels from current filter
	 * 
	 * @return List of panels
	 * @throws Exception if a filter is invalid 
	 */
	List<Panel> search() throws Exception;
}
