public interface MessageManager {
    /*This class should take in User sender and User recipient in the constructor and
    assign to the appropriate fields in the class*/


    /*format of message: sender, message, messageID
    * messageId is basically message number*/

    /* Check if a file with the recipient and sender already exists if not first create
    * the file. For example, a file of messages with abhi and vihaa would be called
    * abhi_vihaa.txt. (notice that the file is named in alphabetical order). */

    /* Format the message it in the way I showed above and make sure that the file
    * access is synchronized. Make file access synchronized using ways we learned. */
    public void message(String message);

    /*find the message with the corresponding messageId and delete it from the file. synchronize
    * the file*/
    public void deleteMessage(int messageID);

    /*get the last 10 messages and return as a String array. Make sure file access is synchronized*/
    public String[] last10Messages();
}

