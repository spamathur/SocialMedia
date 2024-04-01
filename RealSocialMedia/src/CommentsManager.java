import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class dedicated to modifying, finding, and saving comments
 * <p>
 * Purdue University -- CS18000 -- Spring 2024
 *
 * @author Project 5 Team 3 Lab 27
 * @version March 31, 2024
 */

public class CommentsManager implements CommentsManagerInterface {
    private static final String FILENAME = "comments.txt";
    private static List<Comment> commentsList = Collections.synchronizedList(new ArrayList<>());

    public static List<Comment> getCommentsList() {
        return commentsList;
    }

    public static void setCommentsList(List<Comment> commentsList) {
        CommentsManager.commentsList = commentsList;
    }

    public static synchronized void deleteComment(String commentID){
        for (int i = 0; i < commentsList.size(); i++){
            if (commentsList.get(i).getCommentID().equals(commentID)){
                commentsList.remove(i);
                break;
            }
        }
    }

    public static void createComment(String postID, String creator, String content) {
        Post post = PostsManager.findPost(postID);
        Comment comment = new Comment(creator, content, post.getCreator());
        post.addComment(comment);
        commentsList.add(comment);
    }

    public static synchronized Comment findComment(String commentID) {
        for (Comment comment : commentsList) {
            if (comment.getCommentID().equals(commentID))
                return comment;
        }
        return null;
    }

    public static void readComments() throws IOException {
        try (BufferedReader bfr = new BufferedReader(new FileReader(FILENAME))) {
            String line = bfr.readLine();
            while (line != null) {
                String[] commentContents = line.split(";", -1);
                Comment comment = new Comment(commentContents[1], commentContents[2], commentContents[3]);
                comment.setCommentID(commentContents[0]);
                comment.setUpvotes(Integer.parseInt(commentContents[4]));
                comment.setDownvotes(Integer.parseInt(commentContents[5]));
                commentsList.add(comment);
                line = bfr.readLine();
            }
        }
    }

    public static void writeComments() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILENAME))) {
            for (Comment comment : commentsList) {
                pw.println(comment.toString());
            }
        }
    }
}
