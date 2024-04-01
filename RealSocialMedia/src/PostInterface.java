import java.util.ArrayList;

/**
 * An interface dedicated to forming posts for the social media platform
 * <p>
 * Purdue University -- CS18000 -- Spring 2024
 *
 * @author Project 5 Team 3 Lab 27
 * @version March 31, 2024
 */

public interface PostInterface {
    void addComment(Comment comment); //adds a comment

    void upvote(); //increase upvote

    void downvote(); //increases downvote

    String getPostID(); //returns postID

    void setPostID(String postID); //sets postID

    String getCreator(); //gets creator

    void setCreator(String creator); //sets creator

    String getContent(); //returns content

    void setContent(String content); //sets content

    int getUpvotes(); //returns upvotes

    void setUpvotes(int upvotes); //sets upvote to given int

    int getDownvotes(); //returns downvotes

    void setDownvotes(int downvotes); //sets downvotes to given int

    ArrayList<Comment> getComments(); //returns comments

    void setComments(ArrayList<Comment> comments); //sets comments to given comments

    String toString(); //returns string in the format of postID, creator, content, upvotes, downvotes, commentsString
}
