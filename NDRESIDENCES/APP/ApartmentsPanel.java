// Package declaration for the ApartmentsPanel class
package NDRESIDENCES.APP;

// Importing necessary Java libraries and packages
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

// Importing custom components from the project
import NDRESIDENCES.COMPONENTS.Page;

// The main class representing the "ApartmentsPanel"
public class ApartmentsPanel extends Page {
    // GUI components declaration
    private JLabel titleLabel = new JLabel("Apartment Information");
    private JLabel descLabel = new JLabel("<html> Streamline property with 'Apartment Information' - <br> efficiently monitor the status of your apartments, whether available or occupied</html>");
    private ImageIcon IMG_ICON = new ImageIcon(new ImageIcon("NDRESIDENCES\\ASSETS\\ICONS\\ICON_2.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
    private JLabel icon = new JLabel(IMG_ICON);
    private JLabel errorMessage = new JLabel("JSON file does not exist.");
    private JScrollPane scrollPane = new JScrollPane();
    private String[] columnNames = {"Room", "Type", "Capacity", "Floor", "Price", "Available"};
    private JTable table;
    private String fontFilepath = "NDRESIDENCES\\ASSETS\\FONTS\\Hero-Regular.ttf";
    private File file = new File(filepath);

    // Constructor for the ApartmentsPanel class
    public ApartmentsPanel(int radius) {
        super(radius);

        // Setting up GUI components and their design
        setupComponents();
        designComponents();

        // Generating table only if JSON file exists
        if (file.exists()) {
            generateTable();
            scrollPane.setBounds(30, 125, 540, 450);
        }

        // Setting layout and background color
        setLayout(null);
        setBackground(new Color(239, 235, 226));
    }

    // Method to set up GUI components
    protected void setupComponents() {
        // Adding components to the panel
        add(titleLabel);
        add(descLabel);
        add(scrollPane);
        add(icon);
        add(errorMessage);
    }

    // Method to design the appearance of GUI components
    protected void designComponents() {
        // Setting font and bounds for the title label
        titleLabel.setFont(loadCustomFont(fontFilepath, Font.BOLD, 30));
        titleLabel.setBounds(40, 25, 400, 30);

        // Setting bounds and font for the description label
        descLabel.setBounds(40, 49, 500, 75);
        descLabel.setFont(loadCustomFont(fontFilepath, Font.ITALIC, 15));

        // Setting bounds for the icon
        icon.setBounds(220, 230, 150, 150);

        // Setting font, bounds, and color for the error message label
        errorMessage.setFont(loadCustomFont(fontFilepath, Font.BOLD, 15));
        errorMessage.setBounds(210, 400, 300, 30);
        errorMessage.setForeground(new Color(187, 185, 181));
    }

    // Method to generate and display the table
    private void generateTable() {
        // Loading data from JSON file
        renting.loadFromJson(filepath);
        List<HashMap<String, Object>> allApartments = renting.viewApartments();
        Object[][] data = new Object[allApartments.size()][columnNames.length];

        // Populating the data array with apartment details
        for (int i = 0; i < allApartments.size(); i++) {
            HashMap<String, Object> apartmentDetails = allApartments.get(i);
            boolean isAvailable = (boolean) apartmentDetails.get("isAvailable");
            String availability = isAvailable ? "Yes" : "No";

            int price = ((Double) apartmentDetails.get("price")).intValue();
            int capacity = (int) apartmentDetails.get("capacity");
            String type = determineApartmentType(capacity);

            data[i] = new Object[]{
                    apartmentDetails.get("roomNumber"),
                    type,
                    apartmentDetails.get("capacity"),
                    apartmentDetails.get("floor"),
                    price,
                    availability
            };
        }

        // Creating a DefaultTableModel with custom cell rendering
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
        };

        // Creating a JTable with custom settings
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane.setViewportView(table);

        // Setting font and color for the table header
        table.getTableHeader().setFont(loadCustomFont(fontFilepath, Font.BOLD, 14));
        setTableFont(table, fontFilepath, Font.PLAIN, 14);
        setTableHeaderColor(table, new Color(229, 193, 205));

        // Setting preferred widths for table columns
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(85);  // Room Number
        columnModel.getColumn(1).setPreferredWidth(85);  // Type
        columnModel.getColumn(2).setPreferredWidth(90);  // Capacity
        columnModel.getColumn(3).setPreferredWidth(77);  // Floor
        columnModel.getColumn(4).setPreferredWidth(100); // Price
        columnModel.getColumn(5).setPreferredWidth(100); // Available

        // Customizing colors for "Type" and "Available" columns
        table.getColumnModel().getColumn(1).setCellRenderer(new TypeColumnRenderer());
        table.getColumnModel().getColumn(5).setCellRenderer(new AvailabilityColumnRenderer());
    }

    // Method to determine apartment type based on capacity
    private static String determineApartmentType(int capacity) {
        switch (capacity) {
            case 2:
                return "Basic";
            case 4:
                return "Classic";
            case 6:
                return "Premium";
            default:
                return "Unknown";
        }
    }

    // Method to set font for the entire table
    private void setTableFont(JTable table, String fontPath, int style, float size) {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setFont(loadCustomFont(fontPath, style, size));
                ((JLabel) component).setHorizontalAlignment(JLabel.CENTER);
                return component;
            }
        });
    }

    // Method to set header color for the table
    private void setTableHeaderColor(JTable table, Color color) {
        JTableHeader header = table.getTableHeader();
        header.setBackground(color);
        header.setForeground(Color.BLACK);

        TableCellRenderer headerRenderer = header.getDefaultRenderer();
        if (headerRenderer instanceof JLabel) {
            ((JLabel) headerRenderer).setHorizontalAlignment(JLabel.CENTER);
        }
    }

    // Custom TableCellRenderer for the "Type" column
    private class TypeColumnRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            String type = (String) value;

            // Setting font and background color based on apartment type
            Font customFont = loadCustomFont(fontFilepath, Font.PLAIN, 14);
            ((JLabel) component).setFont(customFont);

            switch (type) {
                case "Basic":
                    component.setBackground(new Color(250, 203, 235));
                    component.setForeground(Color.BLACK);
                    break;
                case "Classic":
                    component.setBackground(new Color(218, 136, 185));
                    component.setForeground(Color.WHITE);
                    break;
                case "Premium":
                    component.setBackground(new Color(176, 87, 141));
                    component.setForeground(Color.WHITE);
                    break;
                default:
                    component.setBackground(table.getForeground());
                    break;
            }

            ((JLabel) component).setHorizontalAlignment(JLabel.CENTER);
            return component;
        }
    }

    // Custom TableCellRenderer for the "Available" column
    private class AvailabilityColumnRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            String availability = (String) value;

            // Setting font and background color based on availability
            Font customFont = loadCustomFont(fontFilepath, Font.PLAIN, 14);
            ((JLabel) component).setFont(customFont);

            switch (availability) {
                case "Yes":
                    component.setBackground(new Color(153, 188, 133));
                    break;
                case "No":
                    component.setBackground(new Color(233, 119, 119));
                    break;
                default:
                    component.setBackground(table.getForeground());
                    break;
            }

            ((JLabel) component).setHorizontalAlignment(JLabel.CENTER);
            return component;
        }
    }

    // Method to load a custom font from a file
    public Font loadCustomFont(String fontPath, int style, float size) {
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