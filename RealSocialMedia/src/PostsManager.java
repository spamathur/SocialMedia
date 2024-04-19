import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A class dedicated to finding, reading, and saving posts
 * <p>
 * Purdue University -- CS18000 -- Spring 2024
 *
 * @author Project 5 Team 3 Lab 27
 * @version March 31, 2024
 */

public class PostsManager {
    private static String FILENAME = "posts.txt";
    private static List<Post> postsList = Collections.synchronizedList(new ArrayList<>());

    public static void setPostsList(List<Post> postsList) {
        PostsManager.postsList = postsList;
    }

    public static synchronized Post findPost(String postID) {
        for (Post post : postsList){
            if (post.getPostID().equals(postID))
                return post;
        }
        return new Post("","", "");
    }

    public static List<Post> getPostsList() {
        return postsList;
    }

    public static Post createPost(String creator, String content, String img){
        Post post = new Post(creator, content, img);
        postsList.add(post);
        return post;
    }

    public static void readPosts() throws IOException {
        try (BufferedReader bfr = new BufferedReader(new FileReader(FILENAME))) {
            String line = bfr.readLine();
            while (line != null) {
                String[] postContents = line.split(";", -1);
                Post post = new Post(postContents[1], postContents[2], postContents[3]);
                post.setPostID(postContents[0]);
                post.setVotes(Integer.parseInt(postContents[4]));
                if (!postContents[5].isEmpty()) {
                    for (String commentID : postContents[6].split(",")) {
                        post.addComment(CommentsManager.findComment(commentID));
                    }
                }
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
