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

public class Post  {
    private String postID;
    private String creator;
    private String content;
    private String img;
    private int votes;
    private ArrayList<Comment> comments = new ArrayList<>();

    public Post(String creator, String content, String img) {
        this.creator = creator;
        this.content = content;
        this.img = img;
        this.postID = UUID.randomUUID().toString();
        this.votes = 0;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void upvote() {
        votes++;
    }

    public void downvote() {
        votes--;
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

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
        return String.format("%s;%s;%s;%s;%s;%s", postID, creator, content, img, votes, commentsString);
    }

}
