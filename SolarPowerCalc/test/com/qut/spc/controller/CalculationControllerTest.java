package com.qut.spc.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.qut.spc.api.SystemCalculationAPI;

import static org.mockito.Mockito.*;
public class CalculationControllerTest {
	
	private CalculationController controller;
	private SystemCalculationAPI calculator;
	private SystemCalculationAPI container;
	private String result;
	@Before
	public void setup(){
		calculator=mock(SystemCalculationAPI.class);
		controller=new CalculationController(calculator);
		result = controller.getElectricityProduction(32,4,42,24,"BB",2442);

	}
	
	@Test
	public void testGetElectricityProduction_validInput_PropertiesOnCalculatorIsProperlySet(){
		
		verify(calculator).setBatteryId(42);
		verify(calculator).setInverterId(24);
		verify(calculator).setLocation("BB");
		verify(calculator).setTimespan(2442);
		verify(calculator).setPanelId(32);
		
		verify(calculator).getElectricityProduction();
		
		
	}
	@Test
	public void testGetElectricityProduction_validInput_CorrectContainerIsReturned(){
		Assert.assertFalse(result.isEmpty());

	}

}
