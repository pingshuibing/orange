/*
 * Solar power calculator
 * 
 * Copyright (C) 2012, ORANGE group.
 * See LICENSE.txt for license details.
 */

package com.qut.spc.model;

/**
 * Common interface for each component in solar system.
 * 
 * @author QuocViet
 */
public interface SolarComponent {
	
	String getName();
	
	float getPrice();
}
