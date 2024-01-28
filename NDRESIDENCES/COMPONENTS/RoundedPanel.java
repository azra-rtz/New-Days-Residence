package NDRESIDENCES.COMPONENTS;

import javax.swing.*;
import java.awt.*;

// RoundedPanel class extending JPanel for creating a rounded panel
public class RoundedPanel extends JPanel {
    private final int radius;  // Radius of the rounded corners

    // Constructor that sets the radius and makes the panel transparent
    public RoundedPanel(int radius) {
        this.radius = radius;
        setOpaque(false);  // Make the panel transparent
    }

    // Override paintComponent to customize the painting of the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Enable antialiasing for smoother graphics
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set the color of the rounded rectangle to the background color of the panel
        g2d.setColor(getBackground());

        // Fill the rounded rectangle with the specified radius
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius * 2, radius * 2);

        g2d.dispose();
    }
}
