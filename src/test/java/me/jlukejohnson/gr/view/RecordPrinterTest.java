package me.jlukejohnson.gr.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.jlukejohnson.gr.model.Person;

/**
 * Tests the RecordPrinter
 * 
 * @author James Luke Johnson
 * @version 2019.07.27
 */
class RecordPrinterTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private ArrayList<Person> people;

	/**
	 * Redirects System.out to a PrintStream
	 * 
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		this.people = new ArrayList<Person>();
		this.people.add(new Person("Kindall Rici Female Aquamarine 2004-01-14"));
		this.people.add(new Person("Costley, Dukie, Male, Green, 1947-07-13"));
		this.people.add(new Person("Bettley | Abbe | Female | Purple | 1930-01-01"));
	}

	/**
	 * Returns System.out
	 * 
	 * @throws IOException
	 * 
	 * @throws java.lang.Exception
	 */
	@AfterEach
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	/**
	 * 
	 * Test to confirm that printRecords(gender) sorts and prints correctly
	 * 
	 * Test method for
	 * {@link me.jlukejohnson.gr.view.RecordPrinter#printRecords(java.lang.String)}.
	 * 
	 * @throws IOException
	 */
	@Test
	void testPrintRecordsSortsByGender() {
		RecordPrinter printer = new RecordPrinter(this.people);
		printer.printRecords("gender");
		assertEquals("Last Name      First Name     Gender    Top Color   Birthdate\r\n"
				+ "-------------------------------------------------------------\r\n"
				+ "Bettley        Abbe           female    Purple      1/1/1930  \r\n"
				+ "Kindall        Rici           female    Aquamarine  1/14/2004 \r\n"
				+ "Costley        Dukie          male      Green       7/13/1947 \r\n", outContent.toString());
	}

	/**
	 * Test to confirm that printRecords(birthdate) sorts and prints correctly
	 */
	@Test
	void testPrintRecordsSortsByBirthDate() {
		RecordPrinter printer = new RecordPrinter(this.people);
		printer.printRecords("birthdate");
		assertEquals("Last Name      First Name     Gender    Top Color   Birthdate\r\n"
				+ "-------------------------------------------------------------\r\n"
				+ "Bettley        Abbe           female    Purple      1/1/1930  \r\n"
				+ "Costley        Dukie          male      Green       7/13/1947 \r\n"
				+ "Kindall        Rici           female    Aquamarine  1/14/2004 \r\n", outContent.toString());
	}
	
	/**
	 * Test to confirm that printRecords(lastname) sorts and prints correctly
	 */
	@Test
	void testPrintRecordsSortsByLastName() {
		RecordPrinter printer = new RecordPrinter(this.people);
		printer.printRecords("lastname");
		assertEquals("Last Name      First Name     Gender    Top Color   Birthdate\r\n" + 
				"-------------------------------------------------------------\r\n" + 
				"Kindall        Rici           female    Aquamarine  1/14/2004 \r\n" + 
				"Costley        Dukie          male      Green       7/13/1947 \r\n" + 
				"Bettley        Abbe           female    Purple      1/1/1930  \r\n", outContent.toString());
	}

}
