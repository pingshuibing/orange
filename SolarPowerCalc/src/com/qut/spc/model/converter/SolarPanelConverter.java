package com.qut.spc.model.converter;

import javax.xml.bind.annotation.XmlElement;

import com.qut.spc.model.Panel;
/**
 * Wrapper class for SolarPanels used for jaxb XML output
 * @author Simen
 *
 */
public class SolarPanelConverter extends SolarComponentConverter{
	
	public SolarPanelConverter(){
		super(new Panel());
	}
	
	public SolarPanelConverter(Panel panel) {
		super(panel);	
	}
	
	@XmlElement
	public double getOperatingCurrent(){
		return ((Panel)panel).getOperatingCurrent();
	}

}
