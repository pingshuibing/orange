package com.qut.spc.model;

import java.util.Map;

public class SolarComponent {
	
	private Long id = 0L;
	private String model = "";
	private String manufacturer = "";
	private Double price = 0.0;
	private Double capacity = 0.0;
	private Double voltage = 0.0;
	private String dimensions = "";
	private String postcode = "";
	
	public SolarComponent() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getCapacity() {
		return capacity;
	}
	
	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}
	
	public Double getVoltage() {
		return voltage;
	}
	
	public void setVoltage(Double voltage) {
		this.voltage = voltage;
	}
	
	public String getDimensions() {
		return dimensions;
	}
	
	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}
	
	public String getPostcode() {
		return postcode;
	}
	
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	/**
	 * Bind properties to string map
	 */
	public void bindMap(Map<String, String> map) {
		map.put("model", model);
		map.put("manufacture", manufacturer);
		map.put("price", price.toString());
		map.put("capacity", capacity.toString());
		map.put("voltage", voltage.toString());
		map.put("dimensions", dimensions);
		map.put("postcode", postcode);
	}
}
