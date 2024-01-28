// Package declaration for the LeasePanel class
package NDRESIDENCES.APP;

// Importing necessary Java libraries and packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

// Importing custom components from the project
import NDRESIDENCES.COMPONENTS.Button;
import NDRESIDENCES.COMPONENTS.Page;
import NDRESIDENCES.COMPONENTS.RoundedPanel;
import NDRESIDENCES.COMPONENTS.RoundedTextField;

// The main class representing the "LeasePanel"
public class LeasePanel extends Page implements ActionListener {
    // GUI components declaration
    private JLabel titleLabel = new JLabel("Lease Property");
    private JLabel descLabel = new JLabel("<html>Securely lease your property with ease using our 'Lease Apartment' feature - <br> streamline the rental process and connect with prospective tenants effortlessly</html>");
    private RoundedTextField roomField = new RoundedTextField();
    private RoundedTextField nameField = new RoundedTextField();
    private RoundedTextField contactField = new RoundedTextField();
    private RoundedTextField addressField = new RoundedTextField();
    private RoundedPanel roomPanel = new RoundedPanel(15);
    private RoundedPanel namePanel = new RoundedPanel(15);
    private RoundedPanel contactPanel = new RoundedPanel(15);
    private RoundedPanel addressPanel = new RoundedPanel(15);
    private JLabel roomLabel = new JLabel("Room Number:");
    private JLabel nameLabel = new JLabel("Tenant Name:");
    private JLabel contactLabel = new JLabel("Contact Number:");
    private JLabel addressLabel = new JLabel("Current Address:");
    private Button clearButton = new Button();
    private Button leaseButton = new Button();
    private String fontFilepath = "NDRESIDENCES\\ASSETS\\FONTS\\Hero-Regular.ttf";

    // Constructor for the LeasePanel class
    public LeasePanel(int radius) {
        super(radius);

        // Setting up GUI components and their design
        setupComponents();
        designComponents();

        // Setting layout, visibility, and background color
        setLayout(null);
        setBackground(new Color(239, 235, 226));

        // Setting UI manager properties for consistent appearance
        UIManager.put("OptionPane.background", new Color(229, 193, 205));
        UIManager.put("Panel.background", new Color(229, 193, 205));
        UIManager.put("TitleBar.background", new Color(229, 193, 205));
    }

    // Method to set up GUI components
    protected void setupComponents() {
        // Initializing and adding custom components to the panel
        add(titleLabel);
        add(roomField);
        add(nameField);
        add(contactField);
        add(addressField);
        add(roomPanel);
        add(namePanel);
        add(contactPanel);
        add(addressPanel);
        add(clearButton);
        add(leaseButton);
        add(descLabel);

        // Adding labels to corresponding panels
        roomPanel.add(roomLabel);
        namePanel.add(nameLabel);
        contactPanel.add(contactLabel);
        addressPanel.add(addressLabel);

        // Adding action listeners to buttons
        clearButton.addActionListener(this);
        leaseButton.addActionListener(this);
    }

    // Method to design the appearance of GUI components
    protected void designComponents() {
        // Designing the title label
        titleLabel.setFont(loadCustomFont(fontFilepath, Font.BOLD, 30));
        titleLabel.setBounds(40, 25, 300, 30);

        // Designing the description label
        descLabel.setBounds(40, 48, 500, 75);
        descLabel.setFont(loadCustomFont(fontFilepath, Font.ITALIC, 13));

        // Designing the text fields
        roomField.setBounds(260, 180, 300, 40);
        roomField.setBorderColor(new Color(229 - 20, 193 - 20, 205 - 20));

        nameField.setBounds(260, 240, 300, 40);
        nameField.setBorderColor(new Color(229 - 20, 193 - 20, 205 - 20));

        contactField.setBounds(260, 300, 300, 40);
        contactField.setBorderColor(new Color(229 - 20, 193 - 20, 205 - 20));

        addressField.setBounds(260, 360, 300, 40);
        addressField.setBorderColor(new Color(229 - 20, 193 - 20, 205 - 20));

        // Designing the rounded panels
        roomPanel.setBounds(39, 180, 190, 40);
        roomPanel.setBackground(new Color(229, 193, 205));

        namePanel.setBounds(39, 240, 200, 40);
        namePanel.setBackground(new Color(229, 193, 205));

        contactPanel.setBounds(39, 300, 200, 40);
        contactPanel.setBackground(new Color(229, 193, 205));

        addressPanel.setBounds(39, 360, 200, 40);
        addressPanel.setBackground(new Color(229, 193, 205));

        // Designing the labels
        roomLabel.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 17));
        nameLabel.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 17));
        contactLabel.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 17));
        addressLabel.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 17));

        // Designing the buttons
        clearButton.setBounds(250, 520, 100, 50);
        clearButton.setFocusable(false);
        clearButton.setText("Clear");
        clearButton.setBackground(new Color(229, 193, 205));
        clearButton.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 15));

        leaseButton.setBounds(380, 520, 180, 50);
        leaseButton.setFocusable(false);
        leaseButton.setText("Lease Apartment");
        leaseButton.setBackground(new Color(229, 193, 205));
        leaseButton.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 15));
    }

    // Method to check if a text field is empty
    private static boolean isEmpty(JTextField textField) {
        return textField.getText().trim().isEmpty();
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

    // ActionListener method to handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        // Clear button action
        if (e.getSource() == clearButton) {
            roomField.setText("");
            nameField.setText("");
            contactField.setText("");
            addressField.setText("");
        }

        // Lease button action
        if (e.getSource() == leaseButton) {
            // Checking if all necessary fields are filled
            if (!isEmpty(roomField) && !isEmpty(nameField) && !isEmpty(contactField) && !isEmpty(addressField)) {
                // Retrieving values from text fields
                String room = roomField.getText();
                String name = nameField.getText();
                String contact = contactField.getText();
                String address = addressField.getText();
                File file = new File(filepath);

                // Loading existing data from JSON file if it exists
                if (file.exists()) {
                    renting.loadFromJson(filepath);
                }

                // Attempting to lease the apartment
                if (!renting.rentApartment(room, name, contact, address)) {
                    // Showing an error message if the apartment is already inhabited
                    JOptionPane.showMessageDialog(null, "The apartment is currently inhabited.", "Error in Lease Agreement", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Saving the updated data to the JSON file
                    renting.saveToJson(filepath);
                    // Clearing the text fields
                    roomField.setText("");
                    nameField.setText("");
                    contactField.setText("");
                    addressField.setText("");
                }
            } else {
                // Showing an error message if not all fields are filled
                JOptionPane.showMessageDialog(null, "Kindly provide all the necessary credentials.", "Error in Lease Agreement", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}