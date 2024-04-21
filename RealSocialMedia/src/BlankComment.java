import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class BlankComment extends JPanel {
    private JButton deleteButton;
    private JLabel lblProfileComment;
    private Image deleteImage;
    public BlankComment(){

        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        this.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        deleteButton = new JButton();
        deleteButton.setBorderPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setOpaque(false);
        deleteButton.setBorder(new EmptyBorder(0, 0, 0, 13));

        lblProfileComment = new JLabel("");
        lblProfileComment.setFont(new Font("Arial", Font.BOLD, 14));
        lblProfileComment.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        lblProfileComment.setBackground(new Color(230, 230, 230));

        this.add(lblProfileComment, BorderLayout.WEST);
        this.add(deleteButton, BorderLayout.EAST);
    }

}
