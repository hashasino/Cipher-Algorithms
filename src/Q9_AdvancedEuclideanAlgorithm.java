// Q11. W.A.P. to implement Advanced Euclidean Algorithm.

import java.util.Scanner;

public class Q9_AdvancedEuclideanAlgorithm {

    // Structure to hold results
    static class Result {
        int gcd, x, y;

        Result(int gcd, int x, int y) {
            this.gcd = gcd;
            this.x = x;
            this.y = y;
        }
    }

    // Extended Euclidean Algorithm
    public static Result extendedGCD(int a, int b) {
        if (b == 0) {
            return new Result(a, 1, 0);
        }

        Result next = extendedGCD(b, a % b);
        int x1 = next.y;
        int y1 = next.x - (a / b) * next.y;

        return new Result(next.gcd, x1, y1);
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter integer a: ");
        int a = sc.nextInt();

        System.out.print("Enter integer b: ");
        int b = sc.nextInt();

        Result result = extendedGCD(a, b);

        System.out.println("GCD of " + a + " and " + b + " is: " + result.gcd);
        System.out.println("Coefficients x and y such that ax + by = gcd(a, b):");
        System.out.println("x = " + result.x + ", y = " + result.y);

        sc.close();
    }

}
