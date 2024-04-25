import java.util.ArrayList;

/**
 * An interface dedicated to Users of the social media platform
 * <p>
 * Purdue University -- CS18000 -- Spring 2024
 *
 * @author Project 5 Team 3 Lab 27
 * @version March 31, 2024
 */

public interface UserInterface {
    void addFriend(String username); //adds a friend

    void removeFriend(String username); //removes a friend

    void blockFriend(String username); //blocks a user

    void unblockFriend(String username); //unblocks a user and removes them as a friend

    ArrayList<User> searchUsers(String searchString); //searches up users

    void hidePost(String postID); //hides a post

    ArrayList<Post> getHiddenPosts(); //returns all hidden posts

    void addMyPosts(Post post); //takes in a post, and adds it to the myPost in user class

    void createPost(String content, String img  ); //takes in a post from the PostManagement class and adds it to MyPost in user class

    void createComment(String postID, String commentString); //creates a comment

    String getUserName(); //returns username

    void setUserName(String userName); //sets username

    String getFirstName(); //returns first name

    void setFirstName(String firstName); //sets first name

    String getLastName(); //returns last name

    void setLastName(String lastName); //sets last name

    String getPassword(); //gets password

    void setPassword(String password); //sets password

    String getProfilePic(); //returns profile pic

    void setProfilePic(String profilePic); //sets profile pic

    ArrayList<String> getFriendsList(); //returns friend list

    void setFriendsList(ArrayList<String> friendsList); //sets friend list

    ArrayList<String> getBlockedList(); //returns blocked list

    void setBlockedList(ArrayList<String> blockedList); //sets blocked list

    ArrayList<Post> getMyPosts(); //returns the user's posts

    void setMyPosts(ArrayList<Post> myPosts); //sets the user's posts

    void upvotePost(String postID); //finds and upvotes a post

    void upvoteComment(String commentID); //finds and upvotes a comment

    void downvotePost(String postID); //finds and downvotes a post

    void downvoteComment(String commentID); //finds and downvotes a comment

    ArrayList<Post> getMyFriendsPosts(); //returns the user's friends' posts

    String toString(); //returns the user's username, firstname, lastname, password, profilePic, friendslist,
    // blocklist, the user's posts, and their hidden posts

    void addFollower(String username); //adds a follower

    void removeFollower(String username); //removes a follower

    ArrayList<String> getFollowersList(); //returns follower list

    void setFollowersList(ArrayList<String> followersList); //sets follower list to given list

    void setHiddenPosts(ArrayList<Post> hiddenPosts); //sets hidden posts to a given list


}
