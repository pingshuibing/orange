package com.qut.spc.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qut.spc.model.SolarPanel;
import com.qut.spc.model.converter.SolarPanelConverter;
import com.qut.spc.model.db.PanelDatabase;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
/**
 * Tests for PanelController
 * @author Simen
 *
 */
public class PanelControllerTest {
	
	private List<SolarPanel> panels;
	private PanelDatabase db;
	private PanelController controller;

	@Before
	public void setup(){
		panels = new ArrayList<SolarPanel>();
		db = mock(PanelDatabase.class);
		controller = new PanelController(db);
		when(db.getSolarPanelsInPriceRange(0, 100)).thenReturn(panels);		
		
	}
	
	@Test
	public void testGetPanels_filledDatabase_nonEmptyListOfPanelsIsReturned(){
		SolarPanel sp1=new SolarPanel();
		SolarPanel sp2=new SolarPanel();
		SolarPanel sp3=new SolarPanel();
		SolarPanel sp4=new SolarPanel();
		panels.add(sp1);
		panels.add(sp2);
		panels.add(sp3);
		panels.add(sp4);
		
		List<SolarPanelConverter> result=controller.getPanelsByPrice(0, 100).getConverters();
		assertEquals(4, result.size());
	}
	
	@Test
	public void testGetPanels_emptyDatabase_emptyListIsReturned(){		
		List<SolarPanelConverter> result=controller.getPanelsByPrice(0, 100).getConverters();
		
		assertTrue(result.isEmpty());
		
	}

	
}
