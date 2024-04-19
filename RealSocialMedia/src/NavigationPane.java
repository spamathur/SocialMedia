import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationPane extends JPanel {

    JButton homeButton;
    JButton searchButton;
    JButton profileButton;
    JButton postButton;
    public NavigationPane(){
        setLayout(new GridLayout(1, 4));
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(450, 55));

        homeButton = createButton();
        searchButton = createButton();
        profileButton = createButton();
        postButton = createButton();

        Icon homeIcon = new ImageIcon(new ImageIcon("home.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        Icon searchIcon = new ImageIcon(new ImageIcon("searchh.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        Icon profileIcon = new ImageIcon(new ImageIcon("profile.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        Icon postIcon = new ImageIcon(new ImageIcon("plus_icon.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));

        homeButton.setIcon(homeIcon);
        homeButton.addActionListener(actionListener);
        searchButton.setIcon(searchIcon);
        searchButton.addActionListener(actionListener);
        profileButton.setIcon(profileIcon);
        profileButton.addActionListener(actionListener);
        postButton.setIcon(postIcon);
        postButton.addActionListener(actionListener);

        add(homeButton);
        add(searchButton);
        add(postButton);
        add(profileButton);
    }

    private JButton createButton() {
        JButton button = new JButton();
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        button.setBackground(Color.BLACK);
        return button;
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == homeButton) {
                MainGUIController.refresh("home", new HomePane());
            }
            if (e.getSource() == profileButton) {
                MainGUIController.refresh("profile", new ProfilePane());
            }
            if (e.getSource() == searchButton) {
                MainGUIController.refresh("search", new SearchPane());
            }
            if (e.getSource() == postButton) {
                MainGUIController.refresh("post", new PostPane());
            }
        }
    };
}
