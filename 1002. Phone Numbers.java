import java.util.Scanner;

public class PhoneNumbers {

    public static final int INFINITY = -1;

    static String N = ""; /* Phone number */
    static int    W = 0;  /* nr of words */

    static String[] dict = null; /* dictionary */

    static int[][] Q; /* optimization matrix */
    static int[][] T; /* memory matrix */

    public static void print(int[][] M) {
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < N.length(); j++) {
                System.out.printf("%3d ", M[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean phoneMatch(char letter, char number) {

        switch (number) {
            case '1' : return (letter == 'i' || letter == 'j');
            case '2' : return (letter == 'a' || letter == 'b' || letter == 'c');
            case '3' : return (letter == 'd' || letter == 'e' || letter == 'f');
            case '4' : return (letter == 'g' || letter == 'h');
            case '5' : return (letter == 'k' || letter == 'l');
            case '6' : return (letter == 'm' || letter == 'n');
            case '7' : return (letter == 'p' || letter == 'r' || letter == 's');
            case '8' : return (letter == 't' || letter == 'u' || letter == 'v');
            case '9' : return (letter == 'w' || letter == 'x' || letter == 'y');
            case '0' : return (letter == 'o' || letter == 'q' || letter == 'z');
        }
        return false;
    }

    public static boolean isMatch(int w, int p) {

        if (dict[w].length() > N.length() - p) {
            return false;   /* no match since word is longer */
        }

        for (int i = 0, n = dict[w].length(); i < n; i++) {
            if (!phoneMatch(dict[w].charAt(i), N.charAt(p + i))) {
                return false;
            }
        }
        return true;
    }

    public static int q(int w, int p) throws Exception {

        if (p >= N.length())
            return 0;
        if (Q[w][p] == 0) {
            if (!isMatch(w, p)) {
                Q[w][p] = Integer.MAX_VALUE;  /* aka infinity */
            } else {
                int min = Integer.MAX_VALUE;
                for (int i = 0; i < W; i++) {
                    int t = q(i, p + dict[w].length());
                    if (t < min) {
                        min = t;
                        T[w][p] = i;
                    }
                }
                if (min != Integer.MAX_VALUE) /* otherwise overflow occurs */
                    Q[w][p] = 1 + min;
                else
                    Q[w][p] = Integer.MAX_VALUE;
            }
        }
        return Q[w][p];

    }

    public static void main(String[] args) throws Exception{

        Scanner sc = new Scanner(System.in);

        while (true) {

            N = sc.nextLine();
            if (N.equals("-1")) {
                System.exit(0);
            }

            W = Integer.valueOf(sc.nextLine());
            dict  = new String[W];
            for (int i = 0; i < W; i++) {
                dict[i] = sc.nextLine();
            }

            Q = new int[W][N.length()];
            T = new int[W][N.length()];

            for (int i = 0; i < W; i++) /* run for every word */
                q(i, 0);

            /* find the minimum starting word */
            int min = Integer.MAX_VALUE;
            int w   = 0;
            for (int i = 0; i < W; i++) {
                if (Q[i][0] < min) {
                    min = Q[i][0];
                    w   = i;
                }
            }

            /* print the minimum word sequence */
            if (min == Integer.MAX_VALUE) {
                System.out.println("No solution.");
                continue;
            }

            int p = 0;
            while (p != N.length())  {
                System.out.print(dict[w] + " ");
                int t = p + dict[w].length();
                w = T[w][p];
                p = t;
            }
            System.out.println();


        }




    }

}
