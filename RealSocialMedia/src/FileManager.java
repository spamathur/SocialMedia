import java.io.IOException;

/**
 * Manages files for the CommentsManager, PostsManager, and UsersManager classes
 * <p>
 * Purdue University -- CS18000 -- Spring 2024
 *
 * @author Project 5 Team 3 Lab 27
 * @version March 31, 2024
 */

public class FileManager implements FileManagerInterface {
    public static void readAll() throws IOException {
        CommentsManager.readComments();
        PostsManager.readPosts();
        UsersManager.readUsers();
    }

    public static void writeAll() throws IOException{
        UsersManager.writeUsers();
        PostsManager.writePosts();
        CommentsManager.writeComments();
    }
}
