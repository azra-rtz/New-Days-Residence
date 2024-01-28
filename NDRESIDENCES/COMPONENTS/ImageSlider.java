package NDRESIDENCES.COMPONENTS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ImageSlider class extending RoundedPanel
public class ImageSlider extends RoundedPanel {
    // Components
    private JLabel imageLabel;
    private Timer timer;
    private int currentIndex = 0;

    // Image resources
    private ImageIcon IMG_1 = new ImageIcon(new ImageIcon("NDRESIDENCES\\ASSETS\\DASHBOARD\\DASHBOARD_1.png").getImage().getScaledInstance(550, 260, Image.SCALE_SMOOTH));
    private ImageIcon IMG_2 = new ImageIcon(new ImageIcon("NDRESIDENCES\\ASSETS\\DASHBOARD\\DASHBOARD_2.png").getImage().getScaledInstance(550, 260, Image.SCALE_SMOOTH));
    private ImageIcon IMG_3 = new ImageIcon(new ImageIcon("NDRESIDENCES\\ASSETS\\DASHBOARD\\DASHBOARD_3.png").getImage().getScaledInstance(550, 260, Image.SCALE_SMOOTH));
    ImageIcon images[] = {IMG_1, IMG_2, IMG_3};

    // Constructor
    public ImageSlider(int radius) {
        super(radius);
        setSize(new Dimension(200, 90));
        setBackground(new Color(239, 235, 226));

        // Initialize and set up components
        imageLabel = new JLabel();
        add(imageLabel);

        // Timer for automatic image transition
        timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextImage();
            }
        });
        showNextImage();  // Show the initial image

        timer.start();  // Start the timer for automatic image transition
    }

    // Method to show the next image in the sequence
    private void showNextImage() {
        if (currentIndex < images.length) {
            ImageIcon icon = (images[currentIndex]);
            imageLabel.setIcon(icon);
            currentIndex++;
        } else {
            currentIndex = 0;  // Reset to the first image when the end is reached
            showNextImage();  // Show the first image
        }
    }
}