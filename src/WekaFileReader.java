import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class WekaFileReader {
    
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
