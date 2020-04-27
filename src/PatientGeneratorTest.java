import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PatientGeneratorTest {

	@Test
	void testRandomBetweenRange()
	{
		assertEquals(0.5, PatientGenerator.randomBetweenRange(0.0, 1.0), 1);
	}
	
	void testProbableBoolean()
	{
		assertEquals(true, PatientGenerator.probableBoolean(1.0));
	}

}
