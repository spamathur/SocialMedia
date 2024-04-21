import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class UserComponent extends JPanel {

    JLabel lblProfile;
    JButton arrowButton;

    Image arrowImage;

    public User user;
    public UserComponent(User user){
        this.user = user;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(230, 230, 230));

        try {
            arrowImage = ImageIO.read(new File("arrow.png")).getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        String userString = "<html><b>" + user.getUserName() + "</b>" + ": " + user.getFirstName() + " " + user.getLastName() + "</html>";

        lblProfile = new JLabel(userString);
        lblProfile.setFont(new Font("Arial", Font.PLAIN, 14));
        lblProfile.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        lblProfile.setBackground(new Color(230, 230, 230));

        arrowButton = new JButton();
        arrowButton.addActionListener(actionListener);
        arrowButton.setBorderPainted(false);
        arrowButton.setContentAreaFilled(false);
        arrowButton.setFocusPainted(false);
        arrowButton.setOpaque(false);
        arrowButton.setIcon(new ImageIcon(arrowImage));
        arrowButton.setBorder(new EmptyBorder(0, 0, 0, 7));

        this.add(lblProfile, BorderLayout.WEST);
        this.add(arrowButton, BorderLayout.EAST);
    }
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == arrowButton){
                MainGUIController.refresh("userprofile", new UserProfilePane(user));
            }
        }
    };
}
