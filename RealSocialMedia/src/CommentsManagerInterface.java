import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * An interface dedicated to modifying, finding, and saving comments
 * <p>
 * Purdue University -- CS18000 -- Spring 2024
 *
 * @author Project 5 Team 3 Lab 27
 * @version March 31, 2024
 */

public interface CommentsManagerInterface {
    List<Comment> getCommentsList(); //returns comments

    void deleteComment(String commentID); //finds and deletes a comment based on its ID

    void createComment(String postID, String creator, String content); //creates a comment

    Comment findComment(String commentID); //finds a comment with its ID

    void readComments() throws IOException; //reads a file and gets a comment with its ID, upvotes, downvotes

    void writeComments() throws IOException; //writes all comments to a file
}
