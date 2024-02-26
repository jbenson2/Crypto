public class Permutation {
    public static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String args[]) {
        System.out.println(PermCipher("This Too Shall Pass", "qwertyuiopasdfghjklzxcvbnm"));
    }

    static String PermCipher(String M, String K) {
        String C = "";
        for (int i = 0; i < M.length(); i++) {
            for (int j = 0; j < 26; j++) {
                if (M.charAt(i) == Character.toUpperCase(ALPHA.charAt(j))) {
                    C += Character.toUpperCase(K.charAt(j));
                    break;
                }
                if (M.charAt(i) == ALPHA.charAt(j)) {
                    C += K.charAt(j);
                    break;
                }
                if (!Character.isLetter(M.charAt(i))) {
                    C += M.charAt(i);
                    break;
                }
            }
        }
        return C;
    }
}