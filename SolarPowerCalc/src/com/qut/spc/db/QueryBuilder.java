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
	
	private Integer fieldIdx = 0;
	
	private List<String> whereFilters;
	
	private Map<Integer, Object> queryValues;
	
	public QueryBuilder(String table) {
		whereFilters = new LinkedList<String>();
		queryValues = new LinkedHashMap<Integer, Object>();
		
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
		for (Map.Entry<Integer, Object> entry : queryValues.entrySet()) {
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
		Integer idx;
		String filter;
		
		if (min > 0.0) {
			idx = getNextIndex();
			filter = field + " >= :" + idx;
			whereFilters.add(filter);
			queryValues.put(idx, min);
		}
		if (max > 0.0) {
			idx = getNextIndex();
			filter = field + " <= :" + idx;
			whereFilters.add(filter);
			queryValues.put(idx, max);
		}
	}
	
	/**
	 * Search for string in a list. Do nothing if keyword is empty
	 * @param field Name of the field in database
	 * @param keyword Search keyword
	 */
	public void addStringInList(String field, String keyword) {
		Integer idx;
		String filter;
		
		if (!keyword.isEmpty()) {
			idx = getNextIndex();
			filter = ":" + idx + " MEMBER OF " + field;
			
			whereFilters.add(filter);
			queryValues.put(idx, keyword);
		}
	}
	
	private Integer getNextIndex() {
		return ++fieldIdx;
	}
}
