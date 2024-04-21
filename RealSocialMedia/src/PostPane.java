import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class PostPane extends JPanel {
    private JTextField txtCaption;
    private JButton btnCreatePost;
    private JButton btnChooseImage;
    private JLabel lblImageSelected;
    private File postPic;
    public PostPane() {
        setLayout(new BorderLayout(10,10));

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

        // Last name field
        txtCaption = new JTextField(20);
        txtCaption.setBorder(fieldBorder);
        formPanel.add(new JLabel("What's on your mind?:"), gbc);
        formPanel.add(txtCaption, gbc);

        // Buttons for choosing image and signing up
        lblImageSelected = new JLabel("No image selected");
        btnChooseImage = createButton("Choose Image", new Color(99, 99, 99));
        btnChooseImage.addActionListener(actionListener);
        formPanel.add(btnChooseImage, gbc);
        formPanel.add(lblImageSelected, gbc);

        btnCreatePost = createButton("Create Post", new Color(0, 123, 255));
        btnCreatePost.addActionListener(actionListener);
        formPanel.add(btnCreatePost, gbc);

        add(formPanel, BorderLayout.CENTER);

        add(new NavigationPane(), BorderLayout.SOUTH);
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnCreatePost){
                String caption = txtCaption.getText().trim();
                String image;
                try {
                    image = postPic.getAbsolutePath();
                } catch (Exception f) {
                    JOptionPane.showMessageDialog(null, "Please select an image!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (caption.isEmpty()) {
                    txtCaption.setText("");
                    JOptionPane.showMessageDialog(null, "Please enter all fields!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    MainGUIController.client.createPost(caption, image);
                    txtCaption.setText("");
                    JOptionPane.showMessageDialog(null, "Post Created!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            if (e.getSource() == btnChooseImage){
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();
                    if (isValidImageFile(selectedFile)) {
                        postPic = selectedFile;
                        lblImageSelected.setText(selectedFile.getName());
                    } else {
                        JOptionPane.showMessageDialog(null, "Selected file is not a valid image format!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    };

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        return button;
    }

    private boolean isValidImageFile(File file) {
        String[] validExtensions = {".jpg", ".jpeg", ".png", ".gif", ".bmp"};
        String fileName = file.getName().toLowerCase();
        for (String extension : validExtensions) {
            if (fileName.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
}
