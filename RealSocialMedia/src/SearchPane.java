import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPane extends JPanel {
    JButton searchButton;
    JTextField searchField;
    JPanel middlePanel;
    JPanel usersPanel;
    public SearchPane(){
        setLayout(new BorderLayout());
        JPanel searchBar = new JPanel(new BorderLayout());
        searchField = new JTextField(27);
        Border fieldBorder = BorderFactory.createLineBorder(new Color(150, 150, 150), 1);
        searchField.setBorder(fieldBorder);
        searchButton = createButton("Search", new Color(0, 123, 255));
        searchButton.addActionListener(actionListener);
        searchBar.add(searchField, BorderLayout.WEST);
        searchBar.add(searchButton, BorderLayout.EAST);
        searchBar.setBorder(BorderFactory.createEmptyBorder(35, 20, 0, 20));

        middlePanel = new JPanel(new BorderLayout());
        middlePanel.setBorder(BorderFactory.createEmptyBorder(25, 20, 0, 20));
        add(searchBar, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(new NavigationPane(), BorderLayout.SOUTH);
    }

    public SearchPane(User[] users){
        setLayout(new BorderLayout());
        JPanel searchBar = new JPanel(new BorderLayout());
        searchField = new JTextField(27);
        Border fieldBorder = BorderFactory.createLineBorder(new Color(150, 150, 150), 1);
        searchField.setBorder(fieldBorder);
        searchButton = createButton("Search", new Color(0, 123, 255));
        searchButton.addActionListener(actionListener);
        searchBar.add(searchField, BorderLayout.WEST);
        searchBar.add(searchButton, BorderLayout.EAST);
        searchBar.setBorder(BorderFactory.createEmptyBorder(35, 20, 0, 20));

        middlePanel = new JPanel(new BorderLayout());
        middlePanel.setBorder(BorderFactory.createEmptyBorder(25, 20, 0, 20));
        addUsersPanel(users);
        add(searchBar, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(new NavigationPane(), BorderLayout.SOUTH);
    }
    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        return button;
    }

    public void addUsersPanel(User[] users){
        usersPanel = new JPanel();
        usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.Y_AXIS));
        for (User user : users){
            usersPanel.add(new UserComponent(user));
            System.out.println(user.getUserName());
        }

        JScrollPane scrollPane = new JScrollPane(usersPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        middlePanel.add(scrollPane, BorderLayout.NORTH);
        revalidate();
        repaint();
    }


    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == searchButton){
                String searchString = searchField.getText();
                User[] users = MainGUIController.client.searchUsers(searchString);
                MainGUIController.refresh("search", new SearchPane(users));
            }
        }
    };
}
