import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/* YOU CAN TEST THIS DATABASE BY RUNNING METHODS SIGNING UP/LOGGING IN USERS AND USING THE USER
OBJECTS TO TEST ANY METHOD. BELOW I HAVE SHOWN HOW TO TEST addFriend and getMyFriendsPosts methods.
*/

public class Main {
    public static void main(String[] args) throws IOException, UserNameTakenException, LogInFailedException {
        // This code signs two users up
        //User abhi = UsersManager.signUp(new User("aabhii13", "abhi", "pad", "pass", "profile.png"));
        //User vihaa = UsersManager.signUp(new User("vivi", "vihaa", "pad", "pass", "profile.png"));


        // This code logs both users in
        //User abhi = UsersManager.logIn("aabhii13", "pass");
        //User vihaa = UsersManager.logIn("vivi", "pass");


        //abhi adds vivi as a friend
        //abhi.addFriend("vivi");

        //vivi creates a post
        //vihaa.createPost("testing");

        //abhi gets his friend's posts; only one post from one friend
        //ArrayList<Post> posts = abhi.getMyFriendsPosts();

        //I am printing the post to check if the get my friends posts method works
        //System.out.println(posts.get(0).toString());
    }
}
