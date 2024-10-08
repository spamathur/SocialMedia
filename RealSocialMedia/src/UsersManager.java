import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class UsersManager implements UsersManagerInterface {
    private static final String FILENAME = "users.txt";
    private static List<User> usersList = Collections.synchronizedList(new ArrayList<>());

    public static synchronized User findUser(String username) {
        for (User user : usersList) {
            if (user.getUserName().equals(username))
                return user;
        }
        return new User("", "", "", "", "");
    }

    public static synchronized User signUp(User newUser) throws UserNameTakenException {
        for (User user : usersList) {
            if (user.getUserName().equals(newUser.getUserName())) {
                throw new UserNameTakenException();
            }
        }
        usersList.add(newUser);
        return newUser;
    }

    public static synchronized User logIn(String userName, String password) throws LogInFailedException {
        for (User user : usersList) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new LogInFailedException();
    }

    public static synchronized ArrayList<User> searchUsers(String myUserName, String searchString) {
        ArrayList<User> matchedUsers = new ArrayList<>();
        for (User user : usersList) {
            if (user.getFirstName().toLowerCase().contains(searchString.toLowerCase()) || user.getLastName().toLowerCase().contains(searchString.toLowerCase())
                    || user.getUserName().toLowerCase().contains(searchString.toLowerCase())) {
                if (!user.getBlockedList().contains(myUserName) && !user.getUserName().equals(myUserName))
                    matchedUsers.add(user);
            }
        }
        return matchedUsers;
    }

    public static void readUsers() throws IOException {
        try (BufferedReader bfr = new BufferedReader(new FileReader(FILENAME))) {
            String line = bfr.readLine();
            while (line != null) {
                String[] userContents = line.split(";", -1);
                User user = new User(userContents[0], userContents[1], userContents[2], userContents[3], userContents[4]);
                if (!userContents[5].isEmpty()){
                    user.setFriendsList(new ArrayList<>(Arrays.asList(userContents[5].split(","))));
                }
                if (!userContents[6].isEmpty()){
                    user.setBlockedList(new ArrayList<>(Arrays.asList(userContents[6].split(","))));
                }
                if (!userContents[7].isEmpty()) {
                    for (String postID : userContents[7].split(",")) {
                        user.addMyPosts(PostsManager.findPost(postID));
                    }
                }
                if (!userContents[8].isEmpty()) {
                    for (String postID : userContents[8].split(",")) {
                        user.hidePost(postID);
                    }
                }
                if (!userContents[9].isEmpty()){
                    user.setFollowersList(new ArrayList<>(Arrays.asList(userContents[9].split(","))));
                }
                usersList.add(user);
                line = bfr.readLine();
            }
        }
    }

    public static List<User> getUsersList() {
        return usersList;
    }

    public static void writeUsers() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILENAME))) {
            for (User user : usersList) {
                pw.println(user.toString());
            }
        }
    }

    public static void clearUsers() {
        usersList = new ArrayList<>();
    }
}
