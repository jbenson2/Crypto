import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

class MillerRabin {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Find a prime with how many digits?: ");
        int nDigits = scan.nextInt();
        BigInteger[] res = findRandomPrime(nDigits);
        System.out.println(res[0] + " was randomly found to be a " + nDigits + "-digit prime number after trying "
                + res[1] + " other numbers.");
        scan.close();
    }

    static BigInteger[] findRandomPrime(int nDigits) {
        Random rand = new Random();
        BigInteger lowerBound = BigInteger.TEN.pow(nDigits - 1);
        BigInteger upperBound = BigInteger.TEN.pow(nDigits).subtract(BigInteger.ONE);
        BigInteger[] result = new BigInteger[2];
        int numbersTried = 0;

        while (true) {
            BigInteger n = new BigInteger(upperBound.bitLength(), rand);
            numbersTried++;
            if (n.compareTo(lowerBound) >= 0 && n.compareTo(upperBound) <= 0 && isPrime(n)) {
                result[0] = n;
                result[1] = BigInteger.valueOf(numbersTried);
                return result;
            }
        }
    }

    static boolean isPrime(BigInteger n) {
        if (n.compareTo(BigInteger.ONE) <= 0 || n.equals(BigInteger.valueOf(4))) {
            return false;
        }
        if (n.compareTo(BigInteger.valueOf(3)) <= 0) {
            return true;
        }

        BigInteger q = n.subtract(BigInteger.ONE);
        int kValue = calculateK(q);

        Random rand = new Random();

        for (int i = 0; i < 1; i++) {
            BigInteger a = BigInteger.valueOf(2 + rand.nextInt(Math.max(n.intValue() - 2, 1)));
            if (!isWitness(a, q, n, kValue)) {
                return false;
            }
        }
        return true;
    }

    static boolean isWitness(BigInteger a, BigInteger q, BigInteger n, int kValue) {
        BigInteger aPow = fastPower(a, q, n);
        if (aPow.equals(BigInteger.ONE) || aPow.equals(n.subtract(BigInteger.ONE))) {
            return true;
        }
        for (int j = 0; j < kValue - 1; j++) {
            aPow = fastPower(aPow, BigInteger.valueOf(2), n);
            if (aPow.equals(BigInteger.ONE)) {
                return false;
            }
            if (aPow.equals(n.subtract(BigInteger.ONE))) {
                return true;
            }
        }
        return false;
    }

    static int calculateK(BigInteger q) {
        int kValue = 0;
        while (q.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            q = q.divide(BigInteger.valueOf(2));
            kValue++;
        }
        return kValue;
    }

    static BigInteger fastPower(BigInteger g, BigInteger A, BigInteger N) {
        BigInteger a = g;
        BigInteger b = BigInteger.ONE;
        while (A.compareTo(BigInteger.ZERO) > 0) {
            if (A.testBit(0)) {
                b = b.multiply(a).mod(N);
            }
            a = a.multiply(a).mod(N);
            A = A.shiftRight(1);
        }
        return b;
    }
}
