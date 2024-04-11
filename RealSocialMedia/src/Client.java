import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.List;

/**
 * Project 5 -- Social Media
 * <p>
 * This class is a client that
 * communicates with the server.
 *
 * @author Project 5 Team 3 Lab 27
 * @version March 31, 2024
 */
public class Client {
    private Socket socket;
    private BufferedReader bfr;
    private PrintWriter pw;
    private boolean isConnected = false;
    public static Gson gson = new Gson();

    public Client() {
        try {
            socket = new Socket("localhost", 4242);
            bfr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);
            isConnected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Post[] getMyFriendsPosts() {
        sendRequest("14;");
        String json = getResponse();
        Type listType = new TypeToken<List<Post>>() {
        }.getType();
        List<Post> posts = gson.fromJson(json, listType);
        return posts.toArray(new Post[0]);
    }

    public Post[] getMyPosts() {
        sendRequest("15;");
        String json = getResponse();
        Type listType = new TypeToken<List<Post>>() {
        }.getType();
        List<Post> posts = gson.fromJson(json, listType);
        return posts.toArray(new Post[0]);
    }

    public void upvotePost(String postID) {
        sendRequest(String.format("10;%s", postID));
    }

    public void upvoteComment(String commentID) {
        sendRequest(String.format("12;%s", commentID));
    }

    public void downvotePost(String postID) {
        sendRequest(String.format("11;%s", postID));
    }

    public void downvoteComment(String commentID) {
        sendRequest(String.format("13;%s", commentID));
    }

    public boolean logIn(String username, String password) {
        sendRequest(String.format("2;%s;%s", username, password));
        return Boolean.parseBoolean(getResponse());
    }

    public boolean signUp(String username, String firstName, String lastName, String password, String profilePic) {
        sendRequest(String.format("1;%s;%s;%s;%s;%s", username, firstName, lastName, password, profilePic));
        return Boolean.parseBoolean(getResponse());
    }

    public void addFriend(String username) {
        sendRequest(String.format("3;%s", username));
    }

    public void removeFriend(String username) {
        sendRequest(String.format("4;%s", username));
    }

    public void blockFriend(String username) {
        sendRequest(String.format("5;%s", username));
    }

    public void unblockFriend(String username) {
        sendRequest(String.format("6;%s", username));
    }

    public User[] searchUsers(String searchString) {
        sendRequest(String.format("16;%s", searchString));
        String json = getResponse();
        Type listType = new TypeToken<List<User>>() {
        }.getType();
        List<User> users = gson.fromJson(json, listType);
        return users.toArray(new User[0]);
    }

    public void hidePost(String postID) {
        sendRequest(String.format("9;%s", postID));
    }

    public void createPost(String content) {
        sendRequest(String.format("7;%s", content));
    }

    public void createComment(String postID, String commentString) {
        sendRequest(String.format("8;%s;%s", postID, commentString));
    }

    public User getMyProfile() {
        sendRequest("17;");
        String json = getResponse();
        return gson.fromJson(json, User.class);
    }

    public void sendRequest(String request) {
        if (isConnected) {
            pw.println(request);
        } else {
            System.out.println("Client is not connected to server.");
        }
    }

    public String getResponse() {
        try {
            return bfr.readLine();
        } catch (IOException e) {
            System.out.println("Error reading from server.");
            return null;
        }
    }
    public void closeConnection() {
        try {
            if (isConnected) {
                sendRequest("18;");
                bfr.close();
                pw.close();
                socket.close();
                isConnected = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
