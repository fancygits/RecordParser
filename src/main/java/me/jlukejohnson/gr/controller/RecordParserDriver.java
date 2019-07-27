package me.jlukejohnson.gr.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import me.jlukejohnson.gr.model.RecordParser;
import me.jlukejohnson.gr.view.RecordPrinter;

/**
 * The main application for RecordParser
 * 
 * @author James Luke Johnson
 * @version 2019.07.26
 */
public class RecordParserDriver {
	private static RecordParser parser = new RecordParser();
	private static RecordPrinter printer = new RecordPrinter(parser.getPeople());

	@Parameter(names = { "--sort", "-s" }, description = "Desired sort method")
	String desiredSortMethod;

	@Parameter(names = { "--record", "-r" }, description = "A single delimited record to import")
	String singleRecord;
	
	@Parameter(names = { "--server", "-S"}, description = "Starts the RESTful api server")
	private boolean server = false;
	
	@Parameter(description = "Files to import")
	private List<String> files = new ArrayList<>();
	
	/**
	 * The entry point to the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		final CommandLineArgs commandLineArgs = new CommandLineArgs();
		final JCommander jCommander = new JCommander(commandLineArgs);
		try {
			jCommander.parse(args);
		} catch (final ParameterException pe) {
			jCommander.usage();
			System.exit(1);
		}
		
		if (commandLineArgs.isHelp()) {
			jCommander.usage();
			return;
		}
		try {
			System.out.printf("You ran the jar with arguments: %s, %s\n", commandLineArgs.getSortMethod(), commandLineArgs.getSingleRecord());
			run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void run() throws IOException {
		System.out.println("You ran the app");

	}

}
