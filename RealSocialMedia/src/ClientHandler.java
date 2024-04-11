import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import com.google.gson.Gson;

/**
 * Project 5 -- Social Media
 * <p>
 * This class is the thread that
 * deals with every client. It
 * implements runnable.
 *
 * @author Project 5 Team 3 Lab 27
 * @version March 31, 2024
 */
public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private User user;
    Gson gson = new Gson();
    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try (BufferedReader bfr = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true)) {

            boolean authSuccess = false;
            while (!authSuccess) {
                String inputLine = bfr.readLine();
                System.out.println(inputLine);
                if (inputLine == null || inputLine.isEmpty())
                    continue;

                ArrayList<String> authentication = new ArrayList<>(Arrays.asList(inputLine.split(";")));
                int action = Integer.parseInt(authentication.remove(0));

                if (action == 1) {
                    try {
                        user = UsersManager.signUp(new User(authentication.get(0), authentication.get(1),
                                authentication.get(2), authentication.get(3), authentication.get(4)));
                        pw.println("true");
                        authSuccess = true;
                    } catch (UserNameTakenException e) {
                        pw.println("false");
                    }
                }
                else if (action == 2) {
                    try {
                        user = UsersManager.logIn(authentication.get(0), authentication.get(1));
                        pw.println("true");
                        authSuccess = true;
                    } catch (LogInFailedException e) {
                        pw.println("false");
                    }
                }
                FileManager.writeAll();
            }

            boolean endSession = false;
            while (!endSession) {
                String inputLine = bfr.readLine();
                System.out.println(inputLine);
                if (inputLine == null || inputLine.isEmpty())
                    continue;

                ArrayList<String> parameters = new ArrayList<>(Arrays.asList(inputLine.split(";")));
                int command = Integer.parseInt(parameters.remove(0));

                switch (command) {
                    case 3:
                        user.addFriend(parameters.get(0));
                        break;
                    case 4:
                        user.removeFriend(parameters.get(0));
                        break;
                    case 5:
                        user.blockFriend(parameters.get(0));
                        break;
                    case 6:
                        user.unblockFriend(parameters.get(0));
                        break;
                    case 7:
                        user.createPost(parameters.get(0));
                        break;
                    case 8:
                        user.createComment(parameters.get(0), parameters.get(1));
                        break;
                    case 9:
                        user.hidePost(parameters.get(0));
                        break;
                    case 10:
                        user.upvotePost(parameters.get(0));
                        break;
                    case 11:
                        user.downvotePost(parameters.get(0));
                        break;
                    case 12:
                        user.upvoteComment(parameters.get(0));
                        break;
                    case 13:
                        user.downvoteComment(parameters.get(0));
                        break;
                    case 14:
                        ArrayList<Post> friendsPosts = user.getMyFriendsPosts();
                        pw.println(gson.toJson(friendsPosts));
                        break;
                    case 15:
                        ArrayList<Post> myPosts = user.getMyPosts();
                        pw.println(gson.toJson(myPosts));
                        break;
                    case 16:
                        ArrayList<User> matchedUsers = user.searchUsers(parameters.get(0));
                        pw.println(gson.toJson(matchedUsers));
                        break;
                    case 17:
                        pw.println(gson.toJson(user));
                        break;
                    case 18:
                        endSession = true;
                }
                FileManager.writeAll();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
