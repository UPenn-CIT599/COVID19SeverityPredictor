import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.Assert;

/**
 * JUnit test class for RiskFactorReader.
 * @author cbusc
 *
 */
class RiskFactorReaderTest {
	
	@Test
	void testGetMortalityRate()
	{
		RiskFactorReader.readCSV();
		//assertEquals(double expected, double actual, double delta)
		assertEquals(0.28, RiskFactorReader.getMortalityRate(), 0.02);
	}
	
	@Test
	void testGetRange() 
	{
		Assert.assertArrayEquals(new Double[]{26.5, 31.3}, RiskFactorReader.getRange("29.1 (26.5-31.3))"));
	}
	
	@Test
	void testGetFirstNum()
	{
		double num = RiskFactorReader.getFirstNum(
				"this is not a string it's a sandwich 1337.");
		assertEquals(1337.0, num);
	}

	@Test
	void testGetAbsoluteRiskTrue()
	{
		assertEquals(0.5, RiskFactorReader.getAbsoluteRiskTrue(4.0, 4.0));
	}
	
	
	@Test
	void testGetRiskFactorToRelativeRisk()
	{
		RiskFactorReader.readCSV();
		assertEquals(3.9, RiskFactorReader.getRiskfactorToRelativeRisk().get("Respiratory rate > 24"), 0.2);
	}
}
