import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilePane extends JPanel {
    public ProfilePane(){
        setLayout(new BorderLayout());
        add(new NavigationPane(), BorderLayout.SOUTH);
        add(new ProfileComponent(), BorderLayout.NORTH);
    }
}
