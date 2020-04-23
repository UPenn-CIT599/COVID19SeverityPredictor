//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.Test;
//
//import junit.framework.Assert;
///**
// * Companion test class for the PatientReaderKorea class
// * @author cbusc

//  4/23 NEED TO UPDATE AFTER INCEPTION OF PATIENTGENERATOR CLASS
// *
// */
//class PatientReaderKoreaTest {
//
//	@Test
//	void testGenderInput()
//	{
//		PatientGenerator.generatePatients();
//		String expected = "female";
//		assertEquals(expected, PatientGenerator.getPatients().get(1000).getGender());
//	}
//	
//	@Test
//	void testAgeInput()
//	{
//		PatientGenerator.generatePatients();
//		int expected = 40;
//		assertEquals(expected, PatientGenerator.getPatients().get(2246).getAge());
//	}
//	
//	@Test
//	void testAgeAsDecadeInput()
//	{
//		PatientGenerator.generatePatients();
//		String expected = "20s";
//		assertEquals(expected, PatientGenerator.getPatients().get(2433).getAgeAsDecade());
//	}
//	
//	@Test
//	void testComorbidInput()
//	{
//		PatientGenerator.generatePatients();
//		Boolean expected = true;
//		assertEquals(expected, PatientGenerator.getPatients().get(851).isComorbid());
//	}
//
//	@Test
//	void testStateInput()
//	{
//		PatientGenerator.generatePatients();
//		String expected = "deceased";
//		assertEquals(expected, PatientGenerator.getPatients().get(504).getOutcome());
//	}
//
//}
//
