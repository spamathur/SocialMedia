import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class EditProfilePane extends JPanel {
    private JTextField txtUsername;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JPasswordField txtPassword;
    private JButton btnUpdate;
    private JButton btnChangeImage;
    private JLabel lblImageSelected;
    private File profilePic;

    public User user = MainGUIController.client.getMyProfile();

    public EditProfilePane() {
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
        txtUsername = new JTextField(user.getUserName(), 20);
        txtUsername.setBorder(fieldBorder);
        formPanel.add(new JLabel("Username:"), gbc);
        formPanel.add(txtUsername, gbc);

        // First name field
        txtFirstName = new JTextField(user.getFirstName(), 20);
        txtFirstName.setBorder(fieldBorder);
        formPanel.add(new JLabel("First Name:"), gbc);
        formPanel.add(txtFirstName, gbc);

        // Last name field
        txtLastName = new JTextField(user.getLastName(), 20);
        txtLastName.setBorder(fieldBorder);
        formPanel.add(new JLabel("Last Name:"), gbc);
        formPanel.add(txtLastName, gbc);

        // Password field
        txtPassword = new JPasswordField(user.getPassword(), 20);
        txtPassword.setBorder(fieldBorder);
        formPanel.add(new JLabel("Password:"), gbc);
        formPanel.add(txtPassword, gbc);

        // Buttons for changing image and updating profile
        btnChangeImage = createButton("Change Image", new Color(99, 99, 99));
        btnChangeImage.addActionListener(this::chooseImage);
        formPanel.add(btnChangeImage, gbc);


        btnUpdate = createButton("Update Profile", new Color(0, 123, 255));
        btnUpdate.addActionListener(this::updateProfile);
        formPanel.add(btnUpdate, gbc);

        updateProfilePicLabel();
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.NONE;

        formPanel.add(lblImageSelected, gbc);
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
    private void updateProfilePicLabel() {
        lblImageSelected = new JLabel("No image selected");
        if (user.getProfilePic() != null && !user.getProfilePic().isEmpty()) {
            lblImageSelected.setIcon(new ImageIcon(user.getProfilePic()));
        }
    }

    private void chooseImage(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            profilePic = chooser.getSelectedFile();
            user.setProfilePic(profilePic.getAbsolutePath());
            lblImageSelected.setIcon(new ImageIcon(profilePic.getAbsolutePath()));
        }
    }

    private void updateProfile(ActionEvent e) {
        String username = txtUsername.getText().trim();
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        user.setUserName(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);

       // More code
    }
}