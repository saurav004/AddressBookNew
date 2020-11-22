package com.AddressBook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
	static Scanner scanner = new Scanner(System.in);
	static ArrayList<Person> listOfContacts = new ArrayList<Person>();

	public static void main(String[] args) throws IOException {
		System.out.println("WELCOME TO ADDRESS BOOK PROGRAM");
		createAddressBook();
		OpenAddressBook();
	}

	public static int openAddressBookMenu() throws IOException {
		int end = 0;
		System.out.println("select from the options given below");
		System.out.println("1.\tAdd a contact");
		System.out.println("2.\tEXIT");
		System.out.println("3.\tDelete a Contact");
		System.out.println("4.\tUpdate a Contact");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			addAContact();
			break;
		case 2:
			end = 1;
			break;
		case 3:
			System.out.println("Enter the First Name of the contact of which u want to delete");
			String fName = scanner.next();
			deleteAContact(fName);
			break;
		case 4:
			updateAContact();
			break;
		default:
			System.out.println("Invalid Choice!!!");
		}
		return end;
	}

	private static void updateAContact() throws IOException {
		Person newPerson = new Person();
		System.out.println("Enter the First Name of the person whose contact you want to update");
		String fName = scanner.next();
		newPerson = searchARecord(fName);
		newPerson = switchCaseForUpdateMethod(newPerson);
		deleteAContact(fName);
		String path = "C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\AddressBook.txt";
		writeToTheFile(path, newPerson);
	}

	public static Person switchCaseForUpdateMethod(Person newPerson) {
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

	private static Person searchARecord(String fName) {
		Person person = new Person();
		File file = new File("C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\AddressBook.txt");
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

	private static void deleteAContact(String fName) throws IOException {
		File file = new File("C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\AddressBook.txt");
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

	private static void addAContact() throws IOException {

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
		listOfContacts.add(person);
		String path = "C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\AddressBook.txt";
		writeToTheFile(path, person);

	}

	public static void writeToTheFile(String path, Person person) throws IOException {
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

	public static void OpenAddressBook() throws IOException {
		int end = 0;
		while (end == 0) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(
						new FileReader("C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\AddressBook.txt"));
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
			end = openAddressBookMenu();
		}

	}

	public static void createAddressBook() {
		try {
			File myObj = new File("C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\AddressBook.txt");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
				System.out.println(myObj);
			} else {
				System.out.println("File already exists.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileWriter myWriter = new FileWriter("C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\AddressBook.txt");
			myWriter.write("First Name\tLast Name\tEmail\tAddress\tCity\tState\tZip\tPhone Number\t");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	@Override
	protected void finalize() throws Throwable {
		scanner.close();

	}
}
