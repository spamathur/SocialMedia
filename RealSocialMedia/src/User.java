import java.util.ArrayList;

public class User implements UserInterface{
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String profilePic;
    private ArrayList<String> friendsList = new ArrayList<>();
    private ArrayList<String> followersList = new ArrayList<>();
    private ArrayList<String> blockedList = new ArrayList<>();
    private ArrayList<Post> myPosts = new ArrayList<>();
    private ArrayList<Post> hiddenPosts = new ArrayList<>();

    public User(String userName, String firstName, String lastName, String password, String profilePic) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.profilePic = profilePic;
    }

    // USER METHODS:

    public User findUser(String username){
        return UsersManager.findUser(username);
    }

    public void deleteComment(String postID, String commentID){
        PostsManager.deleteComment(postID, commentID);
    }

    public void addFriend(String username) {
        friendsList.add(username);
        User userFriended = UsersManager.findUser(username);
        userFriended.addFollower(this.userName);
    }

    public void removeFriend(String username) {
        friendsList.remove(username);
        User userFriended = UsersManager.findUser(username);
        userFriended.removeFollower(this.userName);
    }

    public void blockFriend(String username) {
        blockedList.add(username);
        removeFriend(username);
        User userBlocked = UsersManager.findUser(username);
        userBlocked.removeFriend(this.userName);
    }

    public void unblockFriend(String username) {
        blockedList.remove(username);
    }

    public ArrayList<User> searchUsers(String searchString) {
        return UsersManager.searchUsers(this.userName, searchString);
    }

    public void hidePost(String postID) {
        Post post = PostsManager.findPost(postID);
        hiddenPosts.add(post);
    }

    public void createPost(String content, String img) {
        addMyPosts(PostsManager.createPost(this.userName, content, img));
    }

    public void createComment(String postID, String commentString) {
        CommentsManager.createComment(postID, this.userName, commentString);
    }

    public void upvotePost(String postID) {
        Post post = PostsManager.findPost(postID);
        post.upvote();
    }

    public void upvoteComment(String commentID) {
        Comment comment = CommentsManager.findComment(commentID);
        comment.upvote();
    }

    public void downvotePost(String postID) {
        Post post = PostsManager.findPost(postID);
        post.downvote();
    }

    public void downvoteComment(String commentID) {
        Comment comment = CommentsManager.findComment(commentID);
        comment.downvote();
    }

    public ArrayList<Post> getMyFriendsPosts() {
        ArrayList<Post> myFriendsPosts = new ArrayList<>();
        for (String username : friendsList) {
            User user = UsersManager.findUser(username);
            for (Post userPost : user.getMyPosts()) {
                if (!hiddenPosts.contains(userPost))
                    myFriendsPosts.add(userPost);
            }
        }
        return myFriendsPosts;
    }

    // GETTERS AND SETTERS:

    public void addFollower(String username){
        followersList.add(username);
    }

    public void removeFollower(String username){
        followersList.remove(username);
    }

    public void addMyPosts(Post post) {
        myPosts.add(post);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public ArrayList<String> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(ArrayList<String> friendsList) {
        this.friendsList = friendsList;
    }

    public ArrayList<String> getBlockedList() {
        return blockedList;
    }

    public void setBlockedList(ArrayList<String> blockedList) {
        this.blockedList = blockedList;
    }

    public ArrayList<Post> getMyPosts() {
        return myPosts;
    }

    public void setMyPosts(ArrayList<Post> myPosts) {
        this.myPosts = myPosts;
    }

    public ArrayList<String> getFollowersList() {
        return followersList;
    }

    public void setFollowersList(ArrayList<String> followersList) {
        this.followersList = followersList;
    }

    public ArrayList<Post> getHiddenPosts() {
        return hiddenPosts;
    }

    public void setHiddenPosts(ArrayList<Post> hiddenPosts) {
        this.hiddenPosts = hiddenPosts;
    }

    public String toString() {
        String friendsListString = "";
        if (friendsList != null) {
            friendsListString = String.join(",", friendsList);
        }

        String followersListString = "";
        if (followersList != null) {
            followersListString = String.join(",", followersList);
        }

        String blockedListString = "";
        if (blockedList != null) {
            blockedListString = String.join(",", blockedList);
        }

        String myPostsString = "";
        if (myPosts != null) {
            for (int i = 0; i < myPosts.size(); i++) {
                if (i == myPosts.size() - 1)
                    myPostsString = String.format("%s%s", myPostsString, myPosts.get(i).getPostID());
                else
                    myPostsString = String.format("%s%s,", myPostsString, myPosts.get(i).getPostID());
            }
        }

        String hiddenPostsString = "";
        if (hiddenPosts != null) {
            for (int i = 0; i < hiddenPosts.size(); i++) {
                if (i == hiddenPosts.size() - 1)
                    hiddenPostsString = String.format("%s%s", hiddenPostsString, hiddenPosts.get(i).getPostID());
                else
                    hiddenPostsString = String.format("%s%s,", hiddenPostsString, hiddenPosts.get(i).getPostID());
            }
        }

        return String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s", userName, firstName, lastName, password, profilePic, friendsListString, blockedListString, myPostsString, hiddenPostsString, followersListString);
    }
}
