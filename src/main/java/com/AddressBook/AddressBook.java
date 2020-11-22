package com.AddressBook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class AddressBook {
	static Scanner scanner = new Scanner(System.in);
	static HashMap<String, Person> mapOfContacts = new HashMap<String, Person>();
	String addressBookName;

	public AddressBook(String addressBookName) {
		this.addressBookName = addressBookName;
	}

	public void updateAContact() throws IOException {
		Person newPerson = new Person();
		System.out.println("Enter the First Name of the person whose contact you want to update");
		String fName = scanner.next();
		newPerson = this.searchARecord(fName);
		newPerson = this.switchCaseForUpdateMethod(newPerson);
		deleteAContact(fName);
		String path = "C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\" + this.addressBookName + ".txt";
		this.writeToTheFile(path, newPerson);
	}

	public Person switchCaseForUpdateMethod(Person newPerson) {
		System.out.println("What do u want to update");
		System.out.println(
				"\t1.\tFirst Name \t2.\tlast Name \t3.\temail \t4.\tAddress \n \t5.\tcity \t6.\tState \t7.\tzip \t8.\tPhone Number");
		int choice1 = scanner.nextInt();
		switch (choice1) {
		case 1:
			System.out.println("Enter first Name");
			newPerson.setFirstName(scanner.next());
			break;
		case 2:
			System.out.println("Enter last Name");
			newPerson.setLastName(scanner.next());
			break;
		case 3:
			System.out.println("Enter email Address");
			newPerson.setEmailId(scanner.next());
			break;
		case 4:
			System.out.println("Enter your Address");
			newPerson.setAddress(scanner.next());
			break;
		case 5:
			System.out.println("Enter your city Name");
			newPerson.setCity(scanner.next());
			break;
		case 6:
			System.out.println("Enter your state");
			newPerson.setState(scanner.next());
			break;
		case 7:
			System.out.println("Enter your zip");
			newPerson.setZip(scanner.next());
			break;
		case 8:
			System.out.println("Enter your phone Number");
			newPerson.setPhoneNumber(scanner.next());
			break;
		default:
			System.out.println("Incorrect Choice");
		}
		return newPerson;
	}

	public Person searchARecord(String fName) {
		Person person = new Person();
		File file = new File("C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\" + this.addressBookName + ".txt");
		String line = null;
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				int index = line.indexOf(fName);
				if (index != -1) {
					System.out.println(line);
				}
			}
			scan.close();
		} catch (Exception e) {
			System.out.println("Error occured while processing the file");
			e.printStackTrace();
		}
		String[] recordLine = line.trim().split("\\s+");
		person.setFirstName(recordLine[0]);
		person.setLastName(recordLine[1]);
		person.setEmailId(recordLine[2]);
		person.setAddress(recordLine[3]);
		person.setCity(recordLine[4]);
		person.setState(recordLine[5]);
		person.setZip(recordLine[6]);
		person.setPhoneNumber(recordLine[7]);
		return person;
	}

	public void deleteAContact(String fName) throws IOException {
		mapOfContacts.remove(fName);
		File file = new File("C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\" + this.addressBookName + ".txt");
		Scanner scan = null;
		FileWriter myWriter = null;
		try {
			myWriter = new FileWriter("C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\Output.txt", true);
			scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				int index = line.indexOf(fName);
				if (index != -1) {
				} else {
					myWriter.write("\n" + line);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			myWriter.close();
			scan.close();
		}
		if (file.delete()) {
			System.out.println("Deleted the file: " + file.getName());
		} else {
			System.out.println("Failed to delete the file.");
		}
		File oldName = new File("C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\Output.txt");
		if (oldName.renameTo(file))
			System.out.println("Renamed successfully");
		else
			System.out.println("Error");
	}

	public void addAContact() throws IOException {

		Person person = new Person();
		System.out.println("\n\tEnter your First Name");
		person.setFirstName(scanner.next());
		System.out.println("\n\tEnter your last Name");
		person.setLastName(scanner.next());
		System.out.println("\n\tEnter your email id");
		person.setEmailId(scanner.next());
		System.out.println("\n\tEnter your Address");
		person.setAddress(scanner.next());
		System.out.println("\n\tEnter your city");
		person.setCity(scanner.next());
		System.out.println("\n\tEnter your state");
		person.setState(scanner.next());
		System.out.println("\n\tEnter your zip");
		person.setZip(scanner.next());
		System.out.println("\n\tEnter your Phone Number");
		person.setPhoneNumber(scanner.next());
		mapOfContacts.put(person.getFirstName(), person);
		String path = "C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\" + this.addressBookName + ".txt";
		writeToTheFile(path, person);

	}

	public void writeToTheFile(String path, Person person) throws IOException {
		String data = "\n" + person.getFirstName() + "\t" + person.getLastName() + "\t" + person.getEmailId() + "\t"
				+ person.getAddress() + "\t" + person.getCity() + "\t" + person.getState() + "\t" + person.getZip()
				+ "\t" + person.getPhoneNumber() + "\t";
		try {
			FileWriter myWriter = new FileWriter(path, true);
			myWriter.write(data);
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void finalize() throws Throwable {
		scanner.close();

	}
}
