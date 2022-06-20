package String;

import java.util.HashSet;

public class KMP {
    private final int R;       // the radix
    private final int m;       // length of pattern
    public static int[][] dfa;       // the KMP automoton
    public static boolean[] isAsciiUsed;

    public static int[] xValue;

    public KMP(String pat) {
        this.R = 256;
        this.m = pat.length();
        this.dfa = new int[R][m];
        this.xValue = new int [m];

        // build DFA from pattern
        // dfa = new int[R][m];
        dfa[pat.charAt(0)][0] = 1;
        for (int x = 0, j = 1; j < m; j++) {
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][x];     // Copy mismatch cases.
            dfa[pat.charAt(j)][j] = j + 1;   // Set match case.
            x = dfa[pat.charAt(j)][x];     // Update restart state.
            xValue[j] = x;
        }
    }

    public KMP(char[] pattern, int R) {
        this.R = R;
        this.m = pattern.length;
        this.dfa = new int[R][m];
        this.xValue = new int [m];
        // build DFA from pattern
        int m = pattern.length;
        // dfa = new int[R][m];
        dfa[pattern[0]][0] = 1;
        for (int x = 0, j = 1; j < m; j++) {
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][x];     // Copy mismatch cases.
            dfa[pattern[j]][j] = j + 1;      // Set match case.
            x = dfa[pattern[j]][x];        // Update restart state.
            xValue[j] = x;
        }
    }

    /**
     * Returns the index of the first occurrrence of the pattern string
     * in the text string.
     *
     * @param txt the text string
     * @return the index of the first occurrence of the pattern string
     * in the text string; N if no such match
     */
    public int search(String txt) {

        // simulate operation of DFA on text
        int n = txt.length();
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++) {
            j = dfa[txt.charAt(i)][j];
        }
        if (j == m) return i - m;    // found
        return n;                    // not found
    }

    /**
     * Returns the index of the first occurrrence of the pattern string
     * in the text string.
     *
     * @param text the text string
     * @return the index of the first occurrence of the pattern string
     * in the text string; N if no such match
     */
    public int search(char[] text) {

        // simulate operation of DFA on text
        int n = text.length;
        int i, j;
        for (i = 0, j = 0; i < n && j < m; i++) {
            j = dfa[text[i]][j];
        }
        if (j == m) return i - m;    // found
        return n;                    // not found
    }


    /**
     * Takes a pattern string and an input string as command-line arguments;
     * searches for the pattern string in the text string; and prints
     * the first occurrence of the pattern string in the text string.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        isAsciiUsed = new boolean[256];

        String pat = "ABCDABD";
        String txt = "ABC ABCDAB ABCDABCDABDE";

        for(int i = 0; i < pat.length(); i++)
            isAsciiUsed[(int)pat.charAt(i)] = true;
        for(int i = 0; i < txt.length(); i++)
            isAsciiUsed[(int)txt.charAt(i)] = true;

        char[] pattern = pat.toCharArray();
        char[] text = txt.toCharArray();
        HashSet<String> hashSet = new HashSet();
        for (int i = 0; i < txt.length(); i++) {
            hashSet.add(String.valueOf(text[i]));
        }

        KMP kmp1 = new KMP(pat);
        int offset1 = kmp1.search(txt);

        KMP kmp2 = new KMP(pattern, 256);
        int offset2 = kmp2.search(text);

        // print results
        System.out.println("text:    " + txt);

        System.out.print("pattern: ");
        for (int i = 0; i < offset1; i++)
            System.out.print(" ");
        System.out.println(pat);

        System.out.print("pattern: ");
        for (int i = 0; i < offset2; i++)
            System.out.print(" ");
        System.out.println(pat);


        for (int i = 0; i < dfa.length; i++) {
            if(!isAsciiUsed[i]) continue;

            System.out.print((char) i + ":\t");
            for (int j = 0; j < dfa[i].length; j++) {
                System.out.print(kmp1.dfa[i][j]);
            }
            System.out.println();
        }
        System.out.print("\nX:\t");
        for (int i = 0 ; i< pat.length(); i++){
            System.out.print(kmp1.xValue[i]);
        }
        System.out.println();
        /*for (int i = 0; i < hashSet.size(); i++) {
            for (int j = 0; j < pat.length(); j++) {
                System.out.print(kmp2.dfa[i][j]);
            }
            System.out.println();
        }*/

    }
}