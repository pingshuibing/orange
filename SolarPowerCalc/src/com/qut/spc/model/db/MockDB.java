package com.qut.spc.model.db;

import java.util.ArrayList;
import java.util.List;

import com.qut.spc.model.SolarPanel;

public class MockDB implements PanelDatabase{

	@Override
	public List<SolarPanel> getSolarPanelsInPriceRange(double minimum, double maximum) {
		try {
			ArrayList<SolarPanel> panels=new ArrayList<SolarPanel>();
			SolarPanel sp1=new SolarPanel();
			sp1.setEfficiencyDecrease(24.4);
			sp1.setManufacture("MF1");
			sp1.setPrice(225.35);
			SolarPanel sp2=new SolarPanel();
			sp2.setEfficiencyDecrease(10.0);
			sp2.setManufacture("MF2");
			sp2.setPrice(1000000.2);
			panels.add(sp1);
			panels.add(sp2);
			return panels;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<SolarPanel>();
	}

}
