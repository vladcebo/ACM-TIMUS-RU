import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class LinePainting {

	
	public static final int LEFT  = 0;
	public static final int RIGHT = 1;

	public static class Interval {
		
		public int x, y;
		
		public Interval(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		
		LinkedList<Interval> whites = new LinkedList<>();
		/* Initialy the line is white */
		whites.add(new Interval(0, 1000000000)); 
		
		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			
			int  x = sc.nextInt();
			int  y = sc.nextInt();
			char c = (char) sc.next().charAt(0);
			
			Interval k = new Interval(x, y);
		
			if (c == 'b') {
				ListIterator<Interval> iterator = whites.listIterator();
				while (iterator.hasNext()) {
					
					Interval j = iterator.next();

					/* 1) k is totally enclosed within j */
					if (k.x >= j.x && k.y <= j.y) {
						/* Then split the jth interval */
						Interval splitRight = new Interval(k.y, j.y);
						if (j.x == k.x)
							iterator.remove();
						else {
							j.y = k.x;
						}
						if (k.y != j.y)
							iterator.add(splitRight);
					} /* 2) j is totally enclosed within k */ 
					else if (k.x <= j.x && k.y >= j.y) {
						iterator.remove();
					} /* 3) k is to the left of j , intersecting it*/ 
					else if (k.x <= j.x && k.y >= j.x)  {
						j.x = k.y;
					} /* 4) k is to the right of j, intersecting it */
					else if (k.x <= j.y && k.y >= j.y) {
						j.y = k.x;
					}
					
				}
				
			} 
				/* It's white */
			else {

				ListIterator<Interval> iterator = whites.listIterator();
				boolean outside = true;
				while (iterator.hasNext()) {
					
					Interval j = iterator.next();

					/* Is within a white zone, do nothing */
					if (k.x >= j.x && k.y <= j.y) {
						outside = false;
					} 
					else if (k.x <= j.x && k.y >= j.y) {
						iterator.remove();
					}
					/* is strictly left and intersecting */
					else if (k.x < j.x && k.y >= j.x)  {
						outside = false;
						j.x = k.x;
						k.y = j.y;
					} /* is strictly right and intersecting */
					else if (k.x <= j.y && k.y > j.y) {
						outside = false;
						j.y = k.y;
						k.x = j.x;
					}
				}
				
				if (outside) {
					whites.add(new Interval(k.x, k.y));
				}
			}
		}
		 
		int xmax = whites.getFirst().x;
		int ymax = whites.getFirst().y;
		for (Interval w : whites) {
			if ((w.y - w.x) > (ymax - xmax)) {
				ymax = w.y;
				xmax = w.x;
			}
		}
		
		System.out.println(xmax + " " + ymax);
	
	}
}