package com.qut.spc.marshalling;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.qut.spc.model.ComponentContainer;
import com.qut.spc.model.Panel;
import com.qut.spc.model.PanelContainer;

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
	
	public static void main(String[] args) {
		
		try {
			JAXBContext jc=JAXBContext.newInstance(PanelContainer.class);
			Marshaller mc=jc.createMarshaller();
			
			List<Panel> wr=new ArrayList<Panel>();
			Panel p=new Panel();
			List<String> pc=new ArrayList<String>();			
			pc.add("4444");
			pc.add("5555");
			p.setPostcode(pc);
			
			wr.add(p);
			
			PanelContainer pac=new PanelContainer();
			pac.setList(wr);
			mc.marshal(pac, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
}
