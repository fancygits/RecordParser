package me.jlukejohnson.gr.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author James Luke Johnson
 * @version 2019.07.27
 */
class RecordParserDriverTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link me.jlukejohnson.gr.controller.RecordParserDriver#main(java.lang.String[])}.
	 */
	@Test
	void testMain() {
		RecordParserDriver.main(new String[] { "--help" });

	}

	/**
	 * Test method for
	 * {@link me.jlukejohnson.gr.controller.RecordParserDriver#startServer()}.
	 */
	@Test
	void testStartServer() {
		RecordParserDriver.main(new String[] { "--server" });
	}

}
