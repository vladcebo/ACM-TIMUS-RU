
import java.util.Scanner;

public class Factorials {

	static Scanner scann = new Scanner(System.in);

	public static void main(String[] args) {

		int numb = scann.nextInt();
		String semne = scann.next();

		int howManySemne = 0;

		char[] semneToChar = semne.toCharArray();
		for (char c : semneToChar) {
			howManySemne++;
		}
		
		int k = 1;
		for (int i = numb; i > 0; i = i - howManySemne) {
			k = k* (numb);
			numb -= howManySemne;
		}

		System.out.println(k);

	}
}