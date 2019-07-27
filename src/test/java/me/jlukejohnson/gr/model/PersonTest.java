package me.jlukejohnson.gr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.jlukejohnson.gr.model.Person;


/**
 * Tests the Person constructor
 * @author James Luke Johnson
 * @version 2019.07.26
 */
class PersonTest {
		
	/**
	 * Test to confirm that different date formats are allowed in the constructor
	 */
	@Test
	void testPersonConstructorShouldAllowDifferentDateFormats() {
		Person first = new Person("Cruise", "Tom", "male", "dodgerblue", "1962-07-03");
		assertEquals("Cruise         Tom            male      dodgerblue  7/3/1962  ", first.getDetails());
		Person second = new Person("Cruise", "Tom", "male", "dodgerblue", "1962.07.03");
		assertEquals("Cruise         Tom            male      dodgerblue  7/3/1962  ", second.getDetails());
		Person third = new Person("Cruise", "Tom", "male", "dodgerblue", "07/03/1962");
		assertEquals("Cruise         Tom            male      dodgerblue  7/3/1962  ", third.getDetails());
		Person fourth = new Person("Cruise", "Tom", "male", "dodgerblue", "62-07-03");
		assertEquals("Cruise         Tom            male      dodgerblue  7/3/1962  ", fourth.getDetails());
	}
	
	/**
	 * Test to confirm that genders starting with m default to male, f to female, and everything else to other
	 */
	@Test
	void testPersonConstructorShouldAllowThreeGenders() {
		Person male = new Person("Cruise", "Tom", "MalE", "green", "1962-07-03");
		assertEquals("Cruise         Tom            male      green       7/3/1962  ", male.getDetails());
		Person m1 = new Person("Cruise", "Tom", "MalE", "green", "1962-07-03");
		assertEquals("Cruise         Tom            male      green       7/3/1962  ", m1.getDetails());
		Person female = new Person("Holmes", "Katie", "FEMALE", "red", "1978-12-18");
		assertEquals("Holmes         Katie          female    red         12/18/1978", female.getDetails());
		Person f1 = new Person("Holmes", "Katie", "f", "red", "1978-12-18");
		assertEquals("Holmes         Katie          female    red         12/18/1978", f1.getDetails());
		Person other = new Person("Izzard", "Eddie", "Transgender", "purple", "1962-02-07");
		assertEquals("Izzard         Eddie          other     purple      2/7/1962  ", other.getDetails());
		Person another = new Person("Izzard", "Eddie", "gendernonbinary", "purple", "1962-02-07");
		assertEquals("Izzard         Eddie          other     purple      2/7/1962  ", another.getDetails());
	}
	
	/**
	 * Test to confirm that an exception is thrown when missing parameters
	 * Test method for {@link me.jlukejohnson.gr.me.jlukejohnson.gr.model.Person#Person(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date)}.
	 */
	@Test
	void testPersonConstructorShouldNotAllowEmptyOrNullParameters() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Person("", "Ghost", "male", "red", "8/1/1972");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Person("Rider", "", "male", "red", "8/1/1972");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Person("Rider", "Ghost", null, "red", "8/1/1972");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Person("Rider", "Ghost", "male", "", "8/1/1972");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Person("Rider", "Ghost", "male", "red", null);
		});
	}
	
	/**
	 * Test to confirm that invalid date formats will return a null date and throw an IllegalArgumentException
	 */
	@Test
	void testPersonConstructorShouldNotAllowInvalidDateFormats() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Person("Rider", "Ghost", "male", "red", "-666");
		});
	}
}
