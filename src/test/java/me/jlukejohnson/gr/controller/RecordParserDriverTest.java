package me.jlukejohnson.gr.controller;

import org.junit.jupiter.api.Test;

import spark.Spark;

/**
 * 
 * @author James Luke Johnson
 * @version 2019.07.27
 */
class RecordParserDriverTest {

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
		Spark.stop();
	}

}
