import java.util.Scanner;


public class Spiral {
		
	public static long turns(long N, long M) {

		if (N <= M) {
			return  4*(N/2) - 2*((N + 1) % 2);
		} 

		return 4*(M/2) - 2*((M + 1) % 2) + 1;
		
	}
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		long M = sc.nextLong();
		System.out.println(turns(N, M)); 

	}

}