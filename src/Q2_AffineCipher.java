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
        String cipherText = encrypt(plainText, multiplier, shift);
        System.out.println("Cipher Text: " + cipherText);
        String decryptedText = decrypt(cipherText, multiplier, shift);
        System.out.println("Decrypted Text: " + decryptedText);
    }

    public static String encrypt(String plainText, int multiplier, int shift) {
        StringBuilder encryptedText = new StringBuilder();
        for (char ch : plainText.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                ch = (char) ((multiplier * (ch - base) + shift) % 26 + base);
            }
            encryptedText.append(ch);
        }
        return encryptedText.toString();
    }

    public static String decrypt(String cipherText, int multiplier, int shift) {
        StringBuilder decryptedText = new StringBuilder();
        for (char ch : cipherText.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                ch = (char) (modInverse(multiplier) * (ch - base - shift + 26) % 26 + base);
            }
            decryptedText.append(ch);
        }
        return decryptedText.toString();
    }

    // Function to check if a number is coprime with 26
    public static boolean isCoprime(int a) {
        return GCD(a, 26) == 1;
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

}
