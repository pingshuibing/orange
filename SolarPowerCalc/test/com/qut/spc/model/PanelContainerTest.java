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
		container = new PanelContainer();
		helper.setUp();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	@Test
	public void testGetPanelsInPriceRange_Empty() throws Exception {
		list = container.getPanelsInPriceRange(0.0, 100.0);
		
		assertNotNull(list);
		assertEquals(0, list.size());
	}

	@Test
	public void testGetPanelsInPriceRange_NoItems() throws Exception {
		panel = new Panel();
		panel.setManufacture("M 1");
		panel.setPrice(50.0);
		panel.save();
		
		list = container.getPanelsInPriceRange(0.0, 0.0);
		
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
		
		list = container.getPanelsInPriceRange(0.0, 100.0);
		
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
		
		list = container.getPanelsInPriceRange(10.0, 100.0);
		
		assertNotNull(list);
		assertEquals(3, list.size());
		
		assertEquals("M 2", list.get(0).getManufacture());
		assertEquals("M 3", list.get(1).getManufacture());
		assertEquals("M 4", list.get(2).getManufacture());
	}
	
	@Test
	public void testGetPanelsInPriceRange_NoMin() throws Exception {
		panel = new Panel();
		panel.setManufacture("M 1");
		panel.setPrice(10.0);
		panel.save();
		
		panel = new Panel();
		panel.setManufacture("M 2");
		panel.setPrice(30.0);
		panel.save();
		
		panel = new Panel();
		panel.setManufacture("M 3");
		panel.setPrice(20.0);
		panel.save();
		
		panel = new Panel();
		panel.setManufacture("M 4");
		panel.setPrice(40.0);
		panel.save();
		
		list = container.getPanelsInPriceRange(0.0, 25.0);
		
		assertNotNull(list);
		assertEquals(2, list.size());

		assertEquals("M 1", list.get(0).getManufacture());
		assertEquals("M 3", list.get(1).getManufacture());
	}
	
	@Test
	public void testGetPanelsInPriceRange_NoMax() throws Exception {
		panel = new Panel();
		panel.setManufacture("M 1");
		panel.setPrice(9.0);
		panel.save();
		
		panel = new Panel();
		panel.setManufacture("M 2");
		panel.setPrice(10.0);
		panel.save();
		
		panel = new Panel();
		panel.setManufacture("M 3");
		panel.setPrice(20.0);
		panel.save();
		
		panel = new Panel();
		panel.setManufacture("M 4");
		panel.setPrice(30.0);
		panel.save();
		
		list = container.getPanelsInPriceRange(20.0, 0.0);
		
		assertNotNull(list);
		assertEquals(2, list.size());

		assertEquals("M 3", list.get(0).getManufacture());
		assertEquals("M 4", list.get(1).getManufacture());
	}
	
	@Test
	public void testGetPanelsInCapacity_Empty() throws Exception {
		list = container.getPanelsInCapacity(2.0, 3.0);
		
		assertNotNull(list);
		assertEquals(0, list.size());
	}
	
	@Test
	public void testGetPanelsInCapacity_OneItemNotInRange() throws Exception {
		panel = new Panel();
		panel.setModel("A1");
		panel.setCapacity(99.99);
		panel.save();
		
		list = container.getPanelsInCapacity(1.0, 99.98);

		assertEquals(0, list.size());
	}
	
	@Test
	public void testGetPanelsInCapacity_OneItemInRange() throws Exception {
		panel = new Panel();
		panel.setModel("A1");
		panel.setCapacity(89.99);
		panel.save();
		
		list = container.getPanelsInCapacity(1.0, 90.0);
		assertEquals(1, list.size());
		assertEquals("A1", list.get(0).getModel());
	}
	
	@Test
	public void testGetPanelsInCapacity_ManyItemsNotInRange() throws Exception {
		panel = new Panel();
		panel.setModel("A1");
		panel.setCapacity(19.99);
		panel.save();
		
		panel = new Panel();
		panel.setModel("B1");
		panel.setCapacity(189.00);
		panel.save();
		
		panel = new Panel();
		panel.setModel("B1");
		panel.setCapacity(354.00);
		panel.save();
		
		list = container.getPanelsInCapacity(20.0, 150.0);
		assertEquals(0, list.size());
	}
	
	@Test
	public void testGetPanelsInCapacity_ManyItemsInRange() throws Exception {
		panel = new Panel();
		panel.setModel("A1");
		panel.setCapacity(19.99);
		panel.save();
		
		panel = new Panel();
		panel.setModel("B1");
		panel.setCapacity(189.00);
		panel.save();
		
		panel = new Panel();
		panel.setModel("C1");
		panel.setCapacity(44.00);
		panel.save();
		
		panel = new Panel();
		panel.setModel("D1");
		panel.setCapacity(150.00);
		panel.save();
		
		list = container.getPanelsInCapacity(40.0, 150.0);
		assertEquals(2, list.size());
	}
	
	@Test
	public void testGetPanelsInCapacity_NoMin() throws Exception {
		panel = new Panel();
		panel.setModel("A1");
		panel.setCapacity(20.0);
		panel.save();
		
		panel = new Panel();
		panel.setModel("B1");
		panel.setCapacity(50.0);
		panel.save();
		
		panel = new Panel();
		panel.setModel("C1");
		panel.setCapacity(30.0);
		panel.save();
		
		panel = new Panel();
		panel.setModel("D1");
		panel.setCapacity(40.0);
		panel.save();
		
		list = container.getPanelsInCapacity(0, 30.1);
		assertEquals(2, list.size());
		
		assertEquals("A1", list.get(0).getModel());
		assertEquals("C1", list.get(1).getModel());
	}
	
	@Test
	public void testGetPanelsInCapacity_NoMax() throws Exception {
		panel = new Panel();
		panel.setModel("A1");
		panel.setCapacity(20.0);
		panel.save();
		
		panel = new Panel();
		panel.setModel("B1");
		panel.setCapacity(50.0);
		panel.save();
		
		panel = new Panel();
		panel.setModel("C1");
		panel.setCapacity(30.0);
		panel.save();
		
		panel = new Panel();
		panel.setModel("D1");
		panel.setCapacity(40.0);
		panel.save();
		
		list = container.getPanelsInCapacity(43.0, 0.0);
		assertEquals(1, list.size());
		
		assertEquals("B1", list.get(0).getModel());
	}
}
