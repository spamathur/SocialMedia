import javax.swing.*;
import java.awt.*;

public class FullPostPane extends JPanel {
    public FullPostPane(Post post){
        setLayout(new BorderLayout());
        this.setBackground(Color.white);
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));

        postPanel.add(new PostCommentComponent(post));

        add(postPanel, BorderLayout.NORTH);
        add(new NavigationPane(), BorderLayout.SOUTH);
    }
}
