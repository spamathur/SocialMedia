import java.util.ArrayList;
import java.util.UUID;

/**
 * A class dedicated to forming posts for the social media platform
 * <p>
 * Purdue University -- CS18000 -- Spring 2024
 *
 * @author Project 5 Team 3 Lab 27
 * @version March 31, 2024
 */

public class Post {
    private String postID;
    private String creator;
    private String content;
    private int upvotes;
    private int downvotes;
    private ArrayList<Comment> comments = new ArrayList<>();

    public Post(String creator, String content) {
        this.creator = creator;
        this.content = content;
        this.postID = UUID.randomUUID().toString();
        this.upvotes = 0;
        this.downvotes = 0;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void upvote() {
        upvotes++;
    }

    public void downvote() {
        downvotes++;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
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

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String toString() {
        String commentsString = "";
        if (comments != null) {
            for (int i = 0; i < comments.size(); i++) {
                Comment comment = comments.get(i);
                if (i == comments.size() - 1)
                    commentsString = String.format("%s%s", commentsString, comment.getCommentID());
                else
                    commentsString = String.format("%s%s,", commentsString, comment.getCommentID());
            }
        }
        return String.format("%s;%s;%s;%s;%s;%s", postID, creator, content, upvotes, downvotes, commentsString);
    }

}
