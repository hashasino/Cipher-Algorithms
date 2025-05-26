// Q10. W.A.P. to implement Euclidean Algorithm.

import java.util.Scanner;

public class Q10_EuclideanAlgorithm {

    // Method to compute GCD using Euclidean Algorithm
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number (a): ");
        int a = sc.nextInt();

        System.out.print("Enter second number (b): ");
        int b = sc.nextInt();

        int result = gcd(a, b);
        System.out.println("GCD of " + a + " and " + b + " is: " + result);

        sc.close();
    }

}
