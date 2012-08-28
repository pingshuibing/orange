package com.qut.spc.model.db;

import java.util.ArrayList;
import java.util.List;

import com.qut.spc.model.Panel;

public class MockDB implements PanelDatabase{

	@Override
	public List<Panel> getPanelsInPriceRange(double minimum, double maximum) {
		try {
			ArrayList<Panel> panels=new ArrayList<Panel>();
			Panel sp1=new Panel();
			sp1.setEfficiencyDecrease(24.4);
			sp1.setManufacture("MF1");
			sp1.setPrice(225.35);
			Panel sp2=new Panel();
			sp2.setEfficiencyDecrease(10.0);
			sp2.setManufacture("MF2");
			sp2.setPrice(1000000.2);
			panels.add(sp1);
			panels.add(sp2);
			return panels;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<Panel>();
	}

}
