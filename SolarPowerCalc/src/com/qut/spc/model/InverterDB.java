package com.qut.spc.model;

import java.util.List;

public interface InverterDB {
	public List<Inverter> getInvertersInPriceRange(double min, double max);
	
	public List<Inverter> getInvertersInLocation(String location);
	
	public List<Inverter> getInvertersInCapacity(double min, double max);
}
