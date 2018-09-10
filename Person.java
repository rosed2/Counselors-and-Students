

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * This class defines a Person object.
 * 
 * Actions: getSchedule, getDailySchedule, getName, getPassword, toString, printDailySchedule, setPeriod,
 *          accessInbox, deleteInboxMessage, runDeleteMessage, addInboxMessage, 
 *          correctSignIn, readMessages
 * Attributes: firstName, lastName, password, schedule, monthSchedule, inbox
 * 
 * @author: Rose Dinh
 * @date: 3-23-18
 */
public class Person {

    private String firstName, lastName, password;
    private String[] schedule;
    private String[][] monthSchedule;
    private ArrayList<Message> inbox;
    Scanner kb = new Scanner(System.in);
    
    /*
     * This is the constructor of a Person object.
     * 
     * @param first: Person's first name
     * @param last: Person's last name
     * @param password: Person's password
     * @param p1: Person's first period class
     * @param p2: Person's second period class
     * @param p3: Person's third period class
     * @param p4: Person's fourth period class
     * @param p5: Person's fifth period class
     * @param p6: Person's sixth period class
     * @param p7: Person's seventh period class
     * @param p8: Person's eighth period class
     */
    public Person(String first, String last, String password, String p1, String p2, String p3, String p4, String p5, 
            String p6, String p7, String p8)
    {
        firstName = first;
        lastName = last;
        this.password = password;
        schedule = new String[8];
        schedule[0] = p1;
        schedule[1] = p2;
        schedule[2] = p3;
        schedule[3] = p4;
        schedule[4] = p5;
        schedule[5] = p6;
        schedule[6] = p7;
        schedule[7] = p8;
        monthSchedule = new String[30][8];
        for(String[] day: monthSchedule)
        {
            day[0] = p1;
            day[1] = p2;
            day[2] = p3;
            day[3] = p4;
            day[4] = p5;
            day[5] = p6;
            day[6] = p7;
            day[7] = p8;
        }
        inbox = new ArrayList<Message>();
    }
    
    /*
     * This is an access method for the private instance
     * variable schedule of the Person class.
     * 
     * @return the Person's schedule
     */
    public String[] getSchedule()
    {
        return schedule;
    }
    
    /*
     * This method returns the Peron's schedule for a specific day of the month.
     * 
     * @param num: the index of the specified day
     * @return: the Person's schedule for the day at index num in monthSchedule
     */
    public String[] getDailySchedule(int num)
    {
        return monthSchedule[num];
    }
    
    /*
     * This is an access method for the private instance
     * variable firstName and lastName of the Person class.
     * 
     * @return the Person's first and last name
     */
    public String getName()
    {
        return firstName + " " + lastName;
    }
    
    /*
     * This is an access method for the private instance
     * variable password of the Person class.
     * 
     * @return the Person's password
     */
    public String getPassword()
    {
        return password;
    }
    
    /*
     * This method returns the Person's full name and schedule.
     * 
     * @return: the Person's full name and schedule
     */
    public String toString()
    {
        String str = getName();
        for(int n = 0; n < schedule.length; n++)
            str += "\nPeriod " + (n + 1) + ": " + schedule[n];
        return str;
    }
    
    /*
     * This method returns the Peron's full name and schedule 
     * for a specified day.
     * 
     * @param num: index of the day that you want (date - 1)
     * @return: the Person's full name and schedule for a 
     *          specified day
     */
    public String printDailySchedule(int num)
    {
        String str = getName();
        str += "\nDate: " + (num + 1) + "\n";
        for(int n = 0; n < monthSchedule[num].length; n++)
            str += "\nPeriod " + (n + 1) + ": " + monthSchedule[num][n];
        return str;
    }
    
    /*
     * This method sets a specified period on a specified day with a 
     * specified activity.
     * 
     * @param day: the date of the day that you want to change a period on
     * @param period: the period that you want to change
     * @param name: the name of the activity to set the period as
     */
    public void setPeriod(int day, int period, String name)
    {
        monthSchedule[day - 1][period - 1] = name;
    }
    
    /*
     * This is an access method for the Message objects
     * in the Person's inbox.
     * 
     * @param n: the index of the Message in the inbox that you want to access
     * @return: the Message at index n in the inbox
     */
    public Message accessInbox(int n)
    {
        return inbox.get(n);
    }
    
    /*
     * This method deletes a Message in the inbox.
     * 
     * @param n: the index of the Message to be deleted
     */
    public void deleteInboxMessage(int n)
    {
        inbox.remove(n);
    }
    
    /*
     * This method asks what Message the Person wants to delete and 
     * deletes it.
     */
    public void runDeleteMessage()
    {
        System.out.println("\nMessages");
        for(int n = 1; n <= inbox.size(); n++)
            System.out.println(n + ". " + inbox.get(n-1).tagLine());
        if(inbox.size() == 0)
            System.out.println("You have no messages.");
        else
        {
            int messageNum = -1;
            
            boolean validAns = false;
            while(!validAns)
            {
                try
                {
                    System.out.println("\nWhich message would you like to delete? Please enter an integer 1 - " 
                            + inbox.size());
                    int num = kb.nextInt();
                    if(num < 1 || num > inbox.size())
                    {
                        System.out.println("You selected an invalid option. Please enter an integer 1 - "
                                + inbox.size());
                        kb.nextLine();
                    }
                    else
                    {
                        validAns = true;
                        messageNum = num;
                    }
                }
                catch(InputMismatchException e)
                {
                    System.out.println("You selected an invalid option. Please enter an integer 1 - "
                            + inbox.size());
                    kb.nextLine();
                }
            }
            deleteInboxMessage(messageNum - 1);
            System.out.println("You have successfully deleted a message.");
        }
    }
    
    /*
     * This method adds a Message to the Person's inbox.
     * 
     * @param m1: the Message to be added
     */
    public void addInboxMessage(Message m1)
    {
        inbox.add(m1);
    }
    
    /*
     * This method checks if the entered username and password match
     * this Person's username and password.
     * 
     * @param userName: the userName to check
     * @param pass: the password to check
     * @return: true if userName and pass match the Person's username and password
     *          false if not
     */
    public boolean correctSignIn(String userName, String pass)
    {
        return(userName.equals(getName()) && pass.equals(getPassword()));
    }
    
    /*
     * This method allows the Person to read the Messages
     * in their inbox. It asks what Message to read, 
     * prints the Message's content, and then marks that
     * Message as read.
     */
    public void readMessages()
    {
        System.out.println("\nMessages");
        if(inbox.size() == 0)
            System.out.println("You have no messages.");
        else
        {
            for(int n = 1; n <= inbox.size(); n++)
                System.out.println("\n" + n + ". " + inbox.get(n-1).tagLine());
            int messageNum = -1;
            
            boolean validAns = false;
            while(!validAns)
            {
                try
                {
                    System.out.println("Which message would you like to read? Please enter an integer 1 - " 
                            + inbox.size());
                    int num = kb.nextInt();
                    if(num < 1 || num > inbox.size())
                    {
                        System.out.println("You selected an invalid option. Please enter an integer 1 - "
                                + inbox.size());
                        kb.nextLine();
                    }
                    else
                    {
                        validAns = true;
                        messageNum = num;
                    }
                }
                catch(InputMismatchException e)
                {
                    System.out.println("You selected an invalid option. Please enter an integer 1 - "
                            + inbox.size());
                    kb.nextLine();
                }
            }
            
            System.out.println(inbox.get(messageNum - 1));
            inbox.get(messageNum - 1).setRead(true);
        }
    }
    
}