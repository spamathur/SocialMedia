import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.lang.reflect.Modifier;

/**
 * A framework to run public test cases for Comment.java for PJ5.
 *
 * @author Project 5 Team 3 Lab 27
 *
 * @version March 31, 2024
 */

public class RunLocalTestComments {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(RunLocalTestComments.TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    public static class TestCase {

        // Test to check if the initialization values are correct
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

        // Test to check if the Upvote and Downvote methods work as expected
        @Test(timeout = 1000)
        public void testUpvoteDownvote() {
            Comment comment = new Comment("creator", "content", "postAuthor");

            comment.upvote();
            Assert.assertEquals("Upvotes should increase by 1", 1, comment.getUpvotes());

            comment.downvote();
            Assert.assertEquals("Downvotes should Increase by 1", 1, comment.getDownvotes());
        }

        // Test to check if the setters work and if the getters to get those values work as well
        @Test(timeout = 1000)
        public void testSettersAndGetters() {
            Comment comment = new Comment("creator", "content", "postAuthor");

            comment.setCreator("newCreator");
            comment.setContent("New content for the comment");
            comment.setPostAuthor("newPostAuthor");
            comment.upvote();
            comment.downvote();

            Assert.assertEquals("Creator should be updated", "newCreator", comment.getCreator());
            Assert.assertEquals("Content should be updated", "New content for the comment", comment.getContent());
            Assert.assertEquals("Post author should be updated", "newPostAuthor", comment.getPostAuthor());
            Assert.assertEquals("Upvotes should be updated", 1, comment.getUpvotes());
            Assert.assertEquals("Downvotes should be updated", 1, comment.getDownvotes());
        }

        // Test to check if the toString works as expected
        @Test(timeout = 1000)
        public void testToString() {
            Comment comment = new Comment("creator", "This is a test comment", "postAuthor");
            String expected = comment.getCommentID() + ";" + "creator" + ";" + "This is a test comment" + ";" + "postAuthor" + ";0;0";
            Assert.assertEquals("toString should return the correct format", expected, comment.toString());
        }

        // Test to ensure that the class is declared properly
        @Test(timeout = 1000)
        public void CommentClassDeclarationTest() {
            Class<?> clazz;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            clazz = Comment.class;

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `Comment` is `public`!",
                    Modifier.isPublic(modifiers));
            Assert.assertFalse("Ensure that `Comment` is NOT `abstract`!",
                    Modifier.isAbstract(modifiers));
            Assert.assertEquals("Ensure that `Comment` extends `Object`!",
                    Object.class, superclass);
            Assert.assertEquals("Ensure that `Comment` implements one interfaces!",
                    1, superinterfaces.length);
        }
    }
}
