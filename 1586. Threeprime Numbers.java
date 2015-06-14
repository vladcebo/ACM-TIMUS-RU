import static java.lang.Math.*;

import java.util.Scanner;



public class ThreePrimeNumbers {
	
	public static boolean isThreePrime(int n) {
		
		if (n % 2 == 0 || n > 999 || n < 100) {
			return false;
		}
		
		for (int i = 3, m = (int)sqrt(n); i <= m; i = i + 2) {
			if (n % i == 0) 
				return false;
		}
		
		return true;
		
	}
	
	
	public static void main(String[] args) {
		
		long[]      QE = new long[100]; /* QE[d2, d1] = nr of numbers in the current set that
										   have a [d2, d1] ending */
 		
		boolean[][] NE = new boolean[100][10]; /* true if NE[d2, d1][d0] is prime */
		
		for (int i = 1; i <= 99; i++) {
			for (int j = 1; j <= 9; j++) {
				if (isThreePrime(i * 10 + j)) {
					NE[i][j] = true;
					QE[(i % 10)*10 + j]++;
				}
			}
		}
		
		Scanner sc = new Scanner(System.in);
		int  N = sc.nextInt();
		long M = 1000000009;
		for (int i = 3; i < N; i++) {
			long[]  TEMP_QE = new long[100];
			for (int j = 11; j <= 99; j++) {	
				if (QE[j] != 0)  { /* we have this ending in the set */	
					for (int k = 1; k <= 9; k++) {
						if (NE[j][k] == true) {
							TEMP_QE[(j % 10)*10 + k] += QE[j] % M;
						}
					}
				}
			}
			QE = TEMP_QE;
		}
		
		long k = 0;	
		for (int i = 0; i < 100; i++) {
			k += QE[i] % M;
		}
		sc.close();
		System.out.println(k % M);
	}

}
