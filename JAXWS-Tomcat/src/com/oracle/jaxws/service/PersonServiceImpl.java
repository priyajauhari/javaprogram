package com.oracle.jaxws.service;

import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.oracle.jaxws.model.AddPersonRequest;
import com.oracle.jaxws.model.AddPersonResponse;
import com.oracle.jaxws.model.DeletePersonRequest;
import com.oracle.jaxws.model.DeletePersonResponse;
import com.oracle.jaxws.model.GetPersonRequest;
import com.oracle.jaxws.model.GetPersonResponse;
import com.oracle.jaxws.model.Person;

@WebService(endpointInterface = "com.oracle.jaxws.service.PersonService")  
public class PersonServiceImpl implements PersonService {

	private static Map<Integer,Person> persons = new HashMap<Integer,Person>();
	
	@Override
	public AddPersonResponse addPerson(AddPersonRequest addPersonRequest) {
		Person person = addPersonRequest.getPerson();
		AddPersonResponse addPersonResponse = new AddPersonResponse();
		if(persons.get(person.getId()) != null) {
			addPersonResponse.setStatus(false);
		} else {
			persons.put(person.getId(), person);
			addPersonResponse.setStatus(true);
		}
		return addPersonResponse;
	}

	@Override
	public DeletePersonResponse deletePerson(DeletePersonRequest deletePersonRequest) {
		int id = deletePersonRequest.getId();
		DeletePersonResponse deletePersonResponse = new DeletePersonResponse();
		if(persons.get(id) == null) {
			deletePersonResponse.setStatus(false);
		} else {
			persons.remove(id);
			deletePersonResponse.setStatus(true);
		}
		return deletePersonResponse;
	}

	@Override
	public GetPersonResponse getPerson(GetPersonRequest getPersonRequest) {
		int id = getPersonRequest.getId();
		Person person = persons.get(id);
		
		GetPersonResponse getPersonResponse = new GetPersonResponse();
		getPersonResponse.getPerson().add(person);
		return getPersonResponse;
	}

	@Override
	public GetPersonResponse getAllPersons() {
		Set<Integer> ids = persons.keySet();
		GetPersonResponse getPersonResponse = new GetPersonResponse();
		
		for(Integer id : ids){
			Person person = persons.get(id);
			getPersonResponse.getPerson().add(person);
		}
		return getPersonResponse;
	}
}