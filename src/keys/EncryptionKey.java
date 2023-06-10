package keys;

import java.math.BigInteger;

import algorithms.Encoding;

public class EncryptionKey {

	private int exponent;
	private int n;

	public EncryptionKey(int exponent, int n) {
		super();
		this.exponent = exponent;
		this.n = n;
	}

	public EncryptionKey() {
		super();
	}

	public int getExponent() {
		return exponent;
	}

	public void setExponent(int exponent) {
		this.exponent = exponent;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	@Override
	public String toString() {

		String[] attributes = new String[] { "N ", "Exponent " };
		int maxLength = attributes[1].length();

		String placeholder = "%-" + maxLength + "s: %d\n";

		return String.format(placeholder + placeholder, attributes[0], this.n, attributes[1], this.exponent);
	}

	public int[] encrypt(String message) {
		
		int[] unicode = Encoding.toAscii(message);
		
		
		int[] encrypted = new int[unicode.length];
		
		int i = 0;
		for (int letter : unicode) {
			BigInteger base = new BigInteger("" + letter);
			encrypted[i++] = Integer.parseInt(base.modPow(new BigInteger("" + this.exponent), new BigInteger("" + this.n)).toString());
		}
		
		return encrypted;
	}

	
	
}
