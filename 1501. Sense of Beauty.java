import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class SenseOfBeauty {

    public static int N;

    public static byte[]  s1;
    public static byte[]  s2;
    public static byte[]  p;  /* out */

    public static int[][] Q;
    public static int[][] T;

    public static void leeRouting(int i, int j, int k) {

        if (i > N || i < 0 || j < 0 || j > N)
            return;

        T[i][j] = k;
        if (i < N && abs(Q[i + 1][j]) < 2 && T[i + 1][j] == 0)
            leeRouting(i + 1, j, k + 1);
        if (j < N && abs(Q[i][j + 1]) < 2 && T[i][j + 1] == 0)
            leeRouting(i, j + 1, k + 1);

    }


    public static int sign(byte q) {
        return q == 1 ? 1 : -1;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = Integer.valueOf(sc.nextLine());

        s1 = new byte[N + 1];
        s2 = new byte[N + 1];
        p  = new byte[2*N + 1];
        Q  = new int[N + 1][N + 1];
        T  = new int[N + 1][N + 1];

        String S = sc.nextLine();
        for (int i = 1; i <= N; i++) {
            s1[i]   = (byte)(S.charAt(i - 1) - '0');
            Q[i][0] = Q[i - 1][0] + sign(s1[i]);
        }

        S = sc.nextLine();
        for (int i = 1; i <= N; i++) {
            s2[i] = (byte)(S.charAt(i - 1) - '0');
            Q[0][i] = Q[0][i - 1] + sign(s2[i]);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                Q[i][j] = min(Q[i - 1][j] + sign(s1[i]), Q[i][j - 1] + sign(s2[j]));
            }
        }

        /* Lee algorithm trough the matrix */
        leeRouting(0, 0, 0);

        if (T[N][N] == 0) {
            System.out.println("Impossible");
            System.exit(0);
        }

        int i = N;
        int j = N;
        while (i != 0 || j != 0) {

            if (j > 0 && T[i][j - 1] == T[i][j] - 1) {
                p[i + j] = 2;
                j = j - 1;
            }
            else if (i > 0 && T[i - 1][j] == T[i][j] - 1) {
                p[i + j] = 1;
                i = i - 1;
            }
        }

        for (int k = 1; k <= 2*N; k++) {
            System.out.print(p[k]);
        }


    }


}
