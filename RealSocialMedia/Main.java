import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, UserNameTakenException, LogInFailedException {
        FileManager.readAll();
        User sparsh = UsersManager.signUp(new User("sparch123", "sparsh", "mith", "pass", "pic.png"));
        User abhi = UsersManager.logIn("aabhii", "pass");
        sparsh.addFriend(abhi.getUserName());
        abhi.createPost("CS 180 project");
        Post post1 = abhi.getMyPosts().get(0);
        sparsh.createComment(sparsh.getMyFriendsPosts().get(0).getPostID(), "hi");
        Comment comment1 = post1.getComments().get(0);
        abhi.upvoteComment(comment1.getCommentID());
        System.out.println(post1.toString());
        System.out.println(comment1.toString());
        FileManager.writeAll();

    }
}
