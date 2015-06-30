package index;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PhoneticTokens implements Serializable {

	private List<String> tokens;

	public PhoneticTokens() {
		tokens = new ArrayList<String>();
	}

	public void addToken(String token) {
		tokens.add(token);
	}

	public List<String> getTokens() {
		return tokens;
	}

	public String asCSV() {
		StringBuilder builder = new StringBuilder();
		for (String t : tokens)
			builder.append(t).append(',');
		return builder.substring(0, builder.length() - 1);
	}

}
