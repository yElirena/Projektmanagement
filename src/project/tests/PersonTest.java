package project.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.Assert.*;
import project.Person;

class PersonTest {
	
	private Person pTest;
	
	@BeforeAll
	public void testPerson() throws Exception
	{
		Person pTest = new Person();		
	}

	@Test
	void testGetPersonid() {
		pTest.setPersonid(0);
		assertTrue(pTest.getPersonid() == 0);
		
	}

	@Test
	void testGetFirstname() {
		pTest.setFirstname("Sven");
		assertEquals(pTest.getFirstname(), "Sven");
	}

	@Test
	void testGetLastname() {
		pTest.setLastname("Lilienthal");
		assertEquals(pTest.getLastname(), "Lilienthal");
	}

	@Test
	void testGetEmail() {
		pTest.setEmail("something@gmail.com");
		String mailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";
		assertTrue(pTest.getEmail().matches(mailRegex));
	}

	@Test
	void testGetPhone() {
		
	}

	@Test
	void testGetFax() {
		fail("Not yet implemented");
	}

	@Test
	void testGetSex() {
		fail("Not yet implemented");
	}

	@Test
	void testGetUsername() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPassword() {
		fail("Not yet implemented");
	}

}
