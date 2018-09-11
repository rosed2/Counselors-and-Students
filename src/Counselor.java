
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * This class defines a Counselor object.
 * 
 * Actions: importStudents, studentSignIn, getStudents, getStudent, printStudents, printStudentList, 
 * 			sendMessageToStudent, inboxService
 * Attributes: students
 * 
 * @author: Rose Dinh
 * @date: 3-23-18
 */
public class Counselor extends Person{

    private ArrayList<Student> students;
    Scanner kb = new Scanner(System.in);
    
    /*
     * This is the constructor of a Counselor object.
     * 
     * @param first: Counselor's first name
     * @param last: Counselor's last name
     * @param password: Counselor's password
     * @param p1: Counselor's first period class
     * @param p2: Counselor's second period class
     * @param p3: Counselor's third period class
     * @param p4: Counselor's fourth period class
     * @param p5: Counselor's fifth period class
     * @param p6: Counselor's sixth period class
     * @param p7: Counselor's seventh period class
     * @param p8: Counselor's eighth period class
     */
    public Counselor(String first, String last, String password, String p1, String p2, String p3, String p4, 
            String p5, String p6, String p7, String p8)
    {
        super(first, last, password, p1, p2, p3, p4, p5, p6, p7, p8);
        students = new ArrayList<Student>();
    }
    
    /*
     * This method imports the Counselor's Students' information from a file.
     * It adds these Students to the Counselor's students (ArrayList of Students).
     * 
     * @param fileName: name of the file that has the Students' information
     */
    public void importStudents(String fileName) throws FileNotFoundException
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
            
            students.add(new Student(first, last, password, p1, p2, p3, p4, p5, p6, p7, p8, this));
        }
    }

    /*
     * This method checks if the entered username and password match a Student's in
     * the Counselor's students (Array List of Students).
     * 
     * @param userName: the username to check
     * @param pass: the password to check
     * 
     * @return: -1 if there was no Student in the Counselor's students with the entered userName 
     * 			and password
     * 		int > -1 if there was a Student in the Counselor's students with the entered userName
     * 			and password, the int is the index of this Student in the Counselor's students
     */
    public int studentSignIn(String userName, String pass)
    {
        for(int i = 0; i < students.size(); i++)
            if(students.get(i).correctSignIn(userName,  pass))
                return i;
        return -1;
    }
    
    /*
     * This method returns the Counselor's private instance variable students,
     * the Counselor's array list of Student objects.
     * 
     * @return students
     */
    public ArrayList<Student> getStudents()
    {
        return students;
    }
    
    /*
     * This method returns the Student object at a specified index in the Counselor's students.
     * 
     * @param index: index of the Student object to be returned
     * @return: Student at the index in Counselor's students
     */
    public Student getStudent(int index)
    {
        return students.get(index);
    }
    
    /*
     * This method prints the information of every Student in the Counselor's students array list.
     * It prints their names and schedules.
     */
    public void printStudents()
    {
        for(Student s : students)
            System.out.println(s);
    }
    
    /*
     * This method prints the names of every Student in the Counselor's students.
     */
    public void printStudentList()
    {
        for(int i = 0; i < students.size(); i++)
            System.out.println( (i + 1) + ". " + students.get(i).getName());
    }
    
    /*
     * This method allows a Counselor to send a Message to one of the Students in their students. 
     * It first asks what Student to send a Message to and then
     * it asks for the content of the message and the subject line.
     * The message appears in that Student's inbox.
     */
    public void sendMessageToStudent()
    {
        boolean validNum = false;
        int sNum = -1;
        while(!validNum)
        {
            try
            {
                System.out.println("\nWhat student do you want to send a message to?");
                printStudentList();
                sNum = kb.nextInt();
                
                if(sNum < 1 || sNum > students.size() + 1)
                {
                    System.out.println("You entered an invalid option. Please enter a number 1 - "
                            + students.size() + 1 + ".");
                    kb.nextLine();
                }
                else
                {
                    validNum = true;
                    kb.nextLine();
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("You entered an invalid option. Please enter a number 1 - "
                        + students.size() + 1 + ".");
                kb.nextLine();
            }
        }
        
        System.out.print("Please type your message to " + students.get(sNum - 1).getName() + ". ");
        System.out.println("To end your message, please hit the enter button.");
        String message = kb.nextLine();
        System.out.println("Please type the subject line. To end the subject, please hit the enter button.");
        String subject = kb.nextLine();
        students.get(sNum - 1).addInboxMessage(new Message(getName(), message, subject));
        System.out.println("You have successfully sent a message to " + students.get(sNum - 1).getName());
    }
    
    /*
     * This method runs the message service. It allows the Counselor to
     * either read a message, delete a message, or send a message to one of their Students.
     */
    public void inboxService()
    {
        boolean validAction = false;
        int inboxAction = 0;
        while(!validAction)
        {
            try
            {
                System.out.println("What action would you like to complete?");
                System.out.println("1. Read a Message \n2. Delete a Message \n3. Send a Message"
                    + " to a Student");
                inboxAction = kb.nextInt();
                if(inboxAction != 1 && inboxAction != 2 && inboxAction != 3)
                {
                    System.out.println("You selected an invalid option. Please type an integer 1 - 3.");
                    kb.nextLine();
                }
                
                else
                    validAction = true;
            }
            catch(InputMismatchException e)
            {
                System.out.println("You selected an invalid option. Please type an integer 1 - 3.");
                kb.nextLine();
            }
        }
        
        if(inboxAction == 1)
        {
            readMessages();
        }
        else if(inboxAction == 2)
        {
            runDeleteMessage();
        }
        else
        {
            sendMessageToStudent();
        }
        
    }
}
