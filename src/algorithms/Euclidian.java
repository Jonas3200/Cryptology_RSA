package algorithms;

import java.util.Set;
import java.util.TreeSet;

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
		int maxLength = ("" + Integer.max(a, b)).length();
		String placeholder = "%" + maxLength + "d";
		String calculationString = String.format("%s = %s * %s + %s * %s\n", placeholder, placeholder, placeholder,
				placeholder, placeholder);
		/*
		 * Source: [Smart, p. 15]
		 */

		int s = 0, ss = 1;
		int t = 1, tt = 0;
		int r = b, rr = a;
		int q, tmp;

		while (r != 0) {
			q = rr / r;

			tmp = rr;
			rr = r;
			r = tmp - q * r;

			tmp = ss;
			ss = s;
			s = tmp - q * s;

			tmp = tt;
			tt = t;
			t = tmp - q * t;

			if (print) {
				System.out.printf(calculationString, rr, a, s, b, t);
			}

		}

		/*
		 * End Source
		 */

		int d = rr, x = ss, y = tt;

		return new int[] { d, x, y };
	}

	public static boolean isPrime(int number) {
		if (number <= 1) {
			return false;
		}
		return number - 1 == phi(number, false);
	}

	public static int multiplicativeInverse(int a, int b, boolean print) {

		int multiplicativeInverse = -1;

		if (print) {
			System.out.printf("Apply the extended Euclidean Algorithm to %d and %d\n", a, b);
		}

		int[] extendedEuclideanAlgorithmResult = extendedEuclidianAlgorithm(a, b, print);

		int gcd = extendedEuclideanAlgorithmResult[0];
		int x = extendedEuclideanAlgorithmResult[1];

		if (gcd == 1) {
			multiplicativeInverse = (x % b + b) % b;
			if (print) {
				System.out.printf("Since gcd(%d, %d) = 1 there exists a multiplicative inverse to %d %% %d:\n", a, b, a, b);
				System.out.printf("(%d %% %d + %d) %% %d = %d\n", x, b, b, b, multiplicativeInverse);
				
			}
		} else {
			System.out.printf("There is no multiplicative inverse to %d %% %d\n", a, b, a, b);
		}

		return multiplicativeInverse;

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

}
