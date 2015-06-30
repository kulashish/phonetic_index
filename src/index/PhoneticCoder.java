package index;

import org.apache.commons.codec.language.DoubleMetaphone;

public class PhoneticCoder {

	private static DoubleMetaphone dMeta;

	public PhoneticCoder() {
		if (null == dMeta)
			dMeta = new DoubleMetaphone();
	}

	public String phoneticCode(String token) {
		return dMeta.doubleMetaphone(token);
	}

}
