package com.qut.spc.model;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;

import com.qut.spc.db.Database;

/**
 * Battery entity
 * @author QuocViet
 */
@Entity
public class Battery extends SolarComponent {
	
	public Battery() {
		
	}
	
	@XmlElement
	private String type="";
	
	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type=type;
	}
	
	public static Battery load(long id) {
		return Database.loadComponent(id, Battery.class);
	}

	@Override
	public void save() {
		Database.saveComponent(this);
	}
}
