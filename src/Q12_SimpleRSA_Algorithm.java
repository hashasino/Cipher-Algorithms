// Q12. W.A.P. to implement Simple RSA Algorithm with small numbers.

import java.util.Scanner;

public class Q12_SimpleRSA_Algorithm {

    // Function to find GCD
    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    // Function to find multiplicative inverse of e mod φ(n)
    public static int modInverse(int e, int phi) {
        for (int d = 1; d < phi; d++) {
            if ((e * d) % phi == 1)
                return d;
        }
        return -1; // Not found
    }

    // Modular exponentiation (base^exp) % mod
    public static int modPow(int base, int exp, int mod) {
        int result = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = (result * base) % mod;
            exp = exp >> 1;
            base = (base * base) % mod;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Small primes
        System.out.print("Enter first prime number (p): ");
        int p = sc.nextInt();

        System.out.print("Enter second prime number (q): ");
        int q = sc.nextInt();

        int n = p * q;
        int phi = (p - 1) * (q - 1);

        // Choose e such that 1 < e < phi and gcd(e, phi) = 1
        int e = 2;
        while (e < phi) {
            if (gcd(e, phi) == 1)
                break;
            e++;
        }

        int d = modInverse(e, phi);

        System.out.println("\nPublic Key: (e=" + e + ", n=" + n + ")");
        System.out.println("Private Key: (d=" + d + ", n=" + n + ")");

        // Input message
        System.out.print("\nEnter message to encrypt (as integer): ");
        int msg = sc.nextInt();

        // Encryption: c = m^e mod n
        int encrypted = modPow(msg, e, n);
        System.out.println("Encrypted message: " + encrypted);

        // Decryption: m = c^d mod n
        int decrypted = modPow(encrypted, d, n);
        System.out.println("Decrypted message: " + decrypted);

        sc.close();
    }

}
