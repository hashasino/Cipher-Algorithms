// Q5. W.A.P. to implement Auto Key Cipher.

import java.util.Scanner;

public class Q5_AutoKeyCipher {

    // Generate extended key using initial key + plaintext
    public static String generateKey(String plaintext, String key) {
        key = key.toUpperCase();
        plaintext = plaintext.toUpperCase();
        int keyLen = key.length();

        StringBuilder extendedKey = new StringBuilder(key);
        for (int i = 0; extendedKey.length() < plaintext.length(); i++) {
            extendedKey.append(plaintext.charAt(i));
        }

        return extendedKey.toString();
    }

    // Encrypt the plaintext
    public static String encrypt(String plaintext, String key) {
        plaintext = plaintext.toUpperCase();
        String extendedKey = generateKey(plaintext, key);
        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char p = plaintext.charAt(i);
            if (Character.isLetter(p)) {
                char k = extendedKey.charAt(i);
                char c = (char) (((p - 'A') + (k - 'A')) % 26 + 'A');
                cipherText.append(c);
            } else {
                cipherText.append(p); // Keep non-letters
            }
        }

        return cipherText.toString();
    }

    // Decrypt the ciphertext
    public static String decrypt(String cipherText, String key) {
        cipherText = cipherText.toUpperCase();
        StringBuilder plainText = new StringBuilder();
        key = key.toUpperCase();

        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);
            char k;
            if (i < key.length()) {
                k = key.charAt(i);
            } else {
                k = plainText.charAt(i - key.length()); // Use decrypted text as key
            }

            if (Character.isLetter(c)) {
                char p = (char) (((c - k + 26) % 26) + 'A');
                plainText.append(p);
            } else {
                plainText.append(c); // Keep non-letters
            }
        }

        return plainText.toString();
    }

    // Main method
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
