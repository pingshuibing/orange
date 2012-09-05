package com.qut.spc.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class InverterTest {
	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	private Inverter inverter;
	
	// For comparing double values
	private static final double EPSILON = 0.001;
	
	@Before
	public void setUp() throws Exception {
		helper.setUp();
		inverter = new Inverter();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	@Test
	public void testSaveInverter() {
		inverter.setModel("Inverter 1");
		inverter.setBatteryVoltageRange("10.5 - 17");
		inverter.setOutputVoltage("240 AC +/- 4%");
		inverter.setOutputFrequency("50 +/- 0.1%");
		inverter.setPrice(999.9);
		
		inverter.save();
		
		inverter = Inverter.load(inverter.getId());
		
		assertNotNull(inverter);
		assertEquals("Inverter 1", inverter.getModel());
		assertEquals("10.5 - 17", inverter.getBatteryVoltageRange());
		assertEquals("240 AC +/- 4%", inverter.getOutputVoltage());
		assertEquals("50 +/- 0.1%", inverter.getOutputFrequency());
		assertEquals(999.9, inverter.getPrice(), EPSILON);
	}

}
