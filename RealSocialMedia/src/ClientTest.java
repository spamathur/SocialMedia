import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.*;
import java.net.Socket;

/**
 * A framework to run public test cases for Client.java for PJ5.
 *
 * @author Project 5 Team 3 Lab 27
 *
 * @version April 15, 2024
 */


public class ClientTest {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ClientTest.TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    public static class TestCase {
        private Client client;
        private Socket socket;
        private ByteArrayInputStream inStream;
        private ByteArrayOutputStream outStream;

        // Create socket and client
        @Before
        public void setUp() throws Exception {
            String mockServerResponse = "true\n";
            inStream = new ByteArrayInputStream(mockServerResponse.getBytes());
            outStream = new ByteArrayOutputStream();

            socket = new Socket() {
                @Override
                public ByteArrayInputStream getInputStream() {
                    return inStream;
                }
                @Override
                public ByteArrayOutputStream getOutputStream() {
                    return outStream;
                }
            };

            client = new Client();
            client.setSocket(socket);
            client.setBufferedReader(new BufferedReader(new InputStreamReader(socket.getInputStream())));
            client.setPrintWriter(new PrintWriter(socket.getOutputStream(), true));
            client.setConnected(true);
        }

        // Close socket and client
        @After
        public void tearDown() throws Exception {
            client.closeConnection();
            socket.close();
        }

        // Test to check logIn
        @Test(timeout = 1000)
        public void testLogIn() {
            Assert.assertTrue("Expected true when logging in with correct credentials", client.logIn("user", "password"));
        }

        // Test to check signUp
        @Test(timeout = 1000)
        public void testSignUp() {
            Assert.assertTrue("Expected true when signing up with new user credentials", client.signUp("user", "First", "Last", "password", "path/to/profilePic"));
        }

        // Test to check getting posts through client
        @Test(timeout = 1000)
        public void testGetMyPosts() {
            String mockPosts = "[{\"content\":\"Hello World!\", \"author\":\"user\"}]\n";
            inStream = new ByteArrayInputStream(mockPosts.getBytes());
            client.setBufferedReader(new BufferedReader(new InputStreamReader(inStream)));
            Post[] posts = client.getMyPosts();
            Assert.assertEquals("Expected one post", 1, posts.length);
            Assert.assertEquals("Expected post content to be 'Hello World!'", "Hello World!", posts[0].getContent());
        }

        // Test to check creating posts through client
        @Test(timeout = 1000)
        public void testCreatePost() {
            client.createPost("Test Post","upvote.png");
            String sentRequest = outStream.toString();
            Assert.assertTrue("Expected create post request to be sent", sentRequest.contains("7;Test Post"));
        }

        // Test to check creating comment through client
        @Test(timeout = 1000)
        public void testCreateComment() {
            client.createComment("1", "Nice post!");
            String sentRequest = outStream.toString();
            Assert.assertTrue("Expected create comment request to be sent", sentRequest.contains("8;1;Nice post!"));
        }

        // Test to check upvote through client
        @Test(timeout = 1000)
        public void testUpvotePost() {
            client.upvotePost("123");
            String sentRequest = outStream.toString();
            Assert.assertTrue("Expected upvote post request to be sent", sentRequest.contains("10;123"));
        }

        // Test to check downvote through client
        @Test(timeout = 1000)
        public void testDownvotePost() {
            client.downvotePost("456");
            String sentRequest = outStream.toString();
            Assert.assertTrue("Expected downvote post request to be sent", sentRequest.contains("11;456"));
        }

        // Test to check add friend through client
        @Test(timeout = 1000)
        public void testAddFriend() {
            client.addFriend("newFriend");
            String sentRequest = outStream.toString();
            Assert.assertTrue("Expected add friend request to be sent", sentRequest.contains("3;newFriend"));
        }

        // Test to check remove friend through client
        @Test(timeout = 1000)
        public void testRemoveFriend() {
            client.removeFriend("oldFriend");
            String sentRequest = outStream.toString();
            Assert.assertTrue("Expected remove friend request to be sent", sentRequest.contains("4;oldFriend"));
        }

        // Test to check search users through client
        @Test(timeout = 1000)
        public void testSearchUsers() {
            String mockUsers = "[{\"userName\":\"johndoe\",\"firstName\":\"John\",\"lastName\":\"Doe\"}]";
            ByteArrayInputStream inStream = new ByteArrayInputStream(mockUsers.getBytes());
            client.setBufferedReader(new BufferedReader(new InputStreamReader(inStream)));

            User[] users = client.searchUsers("john");
            Assert.assertNotNull("Users array should not be null", users);
            Assert.assertTrue("Expected non-empty users array", users.length > 0);
            Assert.assertEquals("Expected username to be 'johndoe'", "johndoe", users[0].getUserName());
        }

        // Test to check getting profile through client
        @Test(timeout = 1000)
        public void testGetMyProfile() {
            String mockProfile = "{\"userName\":\"user\",\"firstName\":\"First\",\"lastName\":\"Last\"}";
            ByteArrayInputStream inStream = new ByteArrayInputStream(mockProfile.getBytes());
            client.setBufferedReader(new BufferedReader(new InputStreamReader(inStream)));

            User user = client.getMyProfile();
            assertNotNull("Expected a user object", user);
            assertEquals("Expected username to be 'user'", "user", user.getUserName());
        }

        // Test to check hiding posts through client
        @Test(timeout = 1000)
        public void testHidePost() {
            client.hidePost("789");
            String sentRequest = outStream.toString();
            assertTrue("Expected hide post request to be sent", sentRequest.contains("9;789"));
        }
    }
}
