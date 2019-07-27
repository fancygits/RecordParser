package me.jlukejohnson.gr.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A person with a name, gender, favorite color, and birth date
 * @author James Luke Johnson
 * @version 2019.07.26
 */
public class Person {
	private String lastName;
	private String firstName;
	private String gender;
	private String favoriteColor;
	private Date dateOfBirth;
	private String[] dateFormats = {"M/d/y", "y-M-d", "y.M.d"};
	
	/**
	 * Constructs a new Person
	 * @param lastName		Person's last name
	 * @param firstName		Person's first name
	 * @param gender		Person's gender (assuming only male & female for this project)
	 * @param favoriteColor	optional Person's favorite color
	 * @param dateOfBirth	Person's date of birth
	 */
	public Person(String lastName, String firstName, String gender, String favoriteColor, String dateOfBirth) {
		String[] requiredFields = {lastName, firstName, gender, favoriteColor, dateOfBirth};
		for (String item: requiredFields) {
			if (item == null || "".equals(item)) {
				throw new IllegalArgumentException(item + " is a required parameter.");
			}
		}
		this.dateOfBirth = this.parseDateString(dateOfBirth);
		if (this.dateOfBirth == null) {
			throw new IllegalArgumentException("That isn't a valid date format.");
		}
		this.lastName = lastName;
		this.firstName = firstName;
		if (gender.toLowerCase().startsWith("m")) {
			this.gender = "male";
		} else if (gender.toLowerCase().startsWith("f")) {
			this.gender = "female";
		} else {
			this.gender = "other";
		}
		this.favoriteColor = favoriteColor;
	}
	
	/**
	 * Constructs a new Person from a delimited string of data
	 * @param personData	A string delimited with commas, pipes, or spaces
	 */
	public Person(String personData) {
		String splitter = "";
		if (personData.contains("|")) {
			splitter = "\\|";
		} else if (personData.contains(",")) {
			splitter = ",";
		} else {
			splitter = " +";
		}
		String[] details = personData.split(splitter);
		if (details.length != 5) {
			throw new IllegalArgumentException("That line of data cannot create a new Person");
		}
		for (String detail: details) {
			if (detail == null || "".equals(detail)) {
				throw new IllegalArgumentException("Parameters must not be blank");
			}
		}
		this.lastName = details[0].trim();
		this.firstName = details[1].trim();
		this.gender = details[2].trim();
		this.favoriteColor = details[3].trim();
		this.dateOfBirth = this.parseDateString(details[4].trim());
		if (this.dateOfBirth == null) {
			throw new IllegalArgumentException("That isn't a valid date format.");
		}
	}

	/**
	 * Gets the details of the person
	 * @return	String of details
	 */
	public String getDetails() {
		return String.format(
				"%-15s%-15s%-10s%-12s%-10s", 
				this.lastName,
				this.firstName,
				this.gender,
				this.favoriteColor,
				this.getFormattedDate(this.dateOfBirth));
	}
	
	/* GETTERS */
	
	/**
	 * Gets the gender
	 * @return	gender
	 */
	public String getGender() {
		return this.gender;
	}
	
	/**
	 * Gets the last name
	 * @return	lastName
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * Gets the date of birth
	 * @return	dateOfBirth
	 */
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}
	
	/* PRIVATE METHODS */
	
	/**
	 * Parses a string and creates a new Date
	 * @param date	The date string to parse
	 * @return		The Date of the given string
	 */
	private Date parseDateString(String date) {
		for (String format: this.dateFormats) {
			try {
				return new SimpleDateFormat(format).parse(date);
			} catch (ParseException pe) { }
		}
		return null;
	}
	
	/**
	 * Formats a date in the M/D/YYYY format
	 * @param date	Date to format
	 * @return		String in the M/D/YYYY format
	 */
	private String getFormattedDate(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy");
		return df.format(date);
	}
}
