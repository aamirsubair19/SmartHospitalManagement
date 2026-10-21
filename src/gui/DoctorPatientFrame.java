package gui;

import model.Patient;
import service.PatientService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DoctorPatientFrame extends JFrame {

    private final Color BACKGROUND = new Color(242, 246, 251);
    private final Color WHITE = Color.WHITE;

    private final Color DARK_BLUE = new Color(13, 71, 161);
    private final Color PRIMARY_BLUE = new Color(25, 118, 210);
    private final Color GREEN = new Color(46, 125, 50);

    private final Color TEXT = new Color(35, 45, 55);
    private final Color SECONDARY_TEXT = new Color(100, 110, 120);
    private final Color BORDER = new Color(215, 222, 230);

    private JTextField patientIdField;

    private JLabel patientIdValue;
    private JLabel nameValue;
    private JLabel ageValue;
    private JLabel genderValue;
    private JLabel phoneValue;
    private JLabel addressValue;

    private PatientService patientService;

    public DoctorPatientFrame() {

        patientService = new PatientService();

        setTitle("XYZ HOSPITAL - Patient Information");
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        createGUI();
    }

    private void createGUI() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND);

        // =========================
        // HEADER
        // =========================

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(DARK_BLUE);
        headerPanel.setBorder(
                new EmptyBorder(18, 25, 18, 25)
        );

        JPanel hospitalPanel = new JPanel();
        hospitalPanel.setLayout(
                new BoxLayout(hospitalPanel, BoxLayout.Y_AXIS)
        );
        hospitalPanel.setBackground(DARK_BLUE);

        JLabel hospitalName =
                new JLabel("✚  XYZ HOSPITAL");

        hospitalName.setFont(
                new Font("Segoe UI", Font.BOLD, 25)
        );
        hospitalName.setForeground(Color.WHITE);

        JLabel systemName =
                new JLabel("Smart Hospital Management System");

        systemName.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );
        systemName.setForeground(
                new Color(205, 230, 250)
        );

        hospitalPanel.add(hospitalName);
        hospitalPanel.add(
                Box.createVerticalStrut(3)
        );
        hospitalPanel.add(systemName);

        JLabel portalLabel =
                new JLabel("DOCTOR PORTAL");

        portalLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );
        portalLabel.setForeground(
                new Color(129, 212, 250)
        );

        headerPanel.add(
                hospitalPanel,
                BorderLayout.WEST
        );

        headerPanel.add(
                portalLabel,
                BorderLayout.EAST
        );

        // =========================
        // CONTENT
        // =========================

        JPanel contentPanel =
                new JPanel(new BorderLayout());

        contentPanel.setBackground(BACKGROUND);

        contentPanel.setBorder(
                new EmptyBorder(25, 35, 25, 35)
        );

        // =========================
        // TITLE
        // =========================

        JPanel titlePanel = new JPanel();

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        titlePanel.setBackground(BACKGROUND);

        JLabel title =
                new JLabel("Patient Information");

        title.setFont(
                new Font("Segoe UI", Font.BOLD, 27)
        );
        title.setForeground(TEXT);

        JLabel subtitle =
                new JLabel(
                        "Search and view patient details"
                );

        subtitle.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );
        subtitle.setForeground(SECONDARY_TEXT);

        titlePanel.add(title);
        titlePanel.add(
                Box.createVerticalStrut(4)
        );
        titlePanel.add(subtitle);

        contentPanel.add(
                titlePanel,
                BorderLayout.NORTH
        );

        // =========================
        // SEARCH PANEL
        // =========================

        JPanel searchPanel =
                new JPanel(new FlowLayout(
                        FlowLayout.LEFT,
                        10,
                        20
                ));

        searchPanel.setBackground(WHITE);

        searchPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                10, 15, 10, 15
                        )
                )
        );

        JLabel searchLabel =
                new JLabel("Patient ID:");

        searchLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 14)
        );
        searchLabel.setForeground(TEXT);

        patientIdField =
                new JTextField();

        patientIdField.setPreferredSize(
                new Dimension(180, 38)
        );

        patientIdField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        JButton searchButton =
                new JButton("SEARCH PATIENT");

        searchButton.setPreferredSize(
                new Dimension(170, 38)
        );

        searchButton.setFont(
                new Font("Segoe UI", Font.BOLD, 12)
        );

        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(PRIMARY_BLUE);
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);

        searchButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        searchButton.addActionListener(
                e -> searchPatient()
        );

        searchPanel.add(searchLabel);
        searchPanel.add(patientIdField);
        searchPanel.add(searchButton);

        contentPanel.add(
                searchPanel,
                BorderLayout.CENTER
        );

        // =========================
        // PATIENT DETAILS
        // =========================

        JPanel detailsPanel =
                new JPanel(new GridLayout(
                        6, 2, 15, 15
                ));

        detailsPanel.setBackground(WHITE);

        detailsPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                25, 30, 25, 30
                        )
                )
        );

        patientIdValue =
                createValueLabel();

        nameValue =
                createValueLabel();

        ageValue =
                createValueLabel();

        genderValue =
                createValueLabel();

        phoneValue =
                createValueLabel();

        addressValue =
                createValueLabel();

        detailsPanel.add(
                createLabel("Patient ID")
        );
        detailsPanel.add(patientIdValue);

        detailsPanel.add(
                createLabel("Name")
        );
        detailsPanel.add(nameValue);

        detailsPanel.add(
                createLabel("Age")
        );
        detailsPanel.add(ageValue);

        detailsPanel.add(
                createLabel("Gender")
        );
        detailsPanel.add(genderValue);

        detailsPanel.add(
                createLabel("Phone")
        );
        detailsPanel.add(phoneValue);

        detailsPanel.add(
                createLabel("Address")
        );
        detailsPanel.add(addressValue);

        JPanel detailsContainer =
                new JPanel(new BorderLayout());

        detailsContainer.setBackground(
                BACKGROUND
        );

        detailsContainer.setBorder(
                new EmptyBorder(20, 0, 0, 0)
        );

        detailsContainer.add(
                detailsPanel,
                BorderLayout.CENTER
        );

        contentPanel.add(
                detailsContainer,
                BorderLayout.SOUTH
        );

        // =========================
        // FOOTER
        // =========================

        JPanel footerPanel =
                new JPanel(new BorderLayout());

        footerPanel.setBackground(WHITE);

        footerPanel.setBorder(
                BorderFactory.createMatteBorder(
                        1, 0, 0, 0, BORDER
                )
        );

        JLabel statusLabel =
                new JLabel("● PATIENT DATABASE");

        statusLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 12)
        );

        statusLabel.setForeground(GREEN);

        statusLabel.setBorder(
                new EmptyBorder(
                        10, 25, 10, 25
                )
        );

        JButton closeButton =
                new JButton("CLOSE");

        closeButton.setFont(
                new Font("Segoe UI", Font.BOLD, 12)
        );

        closeButton.setForeground(
                new Color(180, 40, 40)
        );

        closeButton.setBackground(WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setBorderPainted(false);

        closeButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        closeButton.addActionListener(
                e -> dispose()
        );

        footerPanel.add(
                statusLabel,
                BorderLayout.WEST
        );

        footerPanel.add(
                closeButton,
                BorderLayout.EAST
        );

        // =========================
        // ADD EVERYTHING
        // =========================

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                footerPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);
    }

    // =========================
    // SEARCH PATIENT
    // =========================

    private void searchPatient() {

        String idText =
                patientIdField.getText().trim();

        if (idText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a Patient ID.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int patientId;

        try {

            patientId =
                    Integer.parseInt(idText);

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Patient ID must be a number.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Patient patient =
                patientService.searchPatient(patientId);

        if (patient == null) {

            clearPatientDetails();

            JOptionPane.showMessageDialog(
                    this,
                    "Patient not found.",
                    "Search Result",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        patientIdValue.setText(
                String.valueOf(
                        patient.getPatientId()
                )
        );

        nameValue.setText(
                patient.getName()
        );

        ageValue.setText(
                String.valueOf(
                        patient.getAge()
                )
        );

        genderValue.setText(
                patient.getGender()
        );

        phoneValue.setText(
                patient.getPhone()
        );

        addressValue.setText(
                patient.getAddress()
        );
    }

    // =========================
    // CLEAR DETAILS
    // =========================

    private void clearPatientDetails() {

        patientIdValue.setText("-");
        nameValue.setText("-");
        ageValue.setText("-");
        genderValue.setText("-");
        phoneValue.setText("-");
        addressValue.setText("-");
    }

    // =========================
    // LABEL CREATION
    // =========================

    private JLabel createLabel(String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        label.setForeground(TEXT);

        return label;
    }

    private JLabel createValueLabel() {

        JLabel label =
                new JLabel("-");

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        label.setForeground(
                SECONDARY_TEXT
        );

        return label;
    }

    // =========================
    // TEST MAIN
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            DoctorPatientFrame frame =
                    new DoctorPatientFrame();

            frame.setVisible(true);
        });
    }
}