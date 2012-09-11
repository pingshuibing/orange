package com.qut.spc.calculations;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.qut.spc.model.BatteryContainer;

public class CalculatorTest {

	
	//@Test
	//public void test() {
	//	fail("Not yet implemented");
	//}
	
	
	@Test
	public void getElectricityProduction_capacity () {
		Calculator calculator = new Calculator();
		double electricity = calculator.getElectricityProduction(100.0,0.8,0.7,120.0,4.0);
		//System.out.println(electricity);
		assertEquals(179.20,electricity,0.01);
	}
	
	@Test
	public void getElectricityProduction_sunEngergy () {
		Calculator calculator = new Calculator();
		double electricity = calculator.getElectricityProduction(120.0,0.8,0.7,100.0,4.0);
		//System.out.println(electricity);
		assertEquals(215.04,electricity,0.01);
	}

}
