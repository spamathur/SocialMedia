import java.io.IOException;

public interface FileManagerInterface {
    void readAll() throws IOException;

    void writeAll() throws IOException;
}