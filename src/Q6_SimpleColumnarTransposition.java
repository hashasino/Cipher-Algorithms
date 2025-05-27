// Q8. W.A.P. to implement Simple Columnar Transposition technique.

import java.util.*;

public class Q6_SimpleColumnarTransposition {

    // Method to sort the key and return the order of columns
    private static int[] getKeyOrder(String key) {
        Character[] keyChars = new Character[key.length()];
        for (int i = 0; i < key.length(); i++) {
            keyChars[i] = key.charAt(i);
        }

        Character[] sortedKey = keyChars.clone();
        Arrays.sort(sortedKey);

        int[] order = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < key.length(); j++) {
                if (keyChars[i] == sortedKey[j]) {
                    order[i] = j;
                    sortedKey[j] = null; // Avoid duplicate assignment
                    break;
                }
            }
        }
        return order;
    }

    // Encryption
    public static String encrypt(String plaintext, String key) {
        plaintext = plaintext.replaceAll("\\s+", "").toUpperCase();
        int col = key.length();
        int row = (int) Math.ceil((double) plaintext.length() / col);
        char[][] matrix = new char[row][col];

        int idx = 0;
        for (int i = 0; i < row && idx < plaintext.length(); i++) {
            for (int j = 0; j < col && idx < plaintext.length(); j++) {
                matrix[i][j] = plaintext.charAt(idx++);
            }
        }

        int[] keyOrder = getKeyOrder(key);
        StringBuilder cipherText = new StringBuilder();

        for (int k = 0; k < col; k++) {
            int colIndex = -1;
            for (int j = 0; j < col; j++) {
                if (keyOrder[j] == k) {
                    colIndex = j;
                    break;
                }
            }

            for (int i = 0; i < row; i++) {
                if (Character.isLetter(matrix[i][colIndex])) {
                    cipherText.append(matrix[i][colIndex]);
                }
            }
        }

        return cipherText.toString();
    }

    // Decryption
    public static String decrypt(String cipherText, String key) {
        int col = key.length();
        int row = (int) Math.ceil((double) cipherText.length() / col);
        char[][] matrix = new char[row][col];

        int[] keyOrder = getKeyOrder(key);
        int idx = 0;

        for (int k = 0; k < col; k++) {
            int colIndex = -1;
            for (int j = 0; j < col; j++) {
                if (keyOrder[j] == k) {
                    colIndex = j;
                    break;
                }
            }

            for (int i = 0; i < row && idx < cipherText.length(); i++) {
                matrix[i][colIndex] = cipherText.charAt(idx++);
            }
        }

        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (Character.isLetter(matrix[i][j])) {
                    plainText.append(matrix[i][j]);
                }
            }
        }

        return plainText.toString();
    }

    // Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Plaintext: ");
        String plaintext = sc.nextLine();

        System.out.print("Enter Key (any word or number string): ");
        String key = sc.nextLine();

        String encrypted = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Text: " + decrypted);

        sc.close();
    }

}
