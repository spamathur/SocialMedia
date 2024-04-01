import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public interface CommentsManagerInterface {
    static List<Comment> getCommentsList() //returns comments
    {
        return null;
    }

    static void deleteComment(String commentID) //finds and deletes a comment based on its ID
    {

    }

    static void createComment(String postID, String creator, String content) //creates a comment
    {

    }

    static Comment findComment(String commentID) //finds a comment with its ID
    {
        return null;
    }

    static void readComments() throws IOException //reads a file and gets a comment with its ID, upvotes, downvotes
    {

    }

    static void writeComments() throws IOException //writes all comments to a file
    {

    }
}
