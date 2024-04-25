import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class PostComponent extends JPanel {
    private JLabel lblImage;
    private JLabel lblCaption;
    private JLabel lblProfile;
    private JButton upvoteButton;
    private JButton downvoteButton;
    private JLabel numVotes;
    private Post post;
    private int originalVotes;
    private Image upvoteImage;
    private Image downvoteImage;
    private Image upvotedImage;
    private Image downvotedImage;
    private Image hideImage;
    private JButton hideButton;
    public PostComponent(Post post) {
        this.addMouseListener(mouseAdapter);
        this.post = post;
        this.originalVotes = post.getVotes();

        try {
            upvoteImage = ImageIO.read(new File("upvote.png")).getScaledInstance(15,15, Image.SCALE_SMOOTH);
            downvoteImage = ImageIO.read(new File("downvote.png")).getScaledInstance(15,15, Image.SCALE_SMOOTH);
            upvotedImage = ImageIO.read(new File("upvoted.png")).getScaledInstance(15,15, Image.SCALE_SMOOTH);
            downvotedImage = ImageIO.read(new File("downvoted.png")).getScaledInstance(15,15, Image.SCALE_SMOOTH);
            hideImage = ImageIO.read(new File("hide.png")).getScaledInstance(15,15, Image.SCALE_SMOOTH);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setAlignmentX(Component.CENTER_ALIGNMENT);

        Border padding = BorderFactory.createEmptyBorder(17, 17, 17, 17);
        Border lineBorder = BorderFactory.createLineBorder(Color.RED, 0);
        setBorder(BorderFactory.createCompoundBorder(lineBorder, padding));

        lblImage = new JLabel();
        lblImage.addMouseListener(mouseAdapter);
        lblImage.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
        lblImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        try {
            BufferedImage postImage = ImageIO.read(new File(post.getImg()));
            ImageIcon imageIcon = new ImageIcon(scaleImage(postImage, 400, 500)); // Adjust size for padding
            lblImage.setIcon(imageIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel captionLike = new JPanel(new BorderLayout());
        captionLike.setBackground(new Color(230, 230, 230));

        JPanel votes = new JPanel(new FlowLayout());
        votes.setBackground(new Color(230, 230, 230));
        numVotes = new JLabel(String.valueOf(post.getVotes()));

        upvoteButton = new JButton();
        upvoteButton.addActionListener(actionListener);
        upvoteButton.setBorderPainted(false);
        upvoteButton.setContentAreaFilled(false);
        upvoteButton.setFocusPainted(false);
        upvoteButton.setOpaque(false);

        downvoteButton = new JButton();
        downvoteButton.addActionListener(actionListener);
        downvoteButton.setBorderPainted(false);
        downvoteButton.setContentAreaFilled(false);
        downvoteButton.setFocusPainted(false);
        downvoteButton.setOpaque(false);

        upvoteButton.setIcon(new ImageIcon(upvoteImage));
        downvoteButton.setIcon(new ImageIcon(downvoteImage));

        votes.add(upvoteButton);
        votes.add(numVotes);
        votes.add(downvoteButton);

        lblCaption = new JLabel(post.getContent());
        lblCaption.setFont(new Font("Arial", Font.PLAIN, 14));
        lblCaption.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        lblCaption.setBackground(new Color(230, 230, 230));

        captionLike.add(lblCaption, BorderLayout.WEST);
        captionLike.add(votes, BorderLayout.EAST);

        JPanel nameHide = new JPanel(new BorderLayout());
        nameHide.setBackground(new Color(230, 230, 230));

        hideButton = new JButton();
        hideButton.addActionListener(actionListener);
        hideButton.setBorderPainted(false);
        hideButton.setContentAreaFilled(false);
        hideButton.setFocusPainted(false);
        hideButton.setOpaque(false);
        hideButton.setIcon(new ImageIcon(hideImage));
        hideButton.setBorder(new EmptyBorder(0, 0, 0, 13));

        lblProfile = new JLabel("<html><b>" + post.getCreator() + "</b></html>" );
        lblProfile.setFont(new Font("Arial", Font.PLAIN, 14));
        lblProfile.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        lblProfile.setBackground(new Color(230, 230, 230));

        nameHide.add(lblProfile, BorderLayout.WEST);
        nameHide.add(hideButton, BorderLayout.EAST);

        add(nameHide);
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

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int currentVotes = Integer.parseInt(numVotes.getText());
            if (e.getSource() == upvoteButton){
                if (originalVotes == currentVotes){
                    MainGUIController.client.upvotePost(post.getPostID());
                    numVotes.setText(String.valueOf(Integer.parseInt(numVotes.getText()) + 1));
                    upvoteButton.setIcon(new ImageIcon(upvotedImage));
                    downvoteButton.setIcon(new ImageIcon(downvoteImage));
                }
                else if (originalVotes-1 == currentVotes){
                    MainGUIController.client.upvotePost(post.getPostID());
                    MainGUIController.client.upvotePost(post.getPostID());
                    numVotes.setText(String.valueOf(Integer.parseInt(numVotes.getText()) + 2));
                    upvoteButton.setIcon(new ImageIcon(upvotedImage));
                    downvoteButton.setIcon(new ImageIcon(downvoteImage));
                }
                else {
                    MainGUIController.client.downvotePost(post.getPostID());
                    numVotes.setText(String.valueOf(originalVotes));
                    upvoteButton.setIcon(new ImageIcon(upvoteImage));
                }
            }
            if (e.getSource() == downvoteButton){
                if (originalVotes == currentVotes){
                    MainGUIController.client.downvotePost(post.getPostID());
                    numVotes.setText(String.valueOf(Integer.parseInt(numVotes.getText()) - 1));
                    downvoteButton.setIcon(new ImageIcon(downvotedImage));
                    upvoteButton.setIcon(new ImageIcon(upvoteImage));
                }
                else if (originalVotes+1 == currentVotes){
                    MainGUIController.client.downvotePost(post.getPostID());
                    MainGUIController.client.downvotePost(post.getPostID());
                    numVotes.setText(String.valueOf(Integer.parseInt(numVotes.getText()) - 2));
                    downvoteButton.setIcon(new ImageIcon(downvotedImage));
                    upvoteButton.setIcon(new ImageIcon(upvoteImage));
                }
                else {
                    MainGUIController.client.upvotePost(post.getPostID());
                    numVotes.setText(String.valueOf(originalVotes));
                    downvoteButton.setIcon(new ImageIcon(downvoteImage));
                }
            }
            if (e.getSource() == hideButton){
                MainGUIController.client.hidePost(post.getPostID());
                MainGUIController.refresh("home", new HomePane());
            }
        }
    };

    MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            Post newPost = MainGUIController.client.findPost(post.getPostID());
            MainGUIController.refresh("fullpost", new FullPostPane(newPost));
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == lblImage) {
                lblImage.setBorder(BorderFactory.createLineBorder(new Color(169,169,169), 1));
            }
        }

        public void mouseExited(MouseEvent e) {
            if (e.getSource() == lblImage) {
                lblImage.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
            }
        }

    };
}
