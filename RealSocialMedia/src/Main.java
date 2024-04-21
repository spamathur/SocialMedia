import java.io.IOException;

/* YOU CAN TEST THIS DATABASE BY RUNNING METHODS SIGNING UP/LOGGING IN USERS AND USING THE USER
OBJECTS TO TEST ANY METHOD. BELOW I HAVE SHOWN HOW TO TEST addFriend and getMyFriendsPosts methods.
*/

public class Main {
    public static void main(String[] args) throws IOException, UserNameTakenException, LogInFailedException {
        Client abhi = new Client();
        Client vivi = new Client();
        vivi.logIn("vivi", "pass");
        vivi.addFriend("abhi");

    }
}
