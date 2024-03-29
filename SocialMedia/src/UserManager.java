import java.io.*;
import java.util.ArrayList;

/* ALL OF THE METHODS ARE STATIC SO YOU CAN IMPLEMENT THEM HERE ITSELF. I DID SIGNUP METHOD FOR YOU GUYS. */
public interface UserManager {
    public static ArrayList<User> readUsers(String filename) {
        ArrayList<User> usersArray = new ArrayList<>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(filename));
            String line = bfr.readLine();
            while (line != null) {
                line = bfr.readLine();
                String[] parts = line.split(",");
                usersArray.add(new User(parts[0], parts[1], parts[2], parts[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersArray;
    }
    /*
    I changed the parameters for the signup method. Former parameter is User. I think for our userManager file
    we need to input the username, password, and the names directly, rather than input a User.\
     */
    public static User signup(String username, String password, String firstName, String lastName, String filename) throws UsernameTakenException {
        ArrayList<User> users = readUsers(filename);
        User user = new User(username, password, firstName, lastName);
        for (User item : users) {
            if (user.equals(item)) {
                throw new UsernameTakenException(String.format("%s is taken", user.getUserName()));
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(filename, true);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(user.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }



    /* This method should check if the username and password match our database Users.txt file.
    If it doesn't match throw login failed exception.
    If it works return the User and construct this user with
    firstname lastname and username not password. (for security)
     */
    public static User login(String userName, String password, String filename) throws LoginFailedException {
        ArrayList<User> users = readUsers(filename);
        for (User item : users) {
            if (item.getUserName().equals(userName) && item.getPassword().equals(password)) {
                return item;
            }
        }
        throw new LoginFailedException("Username or password is incorrect!");
    } // End method



    /* this method should check the username, firstname, and lastname field to check for any matches and
    then return the User[]. Think about how you want to implement this method. Also, when constructing
    the Users to put in the list use the constructor that does not contain the password field for security.
     */
    public static User[] searchUsers(String searchString, String filename) throws LoginFailedException,
            UserNotFoundException {
        ArrayList<User> users = readUsers(filename);
        ArrayList<User> searchUsers = new ArrayList<>();
        for (User item : users) {
            if (item.getUserName().contains(searchString) || item.getFirstName().contains(searchString) ||
                    item.getLastName().contains(searchString)) {
                searchUsers.add(new User(item.getUserName(), item.getFirstName(), item.getLastName()));
            }
        }

        // If no ones found
        if (searchUsers.size() == 0) {
            throw new UserNotFoundException("No results found");
        }
        return searchUsers.toArray(new User[0]); // Converts the arraylist to an array and returns the first name
    }
}

