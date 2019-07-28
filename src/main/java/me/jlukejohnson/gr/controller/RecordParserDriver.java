package me.jlukejohnson.gr.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;

import me.jlukejohnson.gr.model.Person;
import me.jlukejohnson.gr.model.RecordParser;
import me.jlukejohnson.gr.view.RecordPrinter;
import me.jlukejohnson.gr.view.StandardResponse;
import me.jlukejohnson.gr.view.StatusResponse;

/**
 * The main application for RecordParser
 * 
 * @author James Luke Johnson
 * @version 2019.07.26
 */
public class RecordParserDriver {
	private static RecordParser parser = new RecordParser();
	private static RecordPrinter printer = new RecordPrinter(parser.getPeople());
	
	/**
	 * The entry point to the application
	 * 
	 * @param args	See CommandLineArgs for full options
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
		List<String> files = commandLineArgs.getFiles();
		if (!files.isEmpty()) {
			importFiles(files);
		}
		String record = commandLineArgs.getSingleRecord();
		if (record != null) {
			parser.importSingleRecord(record);
		}
		if (commandLineArgs.isServer()) {
			startServer();
		} else {
			if (parser.getPeople().isEmpty()) {
				jCommander.usage();
				return;
			} else {
				String sortMethod = commandLineArgs.getSortMethod();
				if (sortMethod == null) {
					sortMethod = "gender";
				}
				printer.printRecords(sortMethod);
			}
		}
	}
	
	/**
	 * Starts the SparkJava server with REST api endpoints
	 */
	public static void startServer() {
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+++++++      Starting server on port 4567      +++++++");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		get("/", (req, res) -> renderHelpPage());
		
		get("records/gender", (req, res) -> {
			res.type("application/json");
			return printer.getJSONRecords("gender");
		});
		
		get("records/birthdate", (req, res) -> {
			res.type("application/json");
			return printer.getJSONRecords("birthdate");
		});
		
		get("records/name", (req, res) -> {
			res.type("application/json");
			return printer.getJSONRecords("lastname");
		});
		
		post("/records", (req, res) -> {
			res.type("application/json");
			if (parser.importSingleRecord(req.body())) {
				return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, "Person added successfully"));
			} else {
				return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, "There was a problem with that record"));
			}
		});
	}
	
	/**
	 * Renders a Help page if root is called on server
	 * 
	 * @return	A String of HTML help page
	 */
	private static String renderHelpPage() {
		String html = ""
				+ "<html>"
				+   "<body>"
				+     "<h1>Welcome to the Record Parser API</h1>"
				+ 	  "<h2>Getting Records</h2>"
				+ 	  "<p>After importing records, you can get sorted records using the following "
				+ 	  "<strong>API endpoints: </strong></p>"
				+ 	  "<ul>"
				+ 	    "<li>/records/gender &mdash; Records sorted by gender, then last name, ascending</li>"
				+ 		"<li>/records/birthdate &mdash; Records sorted by date of birth, ascending</li>"
				+ 		"<li>/records/name &mdash; Records sorted by last name, descending</li>"
				+ 	  "</ul>"
				+ 	  "<p><hr></p>"
				+     "<h2>Posting Records</h2>"
				+ 	  "<p>You can post a single delimited record to /records.</p>"
				+ 	  "<strong>Examples: </strong>" 
				+ 	  "<ul>"
				+ 	    "<li>/records/\"Costley, Dukie, Male, Green, 1947-07-13\"</li>"
				+ 		"<li>/records/\"Bettley | Abbe | Female | Purple | 1930-01-01\"</li>" 
				+ 		"<li>/records/\"Kindall Rici Female Aquamarine 2004-01-14\"</li>" 
				+	  "</ul>"
				+   "</body>"
				+ "</html>";
		return html;
	}
	
	/**
	 * Imports each file given to the RecordParser
	 * 
	 * @param files	A list of files
	 */
	private static void importFiles(List<String> files) {
		for (String file: files) {
			try {
				parser.importRecords(new File(file));
			} catch (FileNotFoundException fnfe) {
				System.err.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.err.println("~~ " + fnfe.getMessage());
				System.err.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}
		}
	}

}
