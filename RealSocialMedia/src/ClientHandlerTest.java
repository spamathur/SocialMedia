import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.Socket;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ClientHandlerTest {

    @Test
    public void testConstructor() throws IOException {
        Socket testSocket = null;
        try {
            // Ensure the server is running and listening on this port
            testSocket = new Socket("localhost", 4242);
            ClientHandler handler = new ClientHandler(testSocket);

            // Access the clientSocket field using reflection
            Field socketField = ClientHandler.class.getDeclaredField("clientSocket");
            socketField.setAccessible(true); // Make the field accessible
            Socket socketFieldValue = (Socket) socketField.get(handler);
            assertNotNull("Client socket should not be null", socketFieldValue);

            // Access the user field using reflection
            Field userField = ClientHandler.class.getDeclaredField("user");
            userField.setAccessible(true); // Make the field accessible
            Object userFieldValue = userField.get(handler);
            assertNull("User should be null initially", userFieldValue);

        } catch (Exception e) {
            e.printStackTrace();
            assert false : "Constructor test failed due to an exception: " + e.getMessage();
        } finally {
            if (testSocket != null) {
                testSocket.close();
            }
        }
    }
}
