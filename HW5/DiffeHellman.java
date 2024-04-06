import java.math.BigInteger;
import java.util.Random;

class DiffeHellman {

    private static final BigInteger q = new BigInteger("5332087");
    private static final BigInteger alpha = findPrimitiveRoot();

    public static void main(String[] args) {
        BigInteger Xa = generateRandomBigInteger(q);
        BigInteger Xb = generateRandomBigInteger(q);
        System.out.println("Alice's Private Key: " + Xa);
        System.out.println("Bob's Private Key: " + Xb);
        keyExchange(Xa, Xb);
    }

    static void keyExchange(BigInteger Xa, BigInteger Xb) {
        BigInteger Ya = fastPower(alpha, Xa, q);
        BigInteger Yb = fastPower(alpha, Xb, q);
        BigInteger K = fastPower(Ya, Xb, q);
        System.out.println("Alice's pubic key: " + Ya);
        System.out.println("Bob's public key: " + Yb);
        System.out.println("Shared secret key: " + K);
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

    static BigInteger generateRandomBigInteger(BigInteger q) {
        Random rand = new Random();
        int length = q.bitLength() - 1;
        BigInteger result;
        do {
            result = new BigInteger(length, rand);
        } while (result.compareTo(BigInteger.ONE) < 0 || result.compareTo(q.subtract(BigInteger.ONE)) > 0);
        return result;
    }

    public static BigInteger findPrimitiveRoot() {
        BigInteger phi = q.subtract(BigInteger.ONE);
        for (BigInteger candidate = BigInteger.valueOf(2); candidate.compareTo(q) < 0; candidate = candidate
                .add(BigInteger.ONE)) {
            boolean isPrimitiveRoot = true;
            for (BigInteger i = BigInteger.ONE; i.compareTo(phi) < 0; i = i.add(BigInteger.ONE)) {
                if (candidate.modPow(i, q).equals(BigInteger.ONE)) {
                    isPrimitiveRoot = false;
                    break;
                }
            }
            if (isPrimitiveRoot) {
                return candidate;
            }
        }
        return null;
    }

}
