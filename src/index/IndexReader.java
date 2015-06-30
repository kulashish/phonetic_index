package index;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IndexReader {

	private PhoneticIndex index;
	private BufferedReader reader;

	public IndexReader(String indexFile) throws IOException,
			ClassNotFoundException {
		index = new PhoneticIndex();
		reader = new BufferedReader(new FileReader(indexFile));
		// ObjectInput input = new ObjectInputStream(buffer);
		// index = (PhoneticIndex) input.readObject();
	}

	public PhoneticIndex loadIndex() throws IOException {
		String line = null;
		while ((line = reader.readLine()) != null) {
			String[] parts = line.split(":");
			String code = parts[0];
			String[] values = parts[1].split(",");
			index.addEntries(code, values);
		}
		return index;
	}

	public static void main(String[] args) {
		String indexFile = "D:\\Personal\\PhD\\Speech to text\\phonetic_dict\\phonetic_index.txt";
		try {
			IndexReader reader = new IndexReader(indexFile);
			PhoneticIndex index = reader.loadIndex();
			System.out.println(index.queryByToken("gears").asCSV());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void printIndex() {
		System.out.println("Number of entries in the index: "
				+ index.numEntries());
	}

}
