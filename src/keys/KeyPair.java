package keys;

import java.util.Scanner;

import algorithms.Euclidian;

public class KeyPair {

	private EncryptionKey encryptionKey;
	private DecryptionKey decryptionKey;

	public KeyPair(EncryptionKey encryptionKey, DecryptionKey decryptionKey) {
		super();
		this.encryptionKey = encryptionKey;
		this.decryptionKey = decryptionKey;
	}

	public KeyPair() {

	}

	public EncryptionKey getEncryptionKey() {
		return encryptionKey;
	}

	public void setEncryptionKey(EncryptionKey encryptionKey) {
		this.encryptionKey = encryptionKey;
	}

	public DecryptionKey getDecryptionKey() {
		return decryptionKey;
	}

	public void setDecryptionKey(DecryptionKey decryptionKey) {
		this.decryptionKey = decryptionKey;
	}

	public static KeyPair generateKeyPairDialog() {
		Scanner scan = new Scanner(System.in);

		int prime1 = 0, prime2 = 0, N, decryptionExponent, exponent = 0, phiN;

		boolean wrongInput = false;

		do {
			if (wrongInput) {
				System.err.printf("%d is not a prime! Please provide another number. \n", prime1);
			}
			System.out.print("Prime 1: ");
			prime1 = scan.nextInt();
		} while (wrongInput = !Euclidian.isPrime(prime1));

		do {
			if (wrongInput) {
				System.err.printf("%d is not a prime! Please provide another number. \n", prime2);
			}
			System.out.print("Prime 2: ");
			prime2 = scan.nextInt();
		} while (wrongInput = !Euclidian.isPrime(prime2));

		N = prime1 * prime2;
		System.out.println("0. Calculate N");
		System.out.printf("N = %d * %d = %d\n", prime1, prime2, N);

		System.out.println();

		System.out.println("1. Calculate Φ(prime1*prime2) = Φ(N) = (prime1-1) * (prime2-1):");
		phiN = (prime1 - 1) * (prime2 - 1);
		System.out.printf("Φ(N) = (%d-1) * (%d-1) = %d\n", prime1, prime2, phiN);

		do {
			if (wrongInput) {
				System.out.println("The exponent is not coprime to Φ(N).\nPlease provide another Exponent.");
			}
			System.out.print("Exponent (typically 3, 17, 65537): ");
			exponent = scan.nextInt();

			System.out.println("Determine the Secret d:");
			decryptionExponent = Euclidian.multiplicativeInverse(exponent, phiN, true);
			

		} while (wrongInput = !(Euclidian.euclideanAlgorithm(exponent, phiN, false) == 1) );

		
		System.out.println();

		DecryptionKey decryptionKey = new DecryptionKey(prime1, prime2, decryptionExponent);

		System.out.println("The Key Generation is done. \nThe Decryption Key is:\n" + decryptionKey);

		EncryptionKey encryptionKey = new EncryptionKey(exponent, N);
		System.out.println("And The Encryption Key is:\n" + encryptionKey);

		return new KeyPair(encryptionKey, decryptionKey);

	}

}
