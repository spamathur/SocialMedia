import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SignUpPane extends JPanel {
    private JTextField txtUsername;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JPasswordField txtPassword;
    private JButton btnSignUp;
    private JButton btnChooseImage;
    private JLabel lblImageSelected;
    private File profilePic;

    public SignUpPane() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);

        // Create a border for the text fields
        Border fieldBorder = BorderFactory.createLineBorder(new Color(150, 150, 150), 1);

        // Username field
        txtUsername = new JTextField(20);
        txtUsername.setBorder(fieldBorder);
        formPanel.add(new JLabel("Username:"), gbc);
        formPanel.add(txtUsername, gbc);

        // First name field
        txtFirstName = new JTextField(20);
        txtFirstName.setBorder(fieldBorder);
        formPanel.add(new JLabel("First Name:"), gbc);
        formPanel.add(txtFirstName, gbc);

        // Last name field
        txtLastName = new JTextField(20);
        txtLastName.setBorder(fieldBorder);
        formPanel.add(new JLabel("Last Name:"), gbc);
        formPanel.add(txtLastName, gbc);

        // Password field
        txtPassword = new JPasswordField(20);
        txtPassword.setBorder(fieldBorder);
        formPanel.add(new JLabel("Password:"), gbc);
        formPanel.add(txtPassword, gbc);

        // Buttons for choosing image and signing up
        lblImageSelected = new JLabel("No image selected");
        btnChooseImage = createButton("Choose Image", new Color(99, 99, 99));
        btnChooseImage.addActionListener(actionListener);
        formPanel.add(btnChooseImage, gbc);
        formPanel.add(lblImageSelected, gbc);

        btnSignUp = createButton("Sign Up", new Color(0, 123, 255));
        btnSignUp.addActionListener(actionListener);
        formPanel.add(btnSignUp, gbc);

        add(formPanel, BorderLayout.CENTER);
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
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnSignUp) {
                String username = txtUsername.getText().trim();
                String firstName = txtFirstName.getText().trim();
                String lastName = txtLastName.getText().trim();
                char[] encrypted = txtPassword.getPassword();
                String password = String.valueOf(encrypted).trim();

                String pfp;
                try {
                    pfp = profilePic.getAbsolutePath();
                } catch (Exception f) {
                    pfp = "/";
                }


                if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()) {
                    txtUsername.setText("");
                    txtFirstName.setText("");
                    txtLastName.setText("");
                    txtPassword.setText("");
                    JOptionPane.showMessageDialog(null, "Please enter all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean success = MainGUIController.client.signUp(username, firstName, lastName, password, pfp);
                    if (!success) {
                        txtUsername.setText("");
                        txtFirstName.setText("");
                        txtLastName.setText("");
                        txtPassword.setText("");
                        JOptionPane.showMessageDialog(null, "Username Taken!", "Error!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Sign Up Successful!", "Welcome!", JOptionPane.INFORMATION_MESSAGE);
                        MainGUIController.content.add(new SearchPane(), "search");
                        MainGUIController.content.add(new ProfilePane(), "profile");
                        MainGUIController.content.add(new HomePane(), "home");
                        MainGUIController.content.add(new PostPane(), "post");
                        MainGUIController.cardLayout.show(MainGUIController.content, "home");
                    }
                }
            }
            if (e.getSource() == btnChooseImage){
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    profilePic = chooser.getSelectedFile();
                    lblImageSelected.setText(chooser.getSelectedFile().getName());
                }
            }
        }
    };
}