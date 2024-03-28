import java.util.ArrayList;

// Javadoc goes here

public class MessageManagement implements MessageManager {
    // The user sending the message
    private User sender;
    // The user receiveing the message
    private User reciever;
    // The actual message itself
    private String messageContent;
    // Integer to keep track of which message it is
    private int messageID;
    // List of past messages which is constantly added as messsages add
    private ArrayList<String> pastMessages;

    public MessageManagement(User sender, User reciever, String messageContent) {
        // Add logic here to check if both users are valid users
        this.sender = sender;
        this.reciever = reciever;
        this.messageContent = messageContent;
    }

    /* Check if a file with the recipient and sender already exists if not first create
    * the file. For example, a file of messages with abhi and vihaa would be called
    * abhi_vihaa.txt. (notice that the file is named in alphabetical order). */

    /* Format the message it in the way I showed above and make sure that the file
    * access is synchronized. Make file access synchronized using ways we learned. */
    public void message(String message) {
        
    }

    /*find the message with the corresponding messageId and delete it from the file. synchronize
    * the file*/
    public void deleteMessage(int messageID) {
        
    }

    /*get the last 10 messages and return as a String array. Make sure file access is synchronized*/
    public String[] last10Messages() {
        return null;
    }
}
