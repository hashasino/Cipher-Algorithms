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
        String cipherText = encrypt(plainText, shift).toString();
        System.out.println("Cipher Text: " + cipherText);
        String decryptedText = decrypt(cipherText, shift).toString();
        System.out.println("Decrypted Text: " + decryptedText);

    }

    //Encryption method
    public static StringBuilder encrypt(String plainText, int shift) {
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((c - base + shift) % 26 + base);
            }
            cipherText.append(c);
        }
        return cipherText;
    }

    //Decryption method
    public static StringBuilder decrypt(String cipherText, int shift) {
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((c - base - shift + 26) % 26 + base);
            }
            plainText.append(c);
        }
        return plainText;
    }
}
