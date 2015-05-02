import java.math.BigInteger;
import java.util.Scanner
	
	public static BigInteger luckyTickets(int N, int S) {
	
		if (S % 2 == 1)
			return new BigInteger("0");
		S = S/2;
		
		BigInteger Q[][] = new BigInteger[N + 1][S + 1];
		int max = S > 9 ? 9 : S;
		
		for (int i = 0; i <= N; i++) 
			for(int j = 0; j <= S; j++) 
				Q[i][j] = new BigInteger("0");
		
		for (int i = 0; i <= max; i++) {
			Q[1][i] = new BigInteger("1");
		}
		
		for (int i = 0; i <= N; i++) {
			Q[i][0] = new BigInteger("1");
		}
		
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= S; j++) {
				for (int k = 0; k <= (j > 9 ? 9 : j); k++) {
					Q[i][j] = Q[i][j].add(Q[i-1][j - k]);
				}
			}
		}
		return Q[N][S].multiply(Q[N][S]);
}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int S = sc.nextInt();
		
		sc.close();
		System.out.println(luckyTickets(N, S));
	
	}

}
