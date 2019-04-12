import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class ComputerNeet {

		
	public static class Node {
		
		public List<Node> connections;
		public int id;
		
		public Node(int id) {
			this.id = id;
			connections = new LinkedList<Node>();
		}
		
		public void connect(Node n) {
			if (!this.isConnected(n)) {
				connections.add(n);
				n.connections.add(this);
			}
		}
		
		
		public boolean isConnected(Node n) {
			return connections.contains(n);
		}
		
	}
	
	public static ArrayList<Integer> BFS(Node start, int nodes_nr, int dest) {
		
		/* distance from start to every node */
		int dist[]   = new int[nodes_nr + 1];
		int parent[] = new int[nodes_nr + 1];
		for (int i = 1; i <= nodes_nr; i++) {
			dist[i]   = -1;
			parent[i] = -1;
		}
		
		Queue<Node> queue = new LinkedList<>();
		queue.add(start);
		parent[start.id] = start.id;
		int  max_dist = 0;
		Node max_node = start;
		while (!queue.isEmpty()) {
			
			Node node     = queue.remove();
			dist[node.id] = dist[parent[node.id]] + 1;
			if (node.id == dest) {
				max_node = node;
				break;
			}
			if (dist[node.id] > max_dist) {
				max_dist = dist[node.id];
				max_node = node;
			}
			for (Node conn : node.connections) {
				if ((dist[conn.id] == -1)) {
					queue.add(conn);
					parent[conn.id] =  node.id;
				}
			}
			
		}
		
		/* backtrack the path to node with maximum distance */
		ArrayList<Integer> path = new ArrayList<>();
	
		int id = max_node.id;
		while (parent[id] != id) {
			path.add(id);
			id = parent[id];
		}
		path.add(id);
		
		
		return path;
	}
	
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);

		int N            = sc.nextInt();
		List<Node> nodes = new ArrayList<Node>(N + 1);
		
		Node root = new Node(1);
		nodes.add(root);
		nodes.add(1, root);
		for (int i = 2; i <= N; i++) {
			int parent = sc.nextInt();
			Node node = new Node(i);
			nodes.get(parent).connect(node);
			nodes.add(i, node);
		}
		
		ArrayList<Integer> U  = BFS(root, N, -1);
		ArrayList<Integer> V  = BFS(nodes.get(U.get(0)), N, -1);
		
		int mid = V.size()/2;
		if (V.size() % 2 == 0) {
			int min = Math.min(V.get(mid), V.get(mid - 1));
			int max = Math.max(V.get(mid), V.get(mid - 1));
			System.out.printf("%d %d", min, max);
		} else {
			System.out.println(V.get(mid));
		}
		

		sc.close();

	}
}