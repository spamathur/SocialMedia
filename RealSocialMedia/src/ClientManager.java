/**
 * Project 5 -- Social Media
 * <p>
 * The class that implements this interface
 * serializes messages that are sent to the
 * server. For example, if the client wants
 * to login the serialized login String sent
 * to the server could be: "1:username:password"
 * where 1 tells the server to run the login
 * method with the parameters listed right after.
 *
 * This class also has a method to deserialize
 * information from the server and reconstruct
 * as objects.
 *
 * @author Project 5 Team 3 Lab 27
 * @version March 31, 2024
 */
public interface ClientManager {

    //this method deserializes information from the server
    public void deserialize();

    // methods that serialize and send to server
    public void logIn(String username, String password);
    public void signUp(String username, String firstName, String lastName, String password, String profilePic);
    public void addFriend(String username);
    public void removeFriend(String username);
    public void blockFriend(String username);
    public void unblockFriend(String username);
    public void searchUsers(String searchString);
    public void hidePost(String postID);
    public void createPost(String content);
    public void createComment(String postID, String commentString);
    public void getMyFriendsPosts();
    public void getMyPosts();
    public void upvotePost(String postID);
    public void upvoteComment(String commentID);
    public void downvotePost(String postID);
    public void downvoteComment(String commentID);
}
