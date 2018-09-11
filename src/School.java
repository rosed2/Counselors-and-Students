

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * This class defines a School object.
 * 
 * Actions: importCounselorsAndStudents, counselorSignIn, printCounselors, getCounselor, chooseCounselor,
 *          chooseCounselorOrStudent, login, logOut, runProgram,  counselorProgram, studentProgram
 * Attributes: counselors, counselorIndex, counselorOrStudent, studentIndex, c1, s1, runCandS, loggedIn
 *             COUNSELOR, STUDENT
 * 
 * @author: Rose Dinh
 * @date: 3-23-18
 */
public class School {

    private ArrayList<Counselor> counselors;
    Scanner kb = new Scanner(System.in);
    final int COUNSELOR = 1, STUDENT = 2;
    private int counselorIndex, counselorOrStudent, studentIndex;
    private Counselor c1;
    private Student s1;
    private boolean runCAndS;
    private boolean loggedIn;
    
    /*
     * This is the default constructor of a School object.
     */
    public School()
    {
        counselors = new ArrayList<Counselor>();
        counselorIndex = -1;
        counselorOrStudent = 0;
        studentIndex = -1;
        runCAndS = true;
        loggedIn = false;
    }
    
    /*
     * This method imports the School's Counselors and their information
     * and the information about the Counselor's Students.
     * 
     * @param fileName: the name of the file that has the Counselors' information
     */
    public void importCounselorsAndStudents(String fileName) throws FileNotFoundException
    {
        Scanner inf = new Scanner(new File(fileName));
        while(inf.hasNextLine())
        {
            String first = inf.nextLine();
            String last = inf.nextLine();
            String password = inf.nextLine();
            String p1 = inf.nextLine();
            String p2 = inf.nextLine();
            String p3 = inf.nextLine();
            String p4 = inf.nextLine();
            String p5 = inf.nextLine();
            String p6 = inf.nextLine();
            String p7 = inf.nextLine();
            String p8 = inf.nextLine();
            
            counselors.add(new Counselor(first, last, password, p1, p2, p3, p4, p5, p6, p7, p8));
        }
        
        for(int i = 0; i < counselors.size(); i++)
            counselors.get(i).importStudents("Counselor[" + i + "]Students.txt");
        
    }
    
    /*
     * This method checks if the Counselor at a specified index in 
     * the School's counselors array list has 
     * some specified username and password.
     * 
     * @param num: the index of the Counselor in the School's counselors to check
     * @param userName: the username to check
     * @param pass: the password to check
     * @return: true if the Counselor at index num in counselors has username userName and password pass
     *          false if not
     */
    public boolean counselorSignIn(int num, String userName, String pass)
    {
        return (counselors.get(num).correctSignIn(userName, pass));
    }
    
    /*
     * This method prints the values of each Counselor's instance
     * variables in the School's counselors.
     */
    public void printCounselors()
    {
        for(Counselor c: counselors)
            System.out.println(c);
    }
    
    /*
     * This is an access method for Counselor objects in the
     * School's counselors array list.
     * 
     * @param n: the index of the Counselor object in the School's counselors array list
     *           to be returned
     * @return: the Counselor object at index n in the School's counselors array list
     */
    public Counselor getCounselor(int n)
    {
        return counselors.get(n);
    }
    
    /*
     * This method finds what Counselor the user logging in is associated with.
     * 
     * @return: the index of that Counselor in the School's counselors
     *          array list
     */
    public int chooseCounselor()
    {
        int counselorNum = 0;
        boolean validCounselor = false;
        while(!validCounselor)
        {
            try
            {
                System.out.println("If you are a counselor, please select your name. If you are a student,"
                        + " please select your counselor's name.");
                for(int i = 0; i < counselors.size(); i++)
                    System.out.println(i + ". " + counselors.get(i).getName());
                counselorNum = kb.nextInt();
                
                if(counselorNum >= 0 && counselorNum <= counselors.size()-1)
                {
                    validCounselor = true;
                    kb.nextLine();
                }
                else
                    System.out.println("Please enter an integer between 0 and " + (counselors.size()-1));
            }
            catch (InputMismatchException e) 
               {
                  System.out.println("You entered an invalid option. Please enter a number "
                          + "between 0 and " + (counselors.size()-1));
                  kb.nextLine();
               }
        }
        return counselorNum;
    }
    
    /*
     * This method determines if the user logging in is a 
     * student or counselor.
     * 
     * @return: 1 if the user is a counselor
     *          2 if the user is a student
     */
    public int chooseCounselorOrStudent()
    {
        int answer = 0;
        boolean validAns = false;
        while(!validAns)
        {
            try
            {
                System.out.println("\nPlease select if you are a counselor or student.");
                System.out.println(COUNSELOR + ". Counselor \n" + STUDENT + ". Student");
                answer = kb.nextInt();
                kb.nextLine();
                if(answer != COUNSELOR && answer != STUDENT)
                    System.out.println("Please choose " + COUNSELOR + " or " + STUDENT + ".");
                else
                    validAns = true;
            }
            catch (InputMismatchException e)
            {
                System.out.println("You entered an invalid option. Please choose " + COUNSELOR 
                        + " or " + STUDENT + ".");
                kb.nextLine();
            }
        }
        return answer;
    }
    
    /*
     * This method logs the user in. If the user successfully logs in 
     * as a Student, the School's private instance variable s1 becomes
     * a reference variable for this Student object. If the user successfully logs in 
     * as a Counselor, the School's private instance variable c1 becomes
     * a reference variable for this Counselor object. 
     */
    public void login()
    {
        boolean run = true;
        while(run)
        {
            int counselorNum = chooseCounselor();
            counselorOrStudent = chooseCounselorOrStudent();
            if(counselorOrStudent == COUNSELOR)
            {
                System.out.print("\nUsername: ");
                String userName = kb.nextLine();
                System.out.print("\nPassword: ");
                String pass = kb.nextLine();
                
                if(counselorSignIn(counselorNum, userName, pass))
                {
                    counselorIndex = counselorNum;
                    run = false;
                    c1 = counselors.get(counselorIndex);
                    System.out.println("\nLogin Successful. Welcome " + c1.getName());
                }
                else
                    System.out.println("\nLogin Unsuccessful. You either entered an incorrect"
                     + " username or password.");
            }
            
            if(counselorOrStudent == STUDENT)
            {
                System.out.print("\nUsername: ");
                String userName = kb.nextLine();
                System.out.print("\nPassword: ");
                String password = kb.nextLine();
                int studentNum = counselors.get(counselorNum).studentSignIn(userName, password);
                
                if(studentNum != -1)
                {
                    counselorIndex = counselorNum;
                    studentIndex = studentNum;
                    run = false;
                    s1 = counselors.get(counselorIndex).getStudent(studentIndex);
                    System.out.println("\nLogin Successful. Welcome " + s1.getName());
                }
                else
                    System.out.println("\nLogin Unsuccessful. You either entered an incorrect"
                     + " username or password.");
            }
        }   
        loggedIn = true;
    }
    
    /*
     * This method logs the user out. It clears
     * all of the user's specific instance variables
     * (counselorIndex, counselorOrStudent, studentIndex, c1, s1).
     * Then it asks if the user wants to log in again or quit the 
     * program.
     */
    public void logOut()
    {
        loggedIn = false;
        counselorIndex = -1;
        counselorOrStudent = 0;
        studentIndex = -1;
        c1 = null;
        s1 = null;
        System.out.println("You have successfully logged out.");
        boolean validAction = false;
        int actionNum = -1;
        while(!validAction)
        {
            try
            {
                System.out.println("\nWould you like to 1. Log In or 2. Quit");
                actionNum = kb.nextInt();
                if(actionNum != 1 && actionNum != 2)
                {
                    System.out.println("You selected an invalid option. Please type an integer 1 - 2.");
                    kb.nextLine();
                }
                else
                    validAction = true;
            }
            catch(InputMismatchException e)
            {
                System.out.println("You selected an invalid option. Please type an integer 1 - 2.");
                kb.nextLine();
            }
        }
        if(actionNum == 2)
            runCAndS = false;
    }
    
    /*
     * This method runs the program. It lets the user log in and
     * then do their specific actions (either 
     * Student-specific or Counselor-specific).
     * When the user logs out, another can log in.
     * Then it prints an exit message when the user
     * wants to quit the program.
     */
    public void runProgram()
    {
        while(runCAndS)
        {
            System.out.println();
            login();
            while(loggedIn)
            {
                if(counselorOrStudent == COUNSELOR)
                {
                    counselorProgram();
                }
                else
                    studentProgram();
            }
        }
        System.out.println("Thank you for using Counselors and Students.");
    }
    
    /*
     * This method runs the Counselor program.
     * If the user is a Counselor, it lets them
     * do their Counselor-specific actions
     * (view their Student list, access the message service,
     * and view their schedule).
     */
    public void counselorProgram()
    {
        boolean validAction = false;
        int actionNum = 0;
        while(!validAction)
        {
            try
            {
                System.out.println("\nWhat action would you like to complete?");
                System.out.println("1. View Student List \n2. View Your Schedule \n3. Message Service \n4. Log Out");
                actionNum = kb.nextInt();
                if(actionNum != 1 && actionNum != 2 && actionNum != 3 && actionNum != 4)
                {
                    System.out.println("You selected an invalid option. Please type an integer 1 - 4.");
                    kb.nextLine();
                }
                
                else
                    validAction = true;
            }
            catch(InputMismatchException e)
            {
                System.out.println("You selected an invalid option. Please type an integer 1 - 4.");
                kb.nextLine();
            }
        }
        
        if(actionNum == 1)
        {
            c1.printStudentList();
        }
        else if(actionNum == 2)
        {
            boolean validDay = false;
            int day = -1;
            while(!validDay)
            {
                try
                {
                    System.out.println("\nFor what day of this month do you want to see your schedule?");
                    System.out.println("Please enter an integer 1 - 30");
                    day = kb.nextInt();
                    if(day < 1 || day > 30)
                    {
                        System.out.println("You selected an invalid option. Please type an integer 1 - 30.");
                        kb.nextLine();
                    }
                    
                    else
                        validDay = true;
                }
                catch(InputMismatchException e)
                {
                    System.out.println("You selected an invalid option. Please type an integer 1 - 30.");
                    kb.nextLine();
                }
            }
            
            System.out.println(c1.printDailySchedule(day - 1));
        }
        else if(actionNum == 3)
        {
            System.out.println();
            c1.inboxService();
        }
        else
            logOut();
    }
    
    /*
     * This method runs the Student program.
     * If the user is a Student, it lets them
     * do their Student-specific actions (schedule
     * a meeting, access the message service, view their schedule).
     */
    public void studentProgram()
    {
        boolean validAction = false;
        int actionNum = 0;
        while(!validAction)
        {
            try
            {
                System.out.println("\nWhat action would you like to complete?");
                System.out.println("1. Schedule a Meeting with Your Couselor \n2. View Your Schedule"
                        + "\n3. Message Service \n4. Log Out");
                actionNum = kb.nextInt();
                if(actionNum != 1 && actionNum != 2 && actionNum != 3 && actionNum != 4)
                {
                    System.out.println("You selected an invalid option. Please type an integer 1 - 4.");
                    kb.nextLine();
                }
                
                else
                    validAction = true;
            }
            catch(InputMismatchException e)
            {
                System.out.println("You selected an invalid option. Please type an integer 1 - 4.");
                kb.nextLine();
            }
        }
        
        if(actionNum == 1)
        {
            s1.scheduleMeeting();
        }
        else if(actionNum == 2)
        {
            boolean validDay = false;
            int day = -1;
            while(!validDay)
            {
                try
                {
                    System.out.println("\nFor what day of this month do you want to see your schedule?");
                    System.out.println("Please enter an integer 1 - 30");
                    day = kb.nextInt();
                    if(!(1 <= day && day <= 30))
                    {
                        System.out.println("You selected an invalid option. Please type an integer 1 - 30.");
                        kb.nextLine();
                    }
                    
                    else
                        validDay = true;
                }
                catch(InputMismatchException e)
                {
                    System.out.println("You selected an invalid option. Please type an integer 1 - 30.");
                    kb.nextLine();
                }
            }
            
            System.out.println(s1.printDailySchedule(day - 1));
        }
        else if(actionNum == 3)
        {
            System.out.println();
            s1.inboxService();
        }
        else
            logOut();
    }
}
