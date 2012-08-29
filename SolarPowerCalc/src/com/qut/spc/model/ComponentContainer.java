/*
 * test..not complete yet
 */

package com.qut.spc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.qut.spc.EMF;

public abstract class ComponentContainer {
	
	@SuppressWarnings("unchecked")
	protected static List<SolarComponent> fetchComponentsByRange(String field, double min,
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
		
		List<SolarComponent> resultList;
		try {
			resultList = new ArrayList<SolarComponent>(query.getResultList());
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
