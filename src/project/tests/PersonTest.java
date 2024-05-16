package project.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import project.database.Person;

class PersonTest {
	
	private static Person pTest;
	private static String phonePatterns;
	
	
	@BeforeAll
	public static void testPerson() throws Exception
	{
		try {
			pTest = new Person();
			phonePatterns = "^(\\+\\d{1,3}( )?)?((\\(\\d{3,4}\\))|\\d{3,4})[- .]?\\d{0,3}[- .]?\\d{4}$" 
					+ "|^(\\+\\d{1,3}( )?)?(\\d{3,4}[ ]?){2}\\d{3}$" 
					+ "|^(\\+\\d{1,3}( )?)?(\\d{3,4}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
	}

	@Test
	void testGetPersonid() 
	{
		pTest.setPersonid(0);
		assertTrue(pTest.getPersonid() == 0);
		
	}

	@Test
	void testGetFirstname() 
	{
		pTest.setFirstname("Charlie");
		assertEquals(pTest.getFirstname(), "Charlie");
	}

	@Test
	void testGetLastname() 
	{
		pTest.setLastname("Brown");
		assertEquals(pTest.getLastname(), "Brown");
	}

	@Test
	void testGetEmail() 
	{
		pTest.setEmail("something@gmail.de");
		String mailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		assertTrue(pTest.getEmail().matches(mailRegex));
		
		pTest.setEmail("something.something@bildung.cbm-bremen.de");
		assertTrue(pTest.getEmail().matches(mailRegex));
	}

	@Test
	void testGetPhone() 
	{
		
		pTest.setPhone("+49 1234 5258");
		assertTrue(pTest.getPhone().matches(phonePatterns));
		
	}

	@Test
	void testGetFax() {
		pTest.setFax("+49 123 525897");
		assertTrue(pTest.getFax().matches(phonePatterns));
	}

	@Test
	void testGetSex() {
		pTest.setSex("Male");
		assertTrue(pTest.getSex() == "Male");
	}

	@Test
	void testGetUsername() {
		pTest.setUsername("pinkUnicorn");
		assertTrue(pTest.getUsername() == "pinkUnicorn");
	}

	@Test
	void testGetPassword() {
		pTest.setPassword("");
		assertTrue(pTest.getPassword() == "");
	}
	@AfterAll
	public static void teardown() throws Exception 
	{
		pTest = null;		
	}

}
