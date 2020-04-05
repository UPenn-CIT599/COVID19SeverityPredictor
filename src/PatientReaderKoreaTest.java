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
	void testReadCSV()
	{
		PatientReaderKorea.readCSV();
		String expected = "deceased" + "50s" + "male";
		assertEquals(expected, PatientReaderKorea.getPatients().get(504).getState() + PatientReaderKorea.getPatients().get(2483).getAgeAsDecade() + PatientReaderKorea.getPatients().get(2530).getGender());
	}
}