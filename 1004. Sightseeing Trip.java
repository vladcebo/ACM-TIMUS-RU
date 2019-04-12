import java.util.Scanner;

public class SightseeingTrip {

	public static final int INF = 0x7ffffff;
	public static int dist[][] = new int[110][110];
	public static int pre[][]  = new int[110][110];
	public static int w[][]    = new int[110][110];
	
	public static int N, M, num;
	public static int minc;
	public static int path[] = new int[101];
	
	public static void printMatrix(int W[][], int len) {
		System.out.print("  ");
		for (int i = 1; i <= len; i++) {
			System.out.printf("%4d", i);
		}
		System.out.println();
		for (int i = 1; i <= len; i++) {
			System.out.printf("%d ", i);
			for (int j = 1; j <= len; j++) {
				if (W[i][j] >= INF) {
					System.out.printf("%4s", "inf");
				} else {
					System.out.printf("%4d", W[i][j]);
				}
			}
			System.out.println();
		}
	}
	public static void floyd() {
		
		minc = INF;
		for (int k = 1; k <= N; k++) {
			
			/* this finds and remembers the shortest path from the floyd matrix */
			/* for very pair i, j < k */
			for (int i = 1; i < k; i++) {
				/* no need to verify vertices <= i since the graph is undirected */
				for (int j = i + 1; j < k; j++) {
					/* this is a cycle check, where k is the node on the return path, 
					 * i.e  i -> -> j -> k -> i
					 */
					int ans = dist[i][j] + w[i][k] + w[k][j];
					if (ans < minc) {
						minc = ans;
						num = 0;
						int p = j;
						while (p != i) {
							path[num++] = p;
							p = pre[i][p];
						}
						path[num++] = i;
						path[num++] = k;
					}
				}
			}
			
			/* this is floyd for every vertices */
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (dist[i][j] > dist[i][k] + dist[k][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						pre[i][j]  = pre[k][j];
					}
				}
			}
			
//			System.out.println("k = " + k);
//			printMatrix(dist, N);
//			System.out.println();
		}
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		do {
			
			/* Read Input */
			N = sc.nextInt();
			if (N == -1) {
				break;
			}
			M = sc.nextInt();
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					dist[i][j] = w[i][j] = INF;
					pre[i][j]  = i;
				}
			}
			
			for (int i = 1; i <= M; i++) {
				int u   = sc.nextInt();
				int v   = sc.nextInt();
				int len = sc.nextInt();
				if (dist[u][v] > len) {
					/* keep only the shortest edges in case if there are more */
					w[u][v] = w[v][u] = dist[u][v] = dist[v][u] = len;
				}
			}
//			System.out.println("k = " + 0);
//			printMatrix(dist, N);
//			System.out.println();
			floyd();
			
			if (minc == INF) {
				System.out.println("No solution.");
			} else {
				System.out.printf("%d", path[0]);
				for (int  i = 1; i < num; i++) {
					System.out.printf(" %d", path[i]);
				}
				System.out.println();
			}
			
		} while (true);
		
		sc.close();
		
	}
}