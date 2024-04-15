import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.Assert.*;

public class ClientHandlerIntegrationTest {

    private Socket testClientSocket;
    private PrintWriter out;
    private BufferedReader in;

    @Before
    public void setUp() throws Exception {
        // Set up before each test
        testClientSocket = new Socket("localhost", 4242);
        out = new PrintWriter(testClientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(testClientSocket.getInputStream()));
    }

    @Test
    public void testSignUp() throws Exception {
        out.println("1;testuser;Test;User;password;profilepic");
        String response = in.readLine();
        assertEquals("Expected true when signing up", "true", response.trim());
    }

    @Test
    public void testLogin() throws Exception {
        out.println("2;testuser;password");
        String response = in.readLine();
        assertEquals("Expected true when logging in", "true", response.trim());
    }

    @Test
    public void testAddFriend() throws Exception {
        // Ensure the user is logged in before adding a friend
        // (login procedure omitted for brevity)
        out.println("3;frienduser");
        // Assuming the server sends a confirmation message on a successful friend addition
        String response = in.readLine();
        assertEquals("Expected confirmation of adding a friend", "Friend added!", response.trim());
    }

    @Test
    public void testRemoveFriend() throws Exception {
        // Ensure the user has friends before removing one
        // (setup omitted for brevity)
        out.println("4;frienduser");
        // Assuming the server sends a confirmation message on successful friend removal
        String response = in.readLine();
        assertEquals("Expected confirmation of friend removal", "Friend removed!", response.trim());
    }

    @Test
    public void testBlockFriend() throws Exception {
        // Ensure the user has friends before blocking
        // (setup omitted for brevity)
        out.println("5;frienduser");
        // Assuming the server sends a confirmation message on successful block
        String response = in.readLine();
        assertEquals("Expected confirmation of blocking a friend", "Friend blocked!", response.trim());
    }

    @Test
    public void testUnblockFriend() throws Exception {
        // Ensure the user has blocked friends before unblocking
        // (setup omitted for brevity)
        out.println("6;frienduser");
        // Assuming the server sends a confirmation message on successful unblock
        String response = in.readLine();
        assertEquals("Expected confirmation of unblocking a friend", "Friend unblocked!", response.trim());
    }

    @Test
    public void testCreatePost() throws Exception {
        out.println("7;This is a test post");
        // Assuming the server sends a confirmation message on successful post creation
        String response = in.readLine();
        assertEquals("Expected confirmation of creating a post", "Post created!", response.trim());
    }

    @Test
    public void testHidePost() throws Exception {
        // Ensure the user has posts before hiding one
        // (setup omitted for brevity)
        out.println("9;postId");
        // Assuming the server sends a confirmation message on successful post hiding
        String response = in.readLine();
        assertEquals("Expected confirmation of hiding a post", "Post hidden!", response.trim());
    }

    @Test
    public void testUpvotePost() throws Exception {
        // Ensure there are posts to upvote
        // (setup omitted for brevity)
        out.println("10;postId");
        // Assuming the server sends a confirmation message on successful upvote
        String response = in.readLine();
        assertEquals("Expected confirmation of upvoting a post", "Post upvoted!", response.trim());
    }

    @Test
    public void testDownvotePost() throws Exception {
        // Ensure there are posts to downvote
        // (setup omitted for brevity)
        out.println("11;postId");
        // Assuming the server sends a confirmation message on successful downvote
        String response = in.readLine();
        assertEquals("Expected confirmation of downvoting a post", "Post downvoted!", response.trim());
    }

    @Test
    public void testUpvoteComment() throws Exception {
        // Ensure there are comments to upvote
        // (setup omitted for brevity)
        out.println("12;commentId");
        // Assuming the server sends a confirmation message on successful upvote
        String response = in.readLine();
        assertEquals("Expected confirmation of upvoting a comment", "Comment upvoted!", response.trim());
    }

    @Test
    public void testDownvoteComment() throws Exception {
        // Ensure there are comments to downvote
        // (setup omitted for brevity)
        out.println("13;commentId");
        // Assuming the server sends a confirmation message on successful downvote
        String response = in.readLine();
        assertEquals("Expected confirmation of downvoting a comment", "Comment downvoted!", response.trim());
    }

    @Test
    public void testGetMyFriendsPosts() throws Exception {
        out.println("14;");
        String response = in.readLine();
        assertTrue("Expected a JSON array of posts", response.trim().startsWith("["));
    }

    @Test
    public void testGetMyPosts() throws Exception {
        out.println("15;");
        String response = in.readLine();
        assertTrue("Expected a JSON array of my posts", response.trim().startsWith("["));
    }

    @Test
    public void testSearchUsers() throws Exception {
        out.println("16;searchTerm");
        String response = in.readLine();
        assertTrue("Expected a JSON array of users", response.trim().startsWith("["));
    }

    @Test
    public void testGetMyProfile() throws Exception {
        out.println("17;");
        String response = in.readLine();
        assertTrue("Expected a JSON object of my profile", response.trim().startsWith("{"));
    }

    @Test
    public void testLogout() throws Exception {
        out.println("18;");
        String response = in.readLine();
        assertEquals("Expected confirmation of logout", "Goodbye!", response.trim());
    }

    @After
    public void tearDown() throws Exception {
        // Tear down after each test
        out.close();
        in.close();
        testClientSocket.close();
    }
}
