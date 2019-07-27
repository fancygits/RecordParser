package me.jlukejohnson.gr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the Person constructor
 * 
 * @author James Luke Johnson
 * @version 2019.07.26
 */
class PersonTest {
	private Person test;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		this.test = new Person("Macy", "William H", "male", "blue", "1950-03-13");
	}

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
	 * Test to confirm that genders starting with m default to male, f to female,
	 * and everything else to other
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
	 * Test to confirm that an exception is thrown when missing parameters Test
	 * method for
	 * {@link me.jlukejohnson.gr.me.jlukejohnson.gr.model.Person#Person(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Date)}.
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
	 * Test to confirm that invalid date formats will return a null date and throw
	 * an IllegalArgumentException
	 */
	@Test
	void testPersonConstructorShouldNotAllowInvalidDateFormats() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Person("Rider", "Ghost", "male", "red", "-666");
		});
	}
	
	/**
	 * Test to confirm that a delimited string creates a Person
	 */
	@Test
	void testPersonConstructorWithADelimitedString() {
		Person tom = new Person("Cruise | Tom | Male | Green | 1962-07-03");
		assertEquals("Cruise         Tom            male      Green       7/3/1962  ", tom.getDetails());
		Person dukie = new Person("Costley, Dukie, Male, Green, 1947-07-13");
		assertEquals("Costley        Dukie          male      Green       7/13/1947 ", dukie.getDetails());
		Person rici = new Person("Kindall Rici Female Aquamarine 2004-01-14");
		assertEquals("Kindall        Rici           female    Aquamarine  1/14/2004 ", rici.getDetails());
	}
	
	/**
	 * Test to confirm that a string of bad data will not create a Person
	 */
	@Test
	void testPersonConstructorWithBadStringShouldThrowException() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Person("iera;ids 38 akero9z3u 4akjdfa348a ekadrq8w34lkasdf h3aiuher ca38 ;ahf ih34ioah dfap34a;kdfapw834 rsi");
		});
	}

	/**
	 * Test method for
	 * {@link me.jlukejohnson.gr.me.jlukejohnson.gr.model.Person#getDetails()}.
	 */
	@Test
	void testGetDetails() {
		assertEquals("Macy           William H      male      blue        3/13/1950 ", this.test.getDetails());
	}

	/**
	 * Test method for
	 * {@link me.jlukejohnson.gr.me.jlukejohnson.gr.model.Person#getGender()}.
	 */
	@Test
	void testGetGender() {
		assertEquals("male", this.test.getGender());
	}

	/**
	 * Test method for
	 * {@link me.jlukejohnson.gr.me.jlukejohnson.gr.model.Person#getLastName()}.
	 */
	@Test
	void testGetLastName() {
		assertEquals("Macy", this.test.getLastName());
	}

	/**
	 * Test method for
	 * {@link me.jlukejohnson.gr.me.jlukejohnson.gr.model.Person#getDateOfBirth()}.
	 */
	@Test
	void testGetDateOfBirth() {
		try {
			assertTrue(new SimpleDateFormat("y-M-d").parse("1950-3-13").equals(this.test.getDateOfBirth()));
		} catch (ParseException pe) {
			fail("Couldn't parse date");
		}
	}
}
