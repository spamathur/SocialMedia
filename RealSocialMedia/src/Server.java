import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Project 5 -- Social Media
 * <p>
 * This class is a server that
 * accepts clients and starts
 * an echo server and tasks the
 * echo server to communicate with
 * client.
 *
 * @author Project 5 Team 3 Lab 27
 * @version March 31, 2024
 */
public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(4243)) {
            FileManager.readAll();
            while (true) {
                System.out.println("Waiting to connect...");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected...");
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
