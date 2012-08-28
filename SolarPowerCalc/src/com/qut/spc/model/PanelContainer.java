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
	
	public void getList(List<Panel> list) {
		this.list = list;
	}
	
	@Override
	public List<Panel> getPanelsInPriceRange(double min, double max) {
		list = fetchPanelsByRange("price", min, max);
		return list;
	}
	

	@Override
	public List<Panel> getPanelsInLocation(String location) {
		// TODO
		return new ArrayList<Panel>();
	}

	@Override
	public List<Panel> getPanelsInCapacity(double min, double max) {
		list = fetchPanelsByRange("capacity", min, max);
		return list;
	}

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
