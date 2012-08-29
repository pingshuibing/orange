package com.qut.spc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.bind.annotation.XmlElement;

import com.qut.spc.EMF;

public class InverterContainer implements InverterDB {
	
	@XmlElement(name="inverter")
	private List<Inverter> list;
	
	public InverterContainer() {
		list = new ArrayList<Inverter>();
	}
	
	public InverterContainer(List<Inverter> list) {
		this.list = list;
	}
	
	public List<Inverter> getList() {
		return list;
	}
	
	public void getList(List<Inverter> list) {
		this.list = list;
	}

	@Override
	public List<Inverter> getInvertersInPriceRange(double inverterMinPrice,
			double inverterMaxPrice) {
		list = fetchInvertersByRange("price", inverterMinPrice, inverterMaxPrice);
		return list;
	}

	@Override
	public List<Inverter> getInvertersInLocation(String userPostcode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Inverter> getInvertersInCapacity(double inverterMinCapacity,
			double inverterMaxCapacity) {
		list = fetchInvertersByRange("capacity", inverterMinCapacity, inverterMaxCapacity);
		return list;
	}

	@SuppressWarnings("unchecked")
	private static List<Inverter> fetchInvertersByRange(String field,
			double min, double max) {
		
		String queryStr = buildQueryStringFromRange(field, min, max);
		
		EntityManager em = EMF.get().createEntityManager();
		
		Query query = em.createQuery(queryStr);
		if (min > 0.0) {
			query.setParameter("min", min);
		}
		if (max > 0.0) {
			query.setParameter("max", max);
		}
		
		List<Inverter> resultList;
		try {
			resultList = new ArrayList<Inverter>(query.getResultList());
		} finally {
			em.close();
		}
		return resultList;
	}

	private static String buildQueryStringFromRange(String field, double min,
			double max) {
		
		String str = "SELECT FROM " + Inverter.class.getName();
		
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
