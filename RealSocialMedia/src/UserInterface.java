import java.util.ArrayList;

public interface UserInterface {
    void addFriend(String username);

    void removeFriend(String username);

    void blockFriend(String username);

    void unblockFriend(String username);

    ArrayList<User> searchUsers(String searchString);

    void hidePost(String postID);

    ArrayList<Post> getHiddenPosts();

    void addMyPosts(Post post);

    void createPost(String content);

    void createComment(String postID, String commentString);

    String getUserName();

    void setUserName(String userName);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getPassword();

    void setPassword(String password);

    String getProfilePic();

    void setProfilePic(String profilePic);

    ArrayList<String> getFriendsList();

    void setFriendsList(ArrayList<String> friendsList);

    ArrayList<String> getBlockedList();

    void setBlockedList(ArrayList<String> blockedList);

    ArrayList<Post> getMyPosts();

    void setMyPosts(ArrayList<Post> myPosts);

    void upvotePost(String postID);

    void upvoteComment(String commentID);

    void downvotePost(String postID);

    void downvoteComment(String commentID);

    ArrayList<Post> getMyFriendsPosts();

    String toString();
}
