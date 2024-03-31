import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PostsManager {
    private static final String FILENAME = "posts.txt";
    private static List<Post> postsList = Collections.synchronizedList(new ArrayList<>());
    public static synchronized Post findPost(String postID) {
        for (Post post : postsList){
            if (post.getPostID().equals(postID))
                return post;
        }
        return new Post("","");
    }

    public static Post createPost(String creator, String content){
        Post post = new Post(creator, content);
        postsList.add(post);
        return post;
    }

    public static void readPosts() throws IOException {
        try (BufferedReader bfr = new BufferedReader(new FileReader(FILENAME))) {
            String line = bfr.readLine();
            while (line != null) {
                String[] postContents = line.split(";", -1);
                Post post = new Post(postContents[1], postContents[2]);
                post.setPostID(postContents[0]);
                post.setUpvotes(Integer.parseInt(postContents[3]));
                post.setDownvotes(Integer.parseInt(postContents[4]));
                for (String commentID : postContents[5].split(","))
                    post.addComment(CommentsManager.findComment(commentID));
                postsList.add(post);
                line = bfr.readLine();
            }
        }
    }

    public static void writePosts() throws IOException {
        try(PrintWriter pw = new PrintWriter(new FileWriter(FILENAME))){
            for (Post post : postsList) {
                pw.println(post.toString());
            }
        }
    }
}
