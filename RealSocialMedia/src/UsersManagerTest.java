import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

/**
 * A framework to run public test cases for UsersManager.java for PJ5.
 *
 * @author Project 5 Team 3 Lab 27
 *
 * @version March 31, 2024
 */

public class UsersManagerTest {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(UsersManagerTest.TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    public static class TestCase {
        // Test to check the signup functionality
        @Test(timeout = 1000)
        public void testSignUp() throws UserNameTakenException {
            User newUser = new User("john", "John", "Doe", "password", "profile.jpg");
            User signedUpUser = UsersManager.signUp(newUser);
            Assert.assertNotNull(signedUpUser);
            Assert.assertEquals("john", signedUpUser.getUserName());
        }

        // Test to check the duplicate username functionality
        @Test(expected = UserNameTakenException.class)
        public void testSignUpWithExistingUsername() throws UserNameTakenException {
            User user1 = new User("temp", "Temporary", "Doe", "password", "profile.jpg");
            UsersManager.signUp(user1);
            UsersManager.signUp(new User("temp", "Temp2", "Doe", "password", "profile.jpg"));
        }

        // Test to check the login functionality
        @Test(timeout = 1000)
        public void testLogIn() throws UserNameTakenException, LogInFailedException {
            User newUser = new User("jane", "Jane", "Doe", "key", "profile.jpg");
            UsersManager.signUp(newUser);
            User loggedInUser = UsersManager.logIn("jane", "key");
            Assert.assertNotNull(loggedInUser);
            Assert.assertEquals("jane", loggedInUser.getUserName());
        }

        // Test to check the login fail functionality
        @Test(expected = LogInFailedException.class)
        public void testLogInWithIncorrectPassword() throws UserNameTakenException, LogInFailedException {
            User newUser = new User("james", "James", "Doe", "password", "profile.jpg");
            UsersManager.signUp(newUser);
            UsersManager.logIn("james", "wrongpassword"); // This should throw LogInFailedException
        }

        // Test to check the search functionality
        @Test(timeout = 1000)
        public void testSearchUsers() throws UserNameTakenException {
            User user1 = new User("juliet", "Juliet", "Doe", "password", "profile.jpg");
            User user2 = new User("jordan", "Jordan", "Doe", "password", "profile.jpg");
            UsersManager.signUp(user1);
            UsersManager.signUp(user2);
            Assert.assertEquals(1, UsersManager.searchUsers("juliet", "Jordan").size());
            Assert.assertEquals("jordan", UsersManager.searchUsers("juliet", "Jordan").get(0).getUserName());
        }

        // Test to ensure that the class is declared properly
        @Test(timeout = 1000)
        public void UsersManagerClassDeclarationTest() {
            Class<?> clazz;
            int modifiers;
            Class<?> superclass;
            Class<?>[] superinterfaces;

            clazz = UsersManager.class;

            modifiers = clazz.getModifiers();

            superclass = clazz.getSuperclass();

            superinterfaces = clazz.getInterfaces();

            Assert.assertTrue("Ensure that `Comment` is `public`!",
                    Modifier.isPublic(modifiers));
            Assert.assertFalse("Ensure that `Comment` is NOT `abstract`!",
                    Modifier.isAbstract(modifiers));
            Assert.assertEquals("Ensure that `Comment` extends `Object`!",
                    Object.class, superclass);
            Assert.assertEquals("Ensure that `Comment` implements one interfaces!",
                    1, superinterfaces.length);
        }
    }

}
