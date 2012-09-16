package com.qut.spc.api;

public interface ElectricityCalculationApi {
	
	/**
	 * Get the total electricity production
	 * 
	 * @param dailySun Average  daily sun light (kWh, >0)
	 * @param inverterEfficiency The efficiency of a inverter (0-1)
	 * @param solarPanelEfficiency The efficiency of a solar panel (0-1)
	 * @param solarPowerOutput The capacity of a solar panel (>0)
	 * @param dailyHours The average daily hours of sunlight (hour, >0)
	 * @param timespan The time span that user want to calculate (month) (>0)
	 * 
	 * @throws IllegalArgumentException If input is invalid
	 */
	public double getElectricityProduction(
			double dailySun,
			double inverterEfficiency,
			double solarPanelEfficiency,
			double solarPowerOutput, 
			double dailyHours, 
			double timespan);
}
