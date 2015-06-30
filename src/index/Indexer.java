package index;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Indexer {

	private Corpus corpus;
	private PhoneticIndex pIndex;

	public Indexer(Corpus c) {
		corpus = c;
		pIndex = new PhoneticIndex();
	}

	public PhoneticIndex indexCorpus() throws IOException {
		String t = null;
		String code = null;
		while ((t = corpus.getNextWord()) != null) {
			code = new PhoneticCoder().phoneticCode(t);
			pIndex.addEntry(code, t);
		}
		return pIndex;
	}

	public static void main(String[] args) {
//		String dict = "D:\\Personal\\PhD\\Speech to text\\phonetic_dict\\unigrams.dict";
		String dict = "D:\\Personal\\PhD\\Speech to text\\phonetic_dict\\english-words\\words.txt";
//		String indexFile = "D:\\Personal\\PhD\\Speech to text\\phonetic_dict\\phonetic_index.ser";
		String indexText = "D:\\Personal\\PhD\\Speech to text\\phonetic_dict\\phonetic_index_words.txt";
		Corpus c = null;
		try {
			c = new Corpus(dict);
			Indexer indexer = new Indexer(c);
			PhoneticIndex index = indexer.indexCorpus();
			System.out.println("Created index with " + index.numEntries()
					+ " entries");
			index.writeToFile(indexText);

//			OutputStream bufferedStream = new BufferedOutputStream(
//					new FileOutputStream(indexFile));
//			ObjectOutput out = new ObjectOutputStream(bufferedStream);
//			out.writeObject(index);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
