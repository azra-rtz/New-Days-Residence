// Package declaration for the TransactionPanel class
package NDRESIDENCES.APP;

// Importing necessary classes from the project
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.List;

import NDRESIDENCES.COMPONENTS.Page;

// Class representing the panel for viewing transaction records
public class TransactionPanel extends Page {
    // Components for the TransactionPanel
    private JLabel titleLabel = new JLabel("Transaction Record");
    private JLabel descLabel = new JLabel("<html> Stay in control with 'Transaction Record' - effortlessly review and <br> manage transaction records and ensuring financial transparency. </html>");
    private ImageIcon IMG_ICON = new ImageIcon(new ImageIcon("NDRESIDENCES\\ASSETS\\ICONS\\ICON_2.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
    private JLabel icon = new JLabel(IMG_ICON);
    private JLabel errorMessage = new JLabel("JSON file does not exist.");
    private JScrollPane scrollPane = new JScrollPane();
    private String[] columnNames = {"Room", "Action", "Name", "Contact", "Address", "Date", "Time"};
    private JTable table;
    private String fontFilepath = "NDRESIDENCES\\ASSETS\\FONTS\\Hero-Regular.ttf";
    private File file = new File(filepath);

    // Constructor for the TransactionPanel
    public TransactionPanel(int radius) {
        super(radius);

        setupComponents();
        designComponents();

        if (file.exists()) {
            generateTable();
            scrollPane.setBounds(30, 125, 540, 450);
        }

        setLayout(null);
        setBackground(new Color(239, 235, 226));
    }

    // Method to set up components of the panel
    protected void setupComponents() {
        add(titleLabel);
        add(descLabel);
        add(scrollPane);
        add(icon);
        add(errorMessage);
    }

    // Method to design the appearance of components
    protected void designComponents() {
        titleLabel.setFont(loadCustomFont(fontFilepath, Font.BOLD, 30));
        titleLabel.setBounds(40, 25, 350, 30);

        descLabel.setBounds(40,45,500,75);
        descLabel.setFont(loadCustomFont(fontFilepath, Font.ITALIC, 15));

        icon.setBounds(220, 230, 150, 150);

        errorMessage.setFont(loadCustomFont(fontFilepath, Font.BOLD, 15));
        errorMessage.setBounds(210, 400, 300,30);
        errorMessage.setForeground(new Color(187,185,181));
    }

    // Method to generate the transaction table
    private void generateTable() {
        renting.loadFromJson(filepath);

        List<Map<String, String>> allTransactions = renting.viewTransaction();
        Object[][] data = new Object[allTransactions.size()][columnNames.length];
    
        for (int i = 0; i < allTransactions.size(); i++) {
            Map<String, String> transaction = allTransactions.get(i);
            data[i] = new Object[]{
                    transaction.get("room_number"),
                    transaction.get("action"),
                    transaction.get("tenant_name"),
                    transaction.get("contact_number"),
                    transaction.get("current_address"),
                    transaction.get("date"),
                    transaction.get("time")
            };
        }
    
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
        };

        table = new JTable(model);
        table.getTableHeader().setFont(loadCustomFont(fontFilepath, Font.BOLD, 14));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(1).setCellRenderer(new ActionColumnRenderer());

        setTableFont(table, fontFilepath, Font.PLAIN, 14);
        customScrollBar(table);

        table.getColumnModel().getColumn(1).setCellRenderer(new ActionColumnRenderer());
    
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(60);
        columnModel.getColumn(1).setPreferredWidth(60);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(77);
        columnModel.getColumn(4).setPreferredWidth(120);
        columnModel.getColumn(5).setPreferredWidth(80);
        columnModel.getColumn(6).setPreferredWidth(60);
    
        scrollPane.setViewportView(table);

        setTableHeaderColor(table, new Color(229, 193, 205));
    
        SwingUtilities.invokeLater(() -> {
            table.setPreferredScrollableViewportSize(table.getPreferredSize());
        });
    }

    // Method to customize the scrollbar appearance
    private void customScrollBar(JTable table) {
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new RectangleScrollBarUI());

        JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        horizontalScrollBar.setUI(new RectangleScrollBarUI());
    }

    // Class defining a custom scrollbar appearance
    static class RectangleScrollBarUI extends BasicScrollBarUI {
        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(229, 193, 205)); // Set color for the scroll bar thumb
            g2.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);

            g2.dispose();
        }
    }

    // Method to set the font for the table cells
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

    // Method to set the color of the table header
    private void setTableHeaderColor(JTable table, Color color) {
        JTableHeader header = table.getTableHeader();
        header.setBorder(BorderFactory.createLineBorder(new Color(229,193,205))); // Set the border color
        header.setBackground(color);
        header.setForeground(Color.BLACK);

        TableCellRenderer headerRenderer = header.getDefaultRenderer();
        if (headerRenderer instanceof JLabel) {
            ((JLabel) headerRenderer).setHorizontalAlignment(JLabel.CENTER);
        }
    }

    // Class defining a custom renderer for the action column
    private class ActionColumnRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    
            if ("Rent".equals(value)) {
                component.setBackground(new Color(153,188,133));
                component.setForeground(Color.BLACK);
            } else if ("Leave".equals(value)) {
                component.setBackground(new Color(233,119,119));
                component.setForeground(Color.WHITE);
            } else {
                component.setBackground(table.getBackground());
                component.setForeground(table.getForeground());
            }
    
            ((JLabel) component).setHorizontalAlignment(JLabel.CENTER);
    
            ((JLabel) component).setFont(loadCustomFont(fontFilepath, Font.PLAIN, 14));
    
            return component;
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
