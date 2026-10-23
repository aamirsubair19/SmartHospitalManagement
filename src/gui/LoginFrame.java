package gui;
import model.User;
import service.UserService;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private UserService userService;

    // =========================================================
    // COLORS
    // =========================================================

    private final Color BACKGROUND = new Color(242, 246, 251);
    private final Color CARD = Color.WHITE;
    private final Color PRIMARY_BLUE = new Color(25, 118, 210);
    private final Color DARK_BLUE = new Color(13, 71, 161);
    private final Color LIGHT_BLUE = new Color(66, 165, 245);
    private final Color CYAN = new Color(79, 195, 247);
    private final Color TEXT = new Color(35, 45, 55);
    private final Color SECONDARY_TEXT = new Color(100, 110, 120);
    private final Color BORDER = new Color(215, 222, 230);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public LoginFrame() {

        userService = new UserService();
        setTitle("XYZ HOSPITAL - Login");
        setSize(520, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        createGUI();
    }

    // =========================================================
    // CREATE GUI
    // =========================================================

    private void createGUI() {

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setBackground(BACKGROUND);

        mainPanel.setBorder(
                new EmptyBorder(20, 35, 25, 35)
        );


        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(
                new BoxLayout(headerPanel, BoxLayout.Y_AXIS)
        );
        headerPanel.setBackground(DARK_BLUE);
        headerPanel.setBorder(
                new EmptyBorder(20, 15, 20, 15)
        );
        // Hospital icon
        JLabel hospitalIcon = new JLabel("✚");
        hospitalIcon.setFont(
                new Font("Segoe UI", Font.BOLD, 36)
        );
        hospitalIcon.setForeground(Color.WHITE);
        hospitalIcon.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );
        // Hospital name
        JLabel hospitalName = new JLabel(
                "XYZ HOSPITAL"
        );
        hospitalName.setFont(
                new Font("Segoe UI", Font.BOLD, 27)
        );
        hospitalName.setForeground(Color.WHITE);

        hospitalName.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );
        // System name
        JLabel systemName = new JLabel(
                "Smart Hospital Management System"
        );
        systemName.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );
        systemName.setForeground(
                new Color(210, 235, 255)
        );
        systemName.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );
        // Staff login
        JLabel loginTitle = new JLabel(
                "STAFF LOGIN"
        );
        loginTitle.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );
        loginTitle.setForeground(CYAN);
        loginTitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );
        // Add header components
        headerPanel.add(hospitalIcon);
        headerPanel.add(
                Box.createVerticalStrut(2)
        );
        headerPanel.add(hospitalName);
        headerPanel.add(
                Box.createVerticalStrut(3)
        );
        headerPanel.add(systemName);
        headerPanel.add(
                Box.createVerticalStrut(12)
        );
        headerPanel.add(loginTitle);

        // =====================================================
        // LOGIN CARD
        // =====================================================

        JPanel card = new JPanel();
        card.setLayout(
                new BoxLayout(card, BoxLayout.Y_AXIS)
        );
        card.setBackground(CARD);
        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER,
                                1
                        ),

                        new EmptyBorder(
                                30,
                                45,
                                30,
                                45
                        )
                )
        );

        // =====================================================
        // WELCOME TEXT
        // =====================================================

        JLabel welcomeLabel = new JLabel(
                "Welcome Back"
        );
        welcomeLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 24)
        );
        welcomeLabel.setForeground(TEXT);
        welcomeLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );
        JLabel instructionLabel = new JLabel(
                "Please enter your staff credentials"
        );
        instructionLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );
        instructionLabel.setForeground(
                SECONDARY_TEXT
        );
        instructionLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );
        card.add(welcomeLabel);
        card.add(
                Box.createVerticalStrut(5)
        );
        card.add(instructionLabel);
        card.add(
                Box.createVerticalStrut(28)
        );

        // =====================================================
        // USERNAME LABEL
        // =====================================================

        JLabel usernameLabel = new JLabel(
                "Username"
        );
        usernameLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );
        usernameLabel.setForeground(TEXT);
        usernameLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // Username field
        usernameField = new JTextField();
        usernameField.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );
        usernameField.setPreferredSize(
                new Dimension(320, 42)
        );
        usernameField.setMaximumSize(
                new Dimension(320, 42)
        );
        usernameField.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );
        usernameField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER,
                                1
                        ),

                        new EmptyBorder(
                                5,
                                12,
                                5,
                                12
                        )
                )
        );
        card.add(usernameLabel);
        card.add(
                Box.createVerticalStrut(7)
        );

        card.add(usernameField);

        // =====================================================
        // PASSWORD LABEL
        // =====================================================

        card.add(
                Box.createVerticalStrut(18)
        );

        JLabel passwordLabel = new JLabel(
                "Password"
        );

        passwordLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        passwordLabel.setForeground(TEXT);

        passwordLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // Password field
        passwordField = new JPasswordField();

        passwordField.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );

        passwordField.setPreferredSize(
                new Dimension(320, 42)
        );

        passwordField.setMaximumSize(
                new Dimension(320, 42)
        );

        passwordField.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        passwordField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER,
                                1
                        ),

                        new EmptyBorder(
                                5,
                                12,
                                5,
                                12
                        )
                )
        );

        card.add(passwordLabel);
        card.add(
                Box.createVerticalStrut(7)
        );
        card.add(passwordField);

        // =====================================================
        // BUTTONS
        // =====================================================

        card.add(
                Box.createVerticalStrut(25)
        );

        JPanel buttonPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        12,
                        0
                )
        );

        buttonPanel.setBackground(CARD);
        buttonPanel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // LOGIN BUTTON
        JButton loginButton = new JButton(
                "LOGIN"
        );

        loginButton.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(
                PRIMARY_BLUE
        );

        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setPreferredSize(
                new Dimension(125, 42)
        );
        loginButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        // CLEAR BUTTON
        JButton clearButton = new JButton(
                "CLEAR"
        );

        clearButton.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );

        clearButton.setForeground(TEXT);
        clearButton.setBackground(
                new Color(235, 239, 244)
        );

        clearButton.setFocusPainted(false);
        clearButton.setBorderPainted(false);
        clearButton.setPreferredSize(
                new Dimension(125, 42)
        );

        clearButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );
        buttonPanel.add(loginButton);
        buttonPanel.add(clearButton);
        card.add(buttonPanel);

        // =====================================================
        // FOOTER TEXT
        // =====================================================

        card.add(
                Box.createVerticalStrut(22)
        );

        JLabel footerLabel = new JLabel(
                "Authorized hospital staff only"
        );

        footerLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 12)
        );

        footerLabel.setForeground(
                SECONDARY_TEXT
        );

        footerLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );
        card.add(footerLabel);

        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        loginButton.addActionListener(
                e -> login()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        // Press Enter inside password field
        passwordField.addActionListener(
                e -> login()
        );

        // =====================================================
        // CARD CONTAINER
        // =====================================================

        JPanel cardContainer = new JPanel(
                new BorderLayout()
        );

        cardContainer.setBackground(
                BACKGROUND
        );

        cardContainer.setBorder(
                new EmptyBorder(
                        22,
                        0,
                        0,
                        0
                )
        );

        cardContainer.add(
                card,
                BorderLayout.CENTER
        );

        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                cardContainer,
                BorderLayout.CENTER
        );
        add(mainPanel);
    }

    // =========================================================
    // LOGIN
    // =========================================================

    private void login() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                ).trim();

        // Check empty fields
        if (username.isEmpty() ||
                password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter both username and password.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // Authenticate user
        User user =
                userService.authenticate(
                        username,
                        password
                );

        // Successful login
        if (user != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login successful!\nWelcome, "
                            + user.getUsername()
                            + ".",
                    "Login Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );
            openDashboard(user);
        }

        // Failed login
        else {
            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
            passwordField.setText("");
            passwordField.requestFocus();
        }
    }

    // =========================================================
    // ROLE BASED DASHBOARD
    // =========================================================

    private void openDashboard(User user) {

        String role =
                user.getRole().toUpperCase();

        switch (role) {
            case "RECEPTION":
                new ReceptionFrame()
                        .setVisible(true);
                dispose();
                break;

            case "DOCTOR":
                new DoctorDashboardFrame(user).setVisible(true);
                dispose();
                break;

            case "PHARMACY":
                new PharmacyDashboardFrame()
                        .setVisible(true);
                dispose();
                break;

            case "AMBULANCE":
                new AmbulanceDashboardFrame()
                        .setVisible(true);
                dispose();
                break;

            case "CASUALTY":
                new CasualtyDashboardFrame()
                        .setVisible(true);
                dispose();
                break;

            default:
                JOptionPane.showMessageDialog(
                        this,
                        "Unknown user role: "
                                + user.getRole(),
                        "Access Error",
                        JOptionPane.ERROR_MESSAGE
                );
        }
    }

    // =========================================================
    // CLEAR FIELDS
    // =========================================================

    private void clearFields() {
        usernameField.setText("");
        passwordField.setText("");
        usernameField.requestFocus();
    }

    // =========================================================
    // MAIN METHOD
    // =========================================================

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame frame =
                    new LoginFrame();
            frame.setVisible(true);
        });
    }
}