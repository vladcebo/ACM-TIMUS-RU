import java.util.Scanner;

public class LuckyTicketsEasy {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt()/2;
		
		int L[][] = new int[N + 1][N*9 + 1];

		for (int d = 0; d <= N; d++) {
			L[d][0] = 1;
		}
		
		for (int d = 1; d <= N; d++) {
			for (int s = 1; s <= N*9; s++) {
				L[d][s] = 0;
				for (int i = 0; (i <= 9) && ((s - i) >= 0); i++) {
					L[d][s] += L[d - 1][s - i];
				}
			}
		}
		
		int S = 0;
		for (int s = 0; s <= N*9; s++) {
			S += L[N][s]*L[N][s];
		}
		System.out.println(S);
		sc.close();

	}

}