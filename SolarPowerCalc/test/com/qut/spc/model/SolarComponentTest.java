package com.qut.spc.model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Used for testing the abstract class
 */
class MockSolarComponent extends SolarComponent {
	public MockSolarComponent() {
	}
}

public class SolarComponentTest {

	SolarComponent component;
	
	// For comparing double values
	private static final double EPSILON = 0.001;
	
	@Before
	public void setUp() throws Exception {
		component = new MockSolarComponent();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConstructor_InitVariable() throws Exception {
		assertEquals(0.0, component.getPrice(), EPSILON);
		assertEquals(0.0, component.getEfficiencyDecrease(), EPSILON);
		assertEquals("", component.getManufacture());
	}
	
	@Test
	public void testEfficiencyByYear_OneYear() throws Exception {
		List<Double> eff = component.getEfficiencyByYear(1);
		List<Double> result = Arrays.asList(100.0);
		
		assertEquals(result, eff);
	}
	
	@Test
	public void testEfficiencyByYear_ZeroDecrease() throws Exception {
		List<Double> eff = component.getEfficiencyByYear(7);
		List<Double> result = Arrays.asList(100.0, 100.0, 100.0, 100.0, 100.0,
				100.0, 100.0);
		
		assertEquals(result, eff);
	}
	
	@Test
	public void testEfficiencyByYear_SomeYears() throws Exception {
		component.setEfficiencyDecrease(10.0);
		
		List<Double> eff = component.getEfficiencyByYear(5);
		List<Double> result = Arrays.asList(100.0, 90.0, 80.0, 70.0, 60.0);
		
		assertEquals(result, eff);
	}
	
	@Test
	public void testEfficiencyByYear_ZeroYear() throws Exception {
		List<Double> eff = component.getEfficiencyByYear(0);
		
		assertNotNull(eff);
		assertEquals(0, eff.size());
	}

	@Test(expected=Exception.class)
	public void testEfficiencyByYear_NegativeYear() throws Exception {
		component.getEfficiencyByYear(-1);
	}

}
