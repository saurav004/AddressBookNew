package com.AddressBook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class App {
	static Scanner scanner = new Scanner(System.in);
	static ArrayList<Person> listOfContacts = new ArrayList<Person>();
	static HashMap<String, AddressBook> mapOfAddressBooks = new HashMap<String, AddressBook>();

	public static void main(String[] args) throws IOException {
		System.out.println("WELCOME TO ADDRESS BOOK PROGRAM");
		mainMenu();
	}

	public static void displayAllAddressBooks() {
		File directoryPath = new File("C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook");
		File[] files = directoryPath.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});
		System.out.println("All available Address Books are listed below");
		for (File file : files) {

			String fileName = file.getName();
			fileName = fileName.replace(".txt", "");
			AddressBook addressBookObject = new AddressBook(fileName);
			mapOfAddressBooks.put(fileName, addressBookObject);
			System.out.println(fileName);
		}
	}

	public static void mainMenu() throws IOException {
		int end1 = 0;
		while (end1 == 0) {
			displayAllAddressBooks();
			String addressBookName = null;
			System.out.println("Select from the options below");
			System.out.println("1.\tOPEN Address Book");
			System.out.println("2.\tCREATE An Address Book");
			System.out.println("3.\tEXIT");
			int choice1 = scanner.nextInt();
			switch (choice1) {
			case 1:
				System.out.println("Enter Address Book Name You want to open");
				addressBookName = scanner.next();
				OpenAddressBook(addressBookName);
				break;
			case 2:
				System.out.println("Enter Address Book Name you want to create");
				addressBookName = scanner.next();
				createAddressBook(addressBookName);
				break;
			case 3:
				end1 = 1;
			}
		}
	}

	public static int openAddressBookMenu(AddressBook addressBookObject) throws IOException {
		int end = 0;
		System.out.println("select from the options given below");
		System.out.println("1.\tAdd a contact");
		System.out.println("2.\tEXIT");
		System.out.println("3.\tDelete a Contact");
		System.out.println("4.\tUpdate a Contact");
		int choice = scanner.nextInt();
		switch (choice) {
		case 1:
			addressBookObject.addAContact();
			break;
		case 2:
			end = 1;
			break;
		case 3:
			System.out.println("Enter the First Name of the contact of which u want to delete");
			String fName = scanner.next();
			addressBookObject.deleteAContact(fName);
			break;
		case 4:
			addressBookObject.updateAContact();
			break;
		default:
			System.out.println("Invalid Choice!!!");
		}
		return end;
	}

	public static void OpenAddressBook(String AddressBookName) throws IOException {
		AddressBook addressBookObject = mapOfAddressBooks.get(AddressBookName);
		System.out.println(addressBookObject);
		int end = 0;
		while (end == 0) {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(
						"C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\" + AddressBookName + ".txt"));
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
			end = openAddressBookMenu(addressBookObject);
		}

	}

	public static void createAddressBook(String addressBookName) {
		AddressBook addressBookObject = new AddressBook(addressBookName);
		mapOfAddressBooks.put(addressBookName, addressBookObject);
		try {
			File myObj = new File("C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\"
					+ addressBookObject.addressBookName + ".txt");
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
			FileWriter myWriter = new FileWriter("C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\"
					+ addressBookObject.addressBookName + ".txt");
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
