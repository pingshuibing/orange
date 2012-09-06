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

import com.qut.spc.api.PanelFilterAPI;


/**
 * Wrapper class for list of SolarPanel
 * @author QuocViet
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="panels")
public class PanelContainer extends ComponentContainer<Panel>
		implements PanelFilterAPI {
	
	@XmlElement(name="panel")
	private List<Panel> list=new ArrayList<Panel>();


	@Override
	public void setList(List<Panel> list) {
		this.list=list;
	}

	@Override
	public List<Panel> getList() {
		return list;
	}

	@Override
	public Class<? extends SolarComponent> getComponentClass() {
		return Panel.class;
	}
	
}
