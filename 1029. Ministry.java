import java.util.Scanner;
import static java.lang.Math.*;

public class Ministry {

    public static long[][] T;

    public static int N, M;

    public static void findRoute(int k, int f) {

        if (f == 0) {  /* got to the first floor */
            System.out.print((k + 1) + " ");
            return;
        }

        if (k > 0 && k < N - 1) {
            if (T[f - 1][k] <= T[f][k - 1] && T[f - 1][k] <= T[f][k + 1]) {
                findRoute(k, f - 1);
            } else if (T[f][k - 1] <= T[f - 1][k] && T[f][k - 1] <= T[f][k + 1]) {
                findRoute(k - 1, f);
            } else if (T[f][k + 1] <= T[f - 1][k] && T[f][k + 1] <= T[f][k - 1]) {
                findRoute(k + 1, f);
            }
        } else if (k == 0) {
            if (k == N - 1) {
                findRoute(k, f - 1);
            } else if (T[f - 1][k] < T[f][k + 1]) {
                findRoute(k, f - 1);
            } else {
                findRoute(k + 1, f);
            }
        } else if (k == N - 1) {
            if (k == 0) {
                findRoute(k, f - 1);
            } else if (T[f - 1][k] < T[f][k - 1]) {
                findRoute(k, f - 1);
            } else
                findRoute(k - 1, f);
        }

        System.out.print((k + 1) + " ");

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        N = sc.nextInt();

        int[][] Q = new int[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                Q[i][j] = sc.nextInt();


        T = new long[M][N];
        for (int i = 0; i < N; i++) {
            T[0][i] = Q[0][i];
        }


        for (int i = 1; i < M; i++) {  /* move down floor by floor */

            for (int j = 0; j < N; j++) {
                T[i][j] = T[i - 1][j] + Q[i][j]; /* as if we came from the floor below */
            }

            boolean change = true;
            while (change) {
                change = false;
                long min = 0;
                for (int j = 0; j < N; j++) {
                    if (j > 0 && j < N - 1) {
                        min = (long)Q[i][j] + min(T[i][j - 1], T[i][j + 1]);
                    } else if (j == 0 && j != N -1) {
                        min = (long)Q[i][j] + T[i][j + 1];
                    } else if (j == N - 1 && j != 0) {
                        min = (long)Q[i][j] + T[i][j - 1];
                    }
                    if (T[i][j] > min) {
                        change = true;
                        T[i][j] = min;
                    }
                }
            }
        }


        /* find minimum fee */
        long min = T[M - 1][0];
        int  pos = 0;
        for (int i = 1; i < N; i++) {
            if (T[M - 1][i] < min) {
                min = T[M - 1][i];
                pos = i;
            }
        }


        findRoute(pos, M - 1);

    }



}
