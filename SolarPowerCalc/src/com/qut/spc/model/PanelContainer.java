/*
 * Solar power calculator
 * 
 * Copyright (C) 2012, ORANGE group.
 * See LICENSE.txt for license details.
 */

package com.qut.spc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.qut.spc.EMF;


/**
 * Wrapper class for list of SolarPanel
 * @author QuocViet
 */
@XmlRootElement(name="panels")
public class PanelContainer implements PanelDB {
	
	@XmlElement(name="panel")
	private List<Panel> list;
	
	public PanelContainer() {
		list = new ArrayList<Panel>();
	}
	
	public PanelContainer(List<Panel> list) {
		this.list = list;
	}
	
	public List<Panel> getList() {
		return list;
	}
	
	public void setList(List<Panel> list) {
		this.list = list;
	}
	
	@Override
	public List<Panel> getPanelsInPriceRange(double min, double max)
			throws Exception {
		if (min < 0.0) {
			throw new Exception("The minimum price must not be negative");
		}
		if (max < 0.0) {
			throw new Exception("The maximum price must not be negative");
		}
		if (max != 0.0 && max < min) {
			throw new Exception("The minimum price must be greater than or equal to the maximum price");
		}
		list = fetchPanelsByRange("price", min, max);
		return list;
	}
	

	@Override
	public List<Panel> getPanelsInLocation(String location)
			throws Exception {
		// TODO
		return new ArrayList<Panel>();
	}

	@Override
	public List<Panel> getPanelsInCapacity(double min, double max)
			throws Exception {
		if (min < 0.0) {
			throw new Exception("The minimum capacity must not be negative");
		}
		if (max < 0.0) {
			throw new Exception("The maximum capacity must not be negative");
		}
		if (max != 0.0 && max < min) {
			throw new Exception("The minimum capacity must be greater than or equal to the maximum capacity");
		}
		list = fetchPanelsByRange("capacity", min, max);
		return list;
	}

	/**
	 * Get panels from database which its property is in provided range
	 *   
	 * @param field Property name
	 * @param min Minimum value
	 * @param max Maximum value
	 * @return List of panels
	 */
	@SuppressWarnings("unchecked")
	protected static List<Panel> fetchPanelsByRange(String field, double min,
			double max) {
		String queryStr = buildQueryStringFromRange(field, min, max);
		
		EntityManager em = EMF.get().createEntityManager();
		
		Query query = em.createQuery(queryStr);
		if (min > 0.0) {
			query.setParameter("min", min);
		}
		if (max > 0.0) {
			query.setParameter("max", max);
		}
		
		List<Panel> resultList;
		try {
			resultList = new ArrayList<Panel>(query.getResultList());
		} finally {
			em.close();
		}
		return resultList;
	}
	
	/**
	 * Build string to query min/max value
	 * 
	 * @param field Database field name
	 * @param min Minimum value
	 * @param max Maximum value
	 * @return Query string
	 */
	protected static String buildQueryStringFromRange(String field, double min,
			double max) {
		String str = "SELECT FROM " + Panel.class.getName();
		
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
}
