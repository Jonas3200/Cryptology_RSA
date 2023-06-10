package algorithms;

public class Encoding {

	public static int[] toAscii(String plaintext) {
		char[] text = plaintext.toCharArray();

		int[] unicode = new int[text.length];

		int i = 0;
		for (char c : text) {
			unicode[i++] = (int) c;
		}
		return unicode;
	}

	public static String toPlaintext(int[] unicodeRepresentation) {
		char[] text = new char[unicodeRepresentation.length];

		int i = 0;
		for (int unicode : unicodeRepresentation) {
			text[i++] = (char) unicode;
		}

		return new String(text);
	}

}
