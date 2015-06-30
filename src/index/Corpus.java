package index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Corpus {

	private File dataFile;
	private BufferedReader reader;

	public Corpus(String filePath) throws FileNotFoundException {
		dataFile = new File(filePath);
		reader = new BufferedReader(new FileReader(dataFile));
	}

	public String getNextWord() throws IOException {
		return reader.readLine();
	}

	public void close() throws IOException {
		reader.close();
	}

}
