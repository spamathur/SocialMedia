import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PostTests {
    private Post post;

    @Before
    public void setUp() {
        post = new Post("creator", "This is a test post");
    }

    @Test
    public void testPostCreation() {
        assertNotNull("Post ID should not be null after creation", post.getPostID());
        assertEquals("Creator should match constructor argument", "creator", post.getCreator());
        assertEquals("Content should match constructor argument", "This is a test post", post.getContent());
        assertEquals("Initial upvotes should be 0", 0, post.getUpvotes());
        assertEquals("Initial downvotes should be 0", 0, post.getDownvotes());
        assertTrue("Initial comments list should be empty", post.getComments().isEmpty());
    }

    @Test
    public void testAddComment() {
        Comment comment = new Comment("commenter", "This is a comment", "creator");
        post.addComment(comment);
        assertFalse("Comments list should not be empty after adding a comment", post.getComments().isEmpty());
        assertEquals("Comments list should contain the added comment", comment, post.getComments().get(0));
    }

    @Test
    public void testUpvote() {
        post.upvote();
        assertEquals("Upvotes should be incremented", 1, post.getUpvotes());
    }

    @Test
    public void testDownvote() {
        post.downvote();
        assertEquals("Downvotes should be incremented", 1, post.getDownvotes());
    }

    @Test
    public void testSetAndGetContent() {
        post.setContent("Updated content");
        assertEquals("Content should be updated", "Updated content", post.getContent());
    }

    @Test
    public void testSetAndGetCreator() {
        post.setCreator("newCreator");
        assertEquals("Creator should be updated", "newCreator", post.getCreator());
    }

}
