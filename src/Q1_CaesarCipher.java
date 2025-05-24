// Q1. W.A.P. to implement Caesar Cipher.

import java.util.Scanner;

public class Q1_CaesarCipher {

    public static void main(String[] args) {

        //Program Declaration
        System.out.println("This program implements Caesar cipher cryptographic scheme. The methods only substitute letters/alphabets and ignore/skip any other characters.");

        //Instantiating Scanner object
        Scanner scan = new Scanner(System.in);

        //Taking input for plainText & shift value
        System.out.println("- Enter plain text: ");
        String plainText = scan.nextLine();
        System.out.println("- Enter shift value (0-25): ");
        int shift = scan.nextInt() % 26;

        //Executing encryption and decryption methods
        System.out.println("Plain Text: " + plainText);
        String cipherText = encrypt(plainText, shift);
        System.out.println("Cipher Text: " + cipherText);
        String decryptedText = decrypt(cipherText, shift);
        System.out.println("Decrypted Text: " + decryptedText);

    }

    public static String encrypt(String plainText, int shift) {
        StringBuilder encryptedText = new StringBuilder();
        for (char ch : plainText.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                ch = (char) ((ch - base + shift) % 26 + base);
            }
            encryptedText.append(ch);
        }
        return encryptedText.toString();
    }

    public static String decrypt(String cipherText, int shift) {
        StringBuilder decryptedText = new StringBuilder();
        for (char ch : cipherText.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                ch = (char) ((ch - base - shift + 26) % 26 + base);
            }
            decryptedText.append(ch);
        }
        return decryptedText.toString();
    }
}
