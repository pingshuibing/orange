package com.qut.spc.controller;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.qut.spc.exceptions.InvalidArgumentException;
import com.qut.spc.marshalling.SolarComponentUnmarshaller;
import com.qut.spc.model.BatteryContainer;
import com.qut.spc.model.InverterContainer;
import com.qut.spc.model.PanelContainer;



public class DBPopulatorTest {

	String input = "test/panels_test.xml";
	private SolarComponentUnmarshaller um;
	private DBPopulator populator;
	
	@Before
	public void setup(){
		um = Mockito.mock(SolarComponentUnmarshaller.class);
		populator = new DBPopulator(um);
	}
	
	@Test
	public void populateDB_ValidxmlString_ContainerIsReturned(){
		SolarComponentUnmarshaller um=Mockito.mock(SolarComponentUnmarshaller.class);
		DBPopulator populator=new DBPopulator(um);
		PanelContainer pc=Mockito.mock(PanelContainer.class);
		
		
		
		Mockito.when(um.unmarshall(PanelContainer.class, input)).thenReturn(pc);
		String res=populator.populateDB(input,"","");
		
		assertTrue(!res.isEmpty());
	}
	
	@Test (expected=InvalidArgumentException.class)
	public void populateDB_invalidPanelXML_InvalidInputExceptionIsThrown(){
		Mockito.when(um.unmarshall(PanelContainer.class, input)).thenThrow(IllegalArgumentException.class);
		
		populator.populateDB(input,"","");
	}
	
	@Test (expected=InvalidArgumentException.class)
	public void populateDB_invalidBatteryXML_InvalidInputExceptionIsThrown(){
		Mockito.when(um.unmarshall(BatteryContainer.class, input)).thenThrow(IllegalArgumentException.class);
		
		populator.populateDB("","",input);
	}
	
	@Test (expected=InvalidArgumentException.class)
	public void populateDB_invalidInverterXML_InvalidInputExceptionIsThrown(){
		Mockito.when(um.unmarshall(InverterContainer.class, input)).thenThrow(IllegalArgumentException.class);
		
		populator.populateDB("",input,"");
	}

}

