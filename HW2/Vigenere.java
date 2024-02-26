import java.util.*;

public class Vigenere {

    final static String ALPHABET = "abcdefghijklmnopqrstuvwxyz ";

    public static void main(String args[]) {
        int menuChoice = 0;
        Scanner scan = new Scanner(System.in);
        String K = "";
        do {
            showMenu();
            menuChoice = scan.nextInt();
            scan.nextLine();
            switch (menuChoice) {
                case 1:
                    System.out.print("Enter Plaintext: ");
                    String P = scan.nextLine();
                    System.out.print("Enter Key: ");
                    K = scan.nextLine();
                    String cipherText = encrypt(P, K);
                    System.out.println("Ciphertext: " + cipherText);
                    System.out.println("Press Any Key To Continue...");
                    scan.nextLine();
                    break;
                case 2:
                    System.out.print("Enter Ciphertext: ");
                    String C = scan.nextLine();
                    System.out.print("Enter Key: ");
                    K = scan.nextLine();
                    String plainText = decrypt(C, K);
                    System.out.println("Plaintext: " + plainText);
                    System.out.println("Press Any Key To Continue...");
                    scan.nextLine();
                    break;
                default:
                    System.exit(0);
            }
        } while (menuChoice != 3);
        scan.close();
    }

    static String encrypt(String P, String K) {
        StringBuilder cipherText = new StringBuilder("");
        K = generateKey(P, K);
        for (int i = 0; i < P.length(); i++) {
            int shift = 0;
            if (ALPHABET.indexOf(Character.toLowerCase(P.charAt(i))) != -1) {
                shift = getShiftValue(K.charAt(i));
                cipherText.append(encryptLetter(P.charAt(i), shift));
            }
        }
        return cipherText.toString();
    }

    static String decrypt(String C, String K) {
        StringBuilder plainText = new StringBuilder("");
        K = generateKey(C, K);
        for (int i = 0; i < C.length(); i++) {
            int shift = 0;
            if (ALPHABET.indexOf(Character.toLowerCase(C.charAt(i))) != -1) {
                shift = getShiftValue(K.charAt(i));
                plainText.append(decryptLetter(C.charAt(i), shift));
            }
        }
        return plainText.toString();
    }

    static int getShiftValue(char K) {
        return ALPHABET.indexOf(Character.toLowerCase(K));
    }

    static char encryptLetter(char C, int shift) {
        return Character.isUpperCase(C)
                ? Character.toUpperCase(ALPHABET.charAt((ALPHABET.indexOf(Character.toLowerCase(C)) + shift) % 27))
                : ALPHABET.charAt((ALPHABET.indexOf(C) + shift) % 27);
    }

    static char decryptLetter(char C, int shift) {
        return Character.isUpperCase(C)
                ? Character.toUpperCase(ALPHABET.charAt((ALPHABET.indexOf(Character.toLowerCase(C)) - shift + 27) % 27))
                : ALPHABET.charAt((ALPHABET.indexOf(C) - shift + 27) % 27);
    }

    static String generateKey(String P, String K) {
        StringBuilder newKey = new StringBuilder(K);
        int PLength = P.length();
        while (newKey.length() < PLength) {
            newKey.append(K);
        }
        return newKey.substring(0, PLength);
    }

    static void showMenu() {
        System.out.println("Vigenere Cipher");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");
        System.out.println("3. Exit");
        System.out.print("Enter option: ");
    }
}