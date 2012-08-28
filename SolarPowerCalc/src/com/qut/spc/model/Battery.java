package com.qut.spc.model;

public class Battery extends SolarComponent {
	
	public Battery() {
		
	}
	
	public static Battery load(long id) {
		return loadComponent(id, Battery.class);
	}
}
