import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException, UserNameTakenException, LogInFailedException {
        FileManager.readAll();
        //User abhi = UsersManager.signUp(new User("aabhii13", "abhi", "pad", "pass", "profile.png"));
        //User vihaa = UsersManager.signUp(new User("vivi", "vihaa", "pad", "pass", "profile.png"));
        //User sasi = UsersManager.signUp(new User("xsasi", "sasi", "pad", "pass", "profile.png"));
        //User vihaa = UsersManager.logIn("vivi", "pass");
        //User abhi = UsersManager.logIn("aabhii13", "pass");


        FileManager.writeAll();
    }
}
