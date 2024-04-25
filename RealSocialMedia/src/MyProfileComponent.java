import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyProfileComponent extends JPanel {

    public static User user;
    public MyProfileComponent() {
        User user = MainGUIController.client.getMyProfile();
        MyProfileComponent.user = user;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        JLabel usernameLabel = new JLabel(user.getUserName());
        usernameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        JLabel profilePictureLabel = new JLabel();
        profilePictureLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        try {
            String pfpPath;
            if (user.getProfilePic().equals("/")) {
                pfpPath = "profile.png";
            } else {
                pfpPath = user.getProfilePic();
            }
            BufferedImage profileImage = ImageIO.read(new File(pfpPath));
            ImageIcon profileIcon = new ImageIcon(scaleImage(profileImage, 80, 80));
            profilePictureLabel.setIcon(profileIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(2, 3, 10, 0));
        statsPanel.setOpaque(false);

        JLabel postsLabel = new JLabel(String.valueOf(user.getMyPosts().size()), SwingConstants.CENTER);
        JLabel followersLabel = new JLabel(String.valueOf(user.getFollowersList().size()), SwingConstants.CENTER);
        JLabel followingLabel = new JLabel(String.valueOf(user.getFriendsList().size()), SwingConstants.CENTER);
        JLabel postsDescLabel = new JLabel("posts", SwingConstants.CENTER);
        JLabel followersDescLabel = new JLabel("followers", SwingConstants.CENTER);
        JLabel followingDescLabel = new JLabel("following", SwingConstants.CENTER);

        statsPanel.add(postsLabel);
        statsPanel.add(followersLabel);
        statsPanel.add(followingLabel);
        statsPanel.add(postsDescLabel);
        statsPanel.add(followersDescLabel);
        statsPanel.add(followingDescLabel);

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        namePanel.setOpaque(false);
        JLabel nameLabel = new JLabel(user.getFirstName() + " " + user.getLastName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Helvetica", Font.ITALIC, 13));
        namePanel.add(nameLabel);

        SideBySideButtons buttons = new SideBySideButtons("My Posts", "Edit Profile",
                new Color(0, 123, 255), new Color(99, 99, 99), actionListener);

        add(usernameLabel);
        add(Box.createVerticalStrut(20)); // Space above the profile picture
        add(profilePictureLabel);
        add(Box.createVerticalStrut(20)); // Space between profile picture and stats
        add(statsPanel);
        add(Box.createVerticalStrut(25)); // Space between stats and name
        add(namePanel);
        add(Box.createVerticalStrut(25));
        add(buttons);
    }

    private Image scaleImage(BufferedImage originalImage, int maxWidth, int maxHeight) {
        double aspectRatio = (double) originalImage.getWidth() / originalImage.getHeight();
        int width = maxWidth;
        int height = (int) (maxWidth / aspectRatio);

        if (height > maxHeight) {
            height = maxHeight;
            width = (int) (maxHeight * aspectRatio);
        }
        return originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("My Posts")) {
                MainGUIController.content.add(new MyPostsPane(), "myPosts");
                MainGUIController.cardLayout.show(MainGUIController.content,"myPosts");
            }
            if (command.equals("button2")) {

            }

        }
    };
}
