
import java.io.FileNotFoundException;

/*
 * This class runs the Couselors and Students program. Students can schedule
 * a meeting, access the message service, and view their schedule. Counselors can
 * view their Student list, access the message service,
 * and view their schedule
 * Each Person has their own unique username and password.
 * 
 * @author: Rose Dinh
 * @date: 3-23-18
 */
public class CounselorsAndStudentsDriver {

	public static void main(String[] args)  throws FileNotFoundException
	{
		School fremd = new School();
		fremd.importCounselorsAndStudents("CounselorSchedules.txt");
		System.out.println("Welcome to Counselors and Students.");
		fremd.runProgram();
		
	}
}