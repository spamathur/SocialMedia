import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    private User user;

    @Before
    public void setUp() throws UserNameTakenException {
        // Add a new user for testing
        user = new User("john", "John", "Doe", "password", "profile.jpg");
        UsersManager.signUp(user);
    }

    @Test
    public void testSearchUsers() throws UserNameTakenException {
        User newUser = new User("jane", "Jane", "Doe", "password", "profile.jpg");
        UsersManager.signUp(newUser);
        assertEquals(1, user.searchUsers("Jane").size());
    }

    public void testSetAndGetUserName() {
        user.setUserName("johnny");
        assertEquals("johnny", user.getUserName());
    }

    @Test
    public void testSetAndGetFirstName() {
        user.setFirstName("Johnny");
        assertEquals("Johnny", user.getFirstName());
    }

    @Test
    public void testSetAndGetLastName() {
        user.setLastName("Dough");
        assertEquals("Dough", user.getLastName());
    }

    @Test
    public void testSetAndGetPassword() {
        user.setPassword("newpassword");
        assertEquals("newpassword", user.getPassword());
    }

    @Test
    public void testSetAndGetProfilePic() {
        user.setProfilePic("new_profile.jpg");
        assertEquals("new_profile.jpg", user.getProfilePic());
    }

    @Test
    public void testGetMyFriendsPosts() {
        User friend = new User("jane", "Jane", "Doe", "password", "profile.jpg");
        Post post = new Post("jane", "Hello from Jane");
        friend.addMyPosts(post);
        user.addFriend("jane");
        UsersManager.getUsersList().add(friend);
        assertEquals(1, user.getMyFriendsPosts().size());
        assertEquals(post, user.getMyFriendsPosts().get(0));
    }

    @Test
    public void testHidePost() {
        Post post = PostsManager.createPost("john", "Hello World");
        user.addMyPosts(post);
        user.hidePost(post.getPostID());
        assertTrue(user.getMyPosts().contains(post));
        assertTrue(user.getHiddenPosts().contains(post));
    }

    @Test
    public void testCreatePost() {
        user.createPost("Hello World");
        assertEquals(1, user.getMyPosts().size());
        assertEquals("Hello World", user.getMyPosts().get(0).getContent());
    }

    @Test
    public void testCreateComment() {
        Post post = PostsManager.createPost("john", "Hello World");
        user.createComment(post.getPostID(), "Nice post!");
        assertEquals(1, post.getComments().size());
        assertEquals("Nice post!", post.getComments().get(0).getContent());
    }

    @Test
    public void testUpvotePost() {
        Post post = PostsManager.createPost("john", "Hello World");
        user.upvotePost(post.getPostID());
        assertEquals(1, post.getUpvotes());
    }

    @Test
    public void testDownvotePost() {
        Post post = PostsManager.createPost("john", "Hello World");
        user.downvotePost(post.getPostID());
        assertEquals(1, post.getDownvotes());
    }

    // Additional tests can be added for other methods.
}
