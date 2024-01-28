package NDRESIDENCES.COMPONENTS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// RoundedPasswordField class extending JPasswordField to create a password field with rounded corners
public class RoundedPasswordField extends JPasswordField {
    private Color borderColor = Color.BLACK;

    // Constructor
    public RoundedPasswordField() {
        super();
        setOpaque(false);  // Make the component transparent
        setBorder(new RoundedBorder());  // Set the custom rounded border
        setFont(new Font(null, Font.PLAIN, 17));  // Set the font for the password field
    }

    // Set the border color for the rounded border
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    // Override paintComponent to customize the painting of the component
    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque() && getBorder() instanceof RoundedBorder) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setPaint(getBackground());
            g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);  // Fill a rounded rectangle for transparency
            g2d.dispose();
        }
        super.paintComponent(g);
    }

    // Inner class for the custom rounded border
    private class RoundedBorder extends EmptyBorder {
        // Constructor to set padding for the border
        private RoundedBorder() {
            super(10, 10, 10, 10);
        }

        // Override paintBorder to customize the painting of the border
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(borderColor);
            g2d.drawRoundRect(x, y, width - 1, height - 1, 20, 20);  // Draw a rounded rectangle border
            g2d.dispose();
        }
    }
}

