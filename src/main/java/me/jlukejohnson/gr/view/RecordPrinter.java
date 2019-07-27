package me.jlukejohnson.gr.view;

import java.util.Comparator;
import java.util.List;

import me.jlukejohnson.gr.model.Person;

/**
 * Displays RecordParser records
 * 
 * @author James Luke Johnson
 * @version 2019.07.26
 */
public class RecordPrinter {
	private List<Person> people;

	public RecordPrinter(List<Person> people) {
		this.people = people;
	}

	/**
	 * Prints all the records to the console
	 */
	public void printRecords(String sortMethod) {
		this.sortData(sortMethod);
		System.out.println("Last Name      First Name     Gender    Top Color   Birthdate");
		System.out.println("-------------------------------------------------------------");
		for (Person person : this.people) {
			System.out.println(person.getDetails());
		}
	}
	
	
	public String getJSONRecords(String sortMethod) {
		this.sortData(sortMethod);
		return "";
	}

	/**
	 * Sorts the data before export
	 * 
	 * @param sortMethod The method to sort by
	 */
	private void sortData(String desiredSortMethod) {
		String sortMethod = this.parseArgument(desiredSortMethod);
		switch (sortMethod) {
		case "gender":
			this.sortByGender();
			break;
		case "lastname":
			this.sortByLastName();
			break;
		default:
			this.sortByDateOfBirth();
			break;
		}
	}

	/* SORTING METHODS */

	/**
	 * Sorts the people list by gender, then last names ascending
	 */
	private void sortByGender() {
		this.people.sort(Comparator.comparing(Person::getGender).thenComparing(Person::getLastName));
	}

	/**
	 * Sorts the people list by date of birth, ascending
	 */
	private void sortByDateOfBirth() {
		this.people.sort(Comparator.comparing(Person::getDateOfBirth));
	}

	/**
	 * Sorts the people list by last name, descending
	 */
	private void sortByLastName() {
		this.people.sort(Comparator.comparing(Person::getLastName).reversed());
	}

	/**
	 * Parses a string looking for the search term
	 * 
	 * @param arg The argument given
	 * @return The proper search term
	 */
	private String parseArgument(String arg) {
		String sortTerm = arg.toLowerCase();
		String result = "";
		if (sortTerm.contains("gender")) {
			result = "gender";
		} else if (sortTerm.contains("date")) {
			result = "birthdate";
		} else {
			result = "lastname";
		}
		return result;
	}
}
