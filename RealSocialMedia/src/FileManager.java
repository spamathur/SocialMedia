import java.io.IOException;

public class FileManager implements FileManagerInterface {
    public static void readAll() throws IOException {
        CommentsManager.readComments();
        PostsManager.readPosts();
        UsersManager.readUsers();
    }

    public static void writeAll() throws IOException{
        UsersManager.writeUsers();
        PostsManager.writePosts();
        CommentsManager.writeComments();
    }
}
