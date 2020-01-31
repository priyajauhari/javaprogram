package com.oracle.jaxb.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="person")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Person", propOrder = {
    "id",
    "name",
    "age",
    "hobbies",
    "address"
})
public class Person {

	@XmlAttribute(name = "id", required = true)
    protected int id;
    @XmlElement(name = "name", required = true)
    protected String name;
    @XmlElement(name = "age", required = true)
    protected int age;
    @XmlElementWrapper(name = "hobbies")
    @XmlElement(name = "hobby", required = true)
    protected List<String> hobbies;
    @XmlElement(name = "address", required = true)
    protected Address address;

    public Person() {
    	System.out.println("Person Default Constructor");
    }
    
    public Person(int id, String name, int age, List<String> hobbies, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.hobbies = hobbies;
		this.address = address;
	}

	public int getId() {
        return id;
    }

    public void setId(int value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int value) {
        this.age = value;
    }

    public List<String> getHobbies() {
        if (hobbies == null) {
            hobbies = new ArrayList<String>();
        }
        return this.hobbies;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address value) {
        this.address = value;
    }
}