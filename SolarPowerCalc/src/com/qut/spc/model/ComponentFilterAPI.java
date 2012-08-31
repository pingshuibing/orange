package com.qut.spc.model;

public interface ComponentFilterAPI {
	void setMinPrice(double minPrice);
	
	void setMaxPrice(double maxPrice);
	
	void setMinCapacity(double minCapacity);
	
	void setMaxCapacity(double minCapacity);
	
	void setPostcode(String postcode);
	
	void search();
}
