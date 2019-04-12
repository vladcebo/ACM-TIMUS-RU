import java.util.Scanner;

public class DifficultDecision {

	
	public static class ZeroMatrix {
		
		/* find zero matrices, compute sum, done */
		public int values[][];
		public int size;
		
		public ZeroMatrix(int N) {
			values = new int[N][N];
			size   = N;
		}
		
		public static ZeroMatrix zeroPower(ZeroMatrix M, int k) {
			if (k == 1) {
				return M;
			} else {
				if (k % 2 == 0) {
					return zeroPower(zeroMul(M, M), k/2);
				} else {
					return zeroPower(zeroMul(M, M), (k - 1)/2);
				}
			}
		}
		
		public static ZeroMatrix zeroMul(ZeroMatrix A, ZeroMatrix B) {
			
			int N = A.size;
			ZeroMatrix M = new ZeroMatrix(N);
			
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					M.values[i][j] = 0;
					for (int k = 0; k < N; k++) {
						if (A.values[i][k] != 0 && B.values[k][j] != 0) {
							M.values[i][j] = 1;
							break;
						}
					}
				}
			}
			return M;
		}
		
		public static ZeroMatrix zeroSum(ZeroMatrix A, ZeroMatrix B) {
			
			int N = A.size;
			ZeroMatrix M = new ZeroMatrix(N);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					M.values[i][j] = A.values[i][j] + B.values[i][j];
				}
			}
			return M;
		}
		
		@Override
		public String toString() {
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					sb.append(String.format("%2d ", values[i][j]));
				}
				sb.append("\n");
			}
			
			return sb.toString();
		}
		
		public boolean hasZeros() {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (values[i][j] == 0) {
						return true;
					}
				}
			}
			return false;
		}
	}
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		int N = sc.nextInt();
		ZeroMatrix A = new ZeroMatrix(N);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				A.values[i][j] = sc.nextInt();
			}
		}
		if (N == 1) {
			if (A.values[0][0] == 0) {
				System.out.println("No");
			} else {
				System.out.println("Yes");
			}
			System.exit(0);
		}
		
		int kmin = N*(N - 1);
		int kmax = N*(N + 1);
		ZeroMatrix powers[] = new ZeroMatrix[kmax - kmin];
		powers[0] = ZeroMatrix.zeroPower(A, kmin);
		for (int i = 1; i < (kmax - kmin); i++) {
			powers[i] = ZeroMatrix.zeroMul(powers[i - 1], A);
		}
		
		ZeroMatrix zeroSum = powers[0];
		for (int i = 1; i < (kmax - kmin); i++) {
			zeroSum = ZeroMatrix.zeroSum(zeroSum, powers[i]);
		}
		
		if (zeroSum.hasZeros()) {
			System.out.println("No");
		} else {
			System.out.println("Yes");
		}
		
		sc.close();
		
		
	}
	
}