package com.qut.spc.api;

public interface ElectricityCalculationApi {
	public double getElectricityProduction(double dailySun,double inverterEfficiency,double solarPanelEfficiency,double solarPowerOutput,double timespan);
}
