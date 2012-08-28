package com.qut.spc.model.converter;


import org.junit.Before;
import org.junit.Test;

import com.qut.spc.model.Panel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class PanelConverterTest {
	
	private SolarPanelConverter converter;
	private Panel panel;
	
	@Before
	public void setup(){
		panel=mock(Panel.class);
		converter=new SolarPanelConverter(panel);
	}

	@Test
	public void testGetOutputEnergy_validPanel_panelsResultIsReturn(){
		when(panel.getOutputEnergy()).thenReturn(2424.4334);
		
		assertEquals(2424.4334f, converter.getOutputEnergy(),0.0001f);
	}

	
}
