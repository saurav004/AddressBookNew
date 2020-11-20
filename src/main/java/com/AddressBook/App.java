package com.AddressBook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			int choice=sc.nextInt();
			switch(choice) {
			case 1:addAContact();
			break;
			case 2:break;
			}
			
		}
	}

	private static void addAContact() {
		Scanner sc=new Scanner(System.in);
		String fName = null, lName = null, emailId = null, Address = null, city = null, state = null, zip = null,phoneNumber=null;
		fName = checkfName();
		lName = checklName();
		emailId = checkEmail();
		Address = checkAddress();
		city = checkCity();
		state = checkState();
		zip = checkZip();

		try {
			FileWriter myWriter = new FileWriter(
					"C:\\Users\\Saurabh\\eclipse-workspace\\AddressBookJava\\src\\com\\Bridgelabz\\AddressBook.txt",
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
	private static String checklName() {
		Scanner scan = new Scanner(System.in);
		Boolean correct = true;
		String patFname = "^[A-Z]{1}[a-z]{2,30}$";
		String FName = null;
		while (correct) {
			System.out.println("\n\tEnter your last Name");
			FName = scan.nextLine();
			System.out.println(FName);
			Pattern pat = Pattern.compile(patFname);
			Matcher Match = pat.matcher(FName);
			if (Match.matches()) {
				correct=false;
				break;

			}
		}
		return FName;
	}

	private static String checkZip() {
		Scanner scan = new Scanner(System.in);
		Boolean correct = true;
		String patZip = "^[1-9]{1}[0-9]{2}[ ]{0,1}[0-9]{3}$";
		String Zip = null;
		while (correct) {
			System.out.println("\n\tEnter your Zip");
			Zip = scan.nextLine();
			System.out.println(Zip);
			Pattern pat = Pattern.compile(patZip);
			Matcher Match = pat.matcher(Zip);
			if (Match.matches()) {
				correct=false;
				break;

			}
		}
		return Zip;
	}

	private static String checkState() {
		Scanner scan = new Scanner(System.in);
		Boolean correct = true;
		String patState = "^[A-Z]{1}[a-z]{2,50}$";
		String State = null;
		while (correct) {
			System.out.println("\n\tEnter your State");
			State = scan.nextLine();
			System.out.println(State);
			Pattern pat = Pattern.compile(patState);
			Matcher Match = pat.matcher(State);
			if (Match.matches()) {
				correct=false;
				break;

			}
		}
		return State;
	}

	private static String checkCity() {
		Scanner scan = new Scanner(System.in);
		Boolean correct = true;
		String patcity = "^[A-Z]{1}[a-z]{2,50}$";
		String City = null;
		while (correct) {
			System.out.println("\n\tEnter your City");
			City = scan.nextLine();
			System.out.println(City);
			Pattern pat = Pattern.compile(patcity);
			Matcher Match = pat.matcher(City);
			if (Match.matches()) {
				correct=false;
				break;

			}
		}
		return City;
	}

	private static String checkEmail() {
		Scanner scan = new Scanner(System.in);
		Boolean correct = true;
		String patEmail = "^[a-zA-Z][a-zA-Z0-9_\\-+]*[.]{0,1}[a-zA-Z0-9_\\-+]{1,}[@][a-zA-Z0-9]{1,}[.][a-zA-Z]{2,}[.]{0,}[a-zA-Z]*$";
		String Email = null;
		while (correct) {
			System.out.println("\n\tEnter your Email");
			Email = scan.nextLine();
			System.out.println(Email);
			Pattern pat = Pattern.compile(patEmail);
			Matcher Match = pat.matcher(Email);
			if (Match.matches()) {
				correct=false;
				break;

			}
		}
		return Email;
	}

	private static String checkAddress() {
		Scanner scan = new Scanner(System.in);
		Boolean correct = true;
		String patAddress = "^[A-Za-z0-9'\\.\\-\\s\\,]{10,200}$";
		String Address = null;
		while (correct) {
			System.out.println("\n\tEnter your Address");
			Address = scan.nextLine();
			System.out.println(Address);
			Pattern pat = Pattern.compile(patAddress);
			Matcher Match = pat.matcher(Address);
			if (Match.matches()) {
				correct=false;
				break;

			}
		}
		return Address;
	}

	private static String checkfName() {
		Scanner scan = new Scanner(System.in);
		Boolean correct = true;
		String patFname = "^[A-Z]{1}[a-z]{2,30}$";
		String FName = null;
		while (correct) {
			System.out.println("\n\tEnter your First Name");
			FName = scan.nextLine();
			System.out.println(FName);
			Pattern pat = Pattern.compile(patFname);
			Matcher Match = pat.matcher(FName);
			if (Match.matches()) {
				correct=false;
				break;

			}
		}
		return FName;
	}

	private static String checkPhone() {
		Scanner scan = new Scanner(System.in);
		Boolean correct = true;
		String patMobileNumber = "^[0-9]{10}";
		String Phone = null;
		while (correct) {
			System.out.println("\n\tEnter your Phone Number");
			Phone = scan.nextLine();
			System.out.println(Phone);
			Pattern pat = Pattern.compile(patMobileNumber);
			Matcher Match = pat.matcher(Phone);
			if (Match.matches()) {
				correct=false;
				break;

			}
		}
		return Phone;
	}
}
