package gui;

import model.Prescription;
import service.PrescriptionService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DoctorPrescriptionFrame extends JFrame {

    private final Color BACKGROUND = new Color(242, 246, 251);
    private final Color WHITE = Color.WHITE;

    private final Color DARK_BLUE = new Color(13, 71, 161);
    private final Color PRIMARY_BLUE = new Color(25, 118, 210);
    private final Color GREEN = new Color(46, 125, 50);
    private final Color PURPLE = new Color(123, 31, 162);

    private final Color TEXT = new Color(35, 45, 55);
    private final Color SECONDARY_TEXT = new Color(100, 110, 120);
    private final Color BORDER = new Color(215, 222, 230);

    private JTextField consultationIdField;
    private JTextField patientIdField;
    private JTextField doctorIdField;
    private JTextField medicineIdField;

    private JTextField dosageField;
    private JTextField durationField;

    private JTextArea instructionsArea;

    private PrescriptionService prescriptionService;

    public DoctorPrescriptionFrame() {

        prescriptionService = new PrescriptionService();

        setTitle("XYZ HOSPITAL - Prescription");
        setSize(900, 700);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);
        setResizable(false);

        createGUI();
    }

    private void createGUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(BACKGROUND);

        // =========================
        // HEADER
        // =========================

        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setBackground(DARK_BLUE);

        headerPanel.setBorder(
                new EmptyBorder(
                        18,
                        25,
                        18,
                        25
                )
        );

        JPanel hospitalPanel =
                new JPanel();

        hospitalPanel.setLayout(
                new BoxLayout(
                        hospitalPanel,
                        BoxLayout.Y_AXIS
                )
        );

        hospitalPanel.setBackground(DARK_BLUE);

        JLabel hospitalName =
                new JLabel("✚  XYZ HOSPITAL");

        hospitalName.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        25
                )
        );

        hospitalName.setForeground(Color.WHITE);

        JLabel systemName =
                new JLabel(
                        "Smart Hospital Management System"
                );

        systemName.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
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
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
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
                new EmptyBorder(
                        20,
                        30,
                        20,
                        30
                )
        );

        // =========================
        // TITLE
        // =========================

        JPanel titlePanel =
                new JPanel();

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        titlePanel.setBackground(BACKGROUND);

        JLabel title =
                new JLabel("Create Prescription");

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        27
                )
        );

        title.setForeground(TEXT);

        JLabel subtitle =
                new JLabel(
                        "Create and save a prescription for a consulted patient"
                );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(
                SECONDARY_TEXT
        );

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
        // FORM
        // =========================

        JPanel formPanel =
                new JPanel();

        formPanel.setLayout(
                new BoxLayout(
                        formPanel,
                        BoxLayout.Y_AXIS
                )
        );

        formPanel.setBackground(WHITE);

        formPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                25,
                                25,
                                25,
                                25
                        )
                )
        );

        // =========================
        // ID SECTION
        // =========================

        JPanel idPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                15,
                                5
                        )
                );

        idPanel.setBackground(WHITE);

        consultationIdField =
                new JTextField();

        patientIdField =
                new JTextField();

        doctorIdField =
                new JTextField();

        medicineIdField =
                new JTextField();

        idPanel.add(
                createFieldPanel(
                        "Consultation ID",
                        consultationIdField
                )
        );

        idPanel.add(
                createFieldPanel(
                        "Patient ID",
                        patientIdField
                )
        );

        idPanel.add(
                createFieldPanel(
                        "Doctor ID",
                        doctorIdField
                )
        );

        idPanel.add(
                createFieldPanel(
                        "Medicine ID",
                        medicineIdField
                )
        );

        formPanel.add(idPanel);

        formPanel.add(
                Box.createVerticalStrut(25)
        );

        // =========================
        // DOSAGE + DURATION
        // =========================

        JPanel medicineInfoPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                15,
                                5
                        )
                );

        medicineInfoPanel.setBackground(WHITE);

        dosageField =
                new JTextField();

        durationField =
                new JTextField();

        medicineInfoPanel.add(
                createFieldPanel(
                        "Dosage",
                        dosageField
                )
        );

        medicineInfoPanel.add(
                createFieldPanel(
                        "Duration",
                        durationField
                )
        );

        formPanel.add(medicineInfoPanel);

        formPanel.add(
                Box.createVerticalStrut(20)
        );

        // =========================
        // INSTRUCTIONS
        // =========================

        instructionsArea =
                createTextArea();

        formPanel.add(
                createTextAreaPanel(
                        "Instructions",
                        instructionsArea
                )
        );

        JPanel formContainer =
                new JPanel(new BorderLayout());

        formContainer.setBackground(BACKGROUND);

        formContainer.setBorder(
                new EmptyBorder(
                        20,
                        0,
                        0,
                        0
                )
        );

        formContainer.add(
                formPanel,
                BorderLayout.CENTER
        );

        contentPanel.add(
                formContainer,
                BorderLayout.CENTER
        );

        // =========================
        // BUTTONS
        // =========================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                15
                        )
                );

        buttonPanel.setBackground(BACKGROUND);

        JButton clearButton =
                new JButton("CLEAR");

        clearButton.setPreferredSize(
                new Dimension(
                        110,
                        40
                )
        );

        clearButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        clearButton.setForeground(TEXT);

        clearButton.setBackground(WHITE);

        clearButton.setFocusPainted(false);

        clearButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        clearButton.addActionListener(
                e -> clearForm()
        );

        JButton saveButton =
                new JButton("SAVE PRESCRIPTION");

        saveButton.setPreferredSize(
                new Dimension(
                        190,
                        40
                )
        );

        saveButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        saveButton.setForeground(Color.WHITE);

        saveButton.setBackground(PURPLE);

        saveButton.setFocusPainted(false);

        saveButton.setBorderPainted(false);

        saveButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        saveButton.addActionListener(
                e -> savePrescription()
        );

        buttonPanel.add(clearButton);
        buttonPanel.add(saveButton);

        contentPanel.add(
                buttonPanel,
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
                        1,
                        0,
                        0,
                        0,
                        BORDER
                )
        );

        JLabel statusLabel =
                new JLabel(
                        "● PRESCRIPTION MODULE"
                );

        statusLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        statusLabel.setForeground(
                GREEN
        );

        statusLabel.setBorder(
                new EmptyBorder(
                        10,
                        25,
                        10,
                        25
                )
        );

        JButton closeButton =
                new JButton("CLOSE");

        closeButton.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        closeButton.setForeground(
                new Color(180, 40, 40)
        );

        closeButton.setBackground(WHITE);

        closeButton.setFocusPainted(false);

        closeButton.setBorderPainted(false);

        closeButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
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
    // FIELD PANEL
    // =========================

    private JPanel createFieldPanel(
            String labelText,
            JTextField field
    ) {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBackground(WHITE);

        JLabel label =
                new JLabel(labelText);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        label.setForeground(TEXT);

        field.setPreferredSize(
                new Dimension(
                        150,
                        35
                )
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        35
                )
        );

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        panel.add(label);

        panel.add(
                Box.createVerticalStrut(5)
        );

        panel.add(field);

        return panel;
    }

    // =========================
    // TEXT AREA
    // =========================

    private JTextArea createTextArea() {

        JTextArea area =
                new JTextArea();

        area.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        area.setLineWrap(true);

        area.setWrapStyleWord(true);

        area.setRows(4);

        area.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                5,
                                8,
                                5,
                                8
                        )
                )
        );

        return area;
    }

    private JPanel createTextAreaPanel(
            String labelText,
            JTextArea area
    ) {

        JPanel panel =
                new JPanel(new BorderLayout());

        panel.setBackground(WHITE);

        JLabel label =
                new JLabel(labelText);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(TEXT);

        panel.add(
                label,
                BorderLayout.NORTH
        );

        panel.add(
                area,
                BorderLayout.CENTER
        );

        return panel;
    }

    // =========================
    // SAVE PRESCRIPTION
    // =========================

    private void savePrescription() {

        String consultationIdText =
                consultationIdField.getText().trim();

        String patientIdText =
                patientIdField.getText().trim();

        String doctorIdText =
                doctorIdField.getText().trim();

        String medicineIdText =
                medicineIdField.getText().trim();

        String dosage =
                dosageField.getText().trim();

        String duration =
                durationField.getText().trim();

        String instructions =
                instructionsArea.getText().trim();

        // =========================
        // VALIDATION
        // =========================

        if (consultationIdText.isEmpty()
                || patientIdText.isEmpty()
                || doctorIdText.isEmpty()
                || medicineIdText.isEmpty()
                || dosage.isEmpty()
                || duration.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all required fields.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =========================
        // CONVERT IDs
        // =========================

        int consultationId;
        int patientId;
        int doctorId;
        int medicineId;

        try {

            consultationId =
                    Integer.parseInt(
                            consultationIdText
                    );

            patientId =
                    Integer.parseInt(
                            patientIdText
                    );

            doctorId =
                    Integer.parseInt(
                            doctorIdText
                    );

            medicineId =
                    Integer.parseInt(
                            medicineIdText
                    );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Consultation ID, Patient ID, Doctor ID and Medicine ID must be numbers.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =========================
        // CREATE PRESCRIPTION
        // =========================

        Prescription prescription =
                new Prescription(
                        0,
                        consultationId,
                        patientId,
                        doctorId,
                        medicineId,
                        dosage,
                        duration,
                        instructions,
                        null
                );

        // =========================
        // SAVE TO DATABASE
        // =========================

        boolean success =
                prescriptionService.createPrescription(
                        prescription
                );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Prescription saved successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearForm();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to save prescription.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================
    // CLEAR FORM
    // =========================

    private void clearForm() {

        consultationIdField.setText("");
        patientIdField.setText("");
        doctorIdField.setText("");
        medicineIdField.setText("");

        dosageField.setText("");
        durationField.setText("");

        instructionsArea.setText("");
    }

    // =========================
    // MAIN - TESTING
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            DoctorPrescriptionFrame frame =
                    new DoctorPrescriptionFrame();

            frame.setVisible(true);
        });
    }
}