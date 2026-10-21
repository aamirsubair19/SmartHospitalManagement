package gui;

import model.Consultation;
import service.ConsultationService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DoctorConsultationFrame extends JFrame {

    private final Color BACKGROUND = new Color(242, 246, 251);
    private final Color WHITE = Color.WHITE;

    private final Color DARK_BLUE = new Color(13, 71, 161);
    private final Color PRIMARY_BLUE = new Color(25, 118, 210);
    private final Color GREEN = new Color(46, 125, 50);
    private final Color ORANGE = new Color(239, 108, 0);

    private final Color TEXT = new Color(35, 45, 55);
    private final Color SECONDARY_TEXT = new Color(100, 110, 120);
    private final Color BORDER = new Color(215, 222, 230);

    private JTextField patientIdField;
    private JTextField doctorIdField;
    private JTextField appointmentIdField;

    private JTextArea symptomsArea;
    private JTextArea diagnosisArea;
    private JTextArea notesArea;
    private JTextArea treatmentArea;

    private ConsultationService consultationService;

    public DoctorConsultationFrame() {

        consultationService = new ConsultationService();

        setTitle("XYZ HOSPITAL - Consultation");
        setSize(900, 750);

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

        // =================================================
        // HEADER
        // =================================================

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

        // =================================================
        // CONTENT
        // =================================================

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

        // =================================================
        // TITLE
        // =================================================

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
                new JLabel("Patient Consultation");

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
                        "Record symptoms, diagnosis, notes and treatment"
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

        // =================================================
        // FORM PANEL
        // =================================================

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
                                20,
                                25,
                                20,
                                25
                        )
                )
        );

        // =================================================
        // BASIC INFORMATION
        // =================================================

        JPanel basicInfoPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                6,
                                10,
                                5
                        )
                );

        basicInfoPanel.setBackground(WHITE);

        patientIdField =
                new JTextField();

        doctorIdField =
                new JTextField();

        appointmentIdField =
                new JTextField();

        basicInfoPanel.add(
                createFieldPanel(
                        "Patient ID",
                        patientIdField
                )
        );

        basicInfoPanel.add(
                createFieldPanel(
                        "Doctor ID",
                        doctorIdField
                )
        );

        basicInfoPanel.add(
                createFieldPanel(
                        "Appointment ID",
                        appointmentIdField
                )
        );

        formPanel.add(basicInfoPanel);

        formPanel.add(
                Box.createVerticalStrut(20)
        );

        // =================================================
        // SYMPTOMS
        // =================================================

        symptomsArea =
                createTextArea();

        formPanel.add(
                createTextAreaPanel(
                        "Symptoms",
                        symptomsArea
                )
        );

        formPanel.add(
                Box.createVerticalStrut(12)
        );

        // =================================================
        // DIAGNOSIS
        // =================================================

        diagnosisArea =
                createTextArea();

        formPanel.add(
                createTextAreaPanel(
                        "Diagnosis",
                        diagnosisArea
                )
        );

        formPanel.add(
                Box.createVerticalStrut(12)
        );

        // =================================================
        // NOTES
        // =================================================

        notesArea =
                createTextArea();

        formPanel.add(
                createTextAreaPanel(
                        "Doctor Notes",
                        notesArea
                )
        );

        formPanel.add(
                Box.createVerticalStrut(12)
        );

        // =================================================
        // TREATMENT
        // =================================================

        treatmentArea =
                createTextArea();

        formPanel.add(
                createTextAreaPanel(
                        "Treatment",
                        treatmentArea
                )
        );

        contentPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // BUTTON PANEL
        // =================================================

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
                new JButton("SAVE CONSULTATION");

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

        saveButton.setBackground(GREEN);

        saveButton.setFocusPainted(false);

        saveButton.setBorderPainted(false);

        saveButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        saveButton.addActionListener(
                e -> saveConsultation()
        );

        buttonPanel.add(clearButton);

        buttonPanel.add(saveButton);

        contentPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =================================================
        // FOOTER
        // =================================================

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
                        "● CONSULTATION MODULE"
                );

        statusLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        statusLabel.setForeground(
                PRIMARY_BLUE
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

        // =================================================
        // ADD EVERYTHING
        // =================================================

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

    // =====================================================
    // FIELD PANEL
    // =====================================================

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
                        130,
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

    // =====================================================
    // TEXT AREA
    // =====================================================

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

        area.setRows(2);

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

    // =====================================================
    // TEXT AREA PANEL
    // =====================================================

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

    // =====================================================
    // SAVE CONSULTATION
    // =====================================================

    private void saveConsultation() {

        String patientIdText =
                patientIdField.getText().trim();

        String doctorIdText =
                doctorIdField.getText().trim();

        String appointmentIdText =
                appointmentIdField.getText().trim();

        String symptoms =
                symptomsArea.getText().trim();

        String diagnosis =
                diagnosisArea.getText().trim();

        String notes =
                notesArea.getText().trim();

        String treatment =
                treatmentArea.getText().trim();

        // -------------------------------------------------
        // CHECK REQUIRED FIELDS
        // -------------------------------------------------

        if (patientIdText.isEmpty()
                || doctorIdText.isEmpty()
                || appointmentIdText.isEmpty()
                || symptoms.isEmpty()
                || diagnosis.isEmpty()
                || treatment.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all required fields.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int patientId;
        int doctorId;
        int appointmentId;

        // -------------------------------------------------
        // CONVERT IDS
        // -------------------------------------------------

        try {

            patientId =
                    Integer.parseInt(
                            patientIdText
                    );

            doctorId =
                    Integer.parseInt(
                            doctorIdText
                    );

            appointmentId =
                    Integer.parseInt(
                            appointmentIdText
                    );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Patient ID, Doctor ID and Appointment ID must be numbers.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // -------------------------------------------------
        // CREATE CONSULTATION OBJECT
        // -------------------------------------------------

        Consultation consultation =
                new Consultation(
                        0,
                        patientId,
                        doctorId,
                        appointmentId,
                        symptoms,
                        diagnosis,
                        notes,
                        treatment,
                        null
                );

        // -------------------------------------------------
        // SAVE
        // -------------------------------------------------

        boolean success =
                consultationService.createConsultation(
                        consultation
                );

        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Consultation saved successfully.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearForm();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to save consultation.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // CLEAR FORM
    // =====================================================

    private void clearForm() {

        patientIdField.setText("");
        doctorIdField.setText("");
        appointmentIdField.setText("");

        symptomsArea.setText("");
        diagnosisArea.setText("");
        notesArea.setText("");
        treatmentArea.setText("");
    }

    // =====================================================
    // TEST MAIN
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            DoctorConsultationFrame frame =
                    new DoctorConsultationFrame();

            frame.setVisible(true);
        });
    }
}