import java.util.Scanner;

public class CodeWords {

	
	/* '2' skip j. */
	public static void printWord(byte[] chars, int len, int replaceId, char replaceChar) {
	
		for (int i = 0; i < len; i++) {
		
			if (i == replaceId && replaceChar == '2')
				continue;
			
			if (i == replaceId)
				System.out.append(replaceChar);
			System.out.append((char)chars[i]);
		}
		if (replaceId == len)
			System.out.append(replaceChar);
		System.out.println();
	}

	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		int N          = sc.nextInt();
		sc.nextLine();
		int fullSum    = 0;
		
		/*  rightCount[i] = number of 1's to the right of i, i included */
		int rightCount[]  = new int[N + 1];
		
		String word = null;
		byte[] chars = new byte[2001];
		int    len   = 0;
		
		while (true) {
			
			try {
				word = sc.nextLine();
			} catch (Exception e) {
				System.exit(0);
			}
			
			len  = 0;
			
			if (word.isEmpty())
				continue;
			
			for (int i = 0;  i < word.length(); i++) {
				if (word.charAt(i) == '0')
					chars[len++] = '0';
				else if (word.charAt(i) == '1')
					chars[len++] = '1';
			}
			
			
			rightCount[len - 1]  = chars[len - 1] - '0';
			fullSum 			 = len * rightCount[len - 1];
			for (int j = len - 2; j >= 0; j--) {
				rightCount[j] = rightCount[j + 1] + (chars[j] - '0');
				fullSum       += (j + 1)*(chars[j] - '0');
			}
			

			/* A char is changed */
			if (len == N) {	
				
				/* if it's already a multiple of (N + 1) or 0, 
				 * nothing to do
				 */
				 if ((fullSum == 0) || (fullSum % (N + 1) == 0)) {
					 printWord(chars, len, -1, '0');
				 }	/* find to replace an 1 to 0 */
				 else {
					 for (int j = 0; j < len; j++) {
						 if ((fullSum - (j + 1) * (chars[j] - '0')) % (N + 1) == 0) {
							 chars[j] = '0';
							 printWord(chars, len, -1, '0');
							 break;
						 }
					 }
				 }
			
			} 
			/* A char is removed */
			else if (len == N - 1) {
				
				if ((fullSum == 0) || (fullSum % (N + 1) == 0)) {
					 printWord(chars, len, len, '0');
				}	
				else {
					/* Find where to put an 0 or one so it will work */
					for (int j = 0; j <= len; j++) {
						
						/* Try to append it at the end */
						if (j == len) {
							if ((fullSum + (j + 1)) % (N + 1) == 0)
								 printWord(chars, len, len, '1');
						}
						/* Try  0 */
						else if ((fullSum + rightCount[j]) % (N + 1) == 0) {
							 printWord(chars, len, j, '0');
							 break;
						} 
						/* Try 1 */
						else if ((fullSum + rightCount[j] + (j + 1)) % (N + 1) == 0) {
							 printWord(chars, len, j, '1');
							 break;
						}
					}
				}
			}
			/* char is added */
			else {
				/* find a char to delete */
				for (int j = 0; j < len; j++) {
					int temp;
					if (j == len - 1)
						temp = 0;
					else 
						temp = rightCount[j + 1];
					if ((fullSum - temp - (j + 1) * (chars[j] - '0')) % (N + 1) == 0) {
						printWord(chars, len, j, '2');
						break;
					}
				}
			}
	
		}
			
	}
	


	
}