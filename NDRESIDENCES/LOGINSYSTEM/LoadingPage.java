// Package declaration for the login system
package NDRESIDENCES.LOGINSYSTEM;

// Import statements
import javax.swing.*;
import NDRESIDENCES.APP.MainFrame; // Import MainFrame from another package
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

// Class representing the loading page
public class LoadingPage {

    // JFrame and components for the loading page
    private JFrame frame = new JFrame();
    private JProgressBar bar = new JProgressBar();
    private ImageIcon backgroundImg = new ImageIcon(new ImageIcon("NDRESIDENCES\\ASSETS\\LOADING_PAGE\\LOADING_PAGE.png").getImage().getScaledInstance(600, 420, Image.SCALE_SMOOTH));
    private ImageIcon winImage = new ImageIcon("NDRESIDENCES\\ASSETS\\LOGO\\LOGO.png");
    private JLabel background = new JLabel(backgroundImg);
    private JLabel status = new JLabel();
    private String fontFilepath = "NDRESIDENCES\\ASSETS\\FONTS\\Hero-Regular.ttf";

    // Constructor for the LoadingPage class
    public LoadingPage() {
        // Set the window icon
        frame.setIconImage(winImage.getImage());

        // Initialize and configure components
        setupComponents();
        designComponents();

        // Configure JFrame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 420);
        frame.setUndecorated(true); // No window decorations
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 49, 49));

        // Use SwingWorker to perform loading in the background
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                fill();
                return null;
            }

            @Override
            protected void done() {
                frame.dispose(); // Close the loading frame
                new MainFrame(); // Open the main application frame
            }
        }.execute();
    }

    // Method to simulate the loading process
    private void fill() throws InterruptedException {
        int counter = 0;
        String texts[] = {"Initializing rental estate tracker...",
                "Loading property information...", "Retrieving tenant details...",
                "Analyzing lease agreements...",
                "Syncing with json files...",
                "Finalizing setup and preparing dashboard..."};

        while (counter < 100) {
            bar.setValue(counter);

            try {
                Thread.sleep(20); // Simulate some processing time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int index = (counter > 85) ? 5 : (counter / 15);
            status.setText(texts[index]);

            counter += 1;
        }
    }

    // Method to set up the components on the JFrame
    private void setupComponents() {
        background.setLayout(null);
        background.setBounds(0, 0, 600, 420);

        frame.getContentPane().add(background);
        background.add(bar);
        background.add(status);

        bar.setValue(0);
    }

    // Method to design the appearance of components
    private void designComponents() {
        bar.setBounds(50, 380, 500, 10);
        bar.setForeground(new Color(229, 193, 205));
        bar.setBackground(new Color(239, 235, 226));
        bar.setBorderPainted(false);

        status.setBounds(50, 350, 400, 30);
        status.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 13));
    }

    // Method to load a custom font
    private Font loadCustomFont(String fontPath, int style, float size) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
            ge.registerFont(customFont);
            return customFont.deriveFont(style, size);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            return new Font(null, style, (int) size);
        }
    }

    // Main method to execute the loading page
    public static void main(String[] args) {
        new LoadingPage();
    }
}