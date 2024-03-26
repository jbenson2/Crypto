import java.util.Scanner;

class RSA {
    public static void main(String[] args) {
        long M, p, q, n, e, d, euler;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter M: ");
        M = scan.nextLong();
        System.out.print("Enter p: ");
        p = scan.nextLong();
        System.out.print("Enter q: ");
        q = scan.nextLong();
        n = p * q;
        euler = (p - 1) * (q - 1);
        do {
            System.out.print("Enter e: ");
            e = scan.nextLong();
            if (!areCoprime(euler, e)) {
                System.out.println("Euler(pq) and e are not coprime.");
            }
        } while (!areCoprime(euler, e));
        d = modInverse(e, euler);
        long encrypted = encrypt(M, e, n);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypt(encrypted, d, n));
        scan.close();
    }

    static boolean areCoprime(long n, long e) {
        if (gcd(n, e) == 1) {
            return true;
        }
        return false;
    }

    static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    static long fastPower(long g, long A, long N) {
        long a, b;
        a = g;
        b = 1;
        while (A > 0) {
            if (A % 2 == 1) {
                b = b * a % N;
            }
            a = (long) Math.pow((double) a, 2) % N;
            A = Math.floorDiv(A, 2);
        }
        return b;
    }

    static long modInverse(long a, long m) {
        long originalM = m, inverse = 0, lastInverse = 1;
        while (a > 1) {
            long quotient = a / m;
            long temp = m;
            m = a % m;
            a = temp;
            temp = inverse;
            inverse = lastInverse - quotient * inverse;
            lastInverse = temp;
        }
        return (lastInverse < 0) ? lastInverse + originalM : lastInverse;
    }

    static long encrypt(long M, long e, long n) {
        return fastPower(M, e, n);
    }

    static long decrypt(long C, long d, long n) {
        return fastPower(C, d, n);
    }
}