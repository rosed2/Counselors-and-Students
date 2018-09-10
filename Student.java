
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * This class defines a Student object.
 * 
 * Actions: scheduleMeeting, sendMessageToCounselor, inboxService
 * Attributes: c1
 * 
 * @author: Rose Dinh
 * @date: 3-23-18
 */
public class Student extends Person{
    
    private Counselor c1;
    Scanner kb = new Scanner(System.in);
    
    /*
     * This is the constructor of a Student object.
     * 
     * @param first: Student's first name
     * @param last: Student's last name
     * @param password: Student's password
     * @param p1: Student's first period class
     * @param p2: Student's second period class
     * @param p3: Student's third period class
     * @param p4: Student's fourth period class
     * @param p5: Student's fifth period class
     * @param p6: Student's sixth period class
     * @param p7: Student's seventh period class
     * @param p8: Student's eighth period class
     * @param c: Student's Counselor
     */
    public Student(String first, String last, String password, String p1, String p2, String p3, String p4, 
            String p5, String p6, String p7, String p8, Counselor c)
    {
        super(first, last, password, p1, p2, p3, p4, p5, p6, p7, p8);
        c1 = c;
    }
    
    /*
     * This method allows a Student to schedule a meeting with their Counselor.
     * It makes sure that both people are free during that period on that
     * specific day. After scheduling a meeting, the meeting appears
     * on both of their schedules for that day.
     */
    public void scheduleMeeting()
    {
        int day = 0;
        int period = 0;
        boolean validDay = false;
        while(!validDay)
        {
            try
            {
                System.out.println("\nWhat day of the month of April do you want to schedule a meeting? Please "
                        + "enter an integer 1 - 30.");
                day = kb.nextInt();
                if(day > 0 && day < 31)
                {
                    validDay = true;
                    System.out.println();
                    System.out.println(super.printDailySchedule(day - 1));
                    System.out.println("\n" + c1.printDailySchedule(day - 1));
                }
                else
                {
                    System.out.println("You entered an invalid date. Please enter an integer between 1 and 30.");
                    kb.nextLine();
                }
            }
            catch(InputMismatchException e)
            {
                System.out.println("You entered an invalid option. Please enter a number 1 - 30.");
                kb.nextLine();
            }
        }
        
        boolean validAns = false;
        boolean scheduledMeeting = false;
        while(!validAns)
        {
            try 
            {
                System.out.println("\nWhat period would you like to schedule a meeting? You can schedule a meeting"
                        + "\nduring any period both you and your counselor are \"Free\" or have \"Lunch\"."
                        + "\nPlease enter an integer 1 - 8 for periods 1 - 8. If you and your counselor "
                        + "\nhave no free periods in common or you don't want to schedule a meeting"
                        + ", please enter 9.");
                period = kb.nextInt();
                        
                if(period != 1 && period != 2 && period != 3 && period != 4 && period != 5 
                        && period != 6 && period != 7 && period != 8 && period != 9)
                {
                    System.out.println("You entered an invalid number. Please enter a number 1 - 9.");
                }
                else if(period == 9)
                {
                    System.out.println("You have chosen to not schedule a meeting with your counselor.");
                    validAns = true;
                }
                else if(!super.getDailySchedule(day)[period - 1].equalsIgnoreCase("free")
                        && !super.getDailySchedule(day)[period - 1].equalsIgnoreCase("lunch"))
                    System.out.println("You tried to schedule a meeting during a period that you are"
                            + " not available. Please pick a period where you are availabe.");
                else if(!c1.getDailySchedule(day)[period - 1].equalsIgnoreCase("free")
                        && !c1.getDailySchedule(day)[period - 1].equalsIgnoreCase("lunch"))
                    System.out.println("You tried to schedule a meeting during a period that your counselor"
                            + " is not available. Please pick a period where they are availabe.");
                else
                {
                    validAns = true;
                    kb.nextLine();
                    scheduledMeeting = true;
                }
            } 
            catch (InputMismatchException e) 
               {
                  System.out.println("You entered an invalid option. Please enter a number 1 - 9.");
                  kb.nextLine();
               }
         }
        
        if(scheduledMeeting)
        {
            System.out.println("You successfully scheduled a meeting for Period " + period + " on Day " + day + ".");
            super.setPeriod(day, period, "Meeting with Counselor " + c1.getName());
            c1.setPeriod(day, period, "Meeting with Student " + getName());
            System.out.println();
            System.out.println(super.printDailySchedule(day - 1));
            System.out.println("\n" + c1.printDailySchedule(day - 1));
        }
    }
    
    /*
     * This method allows a Student to send a Message to their Counselor. 
     * The message appears in their Counselor's inbox.
     * It asks for the content of the message and the subject line.
     */
    public void sendMessageToCounselor()
    {
        System.out.println("\nPlease type your message to " + c1.getName()
                + ". To end your message, hit the enter button.");
        String message = kb.nextLine();
        System.out.println("Please type the subject line. To end the subject, please hit the enter button.");
        String subject = kb.nextLine();
        c1.addInboxMessage(new Message(getName(), message, subject));
        System.out.println("You have successfully sent a message to " + c1.getName());
    }
    
    /*
     * This method runs the message service. It allows the Student to
     * either read a message, delete a message, or send a message to their counselor.
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
                System.out.println("1. Read a Message \n2. Delete a Message \n3. Send a Message to " 
                    + "Your Counselor");
                inboxAction = kb.nextInt();
                if(inboxAction != 1 && inboxAction != 2 && inboxAction != 3)
                {
                    System.out.println("You selected an invalid option. Please type an integer 1 - 3.");
                    kb.nextLine();
                }
                
                else
                {
                    validAction = true;
                    kb.nextLine();
                }
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
            sendMessageToCounselor();
        }
        
    }
    
}