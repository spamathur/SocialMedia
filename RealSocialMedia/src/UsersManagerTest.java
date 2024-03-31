import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UsersManagerTest {

    @Test
    public void testSignUp() throws UserNameTakenException {
        User newUser = new User("john", "John", "Doe", "password", "profile.jpg");
        User signedUpUser = UsersManager.signUp(newUser);
        assertNotNull(signedUpUser);
        assertEquals("john", signedUpUser.getUserName());
    }

    @Test(expected = UserNameTakenException.class)
    public void testSignUpWithExistingUsername() throws UserNameTakenException {
        User user1 = new User("john", "John", "Doe", "password", "profile.jpg");
        UsersManager.signUp(user1);
        User user2 = new User("john", "Johnny", "Doe", "password123", "profile2.jpg");
        UsersManager.signUp(user2); // This should throw UserNameTakenException
    }

    @Test
    public void testLogIn() throws UserNameTakenException, LogInFailedException {
        User newUser = new User("jane", "Jane", "Doe", "password", "profile.jpg");
        UsersManager.signUp(newUser);
        User loggedInUser = UsersManager.logIn("jane", "password");
        assertNotNull(loggedInUser);
        assertEquals("jane", loggedInUser.getUserName());
    }

    @Test(expected = LogInFailedException.class)
    public void testLogInWithIncorrectPassword() throws UserNameTakenException, LogInFailedException {
        User newUser = new User("jane", "Jane", "Doe", "password", "profile.jpg");
        UsersManager.signUp(newUser);
        UsersManager.logIn("jane", "wrongpassword"); // This should throw LogInFailedException
    }

    @Test
    public void testSearchUsers() throws UserNameTakenException {
        User user1 = new User("john", "John", "Doe", "password", "profile.jpg");
        User user2 = new User("jane", "Jane", "Doe", "password", "profile.jpg");
        UsersManager.signUp(user1);
        UsersManager.signUp(user2);
        assertEquals(1, UsersManager.searchUsers("john", "Jane").size());
        assertEquals("jane", UsersManager.searchUsers("john", "Jane").get(0).getUserName());
    }

    // Additional tests can be added for other methods.
}