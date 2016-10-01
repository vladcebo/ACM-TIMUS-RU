import java.math.BigInteger;
import java.util.Scanner;

public class KBasedNumbers {


	
	
	public static void main(String[] args) {
		
	    
	    Scanner sc = new Scanner(System.in);
	    int N = sc.nextInt();
	    BigInteger K = BigInteger.valueOf(sc.nextInt());
	 
	    BigInteger M = K.subtract(BigInteger.ONE);
	    BigInteger D = BigInteger.ZERO;
	    int i;
	    for (i = 1; i < N; i++)
	    {
	    	BigInteger T = M;
	        M = M.multiply(K).subtract(D);
	        D = T.subtract(D);
	    }
	    System.out.println(M);
	    
	    sc.close();
	}
	
}