import java.math.BigInteger;
import java.util.Scanner;


public class FibonacciSequence {


    public static void main(String[] args) {

    	
    	/* Read the input */
        Scanner sc = new Scanner(System.in);

        int i   = sc.nextInt();
        long Fi = sc.nextLong();
        int j   = sc.nextInt();
        long Fj = sc.nextLong();
        
        int k   = sc.nextInt(); /* Fk to be found */

        
        /* Obviously we find it's already given at the input */
        if (i == k) {
            System.out.println(Fi);
            System.exit(0);
        } else if (j == k) {
            System.out.println(Fj);
            System.exit(0);
        }
        
        /* Normalize, so Fi < Fj */
        if (j < i) {
            int itmp = i;
            i   = j;
            j   = itmp;
            long tmp = Fi;
            Fi  = Fj;
            Fj  = tmp;
        }
        
        BigInteger fib[] = new BigInteger[2001];
        fib[0] = new BigInteger("0");
        fib[1] = new BigInteger("1");
        for (int h = 2; h <= 2000; h++) {
            fib[h] = fib[h - 1].add(fib[h - 2]);
        }


        BigInteger FI = new BigInteger(String.valueOf(Fi));
        BigInteger FJ = new BigInteger(String.valueOf(Fj));

        /* calculate F(i+1) */
        BigInteger Fip = FJ.subtract(fib[j - i - 1].multiply(FI)).divide(fib[j - i]);
        BigInteger FK  = Fip;
        
        if (k < i) {
            while (--i != k) {
                FK  = Fip.subtract(FI);
                Fip = FI;
                FI  = FK;
            }
        } else {
            while (++i != k) {
                FK  = Fip.add(FI);
                FI  = Fip;
                Fip = FK;
            }
        }

        System.out.println(FK);

        sc.close();
    }

}