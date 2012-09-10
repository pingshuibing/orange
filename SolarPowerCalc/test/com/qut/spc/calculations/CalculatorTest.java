package com.qut.spc.calculations;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.qut.spc.model.BatteryContainer;

public class CalculatorTest {

	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void getElectricityProduction_normal () {
		Calculator calculator = new Calculator();
		double electricity = calculator.getElectricityProduction(2.0,0.9,0.8,3.0,4.0);
		assertEquals(17.28,electricity,0.001);
	}

}
