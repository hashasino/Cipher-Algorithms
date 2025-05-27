// Q3. W.A.P. to implement Playfair Cipher with key ldrp.

import java.util.*;

public class Q3_PlayfairCipher {

    static char[][] keyMatrix = new char[5][5];

    public static void main(String[] args) {

        //Program Declaration
        System.out.println("This program implements Playfair cipher cryptographic scheme. \nThe methods only substitute letters/alphabets and ignore/skip any other characters.");

        //Instantiating Scanner object
        Scanner scan = new Scanner(System.in);

        //Taking input for plainText
        System.out.println("\n- Enter plain text: ");
        String plainText = scan.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");

        //Taking input for key
        System.out.println("- Enter key: ");
        String key = scan.nextLine().toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");

        //Generating & printing key matrix
        generateKeyMatrix(key);
        printKeyMatrix();

        //Executing encryption and decryption methods
        System.out.println("\nPlain Text: " + plainText);
        String cipherText = encrypt(preparePlaintext(plainText));
        System.out.println("Cipher Text: " + cipherText);
        String decryptedText = decrypt(breakIntoPairs(cipherText));
        System.out.println("Decrypted Text: " + decryptedText);
    }

    public static String encrypt(List<String> characterPairList) {

        //Initializing a StringBuilder to store the cipher text
        StringBuilder encryptedText = new StringBuilder();

        //Iterating through the character pair list
        for (String characterPair : characterPairList) {

            //Finding positions of the character pair in the key matrix
            int[] pos1 = findPosition(characterPair.charAt(0));
            int[] pos2 = findPosition(characterPair.charAt(1));

            //Throwing an exception if either character is not found in the key matrix
            if (pos1 == null || pos2 == null) {
                throw new IllegalArgumentException("Character not found in key matrix.");
            }

            //If both characters are in the same row, shifting right
            if (pos1[0] == pos2[0]) {
                encryptedText.append(keyMatrix[pos1[0]][(pos1[1] + 1) % 5]);
                encryptedText.append(keyMatrix[pos2[0]][(pos2[1] + 1) % 5]);
            }
            //If both characters are in the same column, shifting down
            else if (pos1[1] == pos2[1]) {
                encryptedText.append(keyMatrix[(pos1[0] + 1) % 5][pos1[1]]);
                encryptedText.append(keyMatrix[(pos2[0] + 1) % 5][pos2[1]]);
            }
            //If both characters are in different rows and columns, swapping their column positions
            else {
                encryptedText.append(keyMatrix[pos1[0]][pos2[1]]);
                encryptedText.append(keyMatrix[pos2[0]][pos1[1]]);
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(List<String> characterPairList) {

        //Initializing a StringBuilder to store the decrypted text
        StringBuilder decryptedText = new StringBuilder();

        for (String pair : characterPairList) {

            //Finding positions of the character pair in the key matrix
            int[] pos1 = findPosition(pair.charAt(0));
            int[] pos2 = findPosition(pair.charAt(1));

            //Throwing an exception if either character is not found in the key matrix
            if (pos1 == null || pos2 == null) {
                throw new IllegalArgumentException("Character not found in key matrix.");
            }

            //If both characters are in the same row, shifting left
            if (pos1[0] == pos2[0]) {
                decryptedText.append(keyMatrix[pos1[0]][(pos1[1] + 4) % 5]);
                decryptedText.append(keyMatrix[pos2[0]][(pos2[1] + 4) % 5]);
            }
            //If both characters are in the same column, shifting up
            else if (pos1[1] == pos2[1]) {
                decryptedText.append(keyMatrix[(pos1[0] + 4) % 5][pos1[1]]);
                decryptedText.append(keyMatrix[(pos2[0] + 4) % 5][pos2[1]]);
            }
            //If both characters are in different rows and columns, swapping their column positions
            else {
                decryptedText.append(keyMatrix[pos1[0]][pos2[1]]);
                decryptedText.append(keyMatrix[pos2[0]][pos1[1]]);
            }
        }

        return decryptedText.toString();
    }

    //To generate the 5x5 key matrix
    public static void generateKeyMatrix(String key) {

        //Initializing the character set
        Set<Character> set = new LinkedHashSet<>();

        //Adding characters of the key to the set first
        for (char ch : key.toCharArray()) set.add(ch);

        //Adding remaining characters of the alphabet to the set
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (ch == 'J') continue;
            set.add(ch);
        }

        //Converting the character set to an iterator in order to fill in the key matrix
        Iterator<Character> itr = set.iterator();
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                keyMatrix[i][j] = itr.next();
    }

    //To print the generated key matrix
    public static void printKeyMatrix() {
        System.out.println("\nKey matrix:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++)
                System.out.print(keyMatrix[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    //To prepare the plainText for encryption by breaking it into pairs
    public static List<String> preparePlaintext(String text) {

        //Initializing a string list to store character pairs
        List<String> pairs = new ArrayList<>();

        //Iterating through the text & pairing characters
        for (int i = 0; i < text.length(); ) {

            //Assigning initial values for a & b
            char a = text.charAt(i);
            char b = 'X';

            //Checking if i+1 is within bounds and assigning b to that index if true
            if (i + 1 < text.length()) {
                b = text.charAt(i + 1);
                if (a == b) {
                    b = 'X';
                    i++; //Incrementing 1 if both characters are the same
                } else {
                    i += 2; //Incrementing 2 if the characters are different
                }
            } else i++;

            pairs.add("" + a + b); //Adding current pair to the list
        }

        return pairs;
    }

    //To prepare the cipherText for decryption by breaking it into pairs
    public static List<String> breakIntoPairs(String text) {

        //Initializing a string list to store character pairs
        List<String> pairs = new ArrayList<>();

        //Iterating through the text & pairing characters
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = (i + 1 < text.length()) ? text.charAt(i + 1) : 'X'; //Checking if i+1 is within bounds and assigning b accordingly
            pairs.add("" + a + b); //Adding current pair to the list
        }
        return pairs;
    }

    //To find the position of a character in key matrix
    public static int[] findPosition(char ch) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (keyMatrix[i][j] == ch) return new int[]{i, j};
        return null;
    }

}
