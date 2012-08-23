/*
 * Solar power calculator
 * 
 * Copyright (C) 2012, ORANGE group.
 * See LICENSE.txt for license details.
 */

package com.qut.spc.model;

import java.util.List;

import com.google.appengine.api.datastore.Key;
import javax.persistence.*;

/**
 * Common interface for each component in solar system.
 * 
 * @author QuocViet
 */
@Entity
@MappedSuperclass
public abstract class SolarComponent {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;

	// TODO: Create class Manufacture
	private String manufacture;
	
	private Double price;
	
	private Double efficiencyDecrease;
	
	public SolarComponent() {
		manufacture = "";
		price = 0.0;
		efficiencyDecrease = 0.0;
	}
	
	/**
	 * @return The name of manufacture
	 */
	public String getManufacture() {
		return this.manufacture;
	}
	
	/**
	 * @param efficiencyDecrease The efficiency to set
	 */
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	
	/**
	 * @return The price of this component
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price The price to set
	 * @throws Exception If price is negative
	 */
	public void setPrice(Double price) throws Exception {
		if (price < 0.0) {
			throw new Exception("Price must not be negative");
		}
		this.price = price;
	}

	/**
	 * @return The efficiency decrease linearly by each year
	 */
	public Double getEfficiencyDecrease() throws Exception {
		return efficiencyDecrease;
	}

	/**
	 * @param efficiencyDecrease The efficiency to set
	 * @throws Exception If efficiency less than 0 or greater than 100
	 */
	public void setEfficiencyDecrease(Double efficiencyDecrease) throws Exception {
		if (efficiencyDecrease < 0.0 || efficiencyDecrease > 100.0) {
			throw new Exception("Efficiency must be from 0 to 100");
		}
		this.efficiencyDecrease = efficiencyDecrease;
	}
	
	/**
	 * Get the list of efficiency by years
	 * @param years Number of years to retrieve
	 * @return 
	 */
	public List<Double> getEfficiencyByYear(int years) throws Exception {
		return null;
	}
}
