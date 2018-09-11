

/*
 * This class defines a Message object.
 * 
 * Actions: setRead, toString, getIfRead, tagLine
 * Attributes: senderName, read, content, subject
 * 
 * @author: Rose Dinh
 * @date: 3-23-18
 */
public class Message{

    private String senderName;
    private boolean read;
    private String content;
    private String subject;
    
    /*
     * This is the constructor for a Message object.
     * 
     * @param sender: the sender's name
     * @param content: the Message's content
     * @param header: the Message's subject line
     */
    public Message(String sender, String content, String header)
    {
        this.senderName = sender;
        this.content = content;
        subject = header;
        read = false;
    }
    
    /*
     * This method sets the private instance variable read of the Message object.
     * 
     * @param check: true if the Message was read
     *               false if the Message is unread
     */
    public void setRead(boolean check)
    {
        read = check;
    }
    
    /*
     * This method returns the values of all of the 
     * private instance variables of the Message object except for read.
     * 
     * @return: the String representation of the Message object
     */
    public String toString()
    {
        String str = "";
        str += "Sent from: " + senderName;
        str += "\nSubject: " + subject;
        str += "\n" + content;
        return str;
    }
    
    /*
     * This is an access method for the private instance
     * variable read of the Message class.
     * 
     * @return: the value of read 
     *          true if the Message was read
     *          false if the Message wasn't read
     */
    public boolean getIfRead()
    {
        return read;
    }
    
    /*
     * This method creates the Message's tagLine, the preview of the Message.
     * 
     * @return: the Message's tag line that includes the Message's
     *          sender's name, subject, and its read status 
     */
    public String tagLine()
    {
        String str = "";
        str += "Sent from: " + senderName;
        str += "\nSubject: " + subject + "\n";
        if(getIfRead())
            str += "Read";
        else
            str += "Unread";
        return str;
    }
    
}
