import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;
/**
 * Companion test class for the PatientReaderKorea class
 * @author cbusc
 *
 */
class PatientReaderKoreaTest {

	@Test
	void testGenderInput()
	{
		PatientReader.readCSV();
		String expected = "female";
		assertEquals(expected, PatientReader.getPatients().get(1000).getGender());
	}
	
	@Test
	void testAgeInput()
	{
		PatientReader.readCSV();
		int expected = 40;
		assertEquals(expected, PatientReader.getPatients().get(2246).getAge());
	}
	
	@Test
	void testAgeAsDecadeInput()
	{
		PatientReader.readCSV();
		String expected = "20s";
		assertEquals(expected, PatientReader.getPatients().get(2433).getAgeAsDecade());
	}
	
	@Test
	void testComorbidInput()
	{
		PatientReader.readCSV();
		Boolean expected = true;
		assertEquals(expected, PatientReader.getPatients().get(851).isComorbid());
	}

	@Test
	void testStateInput()
	{
		PatientReader.readCSV();
		String expected = "deceased";
		assertEquals(expected, PatientReader.getPatients().get(504).getOutcome());
	}

}

