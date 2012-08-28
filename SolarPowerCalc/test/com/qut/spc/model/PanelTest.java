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
	public void testStoring() throws Exception {
		panel.setEfficiencyDecrease(0.1);
		panel.setManufacture("Manufacture 1");
		panel.setOutputEnergy(1009.0);
		panel.setPrice(13.0);
		
		panel.save();
		
		long id = panel.getId();
		
		panel = Panel.load(id);
		assertNotNull(panel);
		assertEquals(id, panel.getId());
		assertEquals(0.1, panel.getEfficiencyDecrease(), EPSILON);
		assertEquals("Manufacture 1", panel.getManufacture());
		assertEquals(1009.0, panel.getOutputEnergy(), EPSILON);
		assertEquals(13.0, panel.getPrice(), EPSILON);
	}

}
