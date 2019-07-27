package me.jlukejohnson.gr.controller;

import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.Parameter;

/**
 * Arguments able to be added to the main application
 * 
 * @author James Luke Johnson
 * @version 2019.07.26
 */
public class CommandLineArgs {
	@Parameter(names = { "--sort", "-s" }, description = "Desired sort method")
	private String sortMethod;

	@Parameter(names = { "--record", "-r" }, description = "A single delimited record to import")
	private String singleRecord;
	
	@Parameter(names = { "--server", "-S"}, description = "Starts the RESTful api server")
	private boolean server = false;
	
	@Parameter(description = "Files to import")
	private List<String> files = new ArrayList<>();
	
	@Parameter(names = "--help", help = true, description = "Help/Usage menu")
    private boolean help;
	
	
	public String getSortMethod() {
		return this.sortMethod;
	}
	
	public String getSingleRecord() {
		return this.singleRecord;
	}
	
	public boolean isServer() {
		return this.server;
	}
	
	public List<String> getFiles() {
		return this.files;
	}
	
	public boolean isHelp() {
		return this.help;
	}
}
