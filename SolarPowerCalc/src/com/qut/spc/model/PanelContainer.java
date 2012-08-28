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
public class PanelContainer {
	
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

	@SuppressWarnings("unchecked")
	public static List<Panel> getPanelsInPriceRange(double min, double max) {
		String queryStr = "SELECT FROM " + Panel.class.getName();
		
		if (min > 0.0 || max > 0.0) {
			queryStr += " WHERE";
			if (min > 0.0) {
				queryStr += " price >= :min";
			}
			if (max > 0.0) {
				if (min > 0.0) {
					queryStr += " AND";
				}
				queryStr += " price <= :max";
			}
		}
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
}
