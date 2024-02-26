import java.util.Scanner;

public class Vernam {
    static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz ";

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
            if (ALPHABET.indexOf(Character.toLowerCase(P.charAt(i))) != -1) {
                cipherText.append(encryptLetter(P.charAt(i), K.charAt(i)));
            }
        }
        return cipherText.toString();
    }

    static String decrypt(String C, String K) {
        StringBuilder cipherText = new StringBuilder("");
        K = generateKey(C, K);
        for (int i = 0; i < C.length(); i++) {
            if (ALPHABET.indexOf(Character.toLowerCase(C.charAt(i))) != -1) {
                cipherText.append(decryptLetter(C.charAt(i), K.charAt(i)));
            }
        }
        return cipherText.toString();
    }

    static int convertToBinary(char K) {
        int index = ALPHABET.indexOf(K);
        if (index < 0 || index > 26) {
            return -1;
        }
        StringBuilder binary = new StringBuilder();
        while (index > 0) {
            binary.insert(0, index % 2);
            index = index / 2;
        }
        while (binary.length() < 5) {
            binary.insert(0, "0");
        }
        return Integer.parseInt(binary.toString(), 2);
    }

    static char encryptLetter(char P, char K) {
        int p = convertToBinary(P);
        int k = convertToBinary(K);
        return ALPHABET.charAt((p ^ k) % 27);
    }

    static char decryptLetter(char C, char K) {
        int c = convertToBinary(C);
        int k = convertToBinary(K);
        return ALPHABET.charAt(((c ^ k) + 27) % 27);
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
        System.out.println("Vernam Cipher");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");
        System.out.println("3. Exit");
        System.out.print("Enter option: ");
    }
}
