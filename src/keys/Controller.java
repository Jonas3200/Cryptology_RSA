package keys;

import java.util.Arrays;
import java.util.Scanner;

public class Controller {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Plain Text: ");
		String plainText = scan.nextLine();
		
		
		
		KeyPair keyPair = KeyPair.generateKeyPairDialog();
		
		
		
		int[] encrypted = keyPair.getEncryptionKey().encrypt(plainText);
		
		String decrypted = (keyPair.getDecryptionKey().decrypt(encrypted));
		
		System.out.print("Encrypted: ");
		System.out.println(Arrays.toString(encrypted));
		System.out.print("Decrypted: ");
		System.out.println(decrypted);
		
//		Euclidian.multiplicativeInverse(7, 19, true);
		
//		System.out.println(65537 % 120);
//		System.out.println(Arrays.toString(extendedEuclideanAlgorithm(7, 19)));
//		System.out.println(Arrays.toString(Euclidian.extendedEuclidianAlgorithm(7, 19, true)));
		
		
		/*
		 * Das Multiplikative Inverse von 65537 mod phi(N) 
		 */
	}

}
