package com.qut.spc.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qut.spc.model.SolarPanel;
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
	public void testGetPanels_filledDatabase_correctSetOfPanelsIsReturned(){
	}
	
	@Test
	public void testGetPanels_emptyDatabase_emptyListIsReturned(){
		
		
		List<SolarPanel> result=controller.getPanelsByPrice(0, 100);
		
		assertTrue(result.isEmpty());
		
	}
	

}
