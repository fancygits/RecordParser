package me.jlukejohnson.gr.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Tests the CommandLineArgs class
 * 
 * @author James Luke Johnson
 * @version 2019.07.27
 */
class CommandLineArgsTest {
	private static CommandLineArgs args;
	
	@BeforeAll
	static void setUp() {
		args = new CommandLineArgs();
	}

	/**
	 * Test method for {@link me.jlukejohnson.gr.controller.CommandLineArgs#getSortMethod()}.
	 */
	@Test
	void testGetSortMethod() {
		assertNull(args.getSortMethod());
	}

	/**
	 * Test method for {@link me.jlukejohnson.gr.controller.CommandLineArgs#getSingleRecord()}.
	 */
	@Test
	void testGetSingleRecord() {
		assertNull(args.getSingleRecord());
	}

	/**
	 * Test method for {@link me.jlukejohnson.gr.controller.CommandLineArgs#isServer()}.
	 */
	@Test
	void testIsServer() {
		assertFalse(args.isServer());
	}

	/**
	 * Test method for {@link me.jlukejohnson.gr.controller.CommandLineArgs#getFiles()}.
	 */
	@Test
	void testGetFiles() {
		assertTrue(args.getFiles().isEmpty());
	}

	/**
	 * Test method for {@link me.jlukejohnson.gr.controller.CommandLineArgs#isHelp()}.
	 */
	@Test
	void testIsHelp() {
		assertFalse(args.isHelp());
	}

}
