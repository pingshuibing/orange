package com.qut.spc.marshalling;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.qut.spc.model.ComponentContainer;

public class SolarComponentUnmarshaller {

	/**
	 * Unmarshalls the given XML file into the specified ComponentContainer
	 * class type
	 * 
	 * @param type Type of class to unmarshal into
	 * @param xml XML string to be unmarshalled
	 * @return T extends ComponentContainer, unmarshalled XML file
	 * @throws IllegalArgumentException
	 */
	public <T extends ComponentContainer> T unmarshall(Class<T> type, String xmlFile)
			throws IllegalArgumentException {
		T p;
		try {
			JAXBContext jc = JAXBContext.newInstance(type);
			Unmarshaller u = jc.createUnmarshaller();

			p = (T) u.unmarshal(new File(xmlFile));
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e.getMessage());
		}
		return p;
	}
}
