package com.qut.spc.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import com.qut.spc.EMF;
import com.qut.spc.api.ComponentFilterAPI;
import com.qut.spc.db.QueryBuilder;
import com.qut.spc.postcode.PostcodeUtil;

/**
 * Common container for all Panel/Battery/Inverter
 * @author QuocViet
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class ComponentContainer<T extends SolarComponent> implements ComponentFilterAPI {
	private double minPrice = 0.0;
	
	private double maxPrice = 0.0;
	
	private double minCapacity = 0.0;
	
	private double maxCapacity = 0.0;
	
	private String postcode = "";
	
	
	@Override
	public void setMinPrice(double minPrice) throws IllegalArgumentException {
		if (minPrice < 0.0) {
			throw new IllegalArgumentException(
					"The minimum price must not be negative");
		}
		this.minPrice = minPrice;
	}

	@Override
	public void setMaxPrice(double maxPrice) throws IllegalArgumentException {
		if (maxPrice < 0.0) {
			throw new IllegalArgumentException(
					"The maximum price must not be negative");
		}
		this.maxPrice = maxPrice;		
	}

	@Override
	public void setMinCapacity(double minCapacity)
			throws IllegalArgumentException {
		if (minCapacity < 0.0) {
			throw new IllegalArgumentException(
					"The minimum capacity must not be negative");
		}
		this.minCapacity = minCapacity;
	}

	@Override
	public void setMaxCapacity(double maxCapacity)
			throws IllegalArgumentException {
		if (maxCapacity < 0.0) {
			throw new IllegalArgumentException(
					"The maximum capacity must not be negative");
		}
		this.maxCapacity = maxCapacity;
	}

	@Override
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	protected List<T> fetchComponents(String table)
			throws IllegalArgumentException {
		if (maxPrice != 0.0 && maxPrice < minPrice) {
			throw new IllegalArgumentException(
					"The minimum price must be greater than or equal to the maximum price");
		}
		if (maxCapacity != 0.0 && maxCapacity < minCapacity) {
			throw new IllegalArgumentException(
					"The minimum capacity must be greater than or equal to the maximum capacity");
		}
		PostcodeUtil.validatePostcode(postcode);
		
		QueryBuilder qbPrice = new QueryBuilder(table);
		QueryBuilder qbCapacity = new QueryBuilder(table);
		QueryBuilder qbPostcode = new QueryBuilder(table);
		
		
		qbPrice.addRange("price", minPrice, maxPrice);
		qbCapacity.addRange("capacity", minCapacity, maxCapacity);
		qbPostcode.addStringInList("postcode", postcode);
		
		List<T> price=makeQueryAndExecute(qbPrice);
		List<T> capacity=makeQueryAndExecute(qbCapacity);
		List<T> postcode=makeQueryAndExecute(qbPostcode);
		
		List<T> result=intersection(intersection(price,capacity), postcode);
		Collections.sort(result);
		
		return result;
	}
	
	
	private List<T> makeQueryAndExecute(QueryBuilder builder){
		EntityManager em = EMF.get().createEntityManager();
		
		Query query = builder.getQuery(em);
		
		List<T> list;
		try{
			list=new ArrayList<T>(query.getResultList());
		}finally{
			em.close();
		}
		return list;

		
	}
	
	public abstract void setList(List<T> list);
	
	private List<T> intersection(List<T>list1,List<T>list2){
		List<T>ret=new ArrayList<T>();
		for(T t: list1){
			if(list2.contains(t))
				ret.add(t);
		}
		return ret;	
	}
	
	
	@XmlTransient
	public abstract List<T> getList();
	
	public void save(){
		for(SolarComponent c:getList())
			c.save();
	}
	
	public List<T> search() throws IllegalArgumentException {
		List<T> list=fetchComponents(getComponentClass().getName());
		setList(list);
		return getList();
	}

	public abstract Class<? extends SolarComponent> getComponentClass();

}