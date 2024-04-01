import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * A framework to run public test cases for PostsManager.java for PJ5.
 *
 * @author Project 5 Team 3 Lab 27
 *
 * @version March 31, 2024
 */

public class PostsManagerTests {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(RunLocalTestCommentManager.TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    public static class TestCase {
        // Test file name
        private final static String INFILE = "posts.txt";

        // Ensure the file is deleted before each test and the list is clear
        @Before
        public void setUp() throws Exception {
            new File(INFILE).delete();
            PostsManager.setPostsList(new ArrayList<>());
        }

        // Ensure the file is deleted after tests
        @After
        public void tearDown() throws Exception {
            new File(INFILE).delete();
        }

        // Test to check create a test post and find it
        @Test(timeout = 1000)
        public void testCreateAndFindPost() {
            PostsManager.setPostsList(new ArrayList<>());
            Post post = PostsManager.createPost("testUser", "Hello World");
            Assert.assertNotNull("Post should be created", PostsManager.getPostsList());

            Post foundPost = PostsManager.findPost(post.getPostID());
            Assert.assertEquals("The found post should match the created post", post, foundPost);
        }

        // Test to check if the post lists are updated properly
        @Test(timeout = 1000)
        public void testGetPostsList() {
            PostsManager.setPostsList(new ArrayList<>());
            PostsManager.createPost("user1", "Post 1");
            PostsManager.createPost("user2", "Post 2");

            List<Post> posts = PostsManager.getPostsList();
            Assert.assertEquals("There should be 2 posts in the list", 2, posts.size());
        }

        // Test to check if the reading and writing to the file works
        @Test(timeout = 1000)
        public void testReadAndWritePosts() throws IOException {
            PostsManager.setPostsList(new ArrayList<>());
            PostsManager.createPost("testUser", "Test post content");
            PostsManager.writePosts();
            PostsManager.setPostsList(new ArrayList<>());
            PostsManager.readPosts();
            List<Post> posts = PostsManager.getPostsList();
            Assert.assertEquals("There should be 1 post after reading from the file", 1, posts.size());
            Assert.assertEquals("The post content should match", "Test post content", posts.get(0).getContent());
        }

        // Test to ensure that the class is declared properly
        @Test(timeout = 1000)
        public void CommentClassDeclarationTest() {
            Class<?> clazz;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            clazz = CommentsManager.class;

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `Comment` is `public`!",
                    Modifier.isPublic(modifiers));
            Assert.assertFalse("Ensure that `Comment` is NOT `abstract`!",
                    Modifier.isAbstract(modifiers));
            Assert.assertEquals("Ensure that `Comment` extends `Object`!",
                    Object.class, superclass);
            Assert.assertEquals("Ensure that `Comment` implements no interfaces!",
                    0, superinterfaces.length);
        }
    }
}
