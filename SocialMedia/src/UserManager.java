import java.io.*;
import java.util.ArrayList;

/* ALL OF THE METHODS ARE STATIC SO YOU CAN IMPLEMENT THEM HERE ITSELF. I DID SIGNUP METHOD FOR YOU GUYS. */
public interface UserManager {


    public static User signup(User user) throws UsernameTakenException {

        try (BufferedReader bfr = new BufferedReader(new FileReader("Users.txt"));
             PrintWriter pw = new PrintWriter(new FileWriter("Users.txt", true))) {

            ArrayList<String> contents = new ArrayList<>();
            String line = bfr.readLine();
            while (line != null) {
                contents.add(line);
                line = bfr.readLine();
            }

            for (String userInfo : contents) {
                String username = userInfo.split(",")[0];
                if (username.equals(user.getUserName()))
                    throw new UsernameTakenException(String.format("%s is taken", user.getUserName()));
            }

            pw.println(String.format("%s,%s,%s,%s", user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }



    /* This method should check if the username and password match our database Users.txt file. If it doesn't match throw login
    failed exception. If it works return the User and construct this user with firstname lastname and username not password. (for security)
     */
    public static User login(String userName, String password) throws LoginFailedException {
        try (BufferedReader br = new BufferedReader(new FileReader("Users.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                String fUsername = parts[0];
                String fPassword = parts[1];
                String fFirst = parts[2];
                String fLast = parts[3];

                if (userName.equals(fUsername) && password.equals(fPassword)) {
                    return new User(fUsername, fFirst, fLast);
                }

            } // End while
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new LoginFailedException("Incorrect Username and password!"); // Throws if it does not return on line 52
    } // End method





    /* this method should check the username, firstname, and lastname field to check for any matches and
    then return the User[]. Think about how you want to implement this method. Also, when constructing
    the Users to put in the list use the constructor that does not contain the password field for security.
     */
    public static User[] searchUsers(String searchString) throws LoginFailedException, UserNotFoundException {
        ArrayList<User> users = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Users.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                String fUsername = parts[0];
                String fFirst = parts[2];
                String fLast = parts[3];

                if (fUsername.contains(searchString) || fFirst.contains(searchString) || fLast.contains(searchString)) {
                   users.add(new User(fUsername, fFirst, fLast));  // adds that user to the ArrayList (Line 71)
                }
            } // End while
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // If no ones found
        if (users.size() == 0) {
            throw new UserNotFoundException("No results found"); // We can make a new exception for this if you want
        }
        return users.toArray(new User[0]); // Converts the arraylist to an array and returns the first name in the list
    }
}

