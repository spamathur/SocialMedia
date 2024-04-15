import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.Socket;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * A framework to run public test cases for ClientHandler.java for PJ5.
 *
 * @author Project 5 Team 3 Lab 27
 *
 * @version April 15, 2024
 */

public class ClientHandlerTest {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ClientHandlerTest.TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    public static class TestCase {

        // Test to check if ClientHandler functions as intended
        @Test(timeout = 1000)
        public void testConstructor() throws IOException {
            Socket testSocket = null;
            try {
                testSocket = new Socket("localhost", 4242);
                ClientHandler handler = new ClientHandler(testSocket);

                // Access the clientSocket field using reflection
                Field socketField = ClientHandler.class.getDeclaredField("clientSocket");
                socketField.setAccessible(true); // Make the field accessible
                Socket socketFieldValue = (Socket) socketField.get(handler);
                Assert.assertNotNull("Client socket should not be null", socketFieldValue);

                // Access the user field using reflection
                Field userField = ClientHandler.class.getDeclaredField("user");
                userField.setAccessible(true); // Make the field accessible
                Object userFieldValue = userField.get(handler);
                Assert.assertNull("User should be null initially", userFieldValue);

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
}
