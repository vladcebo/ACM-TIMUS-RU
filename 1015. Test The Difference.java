import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TestTheDifference {
	

	public static long cycleCheckSum(int a, int b, int c, int d) {
		return (1L << (a*b + (a > b ? 1 : 0))) + (1L <<(b*c + (b > c ? 1 :0))) + 
			   (1L << (c*d + (c > d ? 1 : 0))) + (1L << (d*a + (d > a ? 1 :0)));
	}
	
	public static long checkSum(int left, int right, int top, int front, int bottom, int back) {
		long c1 = top > bottom ? cycleCheckSum(front, right, back, left)
							   : cycleCheckSum(front, left, back, right);  
		long c2 = front > back ? cycleCheckSum(top, left, bottom, right)   
							   : cycleCheckSum(top, right, bottom, left);   
		long c3 = left > right ? cycleCheckSum(top, back, bottom, front)    
							   : cycleCheckSum(top, front, bottom, back);
		long c5 = (c1 + c2 + c3) >> 4;
		return c5;
	}
	
	
	public static int N;
	
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		int count = 0;
		
		HashMap<Long, Integer> checksumMap    = new HashMap<Long, Integer>();
		ArrayList<ArrayList<Integer>> result  = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			
			int left   = sc.nextInt();
			int right  = sc.nextInt();
			int top    = sc.nextInt();
			int front  = sc.nextInt();
			int bottom = sc.nextInt();
			int back   = sc.nextInt();
			
			long checksum = checkSum(left, right, top, front, bottom, back);
			
			if (checksumMap.get(checksum) == null) {
				checksumMap.put(checksum, count);
				result.add(new ArrayList<Integer>());
				count = count + 1;
			}
			
			result.get(checksumMap.get(checksum)).add(i + 1);
		}
			
		System.out.println(count);
		for (int i = 0; i < count; i++) {
			List<Integer> list = result.get(i);
			for (Integer r : list) {
				System.out.print(r);
				System.out.print(" ");
			}
			System.out.println();
		}
		
	}
		
	
}