import static java.lang.Math.abs;

import java.util.Scanner;

public class StonePile {
	
	public static int N       = 0;
	public static int pile1   = 0;
	public static int pile2   = 0;
	public static int minDiff = Integer.MAX_VALUE;
	public static int w[]     = new int[20];
	
	
	public static void findMin(int k) {
		
		if (k == N) {
			if (abs(pile1 - pile2) < minDiff) {
				minDiff = abs(pile1 - pile2);
			}
		} else {
			pile1 += w[k];
			findMin(k + 1);
			pile1 -= w[k];
			pile2 += w[k];
			findMin(k + 1);
			pile2 -= w[k];
		}
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			w[i] = sc.nextInt();
		}
		
		findMin(0);
		
		System.out.println(minDiff);
		
	}
	
}