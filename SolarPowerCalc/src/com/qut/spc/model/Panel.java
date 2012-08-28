/*
 * Solar power calculator
 * 
 * Copyright (C) 2012, ORANGE group.
 * See LICENSE.txt for license details.
 */

package com.qut.spc.model;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;

@Entity
public class Panel extends SolarComponent {

	private double outputEnergy;
	
	public Panel() {
		outputEnergy = 0.0;
	}
	
	/**
	 * @return The output energy
	 */
	@XmlElement
	public double getOutputEnergy() {
		return outputEnergy;
	}

	/**
	 * @param outputEnergy The output energy to set
	 */
	public void setOutputEnergy(double outputEnergy) {
		this.outputEnergy = outputEnergy;
	}
	
	public static Panel load(long id) {
		return loadComponent(id, Panel.class);
	}
}
