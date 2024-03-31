import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PostsManagerTests {
    private final static String TEST_FILENAME = "test_posts.txt";

    @Before
    public void setUp() throws Exception {
        setPrivateStaticField(PostsManager.class, "FILENAME", TEST_FILENAME);
        PostsManager.getPostsList().clear();
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(Path.of(TEST_FILENAME));
    }

    @Test
    public void testCreateAndFindPost() {
        Post post = PostsManager.createPost("testUser", "Hello World");
        assertNotNull(post);

        Post foundPost = PostsManager.findPost(post.getPostID());
        assertEquals("The found post should match the created post", post, foundPost);
    }

    @Test
    public void testGetPostsList() {
        PostsManager.createPost("user1", "Post 1");
        PostsManager.createPost("user2", "Post 2");

        List<Post> posts = PostsManager.getPostsList();
        assertEquals("There should be 2 posts in the list", 2, posts.size());
    }

    @Test
    public void testReadAndWritePosts() throws IOException {
        PostsManager.createPost("testUser", "Test post content");
        PostsManager.writePosts();
        PostsManager.getPostsList().clear();
        PostsManager.readPosts();
        List<Post> posts = PostsManager.getPostsList();
        assertEquals("There should be 1 post after reading from the file", 1, posts.size());
        assertEquals("The post content should match", "Test post content", posts.get(0).getContent());
    }

    private static void setPrivateStaticField(Class<?> clazz, String fieldName, Object value) throws Exception {
        java.lang.reflect.Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(null, value);
    }
}
