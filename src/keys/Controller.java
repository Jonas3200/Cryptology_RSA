package keys;

import java.util.Arrays;
import java.util.Scanner;

import algorithms.Encoding;

public class Controller {

	public static void main(String[] args) {
	
//		System.out.println(Arrays.toString(Encoding.toUnicode("!")));
		
		KeyPair keyPair = KeyPair.generateKeyPairDialog();

		Scanner scanner = new Scanner(System.in);
		System.out.print("Plain Text: ");
		String plainText = scanner.nextLine();
		
		int[] encrypted = keyPair.getEncryptionKey().encrypt(plainText);
		String decrypted = (keyPair.getDecryptionKey().decrypt(encrypted));

		System.out.printf("%-20s:", "Plaintext As ASCII");
		System.out.println(Arrays.toString(Encoding.toAscii(plainText)));
		System.out.printf("%-20s:", "Encrypted");
		System.out.println(Arrays.toString(encrypted));
		System.out.printf("%-20s:", "Decrypted");
		System.out.println(decrypted);
		
		scanner.close();

	}

}
