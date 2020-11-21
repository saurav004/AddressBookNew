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
			System.out.println("3.\tDelete a contact");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				addAContact();
				break;
			case 2:
				break;
			case 3:
				deleteAContact();
				break;
			}

		}
	}

	private static void deleteAContact() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Name of which contact you want to delete");
		String fName = sc.next();
		File file = new File("C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\AddressBook.txt");
		Scanner scanner = null;
		FileWriter myWriter = null;
		try {
			myWriter = new FileWriter("C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\Output.txt", true);
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				int index = line.indexOf(fName);

				if (index != -1) {
				} else {
					myWriter.write("\n" + line);
				}
			}
			myWriter.close();

		} catch (Exception e) {
			System.out.println("Error occured while processing the file");
			e.printStackTrace();
		} finally {
			if (myWriter != null)
				myWriter.close();
			if (scanner != null)
				scanner.close();
		}
		if (file.delete()) {
			System.out.println("Deleted the file: " + file.getName());
		} else {
			System.out.println("Failed to delete the file.");
		}
		System.out.println(file);
		File oldName = new File("C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\Output.txt");
		System.out.println(oldName);

		if (oldName.renameTo(file))
			System.out.println("Renamed successfully");
		else
			System.out.println("Error");
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
			FileWriter myWriter = new FileWriter("C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\AddressBook.txt",
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
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		try {
			FileWriter myWriter = new FileWriter("C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\AddressBook.txt");
			myWriter.write("First Name\tLast Name\tEmail\tAddress\tCity\tState\tZip\tPhone Number\t");
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

}
