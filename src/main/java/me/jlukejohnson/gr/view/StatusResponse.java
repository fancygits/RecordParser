package me.jlukejohnson.gr.view;

/**
 * 
 * @author James Luke Johnson
 * @version 2019.07.27
 */
public enum StatusResponse {
	SUCCESS ("Success"), ERROR ("Error");
	
	private String status;
	
	StatusResponse(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}
