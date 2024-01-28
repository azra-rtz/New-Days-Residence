// Package declaration for the RemovePanel class
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

// Class representing the panel for removing an apartment
public class RemovePanel extends Page implements ActionListener {
    // Components for the RemovePanel
    private JLabel titleLabel = new JLabel("Remove Apartment");
    private JLabel roomLabel = new JLabel("Enter Room Number:");
    private JLabel descLabel = new JLabel("<html>Effortlessly manage your listings with 'RemoveApartment, <br> making it easy to remove properties. </html>");
    private RoundedTextField roomField = new RoundedTextField();
    private RoundedPanel roomPanel = new RoundedPanel(15);
    private Button removeButton = new Button();
    private String fontFilepath = "NDRESIDENCES\\ASSETS\\FONTS\\Hero-Regular.ttf";

    // Constructor for the RemovePanel
    public RemovePanel(int radius) {
        super(radius);

        setupComponents();
        designComponents();

        setLayout(null);
        setBackground(new Color(239, 235, 226));

        // Setting background color for JOptionPane
        UIManager.put("OptionPane.background", new Color(229, 193, 205));
        UIManager.put("Panel.background", new Color(229, 193, 205));
        UIManager.put("TitleBar.background", new Color(229, 193, 205));
    }

    // Method to set up components of the panel
    protected void setupComponents() {
        add(titleLabel);
        add(descLabel);
        add(roomPanel);
        add(removeButton);
        add(roomField);

        roomPanel.add(roomLabel);
        removeButton.addActionListener(this);
    }

    // Method to design the appearance of components
    protected void designComponents() {
        titleLabel.setFont(loadCustomFont(fontFilepath, Font.BOLD, 30));
        titleLabel.setBounds(40, 25, 350, 30);

        descLabel.setBounds(40, 45, 500, 75);
        descLabel.setFont(loadCustomFont(fontFilepath, Font.ITALIC, 15));

        roomField.setBounds(310, 270, 250, 40);
        roomField.setBorderColor(new Color(229 - 20, 193 - 20, 205 - 20));

        roomPanel.setBounds(39, 270, 250, 40);
        roomPanel.setBackground(new Color(229, 193, 205));

        roomLabel.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 17));

        removeButton.setBounds(330, 520, 230, 50);
        removeButton.setFocusable(false);
        removeButton.setText("Remove Apartment");
        removeButton.setBackground(new Color(229, 193, 205));
        removeButton.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 15));
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

    // Method to check if a JTextField is empty
    private static boolean isEmpty(JTextField textField) {
        return textField.getText().trim().isEmpty();
    }

    // ActionListener implementation to handle button click
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeButton) {
            if (!isEmpty(roomField)) {
                String room = roomField.getText();
                File file = new File(filepath);

                if (file.exists()) {
                    renting.loadFromJson(filepath);
                }

                if (!renting.deleteApartment(room)) {
                    JOptionPane.showMessageDialog(null, "Apartment doesn't exist.", "Apartment Deletion Error", JOptionPane.ERROR_MESSAGE);
                } else renting.deleteApartment(room);

                renting.saveToJson(filepath);
                roomField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a room number.", "Apartment Addition Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
