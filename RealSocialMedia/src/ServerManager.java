/**
 * Project 5 -- Social Media
 * <p>
 * The class that implements this interface
 * deserializes the messages from the client
 * and calls the appropriate method from the
 * Users class. For example, given an addFriend
 * request: "3:friend", the deserialize method,
 * calls user.add(friend).
 *
 * This class also sends messages to the client
 * if the client has requested anything. It will
 * serialize and send it
 *
 * @author Project 5 Team 3 Lab 27
 * @version March 31, 2024
 */
public interface ServerManager {

    //this method deserializes information from the client
    public void deserialize();

    // methods that serialize and send to client
    public void sendMyFriendsPosts();
    public void sendMyPosts();
    public void sendSearchedUsers();
    public void sendMyProfile();


}
