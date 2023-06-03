package keys;

import java.util.Arrays;
import java.util.Scanner;

public class Controller {

	public static void main(String[] args) {

		KeyPair keyPair = KeyPair.generateKeyPairDialog();

		Scanner scanner = new Scanner(System.in);
		System.out.print("Plain Text: ");
		String plainText = scanner.nextLine();
		int[] encrypted = keyPair.getEncryptionKey().encrypt(plainText);

		String decrypted = (keyPair.getDecryptionKey().decrypt(encrypted));

		System.out.print("Encrypted: ");
		System.out.println(Arrays.toString(encrypted));
		System.out.print("Decrypted: ");
		System.out.println(decrypted);

	}

}
