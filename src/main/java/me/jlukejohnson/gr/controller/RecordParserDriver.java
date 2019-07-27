package me.jlukejohnson.gr.controller;

import java.io.File;
import java.io.FileNotFoundException;

import me.jlukejohnson.gr.model.RecordParser;
import me.jlukejohnson.gr.view.RecordPrinter;

/**
 * The main application for RecordParser
 * 
 * @author James Luke Johnson
 * @version 2019.07.26
 */
public class RecordParserDriver {
	private static RecordParser parser;
	private static RecordPrinter printer;

	/**
	 * The entry point to the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		parser = new RecordParser();
		printer = new RecordPrinter(parser.getPeople());
		try {
			parser.importRecords(new File("src/main/resources/people-with-commas.txt"));
			parser.importRecords(new File("src/main/resources/people-with-pipes.txt"));
			parser.importRecords(new File("src/main/resources/people-with-spaces.txt"));
			printer.printRecords("birthdate");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
