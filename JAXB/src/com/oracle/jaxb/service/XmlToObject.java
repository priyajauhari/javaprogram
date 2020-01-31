package com.oracle.jaxb.service;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.oracle.jaxb.model.Address;
import com.oracle.jaxb.model.Person;

public class XmlToObject {

	public static void main(String[] args) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		System.out.println("Unmarshalling, Xml to POJO");
		Person person = (Person)jaxbUnmarshaller.unmarshal(new File("src/resources/Person.xml"));
		System.out.println("Id :- "+person.getId());
		System.out.println("Name :- "+person.getName());
		System.out.println("Age :- "+person.getAge());
		System.out.print("Hobbies :- ");
		for(String hobby : person.getHobbies()) {
			System.out.print(hobby+", ");
		}
		System.out.println("\nAddress :- ");
		Address address = person.getAddress();
		System.out.println("City :- "+address.getCity());
		System.out.println("State :- "+address.getState());
		System.out.println("Pincode :- "+address.getPincode());
	}
}