package com.qut.spc.marshalling;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.qut.spc.model.Panel;
import com.qut.spc.model.PanelContainer;

public class SolarComponentUnmarshallerTest {
	
	private List<Panel> panels;
	private String input;

	@Before
	public void setup(){
		input = "<panels><panel><id>43</id><name>PANEL 15</name><model>ABCD3</model><manufacturer>M 0</manufacturer><price>343.51759647942526</price><capacity>567.4477359039762</capacity><voltage>0.0</voltage><dimensions/><description>Panel number: 15</description><warranty>24</warranty><efficiencyDecrease>0.0</efficiencyDecrease><postcode><postcode>1111</postcode></postcode><operatingCurrent>0.0</operatingCurrent></panel><panel><id>4039</id><name>PANEL 21</name><model>ABCD3</model><manufacturer>M 0</manufacturer><price>1211.9665573233776</price><capacity>1606.3252642163527</capacity><voltage>0.0</voltage><dimensions/><description>Panel number: 21</description><warranty>24</warranty><efficiencyDecrease>0.0</efficiencyDecrease><postcode><postcode>2222</postcode></postcode><operatingCurrent>0.0</operatingCurrent></panel><panel><id>5037</id><name>PANEL 0</name><model>ABCD0</model><manufacturer>M 0</manufacturer><price>517.5408852050549</price><capacity>524.092392798865</capacity><voltage>0.0</voltage><dimensions/><description>Panel number: 0</description><warranty>24</warranty><efficiencyDecrease>0.0</efficiencyDecrease><postcode><postcode>2222</postcode></postcode><operatingCurrent>0.0</operatingCurrent></panel></panels>";
		SolarComponentUnmarshaller scu=new SolarComponentUnmarshaller();
		
		PanelContainer p= scu.unmarshall(PanelContainer.class, input);
		
		panels = p.getList();
		
	}
	
	@Test
	public void testUnmarshall_stringWithThreePanels_CorrectNumberOfPanelsAreReturned(){		
		assertEquals(3, panels.size());
	}
	
	@Test
	public void testUnmarshall_stringWithThreePanels_CorrectPanelDetailsForPanelIsReturned(){
		Panel p=panels.get(1);
		
		assertEquals(4039, p.getId());
		assertEquals("PANEL 21",p.getName());
		assertEquals("ABCD3", p.getModel());
		assertEquals("M 0", p.getManufacturer());
		assertEquals(1211.9665573233776, p.getPrice(),0.0001);
		assertEquals(1606.3252642163527, p.getCapacity(),0.0001);
		assertEquals(0, p.getVoltage(),0.0001);
		assertEquals("", p.getDimensions());
		assertEquals("Panel number: 21", p.getDescription());
		assertEquals(24, p.getWarranty());
		assertEquals(0.0, p.getEfficiencyDecrease(),0.0001);
		assertEquals("2222", p.getPostcode().get(0));
		assertEquals(0.0, p.getOperatingCurrent(),0.0001);		
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void unmarshal_wrongXml_exceptionIsThrown(){
		input="<batteries>"+input+"</batteries>";
		SolarComponentUnmarshaller scu=new SolarComponentUnmarshaller();
		
		scu.unmarshall(PanelContainer.class, input);
		
		
	}
}
