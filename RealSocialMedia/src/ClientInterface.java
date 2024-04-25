
/**
 * Project 5 -- Social Media
 * <p>
 * Client Interface
 *
 * @author Project 5 Team 3 Lab 27
 * @version March 31, 2024
 */


public interface ClientInterface {

    Post[] getMyFriendsPosts(); //communicates with the server to return friends' posts

    Post[] getMyPosts(); //communicates with the server to return user's posts

    void upvotePost(String postID); //sends a request to upvote a post

    void upvoteComment(String commentID); //sends a request to upvote a comment

    void downvotePost(String postID); //sends a request to downvote a post

    void downvoteComment(String commentID); //sends a request to downvote a comment

    boolean logIn(String username, String password); //sends a request to login

    boolean signUp(String username, String firstName, String lastName, String password, String profilePic);
    //sends a request to signup

    void addFriend(String username); //sends a request to add a friend

    void removeFriend(String username); //sends a request to remove a friend

    void blockFriend(String username); //sends a request to block a friend

    void unblockFriend(String username); //sends a request to unblock a friend

    User[] searchUsers(String searchString); //sends a request to search users

    void hidePost(String postID); //sends a request to hide posts

    void createPost(String content, String img); //sends a request to create a post

    void createComment(String postID, String commentString); //sends a request to create a comment

    User getMyProfile(); //sends a request to return the user's profile

    void sendRequest(String request); //sends a request to the server

    String getResponse(); //gets a response from the server

    void closeConnection(); //closes connection between server and client

}
