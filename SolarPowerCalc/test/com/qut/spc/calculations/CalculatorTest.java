package com.qut.spc.calculations;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.qut.spc.model.BatteryContainer;

public class CalculatorTest {
	
	Calculator calculator;

	@Before
	public void setUp()  {
		calculator = new Calculator();
	}
	
	//@Test
	//public void test() {
	//	fail("Not yet implemented");
	//}
	
	
	@Test
	public void getElectricityProduction_sunPowerOver () {
		double electricity = calculator.getElectricityProduction(100.0,0.8,0.7,120.0,4.0);
		assertEquals(179.20,electricity,0.01);
	}
	
	@Test
	public void getElectricityProduction_solarPanelOver () {
		double electricity = calculator.getElectricityProduction(120.0,0.8,0.7,100.0,4.0);
		assertEquals(215.04,electricity,0.01);
	}
	
	@Test
	public void getElectricityProduction_solarSunPowerEqual () {
		double electricity = calculator.getElectricityProduction(100.0,0.8,0.7,100.0,4.0);
		assertEquals(179.20,electricity,0.01);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void restrictInput_negativeDailySun () {
		Calculator calculator = new Calculator();
		calculator.getElectricityProduction(-100.0,0.8,0.7,100.0,4.0);
	}
	
	@Test
	public void restrictInput_zeroDailySun () {
		Calculator calculator = new Calculator();
		calculator.getElectricityProduction(0.0,0.8,0.7,100.0,4.0);
	}
	
	@Test
	public void restrictInput_normalDailySun () {
		Calculator calculator = new Calculator();
		calculator.getElectricityProduction(100.0,0.8,0.7,100.0,4.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void restrictInput_negativeinverterEfficiency () {
		Calculator calculator = new Calculator();
		calculator.getElectricityProduction(100.0,-0.8,0.7,100.0,4.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void restrictInput_overOneinverterEfficiency () {
		Calculator calculator = new Calculator();
		calculator.getElectricityProduction(100.0,1.2,0.7,100.0,4.0);
	}
	
	@Test
	public void restrictInput_oneinverterEfficiency () {
		Calculator calculator = new Calculator();
		calculator.getElectricityProduction(100.0,1,0.7,100.0,4.0);
	}
	
	@Test
	public void restrictInput_zeroinverterEfficiency () {
		Calculator calculator = new Calculator();
		calculator.getElectricityProduction(100.0,0.0,0.7,100.0,4.0);
	}
	
	@Test
	public void restrictInput_normalinverterEfficiency () {
		Calculator calculator = new Calculator();
		calculator.getElectricityProduction(100.0,0.8,0.7,100.0,4.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void restrictInput_negativeSolarPanelEfficiency () {
		Calculator calculator = new Calculator();
		calculator.getElectricityProduction(100.0,0.8,-0.7,100.0,4.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void restrictInput_overOneSolarPanelEfficiency () {
		Calculator calculator = new Calculator();
		calculator.getElectricityProduction(100.0,0.8,1.2,100.0,4.0);
	}
	
	@Test
	public void restrictInput_oneSolarPanelEfficiency () {
		Calculator calculator = new Calculator();
		calculator.getElectricityProduction(100.0,0.8,1.0,100.0,4.0);
	}
	
	@Test
	public void restrictInput_zeroSolarPanelEfficiency () {
		Calculator calculator = new Calculator();
		calculator.getElectricityProduction(100.0,0.8,0.0,100.0,4.0);
	}
	
	@Test
	public void restrictInput_normalSolarPanelEfficiency () {
		Calculator calculator = new Calculator();
		calculator.getElectricityProduction(100.0,0.8,0.7,100.0,4.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void restrictInput_negativeSolarPowerOutput () {
		Calculator calculator = new Calculator();
		calculator.getElectricityProduction(100.0,0.8,0.7,-100.0,4.0);
	}
	
	@Test
	public void restrictInput_zeroSolarPowerOutput () {
		Calculator calculator = new Calculator();
		calculator.getElectricityProduction(100.0,0.8,0.7,0.0,4.0);
	}
	
	@Test
	public void restrictInput_normalSolarPowerOutput () {
		Calculator calculator = new Calculator();
		calculator.getElectricityProduction(100.0,0.8,0.7,100.0,4.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void restrictInput_negativeTimespan () {
		Calculator calculator = new Calculator();
		calculator.getElectricityProduction(100.0,0.8,0.7,100.0,-4.0);
	}
	
	@Test
	public void restrictInput_zeroTimespan () {
		Calculator calculator = new Calculator();
		calculator.getElectricityProduction(100.0,0.8,0.7,0.0,0.0);
	}
	
	@Test
	public void restrictInput_normalTimespan () {
		Calculator calculator = new Calculator();
		calculator.getElectricityProduction(100.0,0.8,0.7,100.0,4.0);
	}
	
	
	

}
