import static java.lang.Math.*;

import java.util.Scanner;

public class Rope {

	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		int    N = sc.nextInt();
		double R = sc.nextDouble();
		
		double x[] = new double[N + 1];
		double y[] = new double[N + 1];
		
		for (int i = 0; i < N; i++) {
			x[i] = sc.nextDouble();
			y[i] = sc.nextDouble();
		}
		x[N]     = x[0];
		y[N]     = y[0];

		
		double length = 0;
		for (int i = 0; i < N; i++) {
			/* calculate distance between i and i + 1 */
			length += sqrt(pow(x[i + 1] - x[i], 2) + pow(y[i + 1] - y[i], 2));
		}
		
		System.out.printf("%.2f", length + 2*PI*R);
		
		sc.close();
	}
	
}