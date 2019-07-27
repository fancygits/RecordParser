package me.jlukejohnson.gr.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import me.jlukejohnson.gr.model.Person;

/**
 * Tests the StandardResponse class
 * 
 * @author James Luke Johnson
 * @version 2019.07.27
 */
class StandardResponseTest {
	private static StandardResponse response1 = new StandardResponse(StatusResponse.SUCCESS, "Success message");
	private static StandardResponse response2 = new StandardResponse(StatusResponse.SUCCESS,
			new Gson().toJsonTree(new Person("Holmes", "Katie", "f", "red", "1978-12-18")));

	/**
	 * Test method for
	 * {@link me.jlukejohnson.gr.view.StandardResponse#StandardResponse(me.jlukejohnson.gr.view.StatusResponse, java.lang.String)}.
	 */
	@Test
	void testStandardResponseStatusResponseString() {
		assertEquals(StatusResponse.SUCCESS, response1.getStatus());
	}

	/**
	 * Test method for
	 * {@link me.jlukejohnson.gr.view.StandardResponse#StandardResponse(me.jlukejohnson.gr.view.StatusResponse, com.google.gson.JsonElement)}.
	 */
	@Test
	void testStandardResponseStatusResponseJsonElement() {
		assertEquals(new Gson().toJsonTree(new Person("Holmes", "Katie", "f", "red", "1978-12-18")),
				response2.getData());
	}

	/**
	 * Test method for
	 * {@link me.jlukejohnson.gr.view.StandardResponse#setStatus(me.jlukejohnson.gr.view.StatusResponse)}.
	 */
	@Test
	void testSetStatus() {
		assertEquals(StatusResponse.SUCCESS, response1.getStatus());
		response1.setStatus(StatusResponse.ERROR);
		assertEquals(StatusResponse.ERROR, response1.getStatus());
	}

	/**
	 * Test method for
	 * {@link me.jlukejohnson.gr.view.StandardResponse#setMessage(java.lang.String)}.
	 */
	@Test
	void testSetMessage() {
		assertEquals("Success message", response1.getMessage());
		response1.setMessage("This is a message");
		assertEquals("This is a message", response1.getMessage());
	}

	/**
	 * Test method for
	 * {@link me.jlukejohnson.gr.view.StandardResponse#setData(com.google.gson.JsonElement)}.
	 */
	@Test
	void testSetData() {
		assertEquals(new Gson().toJsonTree(new Person("Holmes", "Katie", "f", "red", "1978-12-18")),
				response2.getData());
		Person person = new Person("Cruise", "Tom", "male", "dodgerblue", "1962-07-03");
		response2.setData(new Gson().toJsonTree(person));
		assertEquals(new Gson().toJsonTree(new Person("Cruise", "Tom", "male", "dodgerblue", "1962-07-03")),
				response2.getData());
	}

}
