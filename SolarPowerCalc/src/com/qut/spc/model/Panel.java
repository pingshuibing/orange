/*
 * Solar power calculator
 * 
 * Copyright (C) 2012, ORANGE group.
 * See LICENSE.txt for license details.
 */

package com.qut.spc.model;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;

import com.qut.spc.db.Database;

/**
 * Panel entity
 * @author QuocViet
 */
@Entity
public class Panel extends SolarComponent {

	@XmlElement
	private double operatingCurrent = 0.0;
	
	@XmlElement
	private double efficiency=100;
	
	/**
	 * Get max power (Operating current) in A
	 */
	public double getOperatingCurrent() {
		return operatingCurrent;
	}

	/**
	 * @param outputEnergy The output energy to set
	 */
	public void setOperatingCurrent(double operatingCurrent)
			throws IllegalArgumentException {
		if (operatingCurrent < 0.0) {
			throw new IllegalArgumentException(
					"Operating current must not be negative");
		}
		this.operatingCurrent = operatingCurrent;
	}
	
	public static Panel load(long id) {
		return Database.loadComponent(id, Panel.class);
	}
	
	@Override
	public void save() {
		Database.saveComponent(this);
	}

	@Override
	public String toString() {
		return "Panel [operatingCurrent=" + operatingCurrent + ", getId()="
				+ getId() + ", getName()=" + getName() + ", getModel()="
				+ getModel() + ", getManufacturer()=" + getManufacturer()
				+ ", getPrice()=" + getPrice() + ", getCapacity()="
				+ getCapacity() + ", getVoltage()=" + getVoltage()
				+ ", getEfficiencyDecrease()=" + getEfficiencyDecrease()
				+ ", getWarranty()=" + getWarranty() + ", getDimensions()="
				+ getDimensions() + ", getDescription()=" + getDescription()
				+ ", getPostcode()=" + getPostcode() + "]";
	}

	public void setEfficiency(double panelEfficiency) {
		this.efficiency=panelEfficiency;
	}

	public double getEfficiency() {
		return efficiency;
	}
	
	
}
