import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.*;
import java.lang.reflect.Modifier;


/**
 * A framework to run public test cases for Comment functionality for PJ5.
 *
 * @author Project 5 Team 3 Lab 27
 *
 * @version March 31, 2024
 */

public class RunLocalTest {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    public static class TestCase {
        private static final String INFILE = "comments.txt";

        @Test(timeout = 1000)
        public void testCommentInitialization() {
            String creator = "creatorName";
            String content = "This is a test comment";
            String postAuthor = "postAuthorName";
            Comment comment = new Comment(creator, content, postAuthor);

            Assert.assertNotNull("Comment ID should not be null", comment.getCommentID());
            Assert.assertEquals("Creator should match the initialization value", creator, comment.getCreator());
            Assert.assertEquals("Content should match the initialization value", content, comment.getContent());
            Assert.assertEquals("Post author should match the initialization value", postAuthor, comment.getPostAuthor());
            Assert.assertEquals("Initial upvotes should be 0", 0, comment.getUpvotes());
            Assert.assertEquals("Initial downvotes should be 0", 0, comment.getDownvotes());
        }

        @Test(timeout = 1000)
        public void testUpvoteDownvote() {
            Comment comment = new Comment("creator", "content", "postAuthor");

            comment.upvote();
            Assert.assertEquals("Upvotes should increase by 1", 1, comment.getUpvotes());

            comment.downvote();
            Assert.assertEquals("Downvotes should increase by 1", 1, comment.getDownvotes());
        }

        @Test(timeout = 1000)
        public void testSettersAndGetters() {
            Comment comment = new Comment("creator", "content", "postAuthor");

            comment.setCreator("newCreator");
            comment.setContent("New content for the comment");
            comment.setPostAuthor("newPostAuthor");
            comment.setUpvotes(5);
            comment.setDownvotes(3);

            Assert.assertEquals("Creator should be updated", "newCreator", comment.getCreator());
            Assert.assertEquals("Content should be updated", "New content for the comment", comment.getContent());
            Assert.assertEquals("Post author should be updated", "newPostAuthor", comment.getPostAuthor());
            Assert.assertEquals("Upvotes should be updated", 5, comment.getUpvotes());
            Assert.assertEquals("Downvotes should be updated", 3, comment.getDownvotes());
        }

        @Test(timeout = 1000)
        public void testToString() {
            Comment comment = new Comment("creator", "This is a test comment", "postAuthor");
            String expected = comment.getCommentID() + ";" + "creator" + ";" + "This is a test comment" + ";" + "postAuthor" + ";0;0";
            Assert.assertEquals("toString should return the correct format", expected, comment.toString());
        }
    }
}