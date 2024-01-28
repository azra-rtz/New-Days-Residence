// Package declaration for the MainFrame class
package NDRESIDENCES.APP;

// Importing necessary Java libraries and packages
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

// Importing custom components from the project
import NDRESIDENCES.COMPONENTS.Button;
import NDRESIDENCES.COMPONENTS.RoundedPanel;
import NDRESIDENCES.LOGINSYSTEM.SignupSignin;
import NDRESIDENCES.LOGINSYSTEM.UserPass;

// The main class representing the application's main frame
public class MainFrame extends JFrame implements ActionListener {
    // Declaration of panels for left and right sections
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();

    // Panels representing different functionalities
    private JPanel dashboard = new ColoredPanel(15, "Dashboard", new Color(229, 193, 205), 10, 120);
    private JPanel add = new ColoredPanel(15, "Add", new Color(229, 193, 205), 10, 175);
    private JPanel remove = new ColoredPanel(15, "Remove", new Color(229, 193, 205), 10, 230);
    private JPanel lease = new ColoredPanel(15, "Lease", new Color(229, 193, 205), 10, 285);
    private JPanel vacate = new ColoredPanel(15, "Vacate", new Color(229, 193, 205), 10, 340);
    private JPanel transaction = new ColoredPanel(15, "Transaction", new Color(229, 193, 205), 10, 395);
    private JPanel apartments = new ColoredPanel(15, "Apartments", new Color(229, 193, 205), 10, 450);

    // Icons and images
    private ImageIcon icomImg = new ImageIcon(new ImageIcon("NDRESIDENCES\\ASSETS\\ICONS\\ICON_1.png").getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH));
    private ImageIcon logoImg = new ImageIcon(new ImageIcon("NDRESIDENCES\\ASSETS\\LOGO\\LOGO_WHITE.png").getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH));
    private ImageIcon winImage = new ImageIcon("NDRESIDENCES\\ASSETS\\LOGO\\LOGO.png");

    // Labels for different functionalities
    private JLabel dashboardLabel = new JLabel("Dashboard");
    private JLabel addLabel = new JLabel("Add");
    private JLabel removeLabel = new JLabel("Remove");
    private JLabel leaseLabel = new JLabel("Lease");
    private JLabel vacateLabel = new JLabel("Vacate");
    private JLabel transactionLabel = new JLabel("Transactions");
    private JLabel apartmentLabel = new JLabel("Apartments");
    private JLabel icon = new JLabel(icomImg);
    private JLabel logo = new JLabel(logoImg);

    // Button to exit or logout
    private Button exitButton = new Button();

    // Font file path
    private String fontFilepath = "NDRESIDENCES\\ASSETS\\FONTS\\Hero-Regular.ttf";

    // Variables to track mouse movement for frame dragging
    private int mouseX, mouseY;

    // Constructor for the MainFrame class
    public MainFrame() {
        // Setting window properties
        this.setIconImage(winImage.getImage());
        setupComponents();
        designComponents();
        setUndecorated(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(818, 626);
        setVisible(true);
        setLocationRelativeTo(null);
        setShape(new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(), 40, 40));

        // Adding mouse listeners for frame dragging
        addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen() - mouseX;
                int y = e.getYOnScreen() - mouseY;
                setLocation(x, y);
            }
        });

        // Setting UI manager properties for consistent appearance
        UIManager.put("OptionPane.background", new Color(229, 193, 205));
        UIManager.put("Panel.background", new Color(229, 193, 205));
        UIManager.put("TitleBar.background", new Color(229, 193, 205));
    }

    // Method to set up GUI components
    private void setupComponents() {
        add(leftPanel);
        add(rightPanel);

        leftPanel.add(dashboard);
        leftPanel.add(add);
        leftPanel.add(remove);
        leftPanel.add(lease);
        leftPanel.add(vacate);
        leftPanel.add(transaction);
        leftPanel.add(apartments);
        leftPanel.add(logo);
        leftPanel.add(exitButton);

        dashboard.add(dashboardLabel);
        add.add(addLabel);
        remove.add(removeLabel);
        lease.add(leaseLabel);
        vacate.add(vacateLabel);
        transaction.add(transactionLabel);
        apartments.add(apartmentLabel);

        exitButton.addActionListener(this);

        // Set the initial panel to be displayed
        ColoredPanel dashboardPanel = getColoredPanelByName("Dashboard");
        if (dashboardPanel != null) {
            dashboardPanel.handlePanelClick();
        }
    }

    // Method to design the appearance of GUI components
    private void designComponents() {
        // Designing left panel
        leftPanel.setLayout(null);
        leftPanel.setBackground(new Color(229, 193, 205));
        leftPanel.setBounds(0, 0, 200, 626);

        // Designing right panel
        rightPanel.setLayout(null);
        rightPanel.setBackground(new Color(229, 193, 205));
        rightPanel.setBounds(200, 0, 618, 626);

        // Designing each functionality panel
        dashboard.setLayout(null);
        add.setLayout(null);
        remove.setLayout(null);
        lease.setLayout(null);
        vacate.setLayout(null);
        transaction.setLayout(null);
        apartments.setLayout(null);

        // Designing labels for each functionality
        dashboardLabel.setBounds(65, 7, 150, 40);
        dashboardLabel.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 17));

        addLabel.setBounds(65, 7, 150, 40);
        addLabel.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 17));

        removeLabel.setBounds(65, 7, 150, 40);
        removeLabel.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 17));

        leaseLabel.setBounds(65, 7, 150, 40);
        leaseLabel.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 17));

        vacateLabel.setBounds(65, 7, 150, 40);
        vacateLabel.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 17));

        transactionLabel.setBounds(65, 7, 150, 40);
        transactionLabel.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 17));

        apartmentLabel.setBounds(65, 7, 150, 40);
        apartmentLabel.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 17));

        // Designing exit button
        exitButton.setBounds(35, 550, 140, 50);
        exitButton.setFocusable(false);
        exitButton.setText("Quit");
        exitButton.setBackground(new Color(239, 235, 226));
        exitButton.setFont(loadCustomFont(fontFilepath, Font.PLAIN, 17));

        // Setting positions for icons
        icon.setBounds(13, 10, 35, 35);
        logo.setBounds(50, 10, 110, 110);
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

    // Method to retrieve a ColoredPanel by its name
    private ColoredPanel getColoredPanelByName(String name) {
        Component[] components = leftPanel.getComponents();
        for (Component component : components) {
            if (component instanceof ColoredPanel) {
                ColoredPanel coloredPanel = (ColoredPanel) component;
                if (coloredPanel.panelName.equals(name)) {
                    return coloredPanel;
                }
            }
        }
        return null;
    }

    // Main method to launch the application
    public static void main(String[] args) {
        new MainFrame();
    }

    // Inner class representing a colored panel with click handling
    private class ColoredPanel extends RoundedPanel implements MouseListener {
        private final String panelName;
        private boolean isSelected = false;

        // Constructor for ColoredPanel
        public ColoredPanel(int radius, String panelName, Color panelColor, int posX, int posY) {
            super(radius);
            this.panelName = panelName;

            setPreferredSize(new Dimension(190, 60));
            setBackground(panelColor);
            setBounds(posX, posY, 190, 55);

            addMouseListener(this);
        }

        // Event handler for mouse click
        @Override
        public void mouseClicked(MouseEvent e) {
            handlePanelClick();
        }

        // Event handler for mouse enter
        @Override
        public void mouseEntered(MouseEvent e) {
            if (!isSelected) {
                setBackground(new Color(229 - 20, 193 - 20, 205 - 20));
            }
        }

        // Event handler for mouse exit
        @Override
        public void mouseExited(MouseEvent e) {
            if (!isSelected) {
                setBackground(new Color(229, 193, 205));
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        // Method to handle panel click
        private void handlePanelClick() {
            resetPanelColors();
            setBackground(new Color(229 - 20, 193 - 20, 205 - 20));

            isSelected = true;

            switch (panelName) {
                case "Dashboard":
                    showDashboardPanel();
                    dashboard.add(icon);
                    break;

                case "Add":
                    showAddPanel();
                    add.add(icon);
                    break;

                case "Remove":
                    showRemovePanel();
                    remove.add(icon);
                    break;

                case "Lease":
                    showLeasePanel();
                    lease.add(icon);
                    break;

                case "Vacate":
                    showVacatePanel();
                    vacate.add(icon);
                    break;

                case "Transaction":
                    showTransactionPanel();
                    transaction.add(icon);
                    break;

                case "Apartments":
                    showApartmentsPanel();
                    apartments.add(icon);
                    break;
            }
        }

        // Method to reset panel colors
        private void resetPanelColors() {
            Component[] components = leftPanel.getComponents();
            for (Component component : components) {
                if (component instanceof ColoredPanel) {
                    ColoredPanel coloredPanel = (ColoredPanel) component;
                    coloredPanel.setBackground(new Color(229, 193, 205));
                    coloredPanel.isSelected = false;
                }
            }
        }

        // Methods to show different functionality panels
        private void showDashboardPanel() {
            DashboardPanel panel = new DashboardPanel(20);
            panel.setBounds(10, 10, 599, 605);
            setRightPanel(panel);
        }

        private void showAddPanel() {
            AddPanel panel = new AddPanel(20);
            panel.setBounds(10, 10, 599, 605);
            setRightPanel(panel);
        }

        private void showRemovePanel() {
            RemovePanel panel = new RemovePanel(20);
            panel.setBounds(10, 10, 599, 605);
            setRightPanel(panel);
        }

        private void showLeasePanel() {
            LeasePanel panel = new LeasePanel(20);
            panel.setBounds(10, 10, 599, 605);
            setRightPanel(panel);
        }

        private void showVacatePanel() {
            VacatePanel panel = new VacatePanel(20);
            panel.setBounds(10, 10, 599, 605);
            setRightPanel(panel);
        }

        private void showTransactionPanel() {
            TransactionPanel panel = new TransactionPanel(20);
            panel.setBounds(10, 10, 599, 605);
            setRightPanel(panel);
        }

        private void showApartmentsPanel() {
            ApartmentsPanel panel = new ApartmentsPanel(20);
            panel.setBounds(10, 10, 599, 605);
            setRightPanel(panel);
        }

        // Method to set the right panel content
        private void setRightPanel(JPanel newPanel) {
            rightPanel.removeAll();
            rightPanel.add(newPanel);
            rightPanel.repaint();
            rightPanel.revalidate();
        }        
    }

    // Event handler for the exit button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            String choices[] = {"Logout", "Exit"};

            // Displaying an option dialog for logout or exit
            int result = JOptionPane.showOptionDialog(
                    null,
                    "Do you want to logout or exit?",
                    "Select an option",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    choices,
                    0
            );

            // Handling the chosen option
            if (result == 0) {
                // Logging out and opening the signup/signin window
                dispose();
                UserPass userPass = new UserPass();
                new SignupSignin(userPass);
            } else if (result == 1) {
                // Exiting the application
                dispose();
            }
        }
    }    
}

