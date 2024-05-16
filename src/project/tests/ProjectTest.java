package project.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import project.database.Project;

class ProjectTest {
	
	private static Project test;
	private static String datePattern;
	
	@BeforeAll
	public static void setUp() throws Exception 
	{
		test = new Project();
		datePattern = "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$" 
			      + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
			      + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$" 
			      + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
	}

	@Test
	void testGetProjectid() 
	{
		test.setProjectID(1);
		assertTrue(test.getProjectID() == 1);
	}

	@Test
	void testGetAcronym() 
	{
		test.setAcronym("ABC");
		assertTrue(test.getAcronym() == "ABC");
	}

	@Test
	void testGetTitle() {
		test.setTitle("Learn your ABC's");
		assertTrue(test.getTitle() == "Learn your ABC's");
	}

	@Test
	void testGetDescription() {
		test.setAcronym("We will learn our ABC's by singing the Alphabet song");
		assertTrue(test.getAcronym() == "We will learn our ABC's by singing the Alphabet song");
	}

	@Test
	void testGetStartdate() {
		test.setStartdate("2024-06-18");
		assertTrue(test.getStartdate().matches(datePattern));
	}

	@Test
	void testGetEnddate() {
		test.setEnddate("2024-06-18");
		assertTrue(test.getEnddate().matches(datePattern));
	}
	
	@AfterAll
	public static void tearDown() throws Exception 
	{
		test = null;
	}

}
