package com.qut.spc.marshalling;

import java.io.ByteArrayInputStream;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.qut.spc.api.ComponentFilterAPI;
import com.qut.spc.model.BatteryContainer;
import com.qut.spc.model.ComponentContainer;
import com.qut.spc.model.PanelContainer;

public class SolarComponentUnmarshaller {
	
	/**
	 * Unmarshalls the given XML file into the specified ComponentContainer class type
	 * @param type Type of class to unmarshal into
	 * @param xml XML string to be unmarshalled
	 * @return T extends ComponentContainer, unmarshalled XML file
	 * @throws IllegalArgumentException
	 */
	public <T extends ComponentContainer> T unmarshall(Class<T> type,String xml) throws IllegalArgumentException{
		try {
			JAXBContext jc=JAXBContext.newInstance(type);
			
			Unmarshaller u=jc.createUnmarshaller();
			
			ByteArrayInputStream is=new ByteArrayInputStream(xml.getBytes());

			T p=(T)u.unmarshal(is);
			
			return p;
			
		} catch (JAXBException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}
}
