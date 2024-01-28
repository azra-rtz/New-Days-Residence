// Package declaration for the login system
package NDRESIDENCES.LOGINSYSTEM;

// Import statements
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import NDRESIDENCES.COMPONENTS.Button;
import NDRESIDENCES.COMPONENTS.Page;
import NDRESIDENCES.COMPONENTS.RoundedPanel;
import NDRESIDENCES.COMPONENTS.RoundedPasswordField;
import NDRESIDENCES.COMPONENTS.RoundedTextField;

// Class representing the signup and signin functionality
public class SignupSignin implements ActionListener, KeyListener {

    // Instance variable for user and password information
    private UserPass userPass;

    // JFrame and components
    private JFrame frame = new JFrame();
    private JButton loginButton = new Button();
    private JButton resetButton = new Button();
    private JButton switch2Signup = new Button();
    private JButton switch2Signin = new Button();
    private JButton registerButton = new Button();
    private RoundedPanel loginPanel = new RoundedPanel(20);
    private RoundedPanel registerPanel = new RoundedPanel(20);
    private RoundedPanel signinSidePanel = new RoundedPanel(20);
    private RoundedPanel signupSidePanel = new RoundedPanel(20);
    private JPanel outerBorder = new JPanel();
    private RoundedTextField userField = new RoundedTextField();
    private RoundedPasswordField passField = new RoundedPasswordField();
    private JLabel signIn = new JLabel("Sign In");
    private JLabel createAccount = new JLabel("Create Account");
    private JLabel userLabel = new JLabel("Username:");
    private JLabel passLabel = new JLabel("Password:");
    private JLabel signupLabel = new JLabel("<html><center>New here? Join us to manage<br>rentals, track leases, and more.<br>Create your account now!</center></html>");
    private JLabel signinLabel = new JLabel("<html><center>Returning user? Sign in to check<br>on your rented properties, track<br>expenses, and stay organized.</center></html>");
    private ImageIcon winImage = new ImageIcon("NDRESIDENCES\\ASSETS\\LOGO\\LOGO.png");
    private ImageIcon logoImg1 = new ImageIcon(new ImageIcon("NDRESIDENCES\\ASSETS\\LOGO\\LOGO_WHITE.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
    private ImageIcon logoImg2 = new ImageIcon(new ImageIcon("NDRESIDENCES\\ASSETS\\LOGO\\LOGO_BLACK.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
    private JLabel logoWhite = new JLabel(logoImg1);
    private JLabel logoBlack = new JLabel(logoImg2);
    private int mouseX, mouseY;

    // Font filepaths
    private String fontPlain = "NDRESIDENCES\\ASSETS\\FONTS\\Hero-Regular.ttf";
    private String fontBold = "NDRESIDENCES\\ASSETS\\FONTS\\Hero-Bold.ttf";

    // Constructor for SignupSignin class
    public SignupSignin(UserPass userPass) {
        this.userPass = userPass;
        setupComponents();
        designComponents();

        // JFrame configuration
        frame.setIconImage(winImage.getImage());
        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.setSize(600, 420);
        frame.setUndecorated(true);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setShape(new RoundRectangle2D.Double(0, 0, frame.getWidth(), frame.getHeight(), 40, 40));

        // Mouse listeners for frame dragging
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen() - mouseX;
                int y = e.getYOnScreen() - mouseY;
                frame.setLocation(x, y);
            }
        });

        // UI Manager settings for JOptionPane and panels
        UIManager.put("OptionPane.background", new Color(229, 193, 205));
        UIManager.put("Panel.background", new Color(229, 193, 205));
        UIManager.put("TitleBar.background", new Color(229, 193, 205));
    }

    // Method to set up GUI components
    private void setupComponents() {
        frame.add(outerBorder);

        outerBorder.setLayout(null);
        outerBorder.add(loginPanel);
        outerBorder.add(registerPanel);

        loginPanel.setLayout(null);
        loginPanel.add(resetButton);
        loginPanel.add(loginButton);
        loginPanel.add(userField);
        loginPanel.add(passField);
        loginPanel.add(signinSidePanel);
        loginPanel.add(signIn);
        loginPanel.add(userLabel);
        loginPanel.add(passLabel);

        signinSidePanel.setLayout(null);
        signinSidePanel.add(switch2Signup);
        signinSidePanel.add(logoWhite);
        signinSidePanel.add(signupLabel);

        signupSidePanel.setLayout(null);
        signupSidePanel.add(switch2Signin);
        signupSidePanel.add(logoBlack);
        signupSidePanel.add(signinLabel);

        resetButton.addActionListener(this);
        loginButton.addActionListener(this);
        switch2Signup.addActionListener(this);
        switch2Signin.addActionListener(this);
        registerButton.addActionListener(this);
    }

    // Method to design the appearance of GUI components
    private void designComponents() {
        outerBorder.setBackground(new Color(229, 193, 205));
        outerBorder.setBounds(0, 0, 600, 420);

        loginPanel.setBackground(new Color(239, 235, 226));
        loginPanel.setBounds(5, 5, 590, 410);

        registerPanel.setBackground(new Color(239, 235, 226));
        registerPanel.setBounds(5, 5, 590, 410);
        registerPanel.setVisible(false);

        signinSidePanel.setBackground(new Color(229, 193, 205));
        signinSidePanel.setBounds(20, 20, 275, 368);

        signupSidePanel.setBackground(new Color(229, 193, 205));
        signupSidePanel.setBounds(20, 20, 275, 368);

        switch2Signup.setBounds(85, 300, 100, 40);
        switch2Signup.setFocusable(false);
        switch2Signup.setText("Sign Up");
        switch2Signup.setBackground(new Color(239, 235, 226));
        switch2Signup.setFont(loadCustomFont(fontPlain, Font.PLAIN, 16));

        switch2Signin.setBounds(85, 300, 100, 40);
        switch2Signin.setFocusable(false);
        switch2Signin.setText("Sign In");
        switch2Signin.setBackground(new Color(239, 235, 226));
        switch2Signin.setFont(loadCustomFont(fontPlain, Font.PLAIN, 16));

        resetButton.setBounds(330, 320, 100, 40);
        resetButton.setFocusable(false);
        resetButton.setText("Clear");
        resetButton.setBackground(new Color(229, 193, 205));
        resetButton.setFont(loadCustomFont(fontPlain, Font.PLAIN, 16));

        loginButton.setBounds(450, 320, 100, 40);
        loginButton.setFocusable(false);
        loginButton.setText("Sign in");
        loginButton.setBackground(new Color(229, 193, 205));
        loginButton.setFont(loadCustomFont(fontPlain, Font.PLAIN, 16));

        registerButton.setBounds(450, 320, 100, 40);
        registerButton.setFocusable(false);
        registerButton.setText("Sign up");
        registerButton.setBackground(new Color(229, 193, 205));
        registerButton.setFont(loadCustomFont(fontPlain, Font.PLAIN, 16));

        signIn.setBounds(410, 30, 100, 25);
        signIn.setFont(loadCustomFont(fontBold, Font.PLAIN, 20));

        createAccount.setBounds(370, 30, 200, 25);
        createAccount.setFont(loadCustomFont(fontBold, Font.PLAIN, 20));

        userField.setBounds(330, 110, 220, 40);
        userField.setFont(loadCustomFont(fontPlain, Font.PLAIN, 15));
        userField.setBackground(new Color(229, 193, 205));
        userField.setBorderColor(new Color(229 - 20, 193 - 20, 205 - 20));

        passField.setBounds(330, 190, 220, 40);
        passField.setFont(loadCustomFont(fontPlain, Font.PLAIN, 15));
        passField.setBackground(new Color(229, 193, 205));
        passField.setBorderColor(new Color(229 - 20, 193 - 20, 205 - 20));

        userLabel.setBounds(330, 80, 80, 30);
        userLabel.setFont(loadCustomFont(fontPlain, Font.PLAIN, 14));

        passLabel.setBounds(330, 160, 80, 30);
        passLabel.setFont(loadCustomFont(fontPlain, Font.PLAIN, 14));

        logoWhite.setBounds(60, -10, 150, 150);
        logoBlack.setBounds(60, -10, 150, 150);

        signupLabel.setBounds(32, 20, 270, 260);
        signupLabel.setFont(loadCustomFont(fontPlain, Font.PLAIN, 14));

        signinLabel.setBounds(26, 20, 270, 260);
        signinLabel.setFont(loadCustomFont(fontPlain, Font.PLAIN, 14));
    }

    // ActionListener method to handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            userField.setText("");
            passField.setText("");
        }

        if (e.getSource() == loginButton) {
            if (userPass.getLoginInfo().containsKey(userField.getText())) {
                if (userPass.getLoginInfo().get(userField.getText()).equals(String.valueOf(passField.getPassword()))) {
                    // If login is successful, open the loading page
                    Page page = new Page(20);
                    page.saveStringToFile(userField.getText());
                    frame.dispose();
                    new LoadingPage();

                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect password.", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == registerButton) {
            String username = userField.getText();
            String password = String.valueOf(passField.getPassword());

            if (!username.isEmpty() && !password.isEmpty()) {
                if (userPass.getLoginInfo().containsKey(username)) {
                    JOptionPane.showMessageDialog(null, "Username already exists.", "Registration Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    // If registration is successful, update the UserPass instance
                    userPass.registerUser(username, password);
                    JOptionPane.showMessageDialog(null, "Registration successful.", "Registration success", JOptionPane.INFORMATION_MESSAGE);
                    userField.setText("");
                    passField.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter both username and password.", "Registration Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if (e.getSource() == switch2Signup) {
            // Switch to the signup panel
            registerPanel.setLayout(null);
            registerPanel.add(resetButton);
            registerPanel.add(registerButton);
            registerPanel.add(userField);
            registerPanel.add(passField);
            registerPanel.add(signupSidePanel);
            registerPanel.add(createAccount);
            registerPanel.add(userLabel);
            registerPanel.add(passLabel);

            loginPanel.setVisible(!loginPanel.isVisible());
            registerPanel.setVisible(!registerPanel.isVisible());
        }

        if (e.getSource() == switch2Signin) {
            // Switch to the signin panel
            loginPanel.setLayout(null);
            loginPanel.add(resetButton);
            loginPanel.add(loginButton);
            loginPanel.add(userField);
            loginPanel.add(passField);
            loginPanel.add(signinSidePanel);
            loginPanel.add(signIn);
            loginPanel.add(userLabel);
            loginPanel.add(passLabel);

            registerPanel.setVisible(!registerPanel.isVisible());
            loginPanel.setVisible(!loginPanel.isVisible());
        }
    }

    // KeyListener method to handle key events
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_ESCAPE) {
            // Prompt user for confirmation before exiting
            String choices[] = {"No", "Exit"};

            int result = JOptionPane.showOptionDialog(
                    null,
                    "Would you like to exit the program?",
                    "Select an option",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    choices,
                    0
            );

            if (result == 1) {
                frame.dispose();
            }
        }
    }

    // KeyListener methods not used, implemented to satisfy the interface
    @Override
    public void keyTyped(KeyEvent e) {
        // Implement if needed
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Implement if needed
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
}
