package com.oracle.jaxb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Address", propOrder = {
    "city",
    "state",
    "pincode"
})
public class Address {

    @XmlElement(name = "city", required = true)
    protected String city;
    @XmlElement(name = "state", required = true)
    protected String state;
    @XmlElement(name = "pincode", required = true)
    protected String pincode;
    
    public Address() {
    	System.out.println("Address Default Constructor");
    }
    
    public Address(String city, String state, String pincode) {
		super();
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public String getCity() {
        return city;
    }

    public void setCity(String value) {
        this.city = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String value) {
        this.state = value;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String value) {
        this.pincode = value;
    }
}