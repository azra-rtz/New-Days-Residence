// Package declaration for the DashboardPanel class
package NDRESIDENCES.APP;

// Importing necessary Java libraries and packages
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

// Importing custom components from the project
import NDRESIDENCES.COMPONENTS.ImageSlider;
import NDRESIDENCES.COMPONENTS.Page;
import NDRESIDENCES.COMPONENTS.MovableZoomable;

// The main class representing the "DashboardPanel"
public class DashboardPanel extends Page {
    // GUI components declaration
    private JLabel titleLabel = new JLabel("Welcome, " + user + "!");
    private JLabel descLabel = new JLabel("<html><center> Welcome to New Days Residence – Your Home, Your Community, Your New Beginning!</center></html>");

    private ImageSlider imageSlider;
    private MovableZoomable movableZoomable;
    private JPanel blackPanel = new JPanel();

    private ImageIcon IMG_1 = new ImageIcon(new ImageIcon("NDRESIDENCES\\ASSETS\\DASHBOARD\\DASHBOARD_4.png").getImage().getScaledInstance(180, 150, Image.SCALE_SMOOTH));
    private JLabel IMG_BLD = new JLabel(IMG_1);

    private ImageIcon IMG_2 = new ImageIcon(new ImageIcon("NDRESIDENCES\\ASSETS\\LOGO\\LOGO_BLACK.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
    private JLabel IMG_LOGO = new JLabel(IMG_2);

    private String fontFilepath = "NDRESIDENCES\\ASSETS\\FONTS\\Hero-Regular.ttf";

    // Constructor for the DashboardPanel class
    public DashboardPanel(int radius) {
        super(radius);
        // Setting up GUI components and their design
        setupComponents();
        designComponents();

        // Setting layout, visibility, and background color
        setLayout(null);
        setVisible(true);
        setBackground(new Color(239, 235, 226));
    }

    // Method to set up GUI components
    protected void setupComponents() {
        // Initializing custom components
        imageSlider = new ImageSlider(20);
        movableZoomable = new MovableZoomable(20, "NDRESIDENCES\\ASSETS\\DASHBOARD\\MAP.png");

        // Adding components to the panel
        add(imageSlider);
        add(movableZoomable);
        add(blackPanel);
        add(titleLabel);
        add(descLabel);
        add(IMG_BLD);
        add(IMG_LOGO);
    }

    // Method to design the appearance of GUI components
    protected void designComponents() {
        // Designing the black panel
        blackPanel.setLayout(null);
        blackPanel.setBackground(Color.BLACK);
        blackPanel.setBounds(20, 560, 410, 10);

        // Designing the title label
        titleLabel.setBounds(40, 25, 400, 50);
        titleLabel.setFont(loadCustomFont(fontFilepath, Font.BOLD, 30));

        // Designing the description label
        descLabel.setLayout(null);
        descLabel.setBounds(31, 350, 600, 40);
        descLabel.setFont(loadCustomFont(fontFilepath, Font.ITALIC, 13));

        // Designing the image slider
        imageSlider.setBounds(0, 80, 600, 270);

        // Designing the movable and zoomable image
        movableZoomable.setBounds(20, 390, 180, 150);

        // Designing the building image
        IMG_BLD.setLayout(null);
        IMG_BLD.setBounds(212, 390, 180, 150);

        // Designing the logo image
        IMG_LOGO.setLayout(null);
        IMG_LOGO.setBounds(400, 440, 170, 170);
    }

    // Method to load a custom font from a file
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
}