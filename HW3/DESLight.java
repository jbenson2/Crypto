import java.lang.Math;

class DESLight {
    public static void main(String[] args) {
        String originalBitPattern = "1011010011010010";
        String originalKey = "1101010110100110";
        int numRounds = 16;
        System.out.println("Encryption:");
        String values[] = encrypt(originalBitPattern, originalKey, numRounds);
        System.out.println("\nDecryption:");
        decrypt(values[0], values[1], numRounds);
    }

    public static String[] encrypt(String bitPattern, String key, int numRounds) {
        StringBuilder outputString = new StringBuilder(bitPattern);
        int substringLength = bitPattern.length() / 2;
        String L, R, subKey, L1, L2;
        for (int i = 0; i < numRounds; i++) {
            L = outputString.substring(0, substringLength);
            R = outputString.substring(substringLength);
            outputString.setLength(0);
            outputString.append(R);
            subKey = key.substring(0, substringLength);
            L1 = flipBits(L, subKey, i);
            L2 = xor(L1, R);
            outputString.append(L2);
            key = keyShift(key);
            System.out.println("Round: " + (i + 1) + " | Bitstring: " + outputString.toString());
        }
        String returnedValues[] = { outputString.toString(), key };
        return returnedValues;
    }

    public static void decrypt(String bitPattern, String key, int numRounds) {
        StringBuilder outputString = new StringBuilder(bitPattern);
        int substringLength = bitPattern.length() / 2;
        String L, R, subKey, L1, L2;
        for (int i = numRounds; i > 0; i--) {
            key = reverseKeyShift(key);
            R = outputString.substring(0, substringLength);
            L2 = outputString.substring(substringLength);
            L1 = xor(R, L2);
            subKey = key.substring(0, substringLength);
            L = flipBits(L1, subKey, i - 1);
            outputString.setLength(0);
            outputString.append(L).append(R);
            System.out.println("Round: " + i + " | Bitstring: " + outputString.toString());
        }
    }

    public static String xor(String L1, String R) {
        StringBuilder L2 = new StringBuilder();
        for (int i = 0; i < L1.length(); i++) {
            L2.append(L1.charAt(i) ^ R.charAt(i));
        }
        return L2.toString();
    }

    public static String flipBits(String bitPattern, String key, int round) {
        StringBuilder newBitPattern = new StringBuilder();
        for (int i = 0; i < bitPattern.length(); i++) {
            char currentBit = bitPattern.charAt(i);
            char currentKey = key.charAt(i);
            boolean flip = (round % 2 == 0 && ((i % 2 == 0 && currentKey == '1') || (i % 2 != 0 && currentKey == '0')))
                    || (round % 2 != 0 && ((i % 2 == 0 && currentKey == '0') || (i % 2 != 0 && currentKey == '1')));
            char newBit = (flip && currentBit == '0') || (!flip && currentBit == '1') ? '1' : '0';
            newBitPattern.append(newBit);
        }
        return newBitPattern.toString();
    }

    public static String keyShift(String key) {
        int substringLength = key.length() / 2;
        int bitsToRemove = (int) Math.pow(2, Math.log(key.length()) / Math.log(2) - 4);
        String L = key.substring(0, substringLength);
        String R = key.substring(substringLength);
        String newL = L.substring(bitsToRemove);
        String newR = R.substring(bitsToRemove);
        String k1 = L.substring(0, bitsToRemove);
        String k2 = R.substring(0, bitsToRemove);
        return newL + newR + k1 + k2;
    }

    public static String reverseKeyShift(String key) {
        int substringLength = key.length() / 2;
        int bitsToRemove = (int) Math.pow(2, (Math.log(key.length()) / Math.log(2)) - 4);
        String removedBits = key.substring(key.length() - bitsToRemove * 2);
        String k1 = removedBits.substring(0, removedBits.length() / 2);
        String k2 = removedBits.substring(removedBits.length() / 2);
        substringLength -= bitsToRemove;
        String L = key.substring(0, substringLength);
        String R = key.substring(substringLength, key.length() - bitsToRemove * 2);
        return k1 + L + k2 + R;
    }
}