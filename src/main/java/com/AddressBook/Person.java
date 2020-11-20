package com.AddressBook;

public class Person {

	String fName = null, lName = null, emailId = null, Address = null, city = null, state = null, zip = null,
			phoneNumber = null;

	public Person(String fName, String lName, String emailId, String Address, String city, String state, String zip,
			String phoneNumber) {
		this.fName = fName;
		this.lName = lName;
		this.emailId = emailId;
		this.Address = Address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
	}
}
