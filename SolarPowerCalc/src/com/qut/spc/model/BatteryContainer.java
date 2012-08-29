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

import com.qut.spc.EMF;

public class BatteryContainer implements BatteryDB {
	
	@XmlElement(name="battery")
	private List<Battery> list;
	
	public BatteryContainer () {
		list = new ArrayList<Battery>();
	}
	
	public BatteryContainer(List<Battery> list) {
		this.list = list;
	}
	
	public List<Battery> getList() {
		return list;
	}
	
	public void getList(List<Battery> list) {
		this.list = list;
	}
	
	

	@Override
	public List<Battery> getBatteriesInPriceRange(double batteryMinPrice, double batteryMaxPrice) {
		list = fetchBatteriesByRange("price", batteryMinPrice, batteryMaxPrice);
		return list;
	}
	
	@Override
	public List<Battery> getBatteriesInLocation(String userPostcode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Battery> getBatteriesInCapacity(double batteryMinCapacity, double batteryMaxCapacity) {
		list = fetchBatteriesByRange("capacity", batteryMinCapacity, batteryMaxCapacity);
		return list;
	}

	@SuppressWarnings("unchecked")
	private static List<Battery> fetchBatteriesByRange(String field, double min,
			double max) {
String queryStr = buildQueryStringFromRange(field, min, max);
		
		EntityManager em = EMF.get().createEntityManager();
		
		Query query = em.createQuery(queryStr);
		if (min > 0.0) {
			query.setParameter("min", min); //should I change min into batteryMinPrice?
		}
		if (max > 0.0) {
			query.setParameter("max", max); //should I change max into batteryMaxPrice?
		}
		
		List<Battery> resultList;
		try {
			resultList = new ArrayList<Battery>(query.getResultList());
		} finally {
			em.close();
		}
		return resultList;
	}

	private static String buildQueryStringFromRange(String field,
			double min, double max) {
		String str = "SELECT FROM " + Battery.class.getName();
		
		// batteryMinPrice/batteryMaxPrice is optional
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
