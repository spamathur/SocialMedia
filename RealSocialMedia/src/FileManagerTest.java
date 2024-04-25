import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.IOException;
import java.lang.reflect.Modifier;
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

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(FileManagerTest.TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    public static class TestCase {
        // Clear data before each test
        @Before
        public void setUp() {
            UsersManager.clearUsers();
            PostsManager.setPostsList(new ArrayList<>());
            CommentsManager.setCommentsList(new ArrayList<>());
        }

        // Clear data after each test
        @After
        public void tearDown() {
            UsersManager.clearUsers();
            PostsManager.setPostsList(new ArrayList<>());
            CommentsManager.setCommentsList(new ArrayList<>());
        }

        // Test to check the read and write functionality
        @Test(timeout = 1000)
        public void testReadAndWriteAll() throws IOException, UserNameTakenException {
            User user = new User("john", "John", "Doe", "password", "profile.jpg");
            UsersManager.signUp(user);
            Post post = PostsManager.createPost("john", "Hello world!","upvote.png");
            CommentsManager.createComment(post.getPostID(),"john", "Nice post!");

            FileManager.writeAll();

            UsersManager.clearUsers();
            PostsManager.setPostsList(new ArrayList<>());
            CommentsManager.setCommentsList(new ArrayList<>());

            FileManager.readAll();

            Assert.assertEquals("Users list should have 1 user", 1, UsersManager.getUsersList().size());
            Assert.assertEquals("Posts list should have 1 post", 1, PostsManager.getPostsList().size());
            Assert.assertEquals("Comments list should have 1 comment", 1, CommentsManager.getCommentsList().size());
        }

        // Test to ensure that the class is declared properly
        @Test(timeout = 1000)
        public void FileManagerDeclarationTest() {
            Class<?> clazz;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            clazz = FileManager.class;

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `FileManager` is `public`!",
                    Modifier.isPublic(modifiers));
            Assert.assertFalse("Ensure that `FileManager` is NOT `abstract`!",
                    Modifier.isAbstract(modifiers));
            Assert.assertEquals("Ensure that `FileManager` extends `Object`!",
                    Object.class, superclass);
            Assert.assertEquals("Ensure that `FileManager` implements one interfaces!",
                    1, superinterfaces.length);
        }
    }


}
