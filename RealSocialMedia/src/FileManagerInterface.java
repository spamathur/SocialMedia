import java.io.IOException;

/**
 * Manages files for the CommentsManager, PostsManager, and UsersManager classes
 * <p>
 * Purdue University -- CS18000 -- Spring 2024
 *
 * @author Project 5 Team 3 Lab 27
 * @version March 31, 2024
 */

public interface FileManagerInterface {
    void readAll() throws IOException; //reads comments, posts, and users

    void writeAll() throws IOException; //writes users, posts, and comments
}
