package com.qut.spc.db;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {
	private String table;
	
	private List<String> whereFilters; 
	
	public QueryBuilder(String table) {
		whereFilters = new ArrayList<String>();
		this.table = table;
	}
	
	public String getQueryString() {
		String queryString = "SELECT FROM " + table;
		if (whereFilters.size() > 0) {
			queryString += " WHERE " + whereFilters.get(0);
			
			for (int i = 1; i < whereFilters.size(); ++i) {
				queryString += " AND " + whereFilters.get(i); 
			}
		}
		return queryString;
	}
	
	public void addRange(String field, double min, double max) {
		if (min > 0.0) {
			String filter = field + " >= :" + field + "Min";
			whereFilters.add(filter);
		}
		if (max > 0.0) {
			String filter = field + " <= :" + field + "Max";
			whereFilters.add(filter);
		}
	}
	
}
