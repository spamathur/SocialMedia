import java.io.IOException;
import java.util.List;

public interface PostsManagerInterface {
    Post findPost(String postID);

    Post createPost(String creator, String content);

    void readPosts() throws IOException;

    void writePosts() throws IOException;
}