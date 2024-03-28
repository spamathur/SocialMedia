import java.util.ArrayList;
import java.io.*;

// Javadoc goes here

public class MessageManagement implements MessageManager {
    // The user sending the message
    private User sender;
    // The user receiveing the message
    private User reciever;
    // Integer to keep track of which message it is
    private int messageID;
    // List of past 10 messages which is constantly added as messsages add
    private ArrayList<String> pastMessages;
    // File name for all the messages
    private File messageHistory;
    // Object for synchronization
    public static Object obj = new Object();

    public MessageManagement(User sender, User reciever) {
        String realSender = sender.toString().toUpperCase();
        String realReciever = reciever.toString().toUpperCase();

        // Add logic here to check if both users are valid users
        if ((realSender != "") && (realReciever != "")) {
            this.sender = sender;
            this.reciever = reciever;
            this.pastMessages = new ArrayList<>();

            /*
             * Check if a file with the recipient and sender already exists if not first
             * create
             * the file. For example, a file of messages with abhi and vihaa would be called
             * abhi_vihaa.txt. (notice that the file is named in alphabetical order).
             */
            if (realSender.compareTo(realReciever) == -1) {
                messageHistory = new File(realSender + "_" + realReciever + ".txt");
            }

            else {
                messageHistory = new File(realReciever + "_" + realSender + ".txt");
            }

            boolean exists = file.exists();

            if (exists) {
                BufferedReader reader = new BufferedReader(new FileReader(messageHistory));
                String line;

                while ((line = reader.readLine()) != null) {
                    pastMessages.add(line);
                    messageID++;
                }
                reader.close();
            }

            else {
                messageId = 0;
            }
        }

        else {
            throw new UserNotFoundException("Message cannot be sent: Invalid Users");
        }

    }

    /*
     * Format the message it in the way I showed above and make sure that the file
     * access is synchronized. Make file access synchronized using ways we learned.
     */
    public void message(String message) {
        synchronized(obj) {
            try {
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(messageHistory)));
                printWriter.println(message);
                messageID++;
                printWriter.close();
            } catch (IOException e) {
                e.printstacktrace();
            }
        }
    }

    /*
     * find the message with the corresponding messageId and delete it from the
     * file. synchronize
     * the file
     */
    public void deleteMessage(String message) {
        synchronized(obj) {
            try {
                File tempFile = new File(sender.toString() + reciever.toString() + "temp.txt");

                BufferedReader reader = new BufferedReader(new FileReader(messageHistory));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                String line;

                while((line = reader.readLine()) != null) {
                    if(line.equals(message)) continue;
                    writer.write(line);
                    writer.newLine();
                }
                writer.close();
                reader.close();
                tempFile.renameTo(messageHistory);
                messageID--;
            } catch (IOException e) {
                e.printstacktrace();
            }
        }
    }

    /*
     * get the last 10 messages and return as a String array. Make sure file access
     * is synchronized
     */
    public ArrayList<String> last10Messages() {
        // Fix this
        return pastMessages;
    }

    public User getSender() {
        return sender;
    }

    public User getReciever() {
        return reciever;
    }

    public int getMessageID() {
        return messageID;
    }

    public ArrayList<String> getPastMessages() {
        return pastMessages;
    }

    public File getMessageHistory() {
        return messageHistory;
    }
}
