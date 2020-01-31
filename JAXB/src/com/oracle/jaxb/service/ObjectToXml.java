package com.oracle.jaxb.service;

import java.awt.List;
import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.oracle.jaxb.model.Address;
import com.oracle.jaxb.model.Person;

public class ObjectToXml {

	public static void main(String[] args) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
		
		ArrayList<String> hobbiesList = new ArrayList<String>();
		hobbiesList.add("Listening Music");
		hobbiesList.add("Playing Badminton");
		hobbiesList.add("Watching Movies");
		Address address = new Address("Noida","UP","201301");
		Person person = new Person(10,"Priya",25, hobbiesList, address);
		
		System.out.println("Marshalling, POJO to Xml");
		jaxbMarshaller.marshal(person, new File("src/resources/Person.xml"));

	}
	
}
