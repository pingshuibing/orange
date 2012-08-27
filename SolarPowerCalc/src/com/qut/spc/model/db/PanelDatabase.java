package com.qut.spc.model.db;

import java.util.List;

import com.qut.spc.model.SolarPanel;

public interface PanelDatabase {
	public List<SolarPanel> getSolarPanelsInPriceRange(double min, double max);
}
