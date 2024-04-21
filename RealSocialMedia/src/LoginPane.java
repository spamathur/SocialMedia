import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class LoginPane extends JPanel {
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private ImageIcon imageIcon;
    private JButton btnSignUp;
    private SideBySideButtons buttons;
    public LoginPane() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Buzz Board!", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Helvetica", Font.BOLD, 36));

        imageIcon = new ImageIcon("bee_dashboard.jpg");
        Image image = imageIcon.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon);

        titlePanel.add(lblTitle);
        titlePanel.add(imageLabel);
        add(titlePanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridwidth = GridBagConstraints.REMAINDER; // Each component in a new row
        gbc.fill = GridBagConstraints.HORIZONTAL;     // Fill horizontally
        gbc.insets = new Insets(10, 50, 10, 50);      // Uniform padding around components

        Border fieldBorder = BorderFactory.createLineBorder(new Color(150, 150, 150), 1);

        JLabel lblUsername = new JLabel("Username:");
        txtUsername = new JTextField(20);
        txtUsername.setBorder(fieldBorder);
        formPanel.add(lblUsername, gbc);
        formPanel.add(txtUsername, gbc);


        JLabel lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField(20);
        txtPassword.setBorder(fieldBorder);
        formPanel.add(lblPassword, gbc);
        formPanel.add(txtPassword, gbc);


        buttons = new SideBySideButtons("Login", "Sign Up",
                new Color(0, 123, 255), new Color(99, 99, 99), actionListener);
        gbc.weightx = 1;
        formPanel.add(buttons, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("Login")) {
                String username = txtUsername.getText();
                boolean isEmpty = false;
                if(username == null){
                    txtUsername.setText("");
                    txtPassword.setText("");
                    JOptionPane.showMessageDialog(null, "Enter all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    isEmpty = true;
                }
                char[] encrypted = txtPassword.getPassword();
                String password = String.valueOf(encrypted);
                if(!isEmpty){
                    boolean success = MainGUIController.client.logIn(username, password);
                    if(!success){
                        txtUsername.setText("");
                        txtPassword.setText("");
                        JOptionPane.showMessageDialog(null, "Username or Password is Incorrect!", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        //JOptionPane.showMessageDialog(null, "Log in Successful!", "Welcome!", JOptionPane.INFORMATION_MESSAGE);
                        MainGUIController.content.add(new SearchPane(), "search");
                        MainGUIController.content.add(new ProfilePane(), "profile");
                        MainGUIController.content.add(new HomePane(), "home");
                        MainGUIController.content.add(new PostPane(), "post");
                        MainGUIController.cardLayout.show(MainGUIController.content, "home");
                    }
                }
            }
            if (command.equals("Sign Up")) {
                MainGUIController.cardLayout.show(MainGUIController.content, "signup");
            }
        }
    };

}