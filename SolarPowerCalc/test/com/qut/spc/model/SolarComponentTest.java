package com.qut.spc.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import javax.persistence.Entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.qut.spc.db.Database;

/**
 * Used for testing the abstract class
 */
@Entity
class MockSolarComponent extends SolarComponent {
	private int mockValue;
	
	public MockSolarComponent() {
	}
	
	public static MockSolarComponent load(long id) {
		return Database.loadComponent(id, MockSolarComponent.class);
	}
	
	@Override
	public void save() {
		Database.saveComponent(this);
	}

	public int getMockValue() {
		return mockValue;
	}

	public void setMockValue(int mockValue) {
		this.mockValue = mockValue;
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
	public void testConstructor_InitVariable() {
		// TODO: check all properties
		assertEquals(0.0, component.getPrice(), EPSILON);
		assertEquals(0.0, component.getEfficiencyDecrease(), EPSILON);
		assertEquals("", component.getManufacturer());
	}
	
	@Test
	public void testEfficiencyByYear_OneYear() throws IllegalArgumentException {
		double eff[] = component.getEfficiencyByYear(1);
		double result[] = new double[] { 100.0 };
		
		assertArrayEquals(result, eff, EPSILON);
	}
	
	@Test
	public void testEfficiencyByYear_ZeroDecrease() throws IllegalArgumentException {
		double eff[] = component.getEfficiencyByYear(7);
		double result[] = new double[] { 100.0, 100.0, 100.0, 100.0, 100.0,
				100.0, 100.0};
		
		assertArrayEquals(result, eff, EPSILON);
	}
	
	@Test
	public void testEfficiencyByYear_SomeYears() throws IllegalArgumentException {
		component.setEfficiencyDecrease(10.0);
		
		double eff[] = component.getEfficiencyByYear(5);
		double result[] = new double[] { 100.0, 90.0, 80.0, 70.0, 60.0 };
		
		assertArrayEquals(result, eff, EPSILON);
	}
	
	@Test
	public void testEfficiencyByYear_ZeroYear() throws IllegalArgumentException {
		double eff[] = component.getEfficiencyByYear(0);
		
		assertNotNull(eff);
		assertEquals(0, eff.length);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testEfficiencyByYear_NegativeYear() throws IllegalArgumentException {
		component.getEfficiencyByYear(-1);
	}
	
	@Test
	public void testSetPrice_ValidZero() throws IllegalArgumentException {
		component.setPrice(0);
		
		assertEquals(0, component.getPrice(), EPSILON);
	}
	
	@Test
	public void testSetPrice_ValidPositive() throws IllegalArgumentException {
		component.setPrice(20.0);
		
		assertEquals(20.0, component.getPrice(), EPSILON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetPrice_InvalidNegative() throws IllegalArgumentException {
		component.setPrice(-1);
	}
	
	@Test
	public void testSetCapacity_ValidZero() throws IllegalArgumentException {
		component.setCapacity(0);
		
		assertEquals(0, component.getCapacity(), EPSILON);
	}
	
	@Test
	public void testSetCapacity_ValidPositive() throws IllegalArgumentException {
		component.setCapacity(220.0);
		
		assertEquals(220.0, component.getCapacity(), EPSILON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetCapacity_InvalidNegative() throws IllegalArgumentException {
		component.setCapacity(-1);
	}
	
	@Test
	public void testSetVoltage_ValidZero() throws IllegalArgumentException {
		component.setVoltage(0);
		
		assertEquals(0, component.getVoltage(), EPSILON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetVoltage_InvalidNegative() throws IllegalArgumentException {
		component.setVoltage(-1);
	}
	
	@Test
	public void testSetEfficiencyDecrease_ValidZero() throws IllegalArgumentException {
		component.setEfficiencyDecrease(0);
		
		assertEquals(0, component.getEfficiencyDecrease(), EPSILON);
	}
	
	@Test
	public void testSetWarranty_ValidZero() throws IllegalArgumentException {
		component.setWarranty(0);
		
		assertEquals(0, component.getWarranty(), EPSILON);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetWarranty_InvalidNegative() throws IllegalArgumentException {
		component.setWarranty(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetEfficiencyDecrease_InvalidNegative() throws IllegalArgumentException {
		component.setEfficiencyDecrease(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetEfficiencyDecrease_InvalidMaxRange() throws IllegalArgumentException {
		component.setEfficiencyDecrease(101);
	}
	
	@Test
	public void testSetPostcode_validPostcode_postCodeIsSet(){
		ArrayList<String> l=new ArrayList<String>();
		l.add("1234");
		component.setPostcode(l);
		assertEquals("[1234]", component.getPostcode().toString());		
	}

	
	@Test
	public void testSaveComponent() throws Exception {
		MockSolarComponent c1, c2;
		
		ArrayList<String> postcodes=new ArrayList<String>();
		postcodes.add("1233");
		c1 = (MockSolarComponent)component;
		
		c1.setEfficiencyDecrease(100.0);
		c1.setManufacturer("A new manufacture");
		c1.setPrice(8000.0);
		c1.setDimensions("2x2x2");
		c1.setDescription("desc");
		c1.setMockValue(12);
		c1.setPostcode(postcodes);
		
		c1.save();
		
		c2 = MockSolarComponent.load(c1.getId());
		assertNotNull(c2);
		
		assertEquals(c1.getEfficiencyDecrease(), c2.getEfficiencyDecrease(), EPSILON);
		assertEquals(c1.getManufacturer(), c2.getManufacturer());
		assertEquals(c1.getPrice(), c2.getPrice(), EPSILON);
		assertEquals(c1.getDimensions(), c2.getDimensions());
		assertEquals(c1.getDescription(), c2.getDescription());
		assertEquals(c1.getMockValue(), c2.getMockValue(), EPSILON);
		assertEquals("[1233]", c2.getPostcode().toString());
	}
}
