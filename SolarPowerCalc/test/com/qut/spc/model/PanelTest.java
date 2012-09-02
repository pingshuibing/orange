package com.qut.spc.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/**
 * Tests for Panel class
 * @author QuocViet
 */
public class PanelTest {
	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	private Panel panel;
	
	// For comparing double values
	private static final double EPSILON = 0.001;
	
	@Before
	public void setUp() throws Exception {
		helper.setUp();
		panel = new Panel();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}
	
	@Test
	public void testSetVoltage_ValidZero() throws IllegalArgumentException {
		panel.setOperatingCurrent(0);
		
		assertEquals(0, panel.getOperatingCurrent(), EPSILON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetVoltage_InvalidNegative() throws IllegalArgumentException {
		panel.setOperatingCurrent(-1);
	}
	
	@Test
	public void testStoring_One() throws Exception {
		panel.setEfficiencyDecrease(0.1);
		panel.setManufacturer("Manufacture 1");
		panel.setOperatingCurrent(1009.0);
		panel.setPrice(13.0);
		
		panel.save();
		
		long id = panel.getId();
		
		panel = Panel.load(id);
		assertNotNull(panel);
		
		// Check that panel's properties
		assertEquals(id, panel.getId());
		assertEquals(0.1, panel.getEfficiencyDecrease(), EPSILON);
		assertEquals("Manufacture 1", panel.getManufacturer());
		assertEquals(1009.0, panel.getOperatingCurrent(), EPSILON);
		assertEquals(13.0, panel.getPrice(), EPSILON);
	}

	@Test
	public void testStoring_Many() throws Exception {
		long id1, id2, id3;
		
		panel.setName("P1");
		panel.setPrice(20.6);
		panel.save();
		id1 = panel.getId();
		
		panel = new Panel();
		panel.setName("P2");
		panel.setPrice(125.0);
		panel.save();
		id2 = panel.getId();
		
		panel = new Panel();
		panel.setName("P3");
		panel.setPrice(25.0);
		panel.save();
		id3 = panel.getId();
		
		panel = Panel.load(id1);
		assertEquals("P1", panel.getName());
		assertEquals(20.6, panel.getPrice(), EPSILON);
		
		panel = Panel.load(id2);
		assertEquals("P2", panel.getName());
		assertEquals(125.0, panel.getPrice(), EPSILON);
		
		panel = Panel.load(id3);
		assertEquals("P3", panel.getName());
		assertEquals(25.0, panel.getPrice(), EPSILON);
	}
}
