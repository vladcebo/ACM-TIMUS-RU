import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.*;

public class Titanic {

	public static final String test = "Message #513.\n" + 
									  "Received at 22:30:11.\n" + 
									  "Current ship's coordinates are\n" +  
									  "41^46'00\" NL\n" + 
									  "and 50^14'00\" WL." + 
									  "An iceberg was noticed at" + 
									  "41^14'11\" NL" + 
									  "and 51^09'00\" WL." + 
									  "===";

	
	public static double toRad(int D, int M, int S, String region) {
		
		double angle = 0;
		
		angle = Math.PI/180*(D + M/60.0 + S/3600.0);
		
		if (region.equals("SL") || region.equals("WL")) {
			angle = - angle;
		}
		
		return angle;
		
	}
	public static void main(String[] args) {

		Pattern pattern = Pattern.compile("(\\d{1,3})\\^(\\d{1,2})'(\\d{1,2})\"\\s*(NL|SL|WL|EL)");
		
		Scanner sc = new Scanner(System.in);
		String  s  = "";
		while (sc.hasNext()) {
			s += sc.nextLine();
		}
		
		Matcher matcher = pattern.matcher(s);
		
		matcher.find();
		double X1 = toRad(Integer.valueOf(matcher.group(1)), 
						  Integer.valueOf(matcher.group(2)), 
						  Integer.valueOf(matcher.group(3)),
						  matcher.group(4));
		matcher.find();
		double Y1 = toRad(Integer.valueOf(matcher.group(1)), 
						  Integer.valueOf(matcher.group(2)), 
						  Integer.valueOf(matcher.group(3)),
						  matcher.group(4));
		matcher.find();
		double X2 = toRad(Integer.valueOf(matcher.group(1)), 
						  Integer.valueOf(matcher.group(2)), 
						  Integer.valueOf(matcher.group(3)),
						  matcher.group(4));
		
		matcher.find();
		double Y2 = toRad(Integer.valueOf(matcher.group(1)), 
						  Integer.valueOf(matcher.group(2)), 
						  Integer.valueOf(matcher.group(3)),
						  matcher.group(4));
		
		double cosAngle = cos(X1)*cos(X2)*cos(Y2 - Y1) + sin(X1)*sin(X2);
		double distance = acos(cosAngle)*6875/2.0;
		
		System.out.printf("The distance to the iceberg: %.2f miles.\n", distance);
		if (Math.round(distance*100) < 10000) {
			System.out.println("DANGER!");
		}
		sc.close();
	}

}