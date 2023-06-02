package keys;

import java.util.Scanner;

import algorithms.Euclidian;

public class DecryptionKey {

	private int primeFactor1;
	private int primeFactor2;
	/**
	 * decryptionExponent = primeFactor1 * primeFactor2
	 */
	private int decryptionExponent;
	/**
	 * optional
	 */
	private int n;

	public DecryptionKey() {

	}

	public DecryptionKey(int primeFactor1, int primeFactor2, int decryptionExponent, int n) {
		super();
		this.primeFactor1 = primeFactor1;
		this.primeFactor2 = primeFactor2;
		this.decryptionExponent = decryptionExponent;
		this.n = n;
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

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	@Override
	public String toString() {

		String[] attributes = new String[] { "Prime Factor 1: ", "Prime Factor 2: ", "N", "Decryption Exponent: " };
		int maxLength = attributes[3].length();

		String placeholder = "%-" + maxLength + "s: %d\n";

		return String.format(placeholder + placeholder + placeholder + placeholder, attributes[0], primeFactor1,
				attributes[1], primeFactor2, attributes[2], n, attributes[3], decryptionExponent);
	}
	
	public static DecryptionKey generateDecryptionKeyDialog() {
		Scanner scan = new Scanner(System.in);

		int prime1 = 0, prime2 = 0, N, decryptionExponent, exponent = 0, phiN;
		int[] bezoutCoefficients;

		boolean wrongInput = false;
		
		do {
			if(wrongInput) {
				System.err.printf("%d is not a prime! Please provide another number. \n", prime1);
			}
			System.out.print("Prime 1: ");
			prime1 = scan.nextInt();
		} while (wrongInput = !Euclidian.isPrime(prime1));

		do {
			if(wrongInput) {
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
			if(wrongInput) {
				System.out.println("The exponent is not coprime to Φ(N).\nPlease provide another Exponent.");
			}
			System.out.print("Exponent (typically 3, 17, 65537): ");
			exponent = scan.nextInt();
			
			if(exponent > phiN) {
				System.err.println("Exponent cannot be Greater than Φ(N), please provide another exponent");
				continue;				
			}
			
			System.out.println();
			System.out.printf("2. Determine that gcd(%d,Φ(%d)) = 1 using the Euclidean Algorithm:\n", exponent, N);
			Euclidian.euclideanAlgorithm(exponent, phiN, true);
			
			System.out.println();
			
		} while (wrongInput = !(Euclidian.euclideanAlgorithm(exponent, phiN, false) == 1) || exponent > phiN);
		
		scan.close();

		System.out.println("3. Determine the Bézout Coefficients using the Extended Euclidean Algorithm:");
		bezoutCoefficients = Euclidian.extendedEuclidianAlgorithm(exponent, phiN, true);

		System.out.println();
		System.out.printf("4. Determine the Secret d such that e * d = 1 mod(Φ(N)) using the Bezout Coefficients\n");

		decryptionExponent = Euclidian.determineSecretExponent(exponent, phiN, bezoutCoefficients, true);
		
		System.out.println();
		
		DecryptionKey decryptionKey = new DecryptionKey(prime1, prime2, decryptionExponent, N);
		
		System.out.println("Therefore, the Decryption Key is:\n" + decryptionKey);

		return decryptionKey;
		
	}

	public static void main(String[] args) {
		generateDecryptionKeyDialog();
	}
	
}
