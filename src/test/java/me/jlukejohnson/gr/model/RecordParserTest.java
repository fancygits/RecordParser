package me.jlukejohnson.gr.model;

import static org.junit.jupiter.api.Assertions.*;

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
		RecordParser importer = new RecordParser();
		try {
			assertTrue(importer.importRecords(temp));
			assertEquals(3, importer.getPeople().size());
			assertEquals("Costley        Dukie          male      Green       7/13/1947 ", importer.getPeople().get(0).getDetails());
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		}
	}

}
