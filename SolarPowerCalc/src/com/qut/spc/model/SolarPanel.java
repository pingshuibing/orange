/*
 * Solar power calculator
 * 
 * Copyright (C) 2012, ORANGE group.
 * See LICENSE.txt for license details.
 */

package com.qut.spc.model;

import javax.persistence.Entity;

@Entity
public class SolarPanel extends SolarComponent {

	private double outputEnergy;
	
	public SolarPanel() {
	}
	
	/**
	 * @return The output energy
	 */
	public double getOutputEnergy() {
		return outputEnergy;
	}

	/**
	 * @param outputEnergy The output energy to set
	 */
	public void setOutputEnergy(double outputEnergy) {
		this.outputEnergy = outputEnergy;
	}
	
	public static SolarPanel load(long id) {
		return loadComponent(id, SolarPanel.class);
	}
}
