
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class HistoryExam {

	public static void main(String[] args) throws IOException {
		
		
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));


		int profList[];
		int listOne = Integer.parseInt(bi.readLine());
		profList = new int[listOne];
		
		for (int i = 0; i < listOne; i++) {
			profList[i] = Integer.parseInt(bi.readLine());
		}

		int listTwo =Integer.parseInt(bi.readLine());
		int count = 0;
		for (int i = 0; i < listTwo; i++) {
			if (Arrays.binarySearch(profList,Integer.parseInt(bi.readLine())) >= 0)
				count++;
		}
		
		System.out.println(count);
		
	}

}