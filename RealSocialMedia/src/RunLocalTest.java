import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RunLocalTest {
    private Comment comment;
    private String creator = "creatorName";
    private String content = "This is a test comment";
    private String postAuthor = "postAuthorName";

    @BeforeEach
    void setUp() {
        comment = new Comment(creator, content, postAuthor);
    }

    @Test
    void testCommentInitialization() {
        assertNotNull(comment.getCommentID(), "Comment ID should not be null");
        assertEquals(creator, comment.getCreator(), "Creator should match the initialization value");
        assertEquals(content, comment.getContent(), "Content should match the initialization value");
        assertEquals(postAuthor, comment.getPostAuthor(), "Post author should match the initialization value");
        assertEquals(0, comment.getUpvotes(), "Initial upvotes should be 0");
        assertEquals(0, comment.getDownvotes(), "Initial downvotes should be 0");
    }

    @Test
    void testUpvote() {
        comment.upvote();
        assertEquals(1, comment.getUpvotes(), "Upvotes should increase by 1");
    }

    @Test
    void testDownvote() {
        comment.downvote();
        assertEquals(1, comment.getDownvotes(), "Downvotes should increase by 1");
    }

    @Test
    void testSettersAndGetters() {
        String newCreator = "newCreator";
        String newContent = "New content for the comment";
        String newPostAuthor = "newPostAuthor";
        int newUpvotes = 5;
        int newDownvotes = 3;

        comment.setCreator(newCreator);
        comment.setContent(newContent);
        comment.setPostAuthor(newPostAuthor);
        comment.setUpvotes(newUpvotes);
        comment.setDownvotes(newDownvotes);

        assertEquals(newCreator, comment.getCreator(), "Creator should be updated");
        assertEquals(newContent, comment.getContent(), "Content should be updated");
        assertEquals(newPostAuthor, comment.getPostAuthor(), "Post author should be updated");
        assertEquals(newUpvotes, comment.getUpvotes(), "Upvotes should be updated");
        assertEquals(newDownvotes, comment.getDownvotes(), "Downvotes should be updated");
    }

    @Test
    void testToString() {
        String expected = comment.getCommentID() + ";" + creator + ";" + content + ";" + postAuthor + ";0;0";
        assertEquals(expected, comment.toString(), "toString should return the correct format");
    }
}
