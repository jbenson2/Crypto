import java.util.*;
import java.lang.Math;

public class FencePost {
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Plaintext: ");
        String plainText = scan.nextLine();
        plainText = plainText.replaceAll("\s", "");
        System.out.print("Enter Fence Depth: ");
        int fenceDepth = scan.nextInt();
        System.out.print("How many rounds?: ");
        int rounds = scan.nextInt();
        System.out.println(encrypt(plainText, fenceDepth, rounds));
        scan.close();
    }

    static char[][] initializeFence(String P, int depth) {
        int columns = (int) Math.ceil((double) P.length() / depth);

        char[][] fence = new char[depth][columns];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < depth; j++) {
                fence[j][i] = ' ';
            }
        }
        return fence;
    }

    static void fillFence(String P, char[][] fence) {
        int pos = 0;
        for (int i = 0; i < fence[0].length; i++) {
            for (int j = 0; j < fence.length; j++) {
                if (pos < P.length()) {
                    fence[j][i] = P.charAt(pos);
                    pos++;
                }
            }
        }
    }

    static String readFence(char[][] fence) {
        StringBuilder cipherText = new StringBuilder("");
        for (int i = 0; i < fence.length; i++) {
            for (int j = 0; j < fence[0].length; j++) {
                if (fence[i][j] != ' ') {
                    cipherText.append(fence[i][j]);
                }
            }
        }
        return cipherText.toString();
    }

    static void printFence(char[][] fence) {
        for (int i = 0; i < fence.length; i++) {
            for (int j = 0; j < fence[0].length; j++) {
                System.out.print(fence[i][j]);
            }
            System.out.println();
        }
    }

    static String encrypt(String P, int depth, int rounds) {
        char[][] fence = initializeFence(P, depth);
        for (int i = 0; i < rounds; i++) {
            fillFence(P, fence);
            P = readFence(fence);
        }
        return P;
    }
}
