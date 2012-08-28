package com.qut.spc.model.converter;
import org.junit.Before;
import org.junit.Test;

import com.qut.spc.model.SolarComponent;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SolarComponentConverterTest {
	private SolarComponentConverter converter;
	private SolarComponent component;
	
	@Before
	public void setup(){
		component=mock(SolarComponent.class);
		converter=new SolarComponentConverter(component);
	}

	@Test
	public void testGetEfficiencyDecrease_validPanel_panelsResultIsReturned(){
		when(component.getEfficiencyDecrease()).thenReturn(24.2);
		
		assertEquals(24.2, converter.getEfficiencyDecrease(),0.00001);
	}
	

	@Test
	public void testGetManufacturer_validPanel_panelsResultIsReturned(){
		when(component.getManufacture()).thenReturn("SPCOOM");
		
		assertEquals("SPCOOM", converter.getManufacturer());
	}
	

	@Test
	public void testGetPrice_validPanel_panelsResultIsReturedn(){
		when(component.getPrice()).thenReturn(2024.012);
		assertEquals(2024.012, converter.getPrice(),0.00001);
	}
	
}
