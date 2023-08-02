import javax.swing.*;
import java.awt.*;

public class RoundedCornerButton extends JButton {
    public RoundedCornerButton(String text) {
        super(text);
        setupButton();
    }

    public RoundedCornerButton(Icon icon) {
        super(icon);
        setupButton();
    }

    public RoundedCornerButton() {
        setupButton();
    }

    private void setupButton() {
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setForeground(Color.BLACK); // Set text color to white
        setFont(new Font("Tahoma", Font.PLAIN, 14)); // Set font for the button text
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(new Color(240, 240, 240));
        } else if (getModel().isRollover()) {
            g.setColor(new Color(220, 220, 220));
        } else {
            g.setColor(new Color(0xFFFFFF)); // Set the background color for the button
        }
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(g);
    }
}
