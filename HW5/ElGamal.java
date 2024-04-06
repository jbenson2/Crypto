import java.math.BigInteger;
import java.util.Random;

class ElGamal {

    private static final BigInteger q = new BigInteger("5332087");
    private static final BigInteger alpha = findPrimitiveRoot();

    public static void main(String[] args) {
        BigInteger Xa = generateRandomBigInteger(q);
        System.out.println("Alice's Private Key: " + Xa);
        BigInteger[] alicePubKey = AliceKeyGen(Xa);
        System.out
                .println("Alice Public Key: {" + alicePubKey[0] + ", " + alicePubKey[1] + ", " + alicePubKey[2] + "}");
        BigInteger[] encrypted = BobEncrypt(alicePubKey);
        System.out.println("Bob Encrypted: (" + encrypted[0] + ", " + encrypted[1] + ")");
        BigInteger decrypted = AliceDecrypt(encrypted, Xa);
        System.out.println("Alice Decrypted: " + decrypted);
    }

    static BigInteger[] AliceKeyGen(BigInteger Xa) {
        BigInteger[] publicKey = new BigInteger[3];
        publicKey[0] = q;
        publicKey[1] = alpha;
        publicKey[2] = alpha.modPow(Xa, q);
        return publicKey;
    }

    static BigInteger[] BobEncrypt(BigInteger[] alicePubKey) {
        BigInteger[] encrypted = new BigInteger[2];
        BigInteger M = new BigInteger("70256");
        BigInteger k = generateRandomBigInteger(q);
        BigInteger K = alicePubKey[2].modPow(k, alicePubKey[0]);
        BigInteger C1 = alicePubKey[1].modPow(k, alicePubKey[0]);
        BigInteger KM = K.multiply(M);
        BigInteger C2 = KM.mod(alicePubKey[0]);
        encrypted[0] = C1;
        encrypted[1] = C2;
        return encrypted;
    }

    static BigInteger AliceDecrypt(BigInteger[] encrypted, BigInteger Xa) {
        BigInteger KInverse = encrypted[0].modPow(Xa, q).modInverse(q);
        BigInteger decrypted = encrypted[1].multiply(KInverse).mod(q);
        return decrypted;
    }

    static BigInteger generateRandomBigInteger(BigInteger q) {
        Random rand = new Random();
        BigInteger result;
        do {
            result = new BigInteger(q.bitLength(), rand);
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
