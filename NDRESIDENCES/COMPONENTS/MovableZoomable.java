package NDRESIDENCES.COMPONENTS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;

// MovableZoomable class extending RoundedPanel
public class MovableZoomable extends RoundedPanel {
    // Components
    private ImageIcon imageIcon;
    private double scale = 0.53;
    private double minScale = 0.3;
    private double maxScale = 1.5;

    // Constructor
    public MovableZoomable(int radius, String imagePath) {
        super(radius);
        loadImage(imagePath);  // Load the image

        // Add a MouseWheelListener for zooming
        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() < 0) {
                    // Zoom in
                    scale *= 1.1;
                    if (scale > maxScale) {
                        scale = maxScale;
                    }
                } else {
                    // Zoom out
                    scale /= 1.1;
                    if (scale < minScale) {
                        scale = minScale;
                    }
                }
                updatePanelSize();  // Update the panel size based on the new scale
                repaint();  // Repaint the component
            }
        });
    }

    // Load the image from the specified file path
    private void loadImage(String imagePath) {
        try {
            File file = new File(imagePath);
            imageIcon = new ImageIcon(file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update the panel size based on the current scale
    private void updatePanelSize() {
        int newWidth = (int) (imageIcon.getIconWidth() * scale);
        int newHeight = (int) (imageIcon.getIconHeight() * scale);

        setPreferredSize(new Dimension(newWidth, newHeight));
        revalidate();  // Ensure the layout manager knows to update
    }

    // Override paintComponent to handle custom painting
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Calculate the position to center the image
        int x = (getWidth() - (int) (imageIcon.getIconWidth() * scale)) / 2;
        int y = (getHeight() - (int) (imageIcon.getIconHeight() * scale)) / 2;

        // Draw the scaled image
        g2d.drawImage(imageIcon.getImage(), x, y, (int) (imageIcon.getIconWidth() * scale), (int) (imageIcon.getIconHeight() * scale), this);

        g2d.dispose();
    }
}
