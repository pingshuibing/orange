/*
 * Solar power calculator
 * 
 * Copyright (C) 2012, ORANGE group.
 * See LICENSE.txt for license details.
 */

package com.qut.spc.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Wrapper class for list of SolarPanel
 * @author QuocViet
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="panels")
public class PanelContainer extends ComponentContainer
		implements PanelFilterAPI {

	@XmlElement(name="panel")
	private List<Panel> list;
	
	public PanelContainer() {
		list = new ArrayList<Panel>();
	}
	
	public List<Panel> getList() {
		return list;
	}

	@Override
	public List<Panel> search() throws Exception {
		list = fetchComponents(Panel.class.getName());
		return list;
	}
	
}
