package com.qut.spc.model.converter;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name="panels")
public class PanelConverterWrapper {
	@XmlElement (name="panel")
	private List<SolarPanelConverter> converters;
	
	public PanelConverterWrapper(){
		converters=new ArrayList<SolarPanelConverter>();
	}
	
	public PanelConverterWrapper(List<SolarPanelConverter> convs) {
		this.converters=convs;
	}
	
	public List<SolarPanelConverter> getConverters(){
		return converters;
		
	}
}
