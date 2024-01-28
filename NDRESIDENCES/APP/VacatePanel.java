// Package declaration for the VacatePanel class
package NDRESIDENCES.APP;

// Importing necessary classes from the project
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import NDRESIDENCES.COMPONENTS.Page;
import NDRESIDENCES.COMPONENTS.RoundedPanel;
import NDRESIDENCES.COMPONENTS.RoundedTextField;
import NDRESIDENCES.COMPONENTS.Button;

// Class representing the panel for handling tenant departures
public class VacatePanel extends Page implements ActionListener {
    // Components for the VacatePanel
    private JLabel titleLabel = new JLabel("End of Tenancy");
    private JLabel descLabel = new JLabel("<html>Effortlessly manage tenant departures with our 'Leave Apartment' <br> - confirm and streamline the process as tenants vacate your property</html>");
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
    private Button vacateButton = new Button();
    private String fontFilepath = "NDRESIDENCES\\ASSETS\\FONTS\\Hero-Regular.ttf";

    // Constructor for the VacatePanel
    public VacatePanel(int radius) {
        super(radius);

        setupComponents();
        designComponents();

        setLayout(null);
        setBackground(new Color(239, 235, 226));

        UIManager.put("OptionPane.background", new Color(229, 193, 205));
        UIManager.put("Panel.background", new Color(229, 193, 205));
        UIManager.put("TitleBar.background", new Color(229, 193, 205));
    }

    // Method to set up components of the panel
    protected void setupComponents() {
        add(titleLabel);
        add(descLabel);
        add(roomField);
        add(nameField);
        add(contactField);
        add(addressField);
        add(roomPanel);
        add(namePanel);
        add(contactPanel);
        add(addressPanel);
        add(clearButton);
        add(vacateButton);

        roomPanel.add(roomLabel);
        namePanel.add(nameLabel);
        contactPanel.add(contactLabel);
        addressPanel.add(addressLabel);

        clearButton.addActionListener(this);
        vacateButton.addActionListener(this);
    }

    // Method to design the appearance of components
    protected void designComponents() {
        titleLabel.setFont(loadCustomFont(fontFilepath, Font.BOLD, 30));
        titleLabel.setBounds(40, 25, 300, 30);

        descLabel.setBounds(40,50,500,75);
        descLabel.setFont(loadCustomFont(fontFilepath, Font.ITALIC, 15));

        roomField.setBounds(260, 180, 300, 40);
        roomField.setBorderColor(new Color(229 - 20, 193 - 20, 205 - 20));

        nameField.setBounds(260, 240, 300, 40);
        nameField.setBorderColor(new Color(229 - 20, 193 - 20, 205 - 20));

        contactField.setBounds(260, 300, 300, 40);
        contactField.setBorderColor(new Color(229 - 20, 193 - 20, 205 - 20));

        addressField.setBounds(260, 360, 300, 40);
        addressField.setBorderColor(new Color(229 - 20, 193 - 20, 205 - 20));
        
        roomPanel.setBounds(39, 180, 190, 40);
        roomPanel.setBackground(new Color(229, 193, 205));

        namePanel.setBounds(39, 240, 200, 40);
        namePanel.setBackground(new Color(229, 193, 205));

        contactPanel.setBounds(39, 300, 200, 40);
        contactPanel.setBackground(new Color(229, 193, 205));

        addressPanel.setBounds(39, 360, 200, 40);
        addressPanel.setBackground(new Color(229, 193, 205));
        
        roomLabel.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 17));
        nameLabel.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 17));
        contactLabel.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 17));
        addressLabel.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 17));

        clearButton.setBounds(250, 520, 100, 50);
        clearButton.setFocusable(false);
        clearButton.setText("Clear");
        clearButton.setBackground(new Color(229, 193, 205));
        clearButton.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 15));

        vacateButton.setBounds(380, 520, 180, 50);
        vacateButton.setFocusable(false);
        vacateButton.setText("Vacate Apartment");
        vacateButton.setBackground(new Color(229, 193, 205));
        vacateButton.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 15));
    }

    // Method to check if a JTextField is empty
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

    // ActionListener implementation for handling button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        // Clear button action
        if (e.getSource() == clearButton) {
            roomField.setText("");
            nameField.setText("");
            contactField.setText("");
            addressField.setText("");
        }

        // Vacate button action
        if (e.getSource() == vacateButton) {
            if (!isEmpty(roomField) && !isEmpty(nameField) && !isEmpty(contactField) && !isEmpty(addressField)) {
                String room = roomField.getText();
                String name = nameField.getText();
                String contact = contactField.getText();
                String address = addressField.getText();
                File file = new File(filepath);

                if (file.exists()) {
                    renting.loadFromJson(filepath);
                }

                // Attempt to leave the apartment
                if (!renting.leaveApartment(room, name, contact, address)) {
                    JOptionPane.showMessageDialog(null, "The apartment is unoccupied.", "Error in Ending Tenancy", JOptionPane.ERROR_MESSAGE);
                } else {
                    renting.leaveApartment(room, name, contact, address);
                }
                
                renting.saveToJson(filepath);
                roomField.setText("");
                nameField.setText("");
                contactField.setText("");
                addressField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Kindly provide all the necessary credentials.", "Error in Ending Tenancy", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
