import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import weka.core.Instances;

class WekaPipelineTest {

    @Test
    void testReadData() throws Exception {
	Instances data = WekaPipeline.readData();
	assertEquals(data.size(), 10001);
    }

    @Test
    void testPipeline() throws Exception {
	Double[] scores = WekaPipeline.pipeline();
	assertEquals(scores[0], 100);
    }


    @Test
    void testPredictOnUserInput() throws Exception {
	double[] recoveredUser = {49.78699774, 1, 0, 1, 0, 0, 0, 4.547949847, 0.852495523, 174.1727584, 33.35398543, 277.718766, 3.545130117, 0.587936248, 544.3907973, 5.579234687, 0.1};
	Instances testData = WekaPipeline.userInputToInstance(recoveredUser);
	double risk_score = WekaPipeline.predictOnUserInput(testData);
	assertEquals(risk_score, 0.089, 0.001);
    }

    @Test
    void testUserInputToInstance() {
	double[] recoveredUser = {49.78699774, 1, 0, 1, 0, 0, 0, 4.547949847, 0.852495523, 174.1727584, 33.35398543, 277.718766, 3.545130117, 0.587936248, 544.3907973, 5.579234687, 0.1};
	Instances testData = WekaPipeline.userInputToInstance(recoveredUser);
	assertEquals(testData.firstInstance().numValues(), 18);
	assertEquals(testData.firstInstance().value(1), -1.0);
    }

}
