import java.util.Arrays;
import java.util.Scanner;

public class SacramentOfTheSum {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N[] = new int[sc.nextInt()];
		for (int i = 0; i < N.length; i++) {
			N[i] = sc.nextInt();
		}
		
		int M[] = new int[sc.nextInt()];
		for (int i = 0; i < M.length; i++) {
			M[i] = sc.nextInt();
		}
		
		Arrays.sort(N);
		Arrays.sort(M);;
		
		for (int i = 0; i < N.length; i++) {
			if (Arrays.binarySearch(M, 10000 - N[i]) >= 0) {
				System.out.println("YES");
				System.exit(0);
			}
		}
		
		System.out.println("NO");
		
	}
}