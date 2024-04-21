import javax.swing.*;
import java.awt.*;

public class ProfilePane extends JPanel {
    public ProfilePane(){
        setLayout(new BorderLayout());
        add(new NavigationPane(), BorderLayout.SOUTH);
        add(new MyProfileComponent(), BorderLayout.NORTH);
    }
}
