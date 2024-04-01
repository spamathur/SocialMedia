import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * A framework to run public test cases for FileManager.java for PJ5.
 *
 * @author Project 5 Team 3 Lab 27
 *
 * @version March 31, 2024
 */

public class FileManagerTest {

    @Before
    public void setUp() {
        // Clear data before each test
        UsersManager.clearUsers();
        PostsManager.setPostsList(new ArrayList<>());
        CommentsManager.getCommentsList().clear(); // Clear the comments list directly
    }

    @After
    public void tearDown() {
        // Clear data after each test
        UsersManager.clearUsers();
        PostsManager.setPostsList(new ArrayList<>());
        CommentsManager.getCommentsList().clear(); // Clear the comments list directly
    }

    @Test
    public void testReadAndWriteAll() throws IOException, UserNameTakenException {
        // Create sample data
        User user = new User("john", "John", "Doe", "password", "profile.jpg");
        UsersManager.signUp(user);
        Post post = PostsManager.createPost("john", "Hello world!");
        Comment comment = new Comment("john", "Nice post!", "john");
        post.addComment(comment);

        // Write data to files
        FileManager.writeAll();

        // Clear in-memory data
        UsersManager.clearUsers();
        PostsManager.setPostsList(new ArrayList<>());
        CommentsManager.getCommentsList().clear(); // Clear the comments list directly

        // Read data from files
        FileManager.readAll();

        // Assert that data is correctly read from files
        assertEquals("Users list should have 1 user", 1, UsersManager.getUsersList().size());
        assertEquals("Posts list should have 1 post", 1, PostsManager.getPostsList().size());
        assertEquals("Comments list should have 1 comment", 1, CommentsManager.getCommentsList().size());
    }
}
