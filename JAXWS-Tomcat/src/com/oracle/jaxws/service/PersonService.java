package com.oracle.jaxws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.oracle.jaxws.model.AddPersonRequest;
import com.oracle.jaxws.model.AddPersonResponse;
import com.oracle.jaxws.model.DeletePersonRequest;
import com.oracle.jaxws.model.DeletePersonResponse;
import com.oracle.jaxws.model.GetPersonRequest;
import com.oracle.jaxws.model.GetPersonResponse;
import com.oracle.jaxws.model.Person;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface PersonService {

	@WebMethod(action="Add Details", operationName="addPerson")
	@WebResult(partName="result")
	public AddPersonResponse addPerson(@WebParam(partName="addPersonRequest") AddPersonRequest addPersonRequest);
	
	//@WebMethod(exclude=true)
	@WebMethod
	@WebResult(partName="result")
	public DeletePersonResponse deletePerson(@WebParam(partName="deletePersonRequest") DeletePersonRequest deletePersonRequest);
	
	@WebMethod
	@WebResult(partName="result")
	public GetPersonResponse getPerson(@WebParam(partName="getPersonRequest") GetPersonRequest getPersonRequest);
	
	@WebMethod
	@WebResult(partName = "result")
	public GetPersonResponse getAllPersons();
}