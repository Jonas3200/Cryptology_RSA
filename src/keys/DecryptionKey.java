package keys;

import java.math.BigInteger;

import algorithms.Encoding;

public class DecryptionKey {

	private int primeFactor1;
	private int primeFactor2;
	/**
	 * decryptionExponent = primeFactor1 * primeFactor2
	 */
	private int decryptionExponent;

	public DecryptionKey() {

	}

	public DecryptionKey(int primeFactor1, int primeFactor2, int decryptionExponent) {
		super();
		this.primeFactor1 = primeFactor1;
		this.primeFactor2 = primeFactor2;
		this.decryptionExponent = decryptionExponent;
	}

	public int getPrimeFactor1() {
		return primeFactor1;
	}

	public void setPrimeFactor1(int primeFactor1) {
		this.primeFactor1 = primeFactor1;
	}

	public int getPrimeFactor2() {
		return primeFactor2;
	}

	public void setPrimeFactor2(int primeFactor2) {
		this.primeFactor2 = primeFactor2;
	}

	public int getDecryptionExponent() {
		return decryptionExponent;
	}

	public void setDecryptionExponent(int decryptionExponent) {
		this.decryptionExponent = decryptionExponent;
	}

	@Override
	public String toString() {

		String[] attributes = new String[] { "Prime Factor 1 ", "Prime Factor 2 ", "Decryption Exponent " };
		int maxLength = attributes[2].length();

		String placeholder = "%-" + maxLength + "s: %d\n";

		return String.format(placeholder + placeholder + placeholder, attributes[0], this.primeFactor1, attributes[1],
				this.primeFactor2, attributes[2], this.decryptionExponent);
	}

	public String decrypt(int[] ciphertext) {
		int[] decrypted = new int[ciphertext.length];

		int i = 0;
		for (int letter : ciphertext) {
			BigInteger base = new BigInteger("" + letter);
			decrypted[i++] = Integer.parseInt(base.modPow(new BigInteger("" + this.decryptionExponent), new BigInteger("" + (this.primeFactor1 * this.primeFactor2))).toString());
		}

		return Encoding.toPlaintext(decrypted);

	}

}
