import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClickResponseTest {

    @Test
    void testRecoveredResponse() throws Exception {
	double[] recoveredUser = {49.78699774, 1, 0, 1, 0, 0, 0, 4.547949847, 0.852495523, 174.1727584, 33.35398543, 277.718766, 3.545130117, 0.587936248, 544.3907973, 5.579234687, 0.1};
	double riskScore = ClickResponse.response(recoveredUser);
	assertEquals(riskScore, 0.09);
    }
    
    @Test
    void testDeceasedResponse() throws Exception {
	double[] deceasedUser = {64.97170575, 0, 0, 1, 1, 0, 0, 10.72312152, 0.652016378, 158.7812943, 30.6666677, 516.5667479, 81.30478587, 5.806466858, 1547.65957, 7.97997343, 0.346800831};
	double riskScore = ClickResponse.response(deceasedUser);
	assertEquals(riskScore, 0.97);
    }

}
