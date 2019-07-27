package me.jlukejohnson.gr.controller;

import static spark.Spark.get;

import java.io.File;
import java.io.FileNotFoundException;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;

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
			System.out.printf("You ran the jar with arguments: %s, %s, %s, %s\n", 
					commandLineArgs.getSortMethod(), 
					commandLineArgs.getSingleRecord(),
					commandLineArgs.isServer(),
					commandLineArgs.isHelp());
			System.out.print("Files: ");
			for (String file: commandLineArgs.getFiles()) {
				System.out.print(file);
			}
			System.out.println();
			if (commandLineArgs.isServer()) {
				startServer();
			}
		} catch (FileNotFoundException fnfe) {
			// TODO Auto-generated catch block
			fnfe.printStackTrace();
		}
	}
	
	public static void startServer() throws FileNotFoundException {
		System.out.println("Setting up some practice files");
		parser.importRecords(new File("src/main/resources/people-with-commas.txt"));
		parser.importRecords(new File("src/main/resources/people-with-pipes.txt"));
		parser.importRecords(new File("src/main/resources/people-with-spaces.txt"));
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("+++++++      Starting server on port 4567      +++++++");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		get("/", (req, res) -> renderHelpPage());
		get("records/gender", (req, res) -> {
			res.type("application/json");
			return new Gson().toJson(
					new StandardResponse(StatusResponse.SUCCESS, new Gson()
							.toJsonTree(parser.getPeople())));
		});
		

	}
	
	
	private static String renderHelpPage() {
		String html = ""
				+ "<html>"
				+   "<body>"
				+     "<h1>Welcome to the Record Parser API</h1>"
				+ 	  "<h2>Getting Records</h2>"
				+ "<p>After importing records, you can get sorted records using the following "
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

}
