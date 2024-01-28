package NDRESIDENCES.COMPONENTS;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

// Custom button class with a shadow and ripple effect
public class Button extends JButton {

    // Roundness of the button corners
    private int round = 10;

    // Color of the shadow
    private Color shadowColor = new Color(170, 170, 170);

    // Image to store the shadow effect
    private BufferedImage imageShadow;

    // Size of the shadow insets
    private final Insets shadowSize = new Insets(2, 5, 8, 5);

    // Ripple effect for button interaction
    private final RippleEffect rippleEffect = new RippleEffect(this);

    // Constructor for the custom button
    public Button() {
        // Set initial properties for the button
        setBorder(new EmptyBorder(10, 12, 15, 12));
        setContentAreaFilled(false);
        setBackground(new Color(255, 255, 255));
        setForeground(new Color(80, 80, 80));
        rippleEffect.setRippleColor(new Color(220, 220, 220));
    }

    // Getter for the roundness of the button
    public int getRound() {
        return round;
    }

    // Setter for the roundness of the button
    public void setRound(int round) {
        this.round = round;
        createImageShadow();
        repaint();
    }

    // Getter for the color of the shadow
    public Color getShadowColor() {
        return shadowColor;
    }

    // Setter for the color of the shadow
    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
        createImageShadow();
        repaint();
    }

    // Setter for the ripple color
    public void setRippleColor(Color color) {
        rippleEffect.setRippleColor(color);
    }

    // Getter for the ripple color
    public Color getRippleColor() {
        return rippleEffect.getRippleColor();
    }

    // Paints the component, including the shadow and ripple effect
    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        double width = getWidth() - (shadowSize.left + shadowSize.right);
        double height = getHeight() - (shadowSize.top + shadowSize.bottom);
        double x = shadowSize.left;
        double y = shadowSize.top;
        
        // Draw the shadow image
        g2.drawImage(imageShadow, 0, 0, null);
        
        // Draw the button background
        g2.setColor(getBackground());
        Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, round, round));
        g2.fill(area);
        
        // Apply the ripple effect
        rippleEffect.reder(grphcs, area);
        
        g2.dispose();
        super.paintComponent(grphcs);
    }

    // Set bounds for the button and recreate the shadow image
    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        createImageShadow();
    }

    // Create the shadow image for the button
    private void createImageShadow() {
        int height = getHeight();
        int width = getWidth();
        if (width > 0 && height > 0) {
            imageShadow = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = imageShadow.createGraphics();
            BufferedImage img = createShadow();
            if (img != null) {
                g2.drawImage(createShadow(), 0, 0, null);
            }
            g2.dispose();
        }
    }

    // Create the shadow for the button
    private BufferedImage createShadow() {
        int width = getWidth() - (shadowSize.left + shadowSize.right);
        int height = getHeight() - (shadowSize.top + shadowSize.bottom);
        if (width > 0 && height > 0) {
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = img.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fill(new RoundRectangle2D.Double(0, 0, width, height, round, round));
            g2.dispose();
            
            // Apply shadow effect
            return new ShadowRenderer(5, 0.3f, shadowColor).createShadow(img);
        } else {
            return null;
        }
    }
}
