import javax.swing.*;
import java.awt.*;

public class SearchPane extends JPanel {
    public SearchPane(){
        setLayout(new BorderLayout());
        add(new NavigationPane(), BorderLayout.SOUTH);
    }
}
