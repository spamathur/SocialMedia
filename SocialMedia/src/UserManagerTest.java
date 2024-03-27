import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {
    private User user;
    private final String testFilePath = "testUsers.txt";

    @BeforeEach
    void setUp() {
        user = new User("testUser", "password123", "John", "Doe");
        // Clean up the test file before each test
        new File(testFilePath).delete();
    }

    @Test
    void testUserGettersAndSetters() {
        assertEquals("testUser", user.getUserName());
        user.setUserName("newUser");
        assertEquals("newUser", user.getUserName());

        assertEquals("password123", user.getPassword());
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());

        assertEquals("John", user.getFirstName());
        user.setFirstName("Jane");
        assertEquals("Jane", user.getFirstName());

        assertEquals("Doe", user.getLastName());
        user.setLastName("Smith");
        assertEquals("Smith", user.getLastName());
    }

    @Test
    void testUserEquals() {
        User otherUser = new User("testUser", "differentPassword", "John", "Doe");
        assertTrue(user.equals(otherUser));
    }

    @Test
    void testUserManagerSignup() throws UsernameTakenException {
        User signedUpUser = UserManager.signup(user, testFilePath);
        assertEquals(user.getUserName(), signedUpUser.getUserName());
    }

    @Test
    void testUserManagerLogin() throws UsernameTakenException, LoginFailedException {
        UserManager.signup(user, testFilePath);
        User loggedInUser = UserManager.login("testUser", "password123", testFilePath);
        assertEquals("testUser", loggedInUser.getUserName());
    }

    @Test
    void testUserManagerSearchUsers() throws UsernameTakenException, LoginFailedException, UserNotFoundException {
        UserManager.signup(user, testFilePath);
        User[] foundUsers = UserManager.searchUsers("testUser", testFilePath);
        assertEquals(1, foundUsers.length);
        assertEquals("testUser", foundUsers[0].getUserName());
    }
}