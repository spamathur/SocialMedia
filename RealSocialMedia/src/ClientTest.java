import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

public class ClientTest {
    private Client client;
    private Socket socket;
    private ByteArrayInputStream inStream;
    private ByteArrayOutputStream outStream;

    @Before
    public void setUp() throws Exception {
        // Mock Socket and Streams
        String mockServerResponse = "true\n";  // Mock response for login/signUp methods
        inStream = new ByteArrayInputStream(mockServerResponse.getBytes());
        outStream = new ByteArrayOutputStream();

        // Create socket and client
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

    @After
    public void tearDown() throws Exception {
        client.closeConnection();
        socket.close();
    }

    @Test
    public void testLogIn() {
        assertTrue("Expected true when logging in with correct credentials", client.logIn("user", "password"));
    }

    @Test
    public void testSignUp() {
        assertTrue("Expected true when signing up with new user credentials", client.signUp("user", "First", "Last", "password", "path/to/profilePic"));
    }

    @Test
    public void testGetMyPosts() {
        String mockPosts = "[{\"content\":\"Hello World!\", \"author\":\"user\"}]\n";
        inStream = new ByteArrayInputStream(mockPosts.getBytes());
        client.setBufferedReader(new BufferedReader(new InputStreamReader(inStream)));
        Post[] posts = client.getMyPosts();
        assertEquals("Expected one post", 1, posts.length);
        assertEquals("Expected post content to be 'Hello World!'", "Hello World!", posts[0].getContent());
    }

    @Test
    public void testCreatePost() {
        client.createPost("Test Post");
        String sentRequest = outStream.toString();
        assertTrue("Expected create post request to be sent", sentRequest.contains("7;Test Post"));
    }

    @Test
    public void testCreateComment() {
        client.createComment("1", "Nice post!");
        String sentRequest = outStream.toString();
        assertTrue("Expected create comment request to be sent", sentRequest.contains("8;1;Nice post!"));
    }

    @Test
    public void testUpvotePost() {
        client.upvotePost("123");
        String sentRequest = outStream.toString();
        assertTrue("Expected upvote post request to be sent", sentRequest.contains("10;123"));
    }

    @Test
    public void testDownvotePost() {
        client.downvotePost("456");
        String sentRequest = outStream.toString();
        assertTrue("Expected downvote post request to be sent", sentRequest.contains("11;456"));
    }

    @Test
    public void testAddFriend() {
        client.addFriend("newFriend");
        String sentRequest = outStream.toString();
        assertTrue("Expected add friend request to be sent", sentRequest.contains("3;newFriend"));
    }

    @Test
    public void testRemoveFriend() {
        client.removeFriend("oldFriend");
        String sentRequest = outStream.toString();
        assertTrue("Expected remove friend request to be sent", sentRequest.contains("4;oldFriend"));
    }

    @Test
    public void testSearchUsers() {
        String mockUsers = "[{\"username\":\"johndoe\",\"firstName\":\"John\",\"lastName\":\"Doe\"}]\n";
        inStream = new ByteArrayInputStream(mockUsers.getBytes());
        client.setBufferedReader(new BufferedReader(new InputStreamReader(inStream)));
        User[] users = client.searchUsers("john");
        assertEquals("Expected one user", 1, users.length);
        assertEquals("Expected username to be 'johndoe'", "johndoe", users[0].getUserName());
    }

    @Test
    public void testGetMyProfile() {
        String mockProfile = "{\"username\":\"user\",\"firstName\":\"First\",\"lastName\":\"Last\"}\n";
        inStream = new ByteArrayInputStream(mockProfile.getBytes());
        client.setBufferedReader(new BufferedReader(new InputStreamReader(inStream)));
        User user = client.getMyProfile();
        assertNotNull("Expected a user object", user);
        assertEquals("Expected username to be 'user'", "user", user.getUserName());
    }

    @Test
    public void testHidePost() {
        client.hidePost("789");
        String sentRequest = outStream.toString();
        assertTrue("Expected hide post request to be sent", sentRequest.contains("9;789"));
    }
}
