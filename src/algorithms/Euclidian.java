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

	public static void extendedEuclidianAlgorithm() {
		//TODO
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
		System.out.println(phi(65537, false));
		euclideanAlgorithm(42, 15, true);
		System.out.println(phi(42, true));
	}

}
