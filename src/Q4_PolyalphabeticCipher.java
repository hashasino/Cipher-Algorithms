// Q4. W.A.P. to implement Polyalphabetic Cipher.

import java.util.Scanner;

public class Q4_PolyalphabeticCipher {

    // Method to repeat the key to match the text length
    public static String generateKey(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                result.append(key.charAt(j % key.length()));
                j++;
            } else {
                result.append(c); // Keep punctuation/space
            }
        }
        return result.toString();
    }

    // Encryption
    public static String encrypt(String plaintext, String key) {
        StringBuilder cipherText = new StringBuilder();
        key = generateKey(plaintext, key);
        plaintext = plaintext.toUpperCase();

        for (int i = 0; i < plaintext.length(); i++) {
            char p = plaintext.charAt(i);
            char k = key.charAt(i);
            if (Character.isLetter(p)) {
                char c = (char) (((p - 'A') + (k - 'A')) % 26 + 'A');
                cipherText.append(c);
            } else {
                cipherText.append(p); // Keep non-alphabet characters
            }
        }
        return cipherText.toString();
    }

    // Decryption
    public static String decrypt(String cipherText, String key) {
        StringBuilder plainText = new StringBuilder();
        key = generateKey(cipherText, key);
        cipherText = cipherText.toUpperCase();

        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);
            char k = key.charAt(i);
            if (Character.isLetter(c)) {
                char p = (char) (((c - k + 26) % 26) + 'A');
                plainText.append(p);
            } else {
                plainText.append(c);
            }
        }
        return plainText.toString();
    }

    // Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Plaintext: ");
        String plaintext = sc.nextLine();

        System.out.print("Enter Key: ");
        String key = sc.nextLine();

        String encrypted = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Text: " + decrypted);

        sc.close();
    }

}