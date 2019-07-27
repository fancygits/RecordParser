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
		String[] params = {lastName, firstName, gender, favoriteColor, dateOfBirth};
		this.checkParams(params);
		this.lastName = lastName;
		this.firstName = firstName;
		this.gender = this.getFilteredGender(gender);
		this.favoriteColor = favoriteColor;
		this.dateOfBirth = this.parseDateString(dateOfBirth);
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
		this.checkParams(details);
		this.lastName = details[0].trim();
		this.firstName = details[1].trim();
		this.gender = this.getFilteredGender(details[2].trim());
		this.favoriteColor = details[3].trim();
		this.dateOfBirth = this.parseDateString(details[4].trim());
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
	
	/**
	 * Checks the parameters needed to construct a person
	 * @param params	A String[] of exactly 5 parameters
	 * 					lastName, firstName, gender, favoriteColor, dateOfBirth
	 */
	private void checkParams(String[] params) {
		if (params.length != 5) {
			throw new IllegalArgumentException("ERROR: That line of data cannot create a new Person");
		}
		for (String item: params) {
			if (item == null || "".equals(item)) {
				throw new IllegalArgumentException("ERROR: Missing required parameter.");
			}
		}
		Date birthdate =  this.parseDateString(params[4]);
		if (birthdate == null) {
			throw new IllegalArgumentException("That isn't a valid date format.");
		}
	}
	
	/**
	 * Filters given gender into male, female, or other
	 * @param gender	A gender
	 * @return			male, female, or other
	 */
	private String getFilteredGender(String gender) {
		String filtered = "";
		if (gender.toLowerCase().startsWith("m")) {
			filtered = "male";
		} else if (gender.toLowerCase().startsWith("f")) {
			filtered = "female";
		} else {
			filtered = "other";
		}
		return filtered;
	}
}
