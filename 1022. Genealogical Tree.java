import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GenealogicalTree {

	
	public static List<Integer> G[];
	public static boolean marked[];
	public static List<Integer> sorted = new LinkedList<Integer>();
	
	public static void DFS(int node) {
		
		if (!marked[node]) {
			
			for (Integer n : G[node]) {
				DFS(n);
			}
			marked[node] = true;
			sorted.add(0,node);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		G       = new List[N + 1];
		marked  = new boolean[N + 1];	
		
		for (int i = 1; i <= N; i++) {
			
			int k = sc.nextInt();
			G[i] = new ArrayList<Integer>();
			while (k != 0) {
				
				/* i->k edge */
				G[i].add(k);
				k = sc.nextInt();
			}
		}
		
		for (int i = 1; i <= N; i++) {
			DFS(i);
		}
		
		for (Integer i : sorted) {
			System.out.printf("%d ", i);
		}
		
		sc.close();
	}
}