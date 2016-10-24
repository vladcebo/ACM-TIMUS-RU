import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Parity {

    
    public static class DSU {
        
        Map<Integer, Integer> root = new HashMap<Integer, Integer>();
        Map<Integer, Integer> size = new HashMap<Integer, Integer>();
        
        private int getRoot(int i) {
            while (root.get(i) != i) {
                i = root.get(i);
            }
            return i;
        }
        
        public int union(int i, int j) {
            
            if (root.get(i) == null) {
                root.put(i, i);
                size.put(i, 1);
            }
            if (root.get(j) == null) {
                root.put(j, j);
                size.put(j, 1);
            }
            
            int root_i = getRoot(i);
            int root_j = getRoot(j);
            int parent = 0;
            /* same set then just return the parent */
            if (root_i == root_j) {
                parent = root_i;
            }
            /* Else different sets */
            else if (size.get(root_i) > size.get(root_j)) {
                parent = root_i;
                root.put(root_j, parent);
            } else {
                parent = root_j;
                root.put(root_i, parent);
            }
            
            return parent;
        }
        
        public boolean find(int i, int j) {
            if (root.get(i) != null && root.get(j) != null) {
                return getRoot(i) == getRoot(j);
            } else {
                return false;
            }
        }   
        
        public Set<Integer> keys() {
            return root.keySet();
        }
        
        public void print() {
            for (Integer key : root.keySet()) {
                System.out.println(key + " = " + root.get(key));
            }
        }
    }
    
    public static void main(String[] args) {
        

        Scanner sc = new Scanner(System.in);
        

        while (true) {
            
            int N = sc.nextInt();
            if (N == -1) {
                System.exit(0);
            }
            
            DSU dsu = new DSU();

            
            int M = sc.nextInt();
            int result = M;
            for (int i = 0; i < M; i++) {
                int k    = sc.nextInt();
                int m    = sc.nextInt() + 1;
                if ((k > N || m > N + 1) && result == M) {
                    result = i;
                }
                String s = sc.next();
                /* odd */
                if (s.charAt(0) == 'o') {
                    dsu.union(k, -m);
                    dsu.union(-k, m);
                } else {
                    dsu.union(k, m);
                    dsu.union(-k, -m);
                }
                
                if ((dsu.find(m, -m) || dsu.find(k, -k)) && result == M) {
                    result = i;
                }
            }
            
            System.out.println(result);
        }
        
    }
}