package com.qut.spc.model.converter;


import javax.xml.bind.annotation.XmlElement;

import com.qut.spc.model.SolarComponent;
public class SolarComponentConverter {

	protected SolarComponent panel;

	public SolarComponentConverter(){}
	public SolarComponentConverter(SolarComponent panel) {
		this.panel=panel;
	}
	
	@XmlElement
	public String getManufacturer(){
		return panel.getManufacture();
	}
	@XmlElement
	public double getPrice(){
		return panel.getPrice();
	}
	@XmlElement
	public double getEfficiencyDecrease(){
		return panel.getEfficiencyDecrease();
	}
	



}
