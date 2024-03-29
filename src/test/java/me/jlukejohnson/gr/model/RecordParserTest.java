package me.jlukejohnson.gr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Tests the RecordParser class
 * 
 * @author James Luke Johnson
 * @version 2019.07.26
 */
class RecordParserTest {

	/**
	 * Test to confirm that importRecords can successfully create Person from a file
	 * @throws IOException 
	 */
	@Test
	void testImportRecordsShouldReadTheTempFileAndSave3Persons() throws IOException {
		File temp = File.createTempFile("temp", "tmp");
		FileWriter writer = new FileWriter(temp);
		writer.write("Costley, Dukie, Male, Green, 1947-07-13\n"
				+ "Bettley | Abbe | Female | Purple | 1930-01-01\n"
				+ "Kindall Rici Female Aquamarine 2004-01-14\n");
		writer.flush();
		writer.close();
		temp.deleteOnExit();
		RecordParser parser = new RecordParser();
		try {
			assertTrue(parser.importRecords(temp));
			assertEquals(3, parser.getPeople().size());
			assertEquals("Costley        Dukie          male      Green       7/13/1947 ", parser.getPeople().get(0).getDetails());
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}
	
	/**
	 * Test to confirm that importRecords will throw an exception for unfound files
	 */
	@Test
	void testImportRecordsShouldThrowExceptionOnInvalidFile() {
		RecordParser parser = new RecordParser();
		assertThrows(FileNotFoundException.class, () -> {
			parser.importRecords(new File("unknown.txt"));
		});
	}
	
	/**
	 * Tests that importRecords returns false on a failed import
	 * @throws IOException 
	 */
	@Test
	void testImportRecordsShouldReturnFalseIfALineHasTooManyWords() throws IOException {
		File badData = File.createTempFile("badData", "tmp");
		FileWriter writer = new FileWriter(badData);
		writer.write("This is a sentence and cannot be parsed to a person because it's too long.");
		writer.flush();
		writer.close();
		badData.deleteOnExit();
		RecordParser parser = new RecordParser();
		try {
			assertFalse(parser.importRecords(badData));
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}
	
	/**
	 * Test to confirm that a file without sufficient data will not create a person
	 * @throws IOException
	 */
	@Test
	void testImportRecordsShouldThrowIllegalArgumentExceptionWhenNotEnoughData() throws IOException {
		RecordParser parser = new RecordParser();
		File notEnoughData = File.createTempFile("notEnoughData", "tmp");
		FileWriter writer = new FileWriter(notEnoughData);
		writer.write("Cruise, Tom, Blue");
		writer.flush();
		writer.close();
		notEnoughData.deleteOnExit();
		assertFalse(parser.importRecords(notEnoughData));
	}
	
	/**
	 * Test to confirm that importSingleRow adds a person and returns true
	 */
	@Test
	void testImportSingleRecordShouldCreateAPersonAndReturnTrue() {
		RecordParser parser = new RecordParser();
		assertTrue(parser.importSingleRecord("Cruise | Tom | Male | Green | 1962-07-03"));
		assertEquals(1, parser.getPeople().size());
	}
	
	/**
	 * Test to confirm that importSingleRecord returns false when unsuccessful
	 */
	@Test
	void testImportSingleRowShouldReturnFalseIfUnsuccessful() {
		RecordParser parser = new RecordParser();
		assertFalse(parser.importSingleRecord("This is a row of bad data. There's too much and nothing useful!"));
	}

	/**
	 * Test to confirm that importPerson returns true when successful
	 */
	@Test
	void testImportPersonShouldReturnTrue() {
		RecordParser parser = new RecordParser();
		Person person = new Person("Cruise | Tom | Male | Green | 1962-07-03");
		assertTrue(parser.importPerson(person));
	}
	
	/**
	 * Test to confirm that exportRecords correctly saves records to a file
	 */
	@Test
	void testExportRecordsShouldExportAFile() {
		RecordParser parser = new RecordParser();
		File temp;
		try {
			temp = File.createTempFile("temp", "tmp");
			FileWriter writer = new FileWriter(temp);
			writer.write("Costley, Dukie, Male, Green, 1947-07-13\n"
					+ "Bettley | Abbe | Female | Purple | 1930-01-01\n"
					+ "Kindall Rici Female Aquamarine 2004-01-14\n");
			writer.flush();
			writer.close();
			temp.deleteOnExit();
			assertTrue(parser.importRecords(temp));
			assertEquals(3, parser.getPeople().size());
			assertEquals("Costley        Dukie          male      Green       7/13/1947 ", parser.getPeople().get(0).getDetails());
			File file = new File("test.txt");
			String fileName = parser.exportRecords("test.txt");
			assertEquals("test.txt", fileName);
			assertTrue(file.delete());
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
