package com.qut.spc.model;

public interface ComponentFilterAPI {
	void setMinPrice(double minPrice) throws Exception;
	
	void setMaxPrice(double maxPrice) throws Exception;
	
	void setMinCapacity(double minCapacity) throws Exception;
	
	void setMaxCapacity(double maxCapacity) throws Exception;
	
	void setPostcode(String postcode) throws Exception;
}
