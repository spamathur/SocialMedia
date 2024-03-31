import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PostsManagerTests {
    private static final String TEST_FILENAME = "test_posts.txt";
    private Post post;

    @Before
    public void setUp() throws Exception {
        setPrivateStaticField(PostsManager.class, "FILENAME", TEST_FILENAME);

        post = PostsManager.createPost("testUser", "This is a test post");
    }

    @After
    public void tearDown() throws Exception {
        Files.deleteIfExists(Path.of(TEST_FILENAME));
    }

    @Test
    public void testCreatePost() {
        assertNotNull("Post should not be null after creation", post);
        assertEquals("Post content should match", "This is a test post", post.getContent());
        assertEquals("Post creator should match", "testUser", post.getCreator());
    }

    @Test
    public void testFindPost() {
        Post foundPost = PostsManager.findPost(post.getPostID());
        assertEquals("Found post should match the created post", post, foundPost);
    }

    @Test
    public void testWriteAndReadPosts() throws IOException {
        PostsManager.writePosts();
        PostsManager.postsList.clear();
        PostsManager.readPosts();

        Post readPost = PostsManager.findPost(post.getPostID());
        assertNotNull("Post should be read from file", readPost);
        assertEquals("Content of read post should match", post.getContent(), readPost.getContent());
    }

    @Test
    public void testConcurrency() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            service.submit(() -> {
                PostsManager.createPost("concurrentUser", "Concurrent post content");
            });
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);
        
        try {
            PostsManager.writePosts();
        } catch (IOException e) {
            fail("IOException should not occur");
        }

        assertTrue("There should be 101 posts after concurrent post creation (including setUp post)",
                PostsManager.postsList.size() == 101);
    }

    private void setPrivateStaticField(Class<?> clazz, String fieldName, Object value) throws Exception {
        java.lang.reflect.Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        java.lang.reflect.Field modifiersField = java.lang.reflect.Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~java.lang.reflect.Modifier.FINAL);
        field.set(null, value);
    }
}
