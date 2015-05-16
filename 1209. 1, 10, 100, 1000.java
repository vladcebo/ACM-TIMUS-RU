import java.util.Scanner;
import static java.lang.Math.*;

public class OneZeros {

	public static void main(String[] args)  {

		Scanner sc = new Scanner(System.in);
		long N = sc.nextLong();
		
		for (long i = 0; i < N; i++) {
			long k = sc.nextLong() - 1;
			
			double x = (sqrt(1 + 8*k) - 1)/2;
			if (abs(x - floor(x)) < 10e-7) {
				System.out.print("1 ");
			} else
				System.out.print("0 ");
		}
		
		sc.close();
		
	}

}