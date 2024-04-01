import java.util.UUID;

/**
 * A class dedicated to creating and modifying comments
 * <p>
 * Purdue University -- CS18000 -- Spring 2024
 *
 * @author Project 5 Team 3 Lab 27
 * @version March 31, 2024
 */

public class Comment {
    private String commentID;
    private String postAuthor;
    private String creator;
    private String content;
    private int upvotes;
    private int downvotes;

    public Comment(String creator, String content, String postAuthor) {
        this.creator = creator;
        this.content = content;
        this.postAuthor = postAuthor;
        this.commentID = UUID.randomUUID().toString();
        this.upvotes = 0;
        this.downvotes = 0;
    }

    public void upvote() {
        upvotes++;
    }

    public void downvote() {
        downvotes++;
    }

    public String getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(String postAuthor) {
        this.postAuthor = postAuthor;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public String toString() {
        return String.format("%s;%s;%s;%s;%s;%s", commentID, creator, content, postAuthor, upvotes, downvotes);
    }
}
