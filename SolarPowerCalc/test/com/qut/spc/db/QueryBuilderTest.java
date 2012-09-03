package com.qut.spc.db;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for QueryBuilder class
 * @author QuocViet
 */
public class QueryBuilderTest {
	QueryBuilder qb;
	
	private static String TABLE = "dummy";

	@Before
	public void setUp() throws Exception {
		qb = new QueryBuilder(TABLE);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddRangeBothZero_NoAffect() {
		qb.addRange("field", 0.0, 0.0);
		
		assertEquals("SELECT FROM " + TABLE, qb.getQueryString());
	}

	@Test
	public void testAddRangeBothNegative_NoAffect() {
		qb.addRange("field", -1, -2);
		
		assertEquals("SELECT FROM " + TABLE, qb.getQueryString());
	}
	
	@Test
	public void testAddRangeOnlyMin_MaxIsIgnored() {
		qb.addRange("field", 10.0, 0.0);
		
		assertEquals("SELECT FROM " + TABLE + " WHERE field >= :1",
				qb.getQueryString());
	}
	
	@Test
	public void testAddRangeOnlyMax_MinIsIgnored() {
		qb.addRange("field", 0.0, 99.9);
		
		assertEquals("SELECT FROM " + TABLE + " WHERE field <= :1",
				qb.getQueryString());
	}
	
	@Test
	public void testAddRange() {
		qb.addRange("field", 44, 55);
		
		assertEquals("SELECT FROM " + TABLE + " WHERE field >= :1 AND field <= :2",
				qb.getQueryString());
	}
}
