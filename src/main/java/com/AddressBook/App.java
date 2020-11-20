package com.AddressBook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class App {
	public static void main(String[] args) throws IOException {
		System.out.println("WELCOME TO ADDRESS BOOK PROGRAM");
		createAddressBook();
		int end = 0;
		Scanner sc = new Scanner(System.in);
		while (end == 0) {
			OpenAddressBook();
			System.out.println("select from the options given below");
			System.out.println("1.\tAdd a contact");
			System.out.println("2.\tEXIT");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				addAContact();
				break;
			case 2:
				break;
			}

		}
	}

	private static void addAContact() {
		Scanner sc = new Scanner(System.in);
		String fName = null, lName = null, emailId = null, Address = null, city = null, state = null, zip = null,
				phoneNumber = null;
		System.out.println("\n\tEnter your First Name");
		fName = sc.next();
		System.out.println("\n\tEnter your last Name");
		lName = sc.next();
		System.out.println("\n\tEnter your email id");
		emailId = sc.next();
		System.out.println("\n\tEnter your Address");
		Address = sc.next();
		System.out.println("\n\tEnter your city");
		city = sc.next();
		System.out.println("\n\tEnter your state");
		state = sc.next();
		System.out.println("\n\tEnter your zip");
		zip = sc.next();
		Person person = new Person(fName, lName, emailId, Address, city, state, zip, phoneNumber);
		try {
			FileWriter myWriter = new FileWriter(
					"C:\\Users\\Saurabh\\eclipse-workspace\\AddressBookJava\\src\\com\\Bridgelabz\\AddressBook.txt",
					true);
			myWriter.write("\n" + person.fName + "\t" + person.lName + "\t" + person.emailId + "\t" + person.Address
					+ "\t" + person.city + "\t" + person.state + "\t" + person.zip + "\t" + person.phoneNumber + "\t");
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public static void OpenAddressBook() throws IOException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(
					"C:\\Users\\Saurabh\\eclipse-workspace\\AddressBookJava\\src\\com\\Bridgelabz\\AddressBook.txt"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println("\t\t" + line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null)
				reader.close();
		}
	}

	public static void createAddressBook() {
		try {
			File myObj = new File(
					"C:\\Users\\Saurabh\\eclipse-workspace\\AddressBookJava\\src\\com\\Bridgelabz\\AddressBook.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
				System.out.println(myObj);
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		try {
			FileWriter myWriter = new FileWriter(
					"C:\\Users\\Saurabh\\eclipse-workspace\\AddressBookJava\\src\\com\\Bridgelabz\\AddressBook.txt");
			myWriter.write("First Name\tLast Name\tEmail\tAddress\tCity\tState\tZip\tPhone Number\t");
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

}
