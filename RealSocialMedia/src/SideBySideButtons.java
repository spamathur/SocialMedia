import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SideBySideButtons extends JPanel {
    public JButton button1 = new JButton();
    public JButton button2 = new JButton();

    public SideBySideButtons(String button1Text, String button2Text, Color button1Color, Color button2Color, ActionListener actionListener){
        this.setLayout(new GridLayout(1, 2, 20, 0));
        this.setBackground(Color.white);

        button1 = createButton(button1Text, button1Color);
        button1.setActionCommand(button1Text);
        button1.addActionListener(actionListener);
        this.add(button1);

        button2 = createButton(button2Text, button2Color);
        button2.setActionCommand(button2Text);
        button2.addActionListener(actionListener);
        this.add(button2);
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

    public JButton getButton1() {
        return button1;
    }

    public void setButton1(JButton button1) {
        this.button1 = button1;
    }
}

