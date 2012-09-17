package com.qut.spc.api;

public interface TotalCostCalculationAPI {
	/**
	 * Get total cost of the system
	 * 
	 * @param panelPrice The price for a panel 
	 * @param panelQuantity The number of panels
	 * @param batteryPrice The price of a battery
	 * @param batteryQuantity The number of batteries -- if only one battery in using, 1 as default
	 * @param interterPrice The price of a inverter
	 * 
	 * @throws IllegalArgumentException If input is invalid
	 */
	public double getSystemTotalCost(
			double panelPrice, 
			int panelsQuantity, 
			double batterPrice, 
			int batteriesQuantity, 
			double intverterPrice ) throws IllegalArgumentException;
	

}
