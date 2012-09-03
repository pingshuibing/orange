package com.qut.spc.db;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * SQL string builder
 * @author QuocViet
 */
public class QueryBuilder {
	private String table;
	
	private List<String> whereFilters;
	
	private Map<String, Object> queryValues;
	
	public QueryBuilder(String table) {
		whereFilters = new LinkedList<String>();
		queryValues = new LinkedHashMap<String, Object>();
		
		this.table = table;
	}
	
	/**
	 * Get final query string from current filters
	 * @return Sql query string
	 */
	public String getQueryString() {
		String queryString = "SELECT FROM " + table;
		Iterator<String> it = whereFilters.iterator();
		
		if (it.hasNext()) {
			// First element
			queryString += " WHERE " + it.next();
			
			// From second one and so on
			while (it.hasNext()) {
				queryString += " AND " + it.next();
			}
		}
		return queryString;
	}
	
	public Query getQuery(EntityManager em) {
		Query query = em.createQuery(getQueryString());
		
		// Add map values
		for (Map.Entry<String, Object> entry : queryValues.entrySet()) {
		    query.setParameter(entry.getKey(), entry.getValue());
		}
		return query;
	}
	
	/**
	 * Add range in the filter.
	 * 
	 * @param field Name of the field in database
	 * @param min Lower bound (should greater than 0)
	 * @param max Upper bound (should greater than 0)
	 */
	public void addRange(String field, double min, double max) {
		if (min > 0.0) {
			String mapName = field + "Min";
			String filter = field + " >= :" + mapName;
			whereFilters.add(filter);
			queryValues.put(mapName, min);
		}
		if (max > 0.0) {
			String mapName = field + "Max";
			String filter = field + " <= :" + mapName;
			whereFilters.add(filter);
			queryValues.put(mapName, max);
		}
	}
	
}
