/*
 * Solar power calculator
 * 
 * Copyright (C) 2012, ORANGE group.
 * See LICENSE.txt for license details.
 */

package com.qut.spc.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Wrapper class for list of Inverters
 * @author QuocViet
 */
@XmlRootElement(name="inverters")
public class InverterContainer extends ComponentContainer
		implements InverterFilterAPI, InverterDB {
	
	@XmlElement(name="inverter")
	private List<Inverter> list;
	
	public InverterContainer() {
		list = new ArrayList<Inverter>();
		setDBTable(Inverter.class.getName());
	}
	
	public InverterContainer(List<Inverter> list) {
		this.list = list;
	}
	
	public List<Inverter> getList() {
		return list;
	}
	
	public void setList(List<Inverter> list) {
		this.list = list;
	}
	
	@Override
	public List<Inverter> getInvertersInPriceRange(double min, double max)
			throws Exception {
		setMinPrice(min);
		setMaxPrice(max);
		return search();
	}
	
	@Override
	public List<Inverter> getInvertersInLocation(String location)
			throws Exception {
		// TODO
		list = new ArrayList<Inverter>();
		return list;
	}

	@Override
	public List<Inverter> getInvertersInCapacity(double min, double max)
			throws Exception {
		setMinCapacity(min);
		setMaxCapacity(max);
		return search();
	}

	@Override
	public List<Inverter> search() throws Exception {
		return fetchComponents();
	}
}
