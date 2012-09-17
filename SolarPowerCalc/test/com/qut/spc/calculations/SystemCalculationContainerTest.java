package com.qut.spc.calculations;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.verification.VerificationMode;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.qut.spc.api.ElectricityCalculationApi;
import com.qut.spc.db.Database;
import com.qut.spc.model.Battery;
import com.qut.spc.model.Inverter;
import com.qut.spc.model.Panel;
import com.qut.spc.postcode.PostcodeUtil;
import com.qut.spc.weather.DailySunProvider;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Database.class)
public class SystemCalculationContainerTest {
	private SystemCalculationContainer container;
	private Panel panel;
	private Inverter inverter;
	private Battery battery;
	private ElectricityCalculationApi calc;
	
	@Before
	public void setup(){
		calc = mock(ElectricityCalculationApi.class);
		
		container=spy(new SystemCalculationContainer(calc));
		when(container.getLocation()).thenReturn("4000");
		
		
		PowerMockito.mockStatic(Database.class);
		PowerMockito.mockStatic(DailySunProvider.class);
		

		
		panel=mock(Panel.class);
		inverter=mock(Inverter.class);
		battery=mock(Battery.class);
		
		PowerMockito.when(Database.loadComponent(25, Panel.class)).thenReturn(panel);
		PowerMockito.when(Database.loadComponent(-411, Panel.class)).thenThrow(EntityNotFoundException.class);

		PowerMockito.when(Database.loadComponent(25, Inverter.class)).thenReturn(inverter);
		PowerMockito.when(Database.loadComponent(-411, Inverter.class)).thenThrow(EntityNotFoundException.class);

		PowerMockito.when(Database.loadComponent(25, Battery.class)).thenReturn(battery);
		PowerMockito.when(Database.loadComponent(-411, Battery.class)).thenThrow(EntityNotFoundException.class);


	}
	
	@Test
	public void testSetPanelId_PanelIsntSetAndValidPanelIdAsParameter_PanelIsSet(){
		container.setPanelId(25);
		assertEquals(panel, container.getPanel());
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testSetPanelId_nonExistingPanelId_ExceptionIsThrown(){
		container.setPanelId(-411);
	}
	
	@Test
	public void testSetInverterId_InverterIsntSetAndValidInverterIdAsParameter_InverterIsSet(){
		container.setInverterId(25);
		assertEquals(inverter, container.getInverter());
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testSetInverterId_nonExistingInverterId_ExceptionIsThrown(){
		container.setInverterId(-411);
	}
	
	
	@Test
	public void testSetBatteryId_BatteryIsntSetAndValidBatteryIdAsParameter_BatteryIsSet(){
		container.setBatteryId(25);
		assertEquals(battery, container.getBattery());
	}

	@Test(expected = EntityNotFoundException.class)
	public void testSetBatteryId_nonExistingBatteryId_ExceptionIsThrown(){
		container.setBatteryId(-411);
	}
	
	
	@Test
	public void testSetLocation_validLocation_locationIsSet(){
		container.setLocation("4560");
		
		assertEquals("4000", container.getLocation());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetLocation_invalidLocation_ExceptionIsThrown(){
		container.setLocation("SDIF");
	}
	
	@Test
	public void getElectricityProduction_validInput_dailySunIsFetchedAndUsed(){

//		PowerMockito.when(DailySunProvider.getDailySunByPostcode("4000")).thenReturn(40d);
//		PowerMockito.when(DailySunProvider.getDailySunLight("4000")).thenReturn(60d);
		PowerMockito.when(DailySunProvider.getDailySunByPostcode(any(String.class))).thenReturn(24d);
		PowerMockito.when(DailySunProvider.getDailySunLight(any(String.class))).thenReturn(276d);
		
		PowerMockito.when(PostcodeUtil.validatePostcode("4000")).thenReturn(true);

		
		when(calc.getElectricityProduction(any(Double.class), any(Double.class), any(Double.class), any(Double.class), any(Double.class), any(Double.class))).thenReturn(42d);
		
		

		PowerMockito.verifyStatic();
		
		DailySunProvider.getDailySunByPostcode("4000");
		container.getElectricityProduction();

	}
}