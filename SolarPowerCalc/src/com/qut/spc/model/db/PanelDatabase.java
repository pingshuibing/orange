package com.qut.spc.model.db;

import java.util.List;

import com.qut.spc.model.Panel;

public interface PanelDatabase {
	public List<Panel> getPanelsInPriceRange(double min, double max);
}
