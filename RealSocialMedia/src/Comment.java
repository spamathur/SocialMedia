import java.util.UUID;

public class Comment {
    private String commentID;
    private String postAuthor;
    private String creator;
    private String content;
    private int votes;

    public Comment(String creator, String content, String postAuthor) {
        this.creator = creator;
        this.content = content;
        this.postAuthor = postAuthor;
        this.commentID = UUID.randomUUID().toString();
        this.votes = 0;
    }

    public void upvote() {
        votes++;
    }

    public void downvote() {
        votes--;
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

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String toString() {
        return String.format("%s;%s;%s;%s;%s", commentID, creator, content, postAuthor, votes);
    }
}
