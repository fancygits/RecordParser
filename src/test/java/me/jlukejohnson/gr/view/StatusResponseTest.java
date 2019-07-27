package me.jlukejohnson.gr.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the StatusReponse class
 * 
 * @author James Luke Johnson
 * @version 2019.07.27
 */
class StatusResponseTest {

	/**
	 * Test method for
	 * {@link me.jlukejohnson.gr.view.StatusResponse#StatusResponse(java.lang.String)}.
	 */
	@Test
	void testStatusResponse() {
		assertEquals("Success", StatusResponse.SUCCESS.getStatus());
	}

	/**
	 * Test method for {@link me.jlukejohnson.gr.view.StatusResponse#getStatus()}.
	 */
	@Test
	void testGetStatus() {
		assertEquals("Error", StatusResponse.ERROR.getStatus());
	}

}
