/**
 * An interface dedicated to creating and modifying comments
 * <p>
 * Purdue University -- CS18000 -- Spring 2024
 *
 * @author Project 5 Team 3 Lab 27
 * @version March 31, 2024
 */
public interface CommentInterface {
    void upvote(); //increases a comment's upvote

    void downvote(); //increases a comment's downvote

    String getPostAuthor(); //returns the post's author

    void setPostAuthor(String postAuthor); //sets the post's author

    String getCommentID(); //gets the comment's ID

    void setCommentID(String commentID); //sets the comment's ID

    String getCreator(); //returns the creator

    void setCreator(String creator); //sets the creator

    String getContent(); //returns content

    void setContent(String content); //sets content

    int getUpvotes(); //returns the amount of upvotes

    void setUpvotes(int upvotes); //sets the amount of upvotes to a given int

    int getDownvotes(); //returns the downvotes

    void setDownvotes(int downvotes); //sets the amount of downvotes to a given int

    String toString(); //returns a comment string in the format of commentID, creator, content, postAuthor, upvotes, downvotes
}
