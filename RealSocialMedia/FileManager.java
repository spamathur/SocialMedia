import java.io.IOException;

public class FileManager {
    public static void readAll() throws IOException {
        UsersManager.readUsers();
        PostsManager.readPosts();
        CommentsManager.readComments();
    }

    public static void writeAll() throws IOException{
        UsersManager.writeUsers();
        PostsManager.writePosts();
        CommentsManager.writeComments();
    }
}
