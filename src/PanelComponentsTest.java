import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PanelComponentsTest {

	@Test
	void testCheckInput() {
		double upper = 100.0;
		double lower = 10.0;
		double num1 = 50.0;
		double num2 = 110.0;
		assertEquals(PanelComponents.checkInput(num1, upper, lower), true);
		assertEquals(PanelComponents.checkInput(num2, upper, lower), false);
	}

	@Test
	void testIsNumeric() {
		String str1 = "100";
		String str2 = "100.1";
		String str3 = "nonNumber";
		String str4 = " ";
		
		assertEquals(PanelComponents.isNumeric(str1), true);
		assertEquals(PanelComponents.isNumeric(str2), true);
		assertEquals(PanelComponents.isNumeric(str3), false);
		assertEquals(PanelComponents.isNumeric(str4), false);
	}

}
