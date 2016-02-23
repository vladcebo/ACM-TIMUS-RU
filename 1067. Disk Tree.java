import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class DiskTree {

	public static class Directory {
		public String name;
		public LinkedList<Directory> child;
		
		public Directory(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	
	public static Directory putSorted(LinkedList<Directory> tree, String name) {
		
		ListIterator<Directory> it = tree.listIterator();
		
		Directory newDir = new Directory(name);
		
		boolean inserted = false;
		
		while (it.hasNext()) {
			Directory dir = it.next();
			int compare = dir.name.compareTo(name);
			/* dir == name */
			if (compare == 0) {
				inserted = true;
				newDir   = dir;
				break;
			}  /* maintain lexicographic order */
			else if (compare > 0) {
				inserted = true;
				it.previous();
				it.add(newDir);
				break;
			}
		}
		
		/* Put it at the end of the tree */
		if (!inserted) {
			tree.add(newDir);
		}
		
		return newDir;
	}
	
	
	public static void put(LinkedList<Directory> tree, String directory) {
		
		int slashId = directory.indexOf('\\');

		
		if (slashId == -1) {
			/* No more directories */
			putSorted(tree, directory);
		} 
		
		else {
			
			String currentDirectory = directory.substring(0, slashId);
			String nextDirectory    = directory.substring(slashId + 1);

			Directory dir = putSorted(tree, currentDirectory);
			if (dir.child == null)
				dir.child = new LinkedList<Directory>();
			
			/* put the current directory in the tree */
			put(dir.child, nextDirectory);
		}
		
	}
	
	public static void printDirectory(LinkedList<Directory> tree, int level) {
		
		if (tree != null) {
			for (Directory dir : tree) {
				for (int j = 0; j < level; j++) {
					System.out.append(' ');
				}
				System.out.println(dir.name);
				printDirectory(dir.child, level + 1);
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		LinkedList<Directory> tree = new LinkedList<Directory>();
		
		Scanner sc = new Scanner(System.in);
		
		
		
		int N = sc.nextInt();
		sc.nextLine();
		
		for (int i = 0; i < N; i++) {
			put(tree, sc.nextLine());
		}
		
		printDirectory(tree, 0);
	}
	
}