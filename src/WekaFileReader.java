import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * File reader Class that reads in dataset
 * @author 1Air
 *
 */
public class WekaFileReader {
    
    /**
     * Reads data from file
     * @param filename
     * @return
     */
    public static BufferedReader readDataFile(String filename) {
	BufferedReader inputReader = null;

	try {
		inputReader = new BufferedReader(new FileReader(filename));
	} catch (FileNotFoundException ex) {
		System.err.println("File not found: " + filename);
	}

	return inputReader;
}

}
