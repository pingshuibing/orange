package com.qut.spc.model;

import javax.persistence.Entity;

import com.qut.spc.db.Database;

/**
 * Battery entity
 * @author QuocViet
 */
@Entity
public class Battery extends SolarComponent {
	
	public Battery() {
		
	}
	
	public static Battery load(long id) {
		return Database.loadComponent(id, Battery.class);
	}

	@Override
	public void save() {
		Database.saveComponent(this);
	}
}
