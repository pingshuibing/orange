package com.qut.spc.model;

public class Inverter extends SolarComponent {
	
	public Inverter() {
		
	}
	
	public static Inverter load(long id) {
		return loadComponent(id, Inverter.class);
	}
}
