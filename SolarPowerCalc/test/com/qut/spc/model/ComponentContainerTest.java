package com.qut.spc.model;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ComponentContainerTest {
	
	private MockContainer container;


	@Before
	public void setup(){
		container=new MockContainer();
	}
	
	private class MockContainer extends ComponentContainer<SolarComponent>{
		private List<SolarComponent> list;

		@Override
		public void setList(List<SolarComponent> list) {
			this.list=list;
		}

		@Override
		public List<SolarComponent> getList() {
			return list;
		}

	
		
	}
	
	
	@Test
	public void save_none_saveOnAllComponentsIsCalled(){
		List<SolarComponent> b=new ArrayList<SolarComponent>();
		SolarComponent b1=mock(SolarComponent.class);
		SolarComponent b2=mock(SolarComponent.class);
		SolarComponent b3=mock(SolarComponent.class);
		SolarComponent b4=mock(SolarComponent.class);
		SolarComponent b5=mock(SolarComponent.class);
		b.add(b1);
		b.add(b2);
		b.add(b3);
		b.add(b4);
		b.add(b5);
		
		container.setList(b);
		
		container.save();
		
		verify(b1).save();
		verify(b2).save();
		verify(b3).save();
		verify(b4).save();
		verify(b5).save();
		
	}

}
