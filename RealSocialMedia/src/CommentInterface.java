public interface CommentInterface {
    void upvote();

    void downvote();

    String getPostAuthor();

    void setPostAuthor(String postAuthor);

    String getCommentID();

    void setCommentID(String commentID);

    String getCreator();

    void setCreator(String creator);

    String getContent();

    void setContent(String content);

    int getUpvotes();

    void setUpvotes(int upvotes);

    int getDownvotes();

    void setDownvotes(int downvotes);

    String toString();
}
