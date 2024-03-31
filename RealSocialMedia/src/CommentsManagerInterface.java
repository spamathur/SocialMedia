import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public interface CommentsManagerInterface {
    List<Comment> getCommentsList();

    void deleteComment(String commentID);

    void createComment(String postID, String creator, String content);

    Comment findComment(String commentID);

    void readComments() throws IOException;

    void writeComments() throws IOException;
}
