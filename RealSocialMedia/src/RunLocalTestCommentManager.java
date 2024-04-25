import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.*;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * A framework to run public test cases for CommentsManager.java for PJ5.
 *
 * @author Project 5 Team 3 Lab 27
 *
 * @version March 31, 2024
 */

public class RunLocalTestCommentManager {

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
        private static final String INFILE = "comments.txt";

        // To store test comment ID
        private String testCommentId;

        // Ensure the file is deleted before each test and the list is clear
        @Before
        public void setUp() throws IOException {
            new File(INFILE).delete();
            CommentsManager.setCommentsList(new ArrayList<>());
        }

        // Ensure the file is deleted after tests
        @After
        public void tearDown() {
            new File(INFILE).delete();
        }

        // Test to check create a test comment and find it
        @Test(timeout = 1000)
        public void testCreateAndFindComment() {
            String postID = UUID.randomUUID().toString();
            String creator = "creatorName";
            String content = "This is a test comment";
            CommentsManager.createComment(postID, creator, content);

            Assert.assertFalse("Comments list should not be empty", CommentsManager.getCommentsList().isEmpty());
            Comment comment = CommentsManager.getCommentsList().get(0);
            testCommentId = comment.getCommentID();

            Comment foundComment = CommentsManager.findComment(testCommentId);
            Assert.assertNotNull("Comment should be found", foundComment);
            Assert.assertEquals("Comment content should match", content, foundComment.getContent());
        }

        // Test to check if the reading and writing to the file works
        @Test(timeout = 1000)
        public void testReadAndWriteComments() throws IOException {
            String postID = UUID.randomUUID().toString();
            String creator = "Creator2";
            String content = "This is another test comment";
            CommentsManager.createComment(postID, creator, content);

            CommentsManager.writeComments();

            // Clear comments list and read from file
            CommentsManager.setCommentsList(new ArrayList<>());
            CommentsManager.readComments();

            Assert.assertFalse("Comments list should not be empty after reading from file", CommentsManager.getCommentsList().isEmpty());
            Comment readComment = CommentsManager.getCommentsList().get(0);
            Assert.assertEquals("Content of the read comment should match", content, readComment.getContent());
        }

        // Test to ensure that the class is declared properly
        @Test(timeout = 1000)
        public void CommentManagerClassDeclarationTest() {
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
            Assert.assertEquals("Ensure that `Comment` implements one interfaces!",
                    1, superinterfaces.length);
        }
    }
}
