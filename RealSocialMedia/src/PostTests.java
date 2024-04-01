import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.lang.reflect.Modifier;

/**
 * A framework to run public test cases for Post.java for PJ5.
 *
 * @author Project 5 Team 3 Lab 27
 *
 * @version March 31, 2024
 */

public class PostTests {

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
        // Post object to be tested
        private Post post = new Post("creator", "This is a test post");

        // Test to check post functionality of initialized post
        @Test(timeout = 1000)
        public void testPostCreation() {
            Assert.assertNotNull("Post ID should not be null after creation", post.getPostID());
            Assert.assertEquals("Creator should match constructor argument", "creator", post.getCreator());
            Assert.assertEquals("Content should match constructor argument", "This is a test post", post.getContent());
            Assert.assertEquals("Initial upvotes should be 0", 0, post.getUpvotes());
            Assert.assertEquals("Initial downvotes should be 0", 0, post.getDownvotes());
            Assert.assertTrue("Initial comments list should be empty", post.getComments().isEmpty());
        }

        // Test to check post functionality of comment on the post (extra test beside the comment tests)
        @Test(timeout = 1000)
        public void testAddComment() {
            Comment comment = new Comment("commenter", "This is a comment", "creator");
            post.addComment(comment);
            Assert.assertFalse("Comments list should not be empty after adding a comment", post.getComments().isEmpty());
            Assert.assertEquals("Comments list should contain the added comment", comment, post.getComments().get(0));
        }

        // Test to check post upvotes
        @Test(timeout = 1000)
        public void testUpvote() {
            post.upvote();
            post.upvote();
            Assert.assertEquals("Upvotes should be incremented", 2, post.getUpvotes());
        }

        // Test to check post downvotes
        @Test(timeout = 1000)
        public void testDownvote() {
            post.downvote();
            post.downvote();
            Assert.assertEquals("Downvotes should be incremented", 2, post.getDownvotes());
        }

        // Test to check set and get content methods
        @Test(timeout = 1000)
        public void testSetAndGetContent() {
            post.setContent("Updated content");
            assertEquals("Content should be updated", "Updated content", post.getContent());
        }

        // Test to check set and get creator methods
        @Test(timeout = 1000)
        public void testSetAndGetCreator() {
            post.setCreator("newCreator");
            assertEquals("Creator should be updated", "newCreator", post.getCreator());
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
