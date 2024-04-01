import java.io.IOException;
import java.util.List;

/**
 * An interface dedicated to finding, reading, and saving posts
 * <p>
 * Purdue University -- CS18000 -- Spring 2024
 *
 * @author Project 5 Team 3 Lab 27
 * @version March 31, 2024
 */

public interface PostsManagerInterface {

    void setPostsList(List<Post> postsList); //sets posts to given posts

    Post findPost(String postID); //given a postID, finds a post

    Post createPost(String creator, String content); //creates a post, adds it to existing post list

    void readPosts() throws IOException; //reads a file, and gets the post information (post ID, upvote, downvote)

    void writePosts() throws IOException; //writes posts (string format) to a file
}
