import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * A framework to run public test cases for User.java for PJ5.
 *
 * @author Project 5 Team 3 Lab 27
 *
 * @version March 31, 2024
 */

public class UserTest {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(UserTest.TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    public static class TestCase {
        // User object to be tested
        private User user;

        // Set up the user object
        @Before
        public void setUp() throws UserNameTakenException {
            // Add a new user for testing
            user = new User("john", "John", "Doe", "password", "profile.jpg");
        }

        // Test to check the search functionality
        @Test(timeout = 1000)
        public void testSearchUsers() throws UserNameTakenException {
            User newUser = new User("jane", "Jane", "Doe", "password", "profile.jpg");
            UsersManager.signUp(newUser);
            Assert.assertEquals(1, user.searchUsers("Jane").size());
        }

        // Test to check the setters
        @Test(timeout = 1000)
        public void testSetAndGetUserName() {
            user.setUserName("johnny");
            Assert.assertEquals("johnny", user.getUserName());
            user.setFirstName("Johnny");
            Assert.assertEquals("Johnny", user.getFirstName());
            user.setLastName("Dough");
            Assert.assertEquals("Dough", user.getLastName());
            user.setPassword("newpassword");
            Assert.assertEquals("newpassword", user.getPassword());
            user.setProfilePic("new_profile.jpg");
            Assert.assertEquals("new_profile.jpg", user.getProfilePic());
        }

        // Test to check getting friends posts
        @Test(timeout = 1000)
        public void testGetMyFriendsPosts() throws UserNameTakenException {
            User friend = new User("chenbo", "Chenbo", "Doe", "password", "profile.jpg");
            UsersManager.signUp(friend);
            user.addFriend("chenbo");
            friend.createPost("Testing something","upvote.png");
            Assert.assertEquals(1, user.getMyFriendsPosts().size());
        }

        // Test to check hide post
        @Test(timeout = 1000)
        public void testHidePost() {
            Post post = PostsManager.createPost("john", "Hello World","upvote.png");
            user.addMyPosts(post);
            user.hidePost(post.getPostID());
            Assert.assertTrue(user.getMyPosts().contains(post));
            User friend = new User("jane", "Jane", "Doe", "password", "profile.jpg");
            user.addFriend("jane");
            Assert.assertFalse(friend.getMyFriendsPosts().contains(post));
            Assert.assertTrue(user.getHiddenPosts().contains(post));
        }

        // Test to check creating a post
        @Test(timeout = 1000)
        public void testCreatePost() {
            user.setMyPosts(new ArrayList<>());
            user.createPost("Hello World 2","upvote.png");
            Assert.assertEquals(1, user.getMyPosts().size());
            Assert.assertEquals("Hello World 2", user.getMyPosts().get(0).getContent());
        }

        // Test to check creating a comment
        @Test(timeout = 1000)
        public void testCreateComment() {
            user.setMyPosts(new ArrayList<>());
            user.createPost("Hello World 3","upvote.png");
            Post post = user.getMyPosts().get(0);
            user.createComment(post.getPostID(), "Nice post!");
            Assert.assertEquals(1, post.getComments().size());
            Assert.assertEquals("Nice post!", post.getComments().get(0).getContent());
        }

        // Test to check upvote
        @Test(timeout = 1000)
        public void testUpvotePost() {
            user.setMyPosts(new ArrayList<>());
            Post post = PostsManager.createPost("john", "Hello World","upvote.png");
            user.upvotePost(post.getPostID());
            Assert.assertEquals(1, post.getVotes());
        }

        // Test to check downvote
        @Test(timeout = 1000)
        public void testDownvotePost() {
            user.setMyPosts(new ArrayList<>());
            Post post = PostsManager.createPost("john", "Hello World","upvote.png");
            user.downvotePost(post.getPostID());
            Assert.assertEquals(-1, post.getVotes());
        }

        // Test to ensure that the class is declared properly
        @Test(timeout = 1000)
        public void UserClassDeclarationTest() {
            Class<?> clazz;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            clazz = User.class;

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
