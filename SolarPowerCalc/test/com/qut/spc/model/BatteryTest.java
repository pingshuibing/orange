package com.qut.spc.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class BatteryTest {
	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	private Battery battery;
	
	// For comparing double values
	private static final double EPSILON = 0.001;
	
	@Before
	public void setUp() throws Exception {
		helper.setUp();
		battery = new Battery();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	@Test
	public void testSaveBattery() {
		battery.setModel("XY");
		battery.setPrice(20.0);
		
		battery.save();
		
		battery = Battery.load(battery.getId());
		
		assertNotNull(battery);
		assertEquals("XY", battery.getModel());
		assertEquals(20.0, battery.getPrice(), EPSILON);
	}

}
