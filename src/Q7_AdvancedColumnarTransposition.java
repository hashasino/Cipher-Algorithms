// Q9. W.A.P. to implement Advanced Columnar Transposition technique.

import java.util.*;

public class Q7_AdvancedColumnarTransposition {

    // Generate column order from key
    private static int[] getKeyOrder(String key) {
        Character[] keyChars = new Character[key.length()];
        for (int i = 0; i < key.length(); i++) keyChars[i] = key.charAt(i);

        Character[] sortedKey = keyChars.clone();
        Arrays.sort(sortedKey);

        int[] order = new int[key.length()];
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < key.length(); j++) {
                if (sortedKey[j] != null && keyChars[i] == sortedKey[j]) {
                    order[i] = j;
                    sortedKey[j] = null;
                    break;
                }
            }
        }
        return order;
    }

    // Single round encryption
    private static String columnarEncrypt(String text, String key) {
        text = text.replaceAll("\\s+", "").toUpperCase();
        int col = key.length();
        int row = (int) Math.ceil((double) text.length() / col);
        char[][] matrix = new char[row][col];

        // Fill matrix row-wise
        int idx = 0;
        for (int i = 0; i < row && idx < text.length(); i++) {
            for (int j = 0; j < col && idx < text.length(); j++) {
                matrix[i][j] = text.charAt(idx++);
            }
        }

        // Get column order
        int[] keyOrder = getKeyOrder(key);
        StringBuilder result = new StringBuilder();

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
                    result.append(matrix[i][colIndex]);
                }
            }
        }

        return result.toString();
    }

    // Single round decryption
    private static String columnarDecrypt(String text, String key) {
        int col = key.length();
        int row = (int) Math.ceil((double) text.length() / col);
        char[][] matrix = new char[row][col];

        int[] keyOrder = getKeyOrder(key);
        int idx = 0;

        // Fill columns according to key order
        for (int k = 0; k < col; k++) {
            int colIndex = -1;
            for (int j = 0; j < col; j++) {
                if (keyOrder[j] == k) {
                    colIndex = j;
                    break;
                }
            }

            for (int i = 0; i < row && idx < text.length(); i++) {
                matrix[i][colIndex] = text.charAt(idx++);
            }
        }

        // Read matrix row-wise
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (Character.isLetter(matrix[i][j])) {
                    result.append(matrix[i][j]);
                }
            }
        }

        return result.toString();
    }

    // Advanced encryption (2 rounds)
    public static String encrypt(String plaintext, String key) {
        String firstRound = columnarEncrypt(plaintext, key);
        return columnarEncrypt(firstRound, key); // 2nd round
    }

    // Advanced decryption (2 rounds)
    public static String decrypt(String ciphertext, String key) {
        String firstRound = columnarDecrypt(ciphertext, key);
        return columnarDecrypt(firstRound, key); // 2nd round
    }

    // Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Plaintext: ");
        String plaintext = sc.nextLine();

        System.out.print("Enter Key: ");
        String key = sc.nextLine().toUpperCase();

        String encrypted = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Text: " + decrypted);

        sc.close();
    }

}
