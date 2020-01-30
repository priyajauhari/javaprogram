package com.oracle.jaxws.test;

import javax.xml.ws.Endpoint;

import com.oracle.jaxws.service.PersonServiceImpl;

public class SOAPPublisher {

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/JAXWS-Tomcat/PersonService", new PersonServiceImpl());
	}
}