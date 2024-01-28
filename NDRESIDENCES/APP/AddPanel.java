// Package declaration for the AddPanel class
package NDRESIDENCES.APP;

// Importing necessary Java libraries and packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

// Importing custom classes and components from the project
import NDRESIDENCES.BACKEND.BasicApt;
import NDRESIDENCES.BACKEND.ClassicApt;
import NDRESIDENCES.BACKEND.PremiumApt;
import NDRESIDENCES.COMPONENTS.Button;
import NDRESIDENCES.COMPONENTS.Page;
import NDRESIDENCES.COMPONENTS.RoundedPanel;
import NDRESIDENCES.COMPONENTS.RoundedTextField;

// The main class representing the "Add Apartment" panel
public class AddPanel extends Page implements ActionListener {

    // GUI components declaration
    private JLabel titleLabel = new JLabel("Add Apartment");
    private JLabel descLabel = new JLabel("<html>Simplify the rental or sale process by easily adding your apartment <br> with our intuitive 'Add Apartment' feature.</html> ");
    private Button addButton = new Button();
    private Button clearButton = new Button();
    private RoundedTextField roomField = new RoundedTextField();
    private RoundedTextField floorField = new RoundedTextField();
    private JLabel roomLabel = new JLabel("Enter Room Number:");
    private JLabel floorLabel = new JLabel("Enter Floor:");
    private RoundedPanel roomPanel = new RoundedPanel(15);
    private RoundedPanel floorPanel = new RoundedPanel(15);
    private JRadioButton basicApt = new JRadioButton();
    private JRadioButton classicApt = new JRadioButton();
    private JRadioButton premiumApt = new JRadioButton();
    private ButtonGroup group = new ButtonGroup();

    // Image icons for different apartment types
    private ImageIcon basicImage = new ImageIcon(new ImageIcon("NDRESIDENCES\\ASSETS\\APARTMENTS\\BasicSelected.png").getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH));
    private ImageIcon basicImageSelected = new ImageIcon(new ImageIcon("NDRESIDENCES\\ASSETS\\APARTMENTS\\Basic.png").getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH));
    private ImageIcon classicImage = new ImageIcon(new ImageIcon("NDRESIDENCES\\ASSETS\\APARTMENTS\\ClassicSelected.png").getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH));
    private ImageIcon classicImageSelected = new ImageIcon(new ImageIcon("NDRESIDENCES\\ASSETS\\APARTMENTS\\Classic.png").getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH));
    private ImageIcon premiumImage = new ImageIcon(new ImageIcon("NDRESIDENCES\\ASSETS\\APARTMENTS\\PremiumSelected.png").getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH));
    private ImageIcon premiumImageSelected = new ImageIcon(new ImageIcon("NDRESIDENCES\\ASSETS\\APARTMENTS\\Premium.png").getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH));

    // Font filepath
    private String fontFilepath = "NDRESIDENCES\\ASSETS\\FONTS\\Hero-Regular.ttf";

    // Constructor for the AddPanel class
    public AddPanel(int radius) {
        super(radius);

        // Setting up GUI components and their design
        setupComponents();
        designComponents();

        // Setting layout and background color
        setLayout(null);
        setBackground(new Color(239, 235, 226));

        // Customizing UI colors
        UIManager.put("OptionPane.background", new Color(229, 193, 205));
        UIManager.put("Panel.background", new Color(229, 193, 205));
        UIManager.put("TitleBar.background", new Color(229, 193, 205));
    }

    // Method to set up GUI components
    protected void setupComponents() {
        // Adding components to the panel
        add(titleLabel);
        add(roomField);
        add(floorField);
        add(roomPanel);
        add(floorPanel);
        add(addButton);
        add(clearButton);
        add(basicApt);
        add(classicApt);
        add(premiumApt);
        add(descLabel);

        // Setting default selection for basic apartment type
        basicApt.setSelected(true);

        // Adding labels to the panels
        roomPanel.add(roomLabel);
        floorPanel.add(floorLabel);

        // Adding radio buttons to a button group
        group.add(basicApt);
        group.add(classicApt);
        group.add(premiumApt);

        // Adding action listeners to buttons
        addButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    // Method to design the appearance of GUI components
    protected void designComponents() {
        // Setting font and bounds for the title label
        titleLabel.setFont(loadCustomFont(fontFilepath, Font.BOLD, 30));
        titleLabel.setBounds(40, 25, 300, 30);

        // Setting bounds and font for the description label
        descLabel.setBounds(40, 45, 500, 75);
        descLabel.setFont(loadCustomFont(fontFilepath, Font.ITALIC, 15));

        // Setting bounds for the room and floor input fields
        roomField.setBounds(310, 370, 250, 40);
        floorField.setBounds(180, 430, 385, 40);

        // Setting border color for the input fields
        roomField.setBorderColor(new Color(229 - 20, 193 - 20, 205 - 20));
        floorField.setBorderColor(new Color(229 - 20, 193 - 20, 205 - 20));

        // Setting bounds for the rounded panels
        roomPanel.setBounds(39, 370, 250, 40);
        floorPanel.setBounds(39, 430, 130, 40);

        // Setting background color for the rounded panels
        roomPanel.setBackground(new Color(229, 193, 205));
        floorPanel.setBackground(new Color(229, 193, 205));

        // Setting font for room and floor labels
        roomLabel.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 17));
        floorLabel.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 17));

        // Setting bounds, appearance, and font for the "Add Apartment" button
        addButton.setBounds(410, 520, 150, 50);
        addButton.setFocusable(false);
        addButton.setText("Add Apartment");
        addButton.setBackground(new Color(229, 193, 205));
        addButton.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 15));

        // Setting bounds, appearance, and font for the "Clear" button
        clearButton.setBounds(310, 520, 80, 50);
        clearButton.setFocusable(false);
        clearButton.setText("Clear");
        clearButton.setBackground(new Color(229, 193, 205));
        clearButton.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 15));

        // Setting bounds, appearance, and background for radio buttons representing apartment types
        basicApt.setBounds(35, 140, 160, 200);
        basicApt.setBackground(new Color(239, 235, 226));
        basicApt.setIcon(basicImage);
        basicApt.setSelectedIcon(basicImageSelected);

        classicApt.setBounds(220, 140, 160, 200);
        classicApt.setBackground(new Color(239, 235, 226));
        classicApt.setIcon(classicImage);
        classicApt.setSelectedIcon(classicImageSelected);

        premiumApt.setBounds(405, 140, 160, 200);
        premiumApt.setBackground(new Color(239, 235, 226));
        premiumApt.setIcon(premiumImage);
        premiumApt.setSelectedIcon(premiumImageSelected);
    }

    // Method to check if a JTextField is empty
    private static boolean isEmpty(JTextField textField) {
        return textField.getText().trim().isEmpty();
    }

    // Action performed method to handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handling "Add Apartment" button click
        if (e.getSource() == addButton) {
            // Checking if room and floor fields are not empty
            if (!isEmpty(roomField) && !isEmpty(floorField)) {
                // Retrieving values from input fields
                String room = roomField.getText();
                int floor = Integer.parseInt(floorField.getText());
                File file = new File(filepath);

                // Loading data from JSON file if it exists
                if (file.exists()) {
                    renting.loadFromJson(filepath);
                }

                // Adding the selected apartment type based on radio button selection
                if (basicApt.isSelected()) {
                    renting.addApartment(new BasicApt(room, floor));
                } else if (classicApt.isSelected()) {
                    renting.addApartment(new ClassicApt(room, floor));
                } else if (premiumApt.isSelected()) {
                    renting.addApartment(new PremiumApt(room, floor));
                }

                // Saving data to JSON file and resetting input fields
                renting.saveToJson(filepath);
                roomField.setText("");
                floorField.setText("");
            } else {
                // Displaying an error message if fields are empty
                JOptionPane.showMessageDialog(null, "Please enter both room and floor number.", "Apartment Addition Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Handling "Clear" button click
        if (e.getSource() == clearButton) {
            // Resetting input fields
            roomField.setText("");
            floorField.setText("");
        }
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
