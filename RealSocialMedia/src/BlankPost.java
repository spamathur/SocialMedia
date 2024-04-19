import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BlankPost extends JPanel {

    private JLabel lblImage;
    private JTextArea lblProfile;
    public BlankPost(String imagePath){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setAlignmentX(Component.CENTER_ALIGNMENT);

        Border padding = BorderFactory.createEmptyBorder(17, 17, 17, 17);
        Border lineBorder = BorderFactory.createLineBorder(Color.RED, 0);
        setBorder(BorderFactory.createCompoundBorder(lineBorder, padding));

        lblImage = new JLabel();
        lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        try {
            BufferedImage postImage = ImageIO.read(new File(imagePath));
            ImageIcon imageIcon = new ImageIcon(scaleImage(postImage, 400, 500)); // Adjust size for padding
            lblImage.setIcon(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel captionLike = new JPanel(new BorderLayout());
        captionLike.setBackground(Color.white);

        lblProfile = new JTextArea();
        lblProfile.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        lblProfile.setBackground(Color.white);
        lblProfile.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(lblProfile);
        add(lblImage);
        add(captionLike);
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
}
