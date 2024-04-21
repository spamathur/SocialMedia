import javax.swing.*;
import java.awt.*;

public class UserProfilePane extends JPanel {
    public UserProfilePane(User user){
        setLayout(new BorderLayout());
        add(new NavigationPane(), BorderLayout.SOUTH);
        boolean isFriended = false;
        boolean isBlocked = false;
        User myProfile = MainGUIController.client.getMyProfile();
        for(String username : myProfile.getFriendsList()){
            if(username.equals(user.getUserName())) {
                isFriended = true;
                break;
            }
        }
        for(String username : myProfile.getBlockedList()){
            if(username.equals(user.getUserName())) {
                isBlocked = true;
                break;
            }
        }
        User updatedUser = MainGUIController.client.findUser(user.getUserName());
        add(new ProfileComponent(updatedUser, isFriended, isBlocked), BorderLayout.NORTH);
    }
}
