import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class CommentComponent extends JPanel {
    private JButton hideButton;
    private JLabel lblProfileComment;
    private Image upvoteImage;
    private Image downvoteImage;
    private Image upvotedImage;
    private Image downvotedImage;
    private Image hideImage;
    private JButton upvoteButton;
    private JButton downvoteButton;
    private JLabel numVotes;
    private Comment comment;
    private int originalVotes;
    private Post post;
    public CommentComponent(Comment comment, Post post) {
        this.post = post;
        String user = MyProfileComponent.user.getUserName();
        this.comment = comment;
        this.originalVotes = comment.getVotes();
        try {
            upvoteImage = ImageIO.read(new File("upvote.png")).getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            downvoteImage = ImageIO.read(new File("downvote.png")).getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            upvotedImage = ImageIO.read(new File("upvoted.png")).getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            downvotedImage = ImageIO.read(new File("downvoted.png")).getScaledInstance(15, 15, Image.SCALE_SMOOTH);
            hideImage = ImageIO.read(new File("trash.png")).getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        this.setLayout(new BorderLayout());
        this.setBackground(new Color(230, 230, 230));

        JPanel votes = new JPanel();
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
        votes.setLayout(flowLayout);
        votes.setBackground(new Color(230, 230, 230));
        numVotes = new JLabel(String.valueOf(comment.getVotes()));

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

        hideButton = new JButton();
        hideButton.addActionListener(actionListener);
        hideButton.setBorderPainted(false);
        hideButton.setContentAreaFilled(false);
        hideButton.setFocusPainted(false);
        hideButton.setOpaque(false);
        hideButton.setIcon(new ImageIcon(hideImage));
        hideButton.setBorder(new EmptyBorder(0, 0, 0, 3));

        votes.add(upvoteButton);
        votes.add(numVotes);
        votes.add(downvoteButton);
        if (user.equals(comment.getCreator()) || user.equals(comment.getPostAuthor()))
            votes.add(hideButton);


        String commentString = "<html><b>" + comment.getCreator() + "</b>" + ": " + comment.getContent() + "</html>";

        lblProfileComment = new JLabel(commentString);
        lblProfileComment.setFont(new Font("Arial", Font.PLAIN, 14));
        lblProfileComment.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        lblProfileComment.setBackground(new Color(230, 230, 230));

        this.add(lblProfileComment, BorderLayout.WEST);
        this.add(votes, BorderLayout.EAST);
    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int currentVotes = Integer.parseInt(numVotes.getText());
            if (e.getSource() == upvoteButton) {
                if (originalVotes == currentVotes) {
                    MainGUIController.client.upvoteComment(comment.getCommentID());
                    numVotes.setText(String.valueOf(Integer.parseInt(numVotes.getText()) + 1));
                    upvoteButton.setIcon(new ImageIcon(upvotedImage));
                    downvoteButton.setIcon(new ImageIcon(downvoteImage));
                } else if (originalVotes - 1 == currentVotes) {
                    MainGUIController.client.upvoteComment(comment.getCommentID());
                    MainGUIController.client.upvoteComment(comment.getCommentID());
                    numVotes.setText(String.valueOf(Integer.parseInt(numVotes.getText()) + 2));
                    upvoteButton.setIcon(new ImageIcon(upvotedImage));
                    downvoteButton.setIcon(new ImageIcon(downvoteImage));
                } else {
                    MainGUIController.client.downvoteComment(comment.getCommentID());
                    numVotes.setText(String.valueOf(originalVotes));
                    upvoteButton.setIcon(new ImageIcon(upvoteImage));
                }
            }
            if (e.getSource() == downvoteButton) {
                if (originalVotes == currentVotes) {
                    MainGUIController.client.downvoteComment(comment.getCommentID());
                    numVotes.setText(String.valueOf(Integer.parseInt(numVotes.getText()) - 1));
                    downvoteButton.setIcon(new ImageIcon(downvotedImage));
                    upvoteButton.setIcon(new ImageIcon(upvoteImage));
                } else if (originalVotes + 1 == currentVotes) {
                    MainGUIController.client.downvoteComment(comment.getCommentID());
                    MainGUIController.client.downvoteComment(comment.getCommentID());
                    numVotes.setText(String.valueOf(Integer.parseInt(numVotes.getText()) - 2));
                    downvoteButton.setIcon(new ImageIcon(downvotedImage));
                    upvoteButton.setIcon(new ImageIcon(upvoteImage));
                } else {
                    MainGUIController.client.upvoteComment(comment.getCommentID());
                    numVotes.setText(String.valueOf(originalVotes));
                    downvoteButton.setIcon(new ImageIcon(downvoteImage));
                }
            }
            if (e.getSource() == hideButton) {
                MainGUIController.client.deleteComment(post.getPostID(), comment.getCommentID());
                ArrayList<Comment> comments = post.getComments();
                for (int i = 0; i < comments.size(); i++) {
                    if (comments.get(i).getCommentID().equals(comment.getCommentID())) {
                        comments.remove(i);
                        break;
                    }
                }
                //Post newPost = MainGUIController.client.findPost(post.getPostID());
                MainGUIController.refresh("fullpost", new FullPostPane(post));
            }
        }
    };
}
