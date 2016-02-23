import java.util.Scanner;

public class DebutAlbum {
	
	public static int MODULO = 1000000007;

	public static void main(String[] args) {
		
		int Qa[] = new int[50001];
		int Qb[] = new int[50001];
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		Qa[0] = 1;
		Qb[0] = 1;
		Qa[1] = 1;
		Qb[1] = 1;
		
		for (int i = 2; i <= N; i++) {
			int sum = 0;
			for (int k = 1; k <= a  && i - k >= 0; k++) {
				sum += Qb[i - k] % MODULO;
				sum %= MODULO;
			}
			Qa[i] = sum;
			sum = 0;
			for (int k = 1; k <= b && i - k >= 0; k++) {
				sum += Qa[i - k] % MODULO;
				sum %= MODULO;
			}
			Qb[i] = sum;
		}
		
		System.out.println((Qa[N] + Qb[N]) % MODULO);
		
	}
	
}