package me.jlukejohnson.gr.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Reads files in 3 formats, sorts the data, and stores it.
 * 
 * @author James Luke Johnson
 * @version 2019.07.26
 */
public class RecordParser {
	private List<Person> people;

	/**
	 * Constructs a FileImporter object and initializes the people list.
	 */
	public RecordParser() {
		this.people = new ArrayList<Person>();
	}

	/**
	 * Takes a file, creates a Person for each line, and adds it to the people list.
	 * 
	 * @param sourceFile The file to import people from
	 * @return True if successful, false if any lines did not read
	 * @throws FileNotFoundException If sourceFile can't be found
	 */
	public boolean importRecords(File sourceFile) throws FileNotFoundException {
		Scanner inFile = null;
		boolean noErrors = true;
		inFile = new Scanner(sourceFile);
		int line = 1;
		while (inFile.hasNextLine()) {
			try {
				String nextLine = inFile.nextLine();
				if (nextLine.trim().length() > 0) {
					this.people.add(new Person(nextLine));
					line++;
				}
			} catch (IllegalArgumentException iae) {
				System.err.println("There was an error on line " + line + ": " + iae.getMessage());
				noErrors = false;
			}
		}
		inFile.close();
		return noErrors;
	}
	
	/**
	 * Imports a single record  to the parser
	 * 
	 * @param record	A delimited string record
	 * @return			True if successful
	 */
	public boolean importSingleRecord(String record) {
		try {
			this.people.add(new Person(record));
			return true;
		} catch (IllegalArgumentException iae) {
			System.err.println(iae.getMessage());
			return false;
		}
	}
	
	/**
	 * Imports a Person to people
	 * 
	 * @param person	The Person to add
	 * @return			True if successful
	 */
	public boolean importPerson(Person person) {
		this.people.add(person);
		return true;
	}

	/**
	 * Getter for people
	 * 
	 * @return List<Person> people
	 */
	public List<Person> getPeople() {
		return this.people;
	}
}
