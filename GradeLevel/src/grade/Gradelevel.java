package grade;
import java.util.Scanner;
public class Gradelevel {

   public static void main(String[] args){
   int score;
	System.out.println("«Î ‰»Î≥…º®£∫");
	Scanner scan = new Scanner(System.in);
	score = scan.nextInt();
	studentLevel(score);
   }

	
	 
	public static  String  studentLevel(int score){
		 
		if(score<60){ asda 
		System.out.println("D");
		return "D";
		}
		else if (score<80){
		System.out.println("C");
		return "C";
		}
		else if (score<90){
		System.out.println("B");
		return "B";
		}
		else{
		System.out.println("A");
		return "A";
		}
	}
	
	
}