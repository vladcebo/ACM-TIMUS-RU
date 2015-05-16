import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;


public class SumOfDigits {
	
	public static int digits[] = new int[100];
	

	 
	public static void main(String[] args) {
	

		int N = 900;
		int M = 8100;
		
		byte Q[][] = new byte[N + 1][M + 1];
		byte P[][] = new byte[N + 1][M + 1];
		Q[0][0]    = 0;
		for (int i = 1; i <= N; i++) {
			if (i < Byte.MAX_VALUE) {
				Q[i][i] = (byte)i;
				P[i][i] = 1;
			}
			else 
				Q[i][i] = Byte.MAX_VALUE;
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= M; j++) {
				
				byte min  = Byte.MAX_VALUE;
				byte mink = Byte.MAX_VALUE;
				for (int k = 9; k > 0; k--) {
					int s = i - k;
					int d = j - k*k;
					if (s > 0  && d > 0 && s <= d) {
						if (Q[s][d] < min) {
							min  = (byte) (Q[s][d] + 1);
							mink = (byte)k; 
						}
					}
					else if (s== 0 && d == 0) {
						min  = 1;
						mink = (byte)k;
					}
				}
				
				Q[i][j] = min;
				P[i][j] = mink;
			}
		}

		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int testCases = sc.nextInt();
		for (int i = 0; i < testCases; i++) {
			
			int s1      = sc.nextInt();
			int s2      = sc.nextInt();

			if (s1 == 0 & s2 == 0) {
				sb.append("No Solution\n");
				continue;
			}
			
			if (s1 > 900 || s2 > 8100 || s1 > s2) {
				sb.append("No solution\n");
				continue;
			}
			else if (Q[s1][s2] > 100) {
				sb.append("No solution\n");
				continue;
			}
			
			byte digits[] = new byte[10];
			while (s1 != 0 && s2 != 0) {
				byte d = P[s1][s2];
				digits[d]++;
				s1 -= d;
				s2 -= d*d;
			}
			
			for (int j = 0; j < 10; j++)
				for (int m = 0; m < digits[j]; m++) {
						sb.append(j);
				}
			sb.append("\n");
			
		}
		System.out.println(sb);
		
	}
	
}