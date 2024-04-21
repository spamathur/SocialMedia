import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainGUIController extends JFrame implements Runnable {
    public static Container content;
    public static CardLayout cardLayout = new CardLayout();

    public static Client client = new Client();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new MainGUIController());
    }

    @Override
    public void run() {

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                client.closeConnection();
            }
        });

        ImageIcon image = new ImageIcon("bee_dashboard.jpg");
        this.setIconImage(image.getImage());
        this.setTitle("Buzz Board");
        this.setSize(450,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        content = this.getContentPane();
        content.setLayout(cardLayout);
        content.add(new LoginPane(), "login");
        content.add(new SignUpPane(), "signup");
        cardLayout.show(content, "login");
        setResizable(false);
        this.setVisible(true);
    }

    public static void refresh(String identifier, JPanel panel){
        Component[] components = content.getComponents();
        for (Component comp : components) {
            if (comp.getName() != null && comp.getName().equals(identifier)) {
                MainGUIController.content.remove(comp);
            }
        }
        MainGUIController.content.add(panel, identifier);
        MainGUIController.cardLayout.show(content, identifier);
    }
}
