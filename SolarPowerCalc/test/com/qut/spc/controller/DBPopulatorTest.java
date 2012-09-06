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

	String input;
	private SolarComponentUnmarshaller um;
	private DBPopulator populator;
	
	@Before
	public void setup(){
		input = "<panels><panel><id>43</id><name>PANEL 15</name><model>ABCD3</model><manufacturer>M 0</manufacturer><price>343.51759647942526</price><capacity>567.4477359039762</capacity><voltage>0.0</voltage><dimensions/><description>Panel number: 15</description><warranty>24</warranty><efficiencyDecrease>0.0</efficiencyDecrease><postcode><postcode>1111</postcode></postcode><operatingCurrent>0.0</operatingCurrent></panel><panel><id>4039</id><name>PANEL 21</name><model>ABCD3</model><manufacturer>M 0</manufacturer><price>1211.9665573233776</price><capacity>1606.3252642163527</capacity><voltage>0.0</voltage><dimensions/><description>Panel number: 21</description><warranty>24</warranty><efficiencyDecrease>0.0</efficiencyDecrease><postcode><postcode>2222</postcode></postcode><operatingCurrent>0.0</operatingCurrent></panel><panel><id>5037</id><name>PANEL 0</name><model>ABCD0</model><manufacturer>M 0</manufacturer><price>517.5408852050549</price><capacity>524.092392798865</capacity><voltage>0.0</voltage><dimensions/><description>Panel number: 0</description><warranty>24</warranty><efficiencyDecrease>0.0</efficiencyDecrease><postcode><postcode>2222</postcode></postcode><operatingCurrent>0.0</operatingCurrent></panel></panels>";

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

