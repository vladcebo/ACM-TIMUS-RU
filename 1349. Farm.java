
import java.util.Scanner;

public class Fermat {

	public static void main(String[] args) {
		Scanner scann = new Scanner(System.in);

		
		int numb = scann.nextInt();

		for (int a = 1; a <= 98; a++) {
			for (int b = a + 1; b <= 99 ; b++) {
				for (int c = b + 1; c <= 100; c++) {
					if(Math.pow(a, numb) + Math.pow(b, numb) == Math.pow(c, numb)){
						System.out.println(a + " "+ b +" "+ c);
						System.exit(0);
					}
					
				}
			}
		}
		
		System.out.println("-1");
	}
}