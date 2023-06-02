package algorithms;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import keys.DecryptionKey;

public class Euclidian {

	public static int gcd(int a, int b) {
		return euclideanAlgorithm(a, b, false);
	}

	public static int euclideanAlgorithm(int a, int b, boolean print) {
		// region formatting – Not relevant for Mathematical Background
		StringBuilder output = new StringBuilder();
		int maxLength = ("" + Integer.max(a, b)).length();
		String placeholder = "%" + maxLength + "d";
		String calculationString = String.format("%s = %s * %s + %s\n", placeholder, placeholder, placeholder,
				placeholder);
		// endregion

		if (a < b) {
			int tmp = b;
			b = a;
			a = tmp;
		}

		int gcm;
		// intitialize to allow for loop
		int remainder = -1;
		int gcd = -1;

		do {
			gcm = a / b;
			remainder = a - (gcm * b);

			output.append(String.format(calculationString, a, gcm, b, remainder));

			if (remainder != 0) {
				a = b;
				b = remainder;
			} else {
				gcd = b;
				output.append("The greatest common divisor is: " + gcd);
			}
		} while (remainder != 0);

		if (print) {
			System.out.println(output);
			if (gcd == 1) {
				System.out.println("The numbers are coprime.");
			}
		}

		return gcd;
	}

	public static int[] extendedEuclidianAlgorithm(int a, int b, boolean print) {

		/*
		 * Source: Wikipedia Link:
		 * https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm Accessed on 23rd
		 * March 2023
		 */

		/*
		 * Usage of Bézouts Identity: a*s + b*t = gcd(a,b)
		 */

		int old_r, r;
		old_r = a;
		r = b;

		int old_s, s;
		old_s = 1;
		s = 0;

		int old_t, t;
		old_t = 0;
		t = 1;

		int quotient, tmp, gcd;
		while (r != 0) {
			quotient = old_r / r;

			tmp = old_r;
			old_r = r;
			r = tmp - quotient * r;

			tmp = old_s;
			old_s = s;
			s = tmp - quotient * s;

			tmp = old_t;
			old_t = t;
			t = tmp - quotient * t;

		}

		gcd = old_r;

		if (print) {

			System.out.printf("%d * %d + %d * %d = %d\n", a, old_s, b, old_t, gcd);

			System.out.println("Bézouts Coefficients: " + old_s + "; " + old_t);

		}
		/*
		 * End Source
		 */

		return new int[] { old_s, old_t };
	}

	public static boolean isPrime(int number) {
		if (number <= 1) {
			return false;
		}
		return number - 1 == phi(number, false);
	}

	public static int determineSecretExponent(int exponent, int phiN, int[] bezoutCoefficients, boolean print) { 

		String[] bezoutCoefficientsString = new String[bezoutCoefficients.length];

		{
			int i = 0;
			for (int bezoutCoefficient : bezoutCoefficients) {
				bezoutCoefficientsString[i++] = "" + bezoutCoefficient;
			}
		}

		Arrays.sort(bezoutCoefficientsString);

		int maxLength = ("" + bezoutCoefficients[bezoutCoefficientsString.length - 1]).length();

		/*
		 * Secret d must satisfy the equation e·d=1 (mod(p−1)(q−1)).
		 */

		StringBuilder sb = new StringBuilder();

		int tmp, result = 0;
		boolean initialized = false;

		for (int bezoutCoefficient : bezoutCoefficients) {
			tmp = (exponent * bezoutCoefficient) % phiN;

			sb.append(
					String.format("(%d * %" + maxLength + "d) mod(%d) = %d\n", exponent, bezoutCoefficient, phiN, tmp));

			if (tmp == 1) {
				result = bezoutCoefficient;
				initialized = true;
			}
		}
		sb.append("Therfore, d is: " + result);

		if (print) {
			System.out.println(sb);
		}

		if (!initialized) {
			throw new RuntimeException("Could not find d to satisfy the equation!");
		}

		return result;
	}

	public static int phi(int number, boolean print) {
		Set<Integer> coprimes = new TreeSet<>();

		for (int i = 1; i < number; i++) {
			if (gcd(i, number) == 1) {
				coprimes.add(i);
			}
		}
		if (print) {
			System.out.println(coprimes);
		}
		return coprimes.size();
	}

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int prime1 = 0, prime2 = 0, N, decryptionExponent, exponent = 0, phiN;
		int[] bezoutCoefficients;

		boolean wrongInput = false;

		do {
			if (wrongInput) {
				System.err.printf("%d is not a prime! Please provide another number. \n", prime1);
			}
			System.out.print("Prime 1: ");
			prime1 = scan.nextInt();
		} while (wrongInput = !isPrime(prime1));

		do {
			if (wrongInput) {
				System.err.printf("%d is not a prime! Please provide another number. \n", prime2);
			}
			System.out.print("Prime 2: ");
			prime2 = scan.nextInt();
		} while (wrongInput = !isPrime(prime2));

		N = prime1 * prime2;
		System.out.println("0. Calculate N");
		System.out.printf("N = %d * %d = %d\n", prime1, prime2, N);

		System.out.println();

		System.out.println("1. Calculate Φ(prime1*prime2) = Φ(N) = (prime1-1) * (prime2-1):");
		phiN = (prime1 - 1) * (prime2 - 1);
		System.out.printf("Φ(N) = (%d-1) * (%d-1) = %d\n", prime1, prime2, phiN);

		do {
			if (wrongInput) {
				System.out.println("The exponent is not coprime to Φ(N).\nTry Again.");
			}
			System.out.print("Exponent (typically 3, 17, 65537): ");
			exponent = scan.nextInt();

			if (exponent > phiN) {
				System.err.println("Exponent cannot be Greater than Φ(N), please provide another exponent");
				continue;
			}

			System.out.println();
			System.out.printf("2. Determine that gcd(%d,Φ(%d)) = 1 using the Euclidean Algorithm:\n", exponent, N);
			euclideanAlgorithm(exponent, phiN, true);

			System.out.println();

		} while (wrongInput = !(euclideanAlgorithm(exponent, phiN, false) == 1) || exponent > phiN);

		System.out.println("3. Determine the Bézout Coefficients using the Extended Euclidean Algorithm:");
		bezoutCoefficients = extendedEuclidianAlgorithm(exponent, phiN, true);

		System.out.println();
		System.out.printf("4. Determine the Secret d such that e * d = 1 mod(Φ(N)) using the bezout Coefficients\n");

		decryptionExponent = determineSecretExponent(exponent, phiN, bezoutCoefficients, true);

		System.out.println();

		DecryptionKey decryptionKey = new DecryptionKey(prime1, prime2, decryptionExponent, N);

		System.out.println("Therefore, the Decryption Key is:\n" + decryptionKey);

	}

}
