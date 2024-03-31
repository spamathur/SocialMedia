import java.util.ArrayList;

public interface PostInterface {
    void addComment(Comment comment);

    void upvote();

    void downvote();

    String getPostID();

    void setPostID(String postID);

    String getCreator();

    void setCreator(String creator);

    String getContent();

    void setContent(String content);

    int getUpvotes();

    void setUpvotes(int upvotes);

    int getDownvotes();

    void setDownvotes(int downvotes);

    ArrayList<Comment> getComments();

    void setComments(ArrayList<Comment> comments);

    String toString();
}
