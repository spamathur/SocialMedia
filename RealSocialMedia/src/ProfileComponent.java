import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProfileComponent extends JPanel {
    public User user;
    public SideBySideButtons buttons;
    public boolean isFriended;
    public boolean isBlocked;
    public String button1Text;
    public String button2Text;
    public ProfileComponent(User user, boolean isFriended, boolean isBlocked) {
        this.isFriended = isFriended;
        this.isBlocked = isBlocked;
        this.user = user;
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

        if (isFriended)
            button1Text = "Remove Friend";
        else
            button1Text = "Add Friend";

        if (isBlocked)
            button2Text = "Unblock Friend";
        else
            button2Text = "Block Friend";

        buttons = new SideBySideButtons(button1Text, button2Text,
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
            if (command.equals("Add Friend")) {
                if (button2Text.equals("Unblock Friend")){
                    JOptionPane.showMessageDialog(null, "Cannot add a blocked user!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    MainGUIController.client.addFriend(user.getUserName());
                    MainGUIController.refresh("userprofile", new UserProfilePane(user));
                }
            }
            if (command.equals("Block Friend")) {
                MainGUIController.client.blockFriend(user.getUserName());
                MainGUIController.refresh("userprofile", new UserProfilePane(user));
            }
            if (command.equals("Remove Friend")) {
                MainGUIController.client.removeFriend(user.getUserName());
                MainGUIController.refresh("userprofile", new UserProfilePane(user));
            }
            if (command.equals("Unblock Friend")) {
                MainGUIController.client.unblockFriend(user.getUserName());
                MainGUIController.refresh("userprofile", new UserProfilePane(user));
            }
        }
    };

}
