// Q2. W.A.P. to implement Affine Cipher with equation c = 3x + 12

import java.util.Scanner;

public class Q2_AffineCipher {
    public static void main(String[] args) {

        //Program Declaration
        System.out.println("This program implements Affine cipher cryptographic scheme. The methods only substitute letters/alphabets and ignore/skip any other characters.");

        //Instantiating Scanner object
        Scanner scan = new Scanner(System.in);

        //Taking input for plainText
        System.out.println("- Enter plain text: ");
        String plainText = scan.nextLine();
        int shift, multiplier;

        //Taking input for shift value & validating it
        while (true) {
            System.out.println("- Enter shift value (0-25): ");
            shift = scan.nextInt() % 26;
            if (shift < 0) System.out.println("Shift value cannot be negative. Please enter a valid shift value.");
            else break;
        }

        //Taking input for multiplier value & validating it
        while (true) {
            System.out.println("- Enter multiplier value (1-25): ");
            multiplier = scan.nextInt() % 26;
            if (multiplier < 0)
                System.out.println("Multiplier value cannot be negative. Please enter a valid multiplier value.");
            else if (multiplier == 0)
                System.out.println("Multiplier value cannot be zero or multiples of 26. Please enter a valid multiplier value.");
            else if (!isCoprime(multiplier))
                System.out.println("Multiplier value must be coprime with 26. Please enter a valid multiplier value.");
            else break;
        }

        //Executing encryption and decryption methods
        System.out.println("Plain Text: " + plainText);
        String cipherText = encrypt(plainText, multiplier, shift).toString();
        System.out.println("Cipher Text: " + cipherText);
        String decryptedText = decrypt(cipherText, multiplier, shift).toString();
        System.out.println("Decrypted Text: " + decryptedText);
    }

    //Encryption method
    public static StringBuilder encrypt(String plainText, int multiplier, int shift) {
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((multiplier * (c - base) + shift) % 26 + base);
            }
            cipherText.append(c);
        }
        return cipherText;
    }

    //Decryption method
    public static StringBuilder decrypt(String cipherText, int multiplier, int shift) {
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((modInverse(multiplier) * ((c - base - shift + 26) % 26)) % 26 + base);
            }
            plainText.append(c);
        }
        return plainText;
    }

    // Function to calculate modular inverse of a number under mod 26
    public static int modInverse(int a) {
        for (int x = 1; x < 26; x++) {
            if ((a * x) % 26 == 1) return x;
        }
        return 0; // Inverse doesn't exist
    }

    // Function to calculate GCD of two numbers
    public static int GCD(int a, int b) {
        if (b == 0) return a;
        return GCD(b, a % b);
    }

    // Function to check if a number is coprime with 26
    public static boolean isCoprime(int a) {
        return GCD(a, 26) == 1;
    }

}
