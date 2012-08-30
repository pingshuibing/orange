/*
 * Solar power calculator
 * 
 * Copyright (C) 2012, ORANGE group.
 * See LICENSE.txt for license details.
 */

package com.qut.spc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Query;
import javax.xml.bind.annotation.XmlElement;

import com.google.appengine.api.datastore.Key;
import com.qut.spc.EMF;

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

	@XmlElement
	private String name = "";
	
	@XmlElement
	private String model = "";
	
	// TODO: Create class Manufacture
	@XmlElement
	private String manufacture = "";
	
	@XmlElement
	private double price = 0.0;
	
	@XmlElement
	private double capacity = 0.0;
	
	@XmlElement
	private double voltage = 0.0;
	
	@XmlElement
	private String dimensions = "";
	
	@XmlElement
	private String description = "";
	
	@XmlElement
	private int warranty = 0;
	
	@XmlElement
	private double efficiencyDecrease = 0.0;
	
	
	public SolarComponent() {
	}

	/**
	 * @return Id of this component in database
	 */
	public long getId() {
		if (key != null) {
			return key.getId();
		}
		return -1;
	}
	
	/**
	 * @return The name of this component
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return The model of this component
	 */
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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
	public double getPrice() {
		return price;
	}

	/**
	 * @param price The price to set
	 * @throws Exception If price is negative
	 */
	public void setPrice(double price) throws Exception {
		if (price < 0.0) {
			throw new Exception("Price must not be negative");
		}
		this.price = price;
	}
	
	/**
	 * Get capacity (W or Ah)
	 * @return
	 */
	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) throws Exception {
		if (capacity < 0.0) {
			throw new Exception("Capacity must not be negative");
		}
		this.capacity = capacity;
	}

	/**
	 * Get voltage (V)
	 * @return
	 */
	public double getVoltage() {
		return voltage;
	}

	public void setVoltage(double voltage) throws Exception {
		if (voltage < 0.0) {
			throw new Exception("Voltage must not be negative");
		}
		this.voltage = voltage;
	}
	
	/**
	 * @return The efficiency decrease linearly by each year
	 */
	public double getEfficiencyDecrease() {
		return efficiencyDecrease;
	}

	/**
	 * @param efficiencyDecrease The efficiency to set
	 * @throws Exception If efficiency less than 0 or greater than 100
	 */
	public void setEfficiencyDecrease(double efficiencyDecrease) throws Exception {
		if (efficiencyDecrease < 0.0 || efficiencyDecrease > 100.0) {
			throw new Exception("Efficiency must be from 0 to 100");
		}
		this.efficiencyDecrease = efficiencyDecrease;
	}
	
	/**
	 * Get warranty time in months
	 */
	public int getWarranty() {
		return warranty;
	}

	public void setWarranty(int warranty) throws Exception {
		if (warranty < 0) {
			throw new Exception("Warranty must not be negative");
		}
		this.warranty = warranty;
	}

	/**
	 * Get dimensions in mm
	 * @return Format LxWxH
	 */
	public String getDimensions() {
		return dimensions;
	}

	/**
	 * Set dimensions in mm, format LxWxH
	 */
	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Get the list of efficiency by years
	 * @param years Number of years to retrieve
	 * @return list of efficiency
	 */
	public double[] getEfficiencyByYear(int years) throws Exception {
		if (years < 0) {
			throw new Exception("Years must not be negative");
		}
		double listEff[] = new double[years];
		double eff = 100.0;
		
		for (int i = 0; i < years; ++i) {
			listEff[i] = eff;
			eff -= efficiencyDecrease;
		}
		return listEff;
	}
	
	/**
	 * Store this component to database
	 */
	public void save() {
		saveComponent(this);
	}
	
	/**
	 * Get list of components from database which its property is in price
	 * range
	 *
	 * @param type Object type. E.g. Panel.class
	 * @param min Minimum value
	 * @param max Maximum value
	 * @return List of panels
	 */
	public static <T> List<T> getComponentsInPrice(Class<T> type, 
			double min, double max) throws Exception {
		if (min < 0.0) {
			throw new Exception("The minimum price must not be negative");
		}
		if (max < 0.0) {
			throw new Exception("The maximum price must not be negative");
		}
		if (max != 0.0 && max < min) {
			throw new Exception("The minimum price must be greater than or equal to the maximum price");
		}
		return SolarComponent.fetchComponentsByRange(type.getName(),
				"price", min, max);
	}
	
	public static <T> List<T> getComponentsInCapacity(Class<T> type,
			double min, double max) throws Exception {
		if (min < 0.0) {
			throw new Exception("The minimum capacity must not be negative");
		}
		if (max < 0.0) {
			throw new Exception("The maximum capacity must not be negative");
		}
		if (max != 0.0 && max < min) {
			throw new Exception("The minimum capacity must be greater than or equal to the maximum capacity");
		}
		return SolarComponent.fetchComponentsByRange(type.getName(),
				"capacity", min, max);
	}
	
	/**
	 * Get panels from database which its property is in provided range
	 *
	 * @param table Table name. e.g. Panel.class.getName()
	 * @param field Property name. E.g. price
	 * @param min Minimum value
	 * @param max Maximum value
	 * @return List of panels
	 */
	@SuppressWarnings("unchecked")
	protected static <T> List<T> fetchComponentsByRange(String table, String field,
			double min, double max) {
		String queryStr = buildQueryStringFromRange(table, field, min, max);
		
		EntityManager em = EMF.get().createEntityManager();
		
		Query query = em.createQuery(queryStr);
		if (min > 0.0) {
			query.setParameter("min", min);
		}
		if (max > 0.0) {
			query.setParameter("max", max);
		}
		
		List<T> resultList;
		try {
			resultList = new ArrayList<T>(query.getResultList());
		} finally {
			em.close();
		}
		return resultList;
	}
	
	/**
	 * Build string to query min/max value
	 * 
	 * @param table Table name. e.g. Panel.class.getName()
	 * @param field Database field name
	 * @param min Minimum value
	 * @param max Maximum value
	 * @return Query string
	 */
	protected static String buildQueryStringFromRange(String table, String field,
			double min, double max) {
		String str = "SELECT FROM " + table;
		
		// Min/max is optional
		if (min > 0.0 || max > 0.0) {
			str += " WHERE";
			if (min > 0.0) {
				str += " " + field + " >= :min";
			}
			if (max > 0.0) {
				if (min > 0.0) {
					str += " AND";
				}
				str += " " + field + " <= :max";
			}
		}
		return str;
	}
	
	/**
	 * Store object to database
	 * 
	 * @param self Object to store
	 * @return Provided object
	 */
	protected static <T> T saveComponent(T self) {
		EntityManager em = EMF.get().createEntityManager();
		try {
			em.persist(self);
		} finally {
			em.close();
		}
		return self;
	}
	
	/**
	 * Get the object from database using its ID
	 * 
	 * @param id ID of the object, its type can be "long" or "Key"
	 * @param cls Class name of the object, e.g. "Panel.class"
	 * @return Object which full properties are retrieved from database 
	 */
	protected static <T> T loadComponent(Object id, Class<T> cls) {
		EntityManager em = EMF.get().createEntityManager();
		T self;
		try {
			self = em.find(cls, id);
		} finally {
			em.close();
		}
		return self;
	}
}
