package me.jlukejohnson.gr.view;

import com.google.gson.JsonElement;

/**
 * Defines a standard response from the REST api
 * 
 * Following tutorial at https://www.baeldung.com/spark-framework-rest-api
 * 
 * @author James Luke Johnson
 * @version 2019.07.27
 */
public class StandardResponse {
	private StatusResponse status;
	private String message;
	private JsonElement data;
	
	public StandardResponse(StatusResponse status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public StandardResponse(StatusResponse status, JsonElement data) {
		this.status = status;
		this.data = data;
	}
	
	public StatusResponse getStatus() {
		return this.status;
	}
	
	public void setStatus(StatusResponse status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonElement getData() {
        return data;
    }

    public void setData(JsonElement data) {
        this.data = data;
    }
}
