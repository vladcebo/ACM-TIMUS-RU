import java.util.Scanner;
import static java.lang.Math.*;
public class GMPineapple {

	
	public static double pieceSize(double x1, double x2, double bh, double ah) {
		return PI*pow(ah, 2)*((x2 - x1) + 1/(3.0*pow(bh, 2))*(pow(x1, 3) - pow(x2, 3)));
	}
	
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		double a = sc.nextDouble();
		double b = sc.nextDouble();
		int    n = sc.nextInt();
		
		double dx = b/n;
		double x  = -b/2 + dx;
		while (x <= b/2 + 0.000001) {
			System.out.printf("%.6f\n", pieceSize(x - dx, x, b/2, a/2));
			x += dx;
		}
		
		sc.close();
		
	}

}