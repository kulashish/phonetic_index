package index;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PhoneticIndex implements Serializable {

	private Map<String, PhoneticTokens> index;

	public PhoneticIndex() {
		index = new HashMap<String, PhoneticTokens>();
	}

	public int numEntries() {
		return index.size();
	}

	public void addEntry(String code, String token) {
		PhoneticTokens pTokens = index.get(code);
		if (pTokens == null) {
			pTokens = new PhoneticTokens();
			index.put(code, pTokens);
		}
		pTokens.addToken(token);
	}

	public void addEntries(String code, String[] tokens) {
		for (String t : tokens)
			addEntry(code, t);
	}

	public PhoneticTokens queryByCode(String code) {
		return index.get(code);
	}

	public PhoneticTokens queryByToken(String token) {
		return queryByCode(new PhoneticCoder().phoneticCode(token));
	}

	public void writeToFile(String indexText) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(indexText));
		Set<Entry<String, PhoneticTokens>> entries = index.entrySet();

		for (Entry<String, PhoneticTokens> e : entries) {
			StringBuilder builder = new StringBuilder();
			builder.append(e.getKey()).append(':');
			builder.append(e.getValue().asCSV());
			writer.write(builder.toString());
			writer.newLine();
		}
		writer.flush();
		writer.close();
	}
}
