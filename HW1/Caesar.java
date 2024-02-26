import java.util.Scanner;

public class Caesar {

    public static final String fileText = "B dghp. Bm'l tee pkhgz. Ur kbzaml px lahnewg'm xoxg ux axkx. \\n" + //
            "\r\n" + //
            "Unm px tkx. Bm'l ebdx bg max zkxtm lmhkbxl, Fk.Ykhwh. \\n" + //
            "\r\n" + //
            "Max hgxl matm kxteer ftmmxkxw. Ynee hy wtkdgxll tgw wtgzxk maxr pxkx. \\n" + //
            "\r\n" + //
            "Tgw lhfxmbfxl rhn wbwg'm ptgm mh dghp max xgw. \\n" + //
            "\r\n" + //
            "Uxvtnlx ahp vhnew max xgw ux atiir? \\n" + //
            "\r\n" + //
            "Ahp vhnew max phkew zh utvd mh max ptr bm ptl paxg lh fnva utw atw atiixgxw? \\n" + //
            "\r\n" + //
            "Unm bg max xgw, bm'l hger t itllbgz mabgz, mabl latwhp. \\n" + //
            "\r\n" + //
            "Xoxg wtkdgxll fnlm itll. T gxp wtr pbee vhfx. \\n" + //
            "\r\n" + //
            "Tgw paxg max lng labgxl bm pbee labgx hnm max vextkxk. \\n" + //
            "\r\n" + //
            "Mahlx pxkx max lmhkbxl matm lmtrxw pbma rhn. \\n" + //
            "\r\n" + //
            "Matm fxtgm lhfxmabgz. Xoxg by rhn pxkx mhh lftee mh ngwxklmtgw par. \\n" + //
            "\r\n" + //
            "Unm B mabgd, Fk. Ykhwh, B wh ngwxklmtgw. B dghp ghp. \\n" + //
            "\r\n" + //
            "Yhed bg mahlx lmhkbxl atw ehml hy vatgvxl hy mnkgbgz utvd hger maxr wbwg'm. \\n" + //
            "\r\n" + //
            "Uxvtnlx maxr pxkx ahewbgz hg mh lhfxmabgz. \\n" + //
            "\r\n" + //
            ". \\n" + //
            "\r\n" + //
            ". \\n" + //
            "\r\n" + //
            ". \\n" + //
            "\r\n" + //
            "Matm maxkx'l lhfx zhhw bg mabl phkew, Fk. Ykhwh. \\n" + //
            "\r\n" + //
            "Tgw bm'l phkma ybzambgz yhk.";

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        boolean isCorrect = false;
        int key = 1;
        System.out.println("Encrypted Song Title: " + CaesarEncrypt(9, "The Race Is On"));
        do {
            System.out.println("Decrypted Song Title (Key: " + key + "): " + CaesarDecrypt(key, "Cqn Ajln Rb Xw"));
            System.out.print("Does this look correct? (y/n): ");
            String correct = scanner.nextLine();
            if (correct.equals("y")) {
                isCorrect = true;
            } else {
                key++;
            }
        } while (!isCorrect);
        isCorrect = false;
        do {
            System.out.println("Decrypted Song Title (Key: " + key + "): " + CaesarDecrypt(key, fileText));
            System.out.print("Does this look correct? (y/n): ");
            String correct = scanner.nextLine();
            if (correct.equals("y")) {
                isCorrect = true;
            } else {
                key++;
            }
        } while (!isCorrect);
        scanner.close();
    }

    static String CaesarEncrypt(int K, String M) {
        String C = "";
        for (char c : M.toCharArray()) {
            if (Character.isLetter(c)) {
                int plainPos = Character.isUpperCase(c) ? c - 'A' : c - 'a';
                int cipherPos = (plainPos + K) % 26;
                char cipherChar = Character.isUpperCase(c) ? (char) ('A' + cipherPos) : (char) ('a' + cipherPos);
                C += cipherChar;
            } else {
                C += c;
            }
        }
        return C.toString();
    }

    static String CaesarDecrypt(int K, String M) {
        String P = "";
        for (char c : M.toCharArray()) {
            if (Character.isLetter(c)) {
                int plainPos = Character.isUpperCase(c) ? c - 'A' : c - 'a';
                int cipherPos = (plainPos - K + 26) % 26;
                char cipherChar = Character.isUpperCase(c) ? (char) ('A' + cipherPos) : (char) ('a' + cipherPos);
                P += cipherChar;
            } else {
                P += c;
            }
        }
        return P.toString();
    }
}