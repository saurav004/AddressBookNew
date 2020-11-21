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
			System.out.println("3.\tDelete a Contact");
			System.out.println("4.\tUpdate a Contact");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				addAContact();
				break;
			case 2:
				end = 1;
				break;
			case 3:
				System.out.println("Enter the Name the contact of which u want to delete");
				String fName = sc.next();
				deleteAContact(fName);
				break;
			case 4:
				updateAContact();
				break;
			default:
				System.out.println("Invalid Choice!!!");
			}

		}
	}

	private static void updateAContact() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Name of which contact you want to delete");
		String fName = sc.next();
		String record = searchARecord(fName);
		System.out.println();
		String[] recordLine = record.trim().split("\\s+");
		System.out.println("What do u want to update");
		System.out.println(
				"\t1.\tFirst Name \t2.\tlast Name \t3.\temail \t4.\tAddress \n \t5.\tcity \t6.\tState \t7.\tzip \t8.\tPhone Number");

		Scanner scan = new Scanner(System.in);
		int choice1 = scan.nextInt();
		fName = recordLine[0];
		String lName = recordLine[1];
		String emailId = recordLine[2];
		String Address = recordLine[3];
		String city = recordLine[4];
		String state = recordLine[5];
		String zip = recordLine[6];
		String phoneNumber = recordLine[7];
		deleteAContact(fName);

		switch (choice1) {
		case 1:
			System.out.println("Enter first Name");
			fName = sc.next();
			break;
		case 2:
			System.out.println("Enter last Name");
			lName = sc.next();
			break;
		case 3:
			System.out.println("Enter email Address");
			emailId = sc.next();
			break;
		case 4:
			System.out.println("Enter your Address");
			Address = sc.next();
			break;
		case 5:
			System.out.println("Enter your city Name");
			city = sc.next();
			break;
		case 6:
			System.out.println("Enter your state");
			state = sc.next();
			break;
		case 7:
			System.out.println("Enter your zip");
			zip = sc.next();
			break;
		case 8:
			System.out.println("Enter your phone Number");
			phoneNumber = sc.next();
			break;
		default:
			System.out.println("Incorrect Choice");
		}
		try {
			FileWriter myWriter = new FileWriter("C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\AddressBook.txt",
					true);
			myWriter.write("\n" + fName + "\t" + lName + "\t" + emailId + "\t" + Address + "\t" + city + "\t" + state
					+ "\t" + zip + "\t" + phoneNumber + "\t");
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	private static String searchARecord(String fName) {

		File file = new File("C:\\Users\\Saurabh\\eclipse-workspace\\AddressBook\\AddressBook.txt");
		Scanner scanner = null;
		String line = null;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				int index = line.indexOf(fName);
				if (index != -1) {
					System.out.println(line);
				}
			}
		} catch (Exception e) {
			System.out.println("Error occured while processing the file");
			e.printStackTrace();
		}
		scanner.close();
		return line;
	}

	private static void deleteAContact(String fName) throws IOException {
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
