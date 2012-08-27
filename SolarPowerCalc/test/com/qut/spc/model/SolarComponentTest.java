package com.qut.spc.model;

import static org.junit.Assert.*;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.qut.spc.EMF;

/**
 * Used for testing the abstract class
 */
@Entity
class MockSolarComponent extends SolarComponent {
	public MockSolarComponent() {
	}
	
	public static MockSolarComponent load(Key key) {
		MockSolarComponent self;
		
		EntityManager em = EMF.get().createEntityManager();
		try {
			self = em.find(MockSolarComponent.class, key);
		} finally {
			em.close();
		}
		return self;
	}
}

/**
 * Test for Solar component abstract class
 * 
 * @author QuocViet
 */
public class SolarComponentTest {

	SolarComponent component;
	
	private final LocalServiceTestHelper helper =
	        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	// For comparing double values
	private static final double EPSILON = 0.001;
	
	@Before
	public void setUp() throws Exception {
		helper.setUp();
		component = new MockSolarComponent();
	}

	@After
	public void tearDown() throws Exception {
		helper.tearDown();
	}

	@Test
	public void testConstructor_InitVariable() throws Exception {
		assertEquals(0.0, component.getPrice(), EPSILON);
		assertEquals(0.0, component.getEfficiencyDecrease(), EPSILON);
		assertEquals("", component.getManufacture());
	}
	
	@Test
	public void testEfficiencyByYear_OneYear() throws Exception {
		double eff[] = component.getEfficiencyByYear(1);
		double result[] = new double[] { 100.0 };
		
		assertArrayEquals(result, eff, EPSILON);
	}
	
	@Test
	public void testEfficiencyByYear_ZeroDecrease() throws Exception {
		double eff[] = component.getEfficiencyByYear(7);
		double result[] = new double[] { 100.0, 100.0, 100.0, 100.0, 100.0,
				100.0, 100.0};
		
		assertArrayEquals(result, eff, EPSILON);
	}
	
	@Test
	public void testEfficiencyByYear_SomeYears() throws Exception {
		component.setEfficiencyDecrease(10.0);
		
		double eff[] = component.getEfficiencyByYear(5);
		double result[] = new double[] { 100.0, 90.0, 80.0, 70.0, 60.0 };
		
		assertArrayEquals(result, eff, EPSILON);
	}
	
	@Test
	public void testEfficiencyByYear_ZeroYear() throws Exception {
		double eff[] = component.getEfficiencyByYear(0);
		
		assertNotNull(eff);
		assertEquals(0, eff.length);
	}

	@Test(expected=Exception.class)
	public void testEfficiencyByYear_NegativeYear() throws Exception {
		component.getEfficiencyByYear(-1);
	}

	@Test
	public void testSaveComponent() throws Exception {
		component.setEfficiencyDecrease(100.0);
		component.setManufacture("A new manufacture");
		component.setPrice(8000.0);
		
		component.save();
		
		MockSolarComponent saved = MockSolarComponent.load(component.getKey());
		assertNotNull(saved);
		
		assertEquals(component.getEfficiencyDecrease(), saved.getEfficiencyDecrease(), EPSILON);
		assertEquals(component.getManufacture(), saved.getManufacture());
		assertEquals(component.getPrice(), saved.getPrice(), EPSILON);
	}
}
