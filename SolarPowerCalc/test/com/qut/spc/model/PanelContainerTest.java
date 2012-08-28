package com.qut.spc.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class PanelContainerTest {
	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	PanelContainer container;
	List<Panel> list;
	Panel panel;
	
	@Before
	public void setUp() throws Exception {
		helper.setUp();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	@Test
	public void testGetPanelsInPriceRange_Empty() {
		list = PanelContainer.getPanelsInPriceRange(0.0, 100.0);
		
		assertNotNull(list);
		assertEquals(0, list.size());
	}

	@Test
	public void testGetPanelsInPriceRange_NoRange() throws Exception {
		panel = new Panel();
		panel.setManufacture("M 1");
		panel.setPrice(50.0);
		panel.save();
		
		list = PanelContainer.getPanelsInPriceRange(0.0, 0.0);
		
		assertNotNull(list);
		assertEquals(1, list.size());
		panel = list.get(0);
		
		assertEquals("M 1", panel.getManufacture());
		assertEquals(50.0, panel.getPrice(), 0.001);
	}
	
	@Test
	public void testGetPanelsInPriceRange_OneItemInRange() throws Exception {
		panel = new Panel();
		panel.setManufacture("M 1");
		panel.setPrice(50.0);
		panel.save();
		
		list = PanelContainer.getPanelsInPriceRange(0.0, 100.0);
		
		assertNotNull(list);
		assertEquals(1, list.size());
		panel = list.get(0);
		
		assertEquals("M 1", panel.getManufacture());
		assertEquals(50.0, panel.getPrice(), 0.001);
	}
	
	@Test
	public void testGetPanelsInPriceRange_ManyItemsInRange() throws Exception {
		panel = new Panel();
		panel.setManufacture("M 1");
		panel.setPrice(9.9);
		panel.save();
		
		panel = new Panel();
		panel.setManufacture("M 2");
		panel.setPrice(10.0);
		panel.save();
		
		panel = new Panel();
		panel.setManufacture("M 3");
		panel.setPrice(50.0);
		panel.save();
		
		panel = new Panel();
		panel.setManufacture("M 4");
		panel.setPrice(100.0);
		panel.save();
		
		panel = new Panel();
		panel.setManufacture("M 5");
		panel.setPrice(100.1);
		panel.save();
		
		panel = new Panel();
		panel.setManufacture("M 6");
		panel.setPrice(100.1);
		panel.save();
		
		list = PanelContainer.getPanelsInPriceRange(10.0, 100.0);
		
		assertNotNull(list);
		assertEquals(3, list.size());
		
		assertEquals("M 2", list.get(0).getManufacture());
		assertEquals("M 3", list.get(1).getManufacture());
		assertEquals("M 4", list.get(2).getManufacture());
	}
}
