import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * An interface dedicated to finding users, signing them up, logging them in and saving them to a file
 * <p>
 * Purdue University -- CS18000 -- Spring 2024
 *
 * @author Project 5 Team 3 Lab 27
 * @version March 31, 2024
 */

public interface UsersManagerInterface {
    static User findUser(String username) //finds a user given their username
    {
        return null;
    }

    static User signUp(User newUser) throws UserNameTakenException //checks if username already exists, if not, signs them up
    {
        return null;
    }

    static User logIn(String userName, String password) throws LogInFailedException //logs in a user given their username and password
    {
        return null;
    }

    static ArrayList<User> searchUsers(String myUserName, String searchString) //returns searched users (ignores blocked)
    {
        return null;
    }

    static void readUsers() throws IOException //reads the users in a file, gets their friendList, blockList, and posts (if present)
    {

    }

    static void writeUsers() throws IOException //writes all the users to a file
    {

    }
}
