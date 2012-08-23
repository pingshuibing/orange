/*
 * Solar power calculator
 * 
 * Copyright (C) 2012, ORANGE group.
 * See LICENSE.txt for license details.
 */

package com.qut.spc.model;

import javax.persistence.*; 

@Entity
public class SolarPanel extends SolarComponent {

	private float outputEnergy;
	
	/**
	 * @return The output energy
	 */
	public float getOutputEnergy() {
		return outputEnergy;
	}

	/**
	 * @param outputEnergy The output energy to set
	 */
	public void setOutputEnergy(float outputEnergy) {
		this.outputEnergy = outputEnergy;
	}
}
