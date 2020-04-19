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
		PatientReaderKorea.readCSV();
		String expected = "female";
		assertEquals(expected, PatientReaderKorea.getPatients().get(1000).getGender());
	}
	
	@Test
	void testAgeInput()
	{
		PatientReaderKorea.readCSV();
		int expected = 40;
		assertEquals(expected, PatientReaderKorea.getPatients().get(2246).getAge());
	}
	
	@Test
	void testAgeAsDecadeInput()
	{
		PatientReaderKorea.readCSV();
		String expected = "20s";
		assertEquals(expected, PatientReaderKorea.getPatients().get(2433).getAgeAsDecade());
	}
	
	@Test
	void testComorbidInput()
	{
		PatientReaderKorea.readCSV();
		Boolean expected = true;
		assertEquals(expected, PatientReaderKorea.getPatients().get(851).getComorbid());
	}

	@Test
	void testStateInput()
	{
		PatientReaderKorea.readCSV();
		String expected = "deceased";
		assertEquals(expected, PatientReaderKorea.getPatients().get(504).getState());
	}

}

