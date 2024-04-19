import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyPostsPane extends JPanel {
    public MyPostsPane(){
        User user = MainGUIController.client.getMyProfile();
        ArrayList<Post> posts = user.getMyPosts();
        setLayout(new BorderLayout());
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));

        for (Post post : posts) {
            postPanel.add(new PostComponent(post));
        }

        postPanel.add(new BlankPost("caughtup.jpeg"));

        JScrollPane scrollPane = new JScrollPane(postPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);
        add(new NavigationPane(), BorderLayout.SOUTH);
    }

}
