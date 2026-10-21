package gui;

import model.EmergencyCase;
import service.EmergencyCaseService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;

public class AmbulanceEmergencyFrame extends JFrame {

    private JTextField patientNameField;
    private JTextField ageField;
    private JTextField genderField;
    private JTextField bloodGroupField;

    private JTextField conditionField;
    private JTextField symptomsField;
    private JTextField ambulanceNumberField;
    private JTextField etaField;

    private JLabel heartRateValue;
    private JLabel spo2Value;
    private JLabel temperatureValue;
    private JLabel bloodPressureValue;

    private JLabel vitalStatusLabel;

    private EmergencyCaseService emergencyCaseService;

    private final Random random = new Random();

    // =====================================================
    // MULTITHREADING VARIABLES
    // =====================================================

    private Thread vitalSimulationThread;

    private volatile boolean vitalSimulationRunning = false;

    private int currentHeartRate;
    private int currentSpo2;
    private double currentTemperature;
    private String currentBloodPressure;


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public AmbulanceEmergencyFrame() {

        emergencyCaseService =
                new EmergencyCaseService();

        setTitle(
                "XYZ HOSPITAL - Emergency Case"
        );

        setSize(1000, 750);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);


        // =====================================================
        // STOP THREAD WHEN WINDOW IS CLOSED
        // =====================================================

        addWindowListener(
                new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(
                            java.awt.event.WindowEvent e
                    ) {

                        stopVitalSimulation();
                    }
                }
        );


        // =====================================================
        // MAIN PANEL
        // =====================================================

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(
                new Color(245, 247, 250)
        );


        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel =
                new JPanel();

        headerPanel.setLayout(
                new BoxLayout(
                        headerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        headerPanel.setBackground(
                new Color(30, 90, 160)
        );

        headerPanel.setBorder(
                new EmptyBorder(
                        20,
                        30,
                        20,
                        30
                )
        );


        JLabel hospitalLabel =
                new JLabel(
                        "✚  XYZ HOSPITAL"
                );

        hospitalLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        24
                )
        );

        hospitalLabel.setForeground(
                Color.WHITE
        );

        hospitalLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JLabel portalLabel =
                new JLabel(
                        "AMBULANCE EMERGENCY CASE"
                );

        portalLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        portalLabel.setForeground(
                new Color(220, 235, 255)
        );

        portalLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        headerPanel.add(
                hospitalLabel
        );

        headerPanel.add(
                Box.createVerticalStrut(8)
        );

        headerPanel.add(
                portalLabel
        );


        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // FORM PANEL
        // =====================================================

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBackground(
                new Color(245, 247, 250)
        );

        formPanel.setBorder(
                new EmptyBorder(
                        20,
                        35,
                        20,
                        35
                )
        );


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        6,
                        8,
                        6,
                        8
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;


        // =====================================================
        // PATIENT INFORMATION
        // =====================================================

        JLabel patientSection =
                createSectionLabel(
                        "PATIENT INFORMATION"
                );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;

        formPanel.add(
                patientSection,
                gbc
        );


        gbc.gridwidth = 1;


        patientNameField =
                new JTextField();

        ageField =
                new JTextField();

        genderField =
                new JTextField();

        bloodGroupField =
                new JTextField();


        addField(
                formPanel,
                gbc,
                "Patient Name:",
                patientNameField,
                1,
                0
        );

        addField(
                formPanel,
                gbc,
                "Age:",
                ageField,
                1,
                2
        );

        addField(
                formPanel,
                gbc,
                "Gender:",
                genderField,
                2,
                0
        );

        addField(
                formPanel,
                gbc,
                "Blood Group:",
                bloodGroupField,
                2,
                2
        );


        // =====================================================
        // EMERGENCY INFORMATION
        // =====================================================

        JLabel emergencySection =
                createSectionLabel(
                        "EMERGENCY INFORMATION"
                );

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;

        formPanel.add(
                emergencySection,
                gbc
        );


        gbc.gridwidth = 1;


        conditionField =
                new JTextField();

        symptomsField =
                new JTextField();

        ambulanceNumberField =
                new JTextField();

        etaField =
                new JTextField();


        addField(
                formPanel,
                gbc,
                "Condition:",
                conditionField,
                4,
                0
        );

        addField(
                formPanel,
                gbc,
                "Symptoms:",
                symptomsField,
                4,
                2
        );

        addField(
                formPanel,
                gbc,
                "Ambulance No:",
                ambulanceNumberField,
                5,
                0
        );

        addField(
                formPanel,
                gbc,
                "ETA:",
                etaField,
                5,
                2
        );


        // =====================================================
        // SIMULATED VITAL SIGNS
        // =====================================================

        JLabel vitalSection =
                createSectionLabel(
                        "SIMULATED VITAL SIGNS"
                );

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 4;

        formPanel.add(
                vitalSection,
                gbc
        );


        gbc.gridwidth = 1;


        JPanel vitalPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                10,
                                0
                        )
                );

        vitalPanel.setBackground(
                Color.WHITE
        );

        vitalPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 225, 230)
                )
        );


        heartRateValue =
                createVitalLabel(
                        "Heart Rate",
                        "-- bpm"
                );

        spo2Value =
                createVitalLabel(
                        "SpO₂",
                        "-- %"
                );

        temperatureValue =
                createVitalLabel(
                        "Temperature",
                        "-- °C"
                );

        bloodPressureValue =
                createVitalLabel(
                        "Blood Pressure",
                        "-- / -- mmHg"
                );


        vitalPanel.add(
                heartRateValue
        );

        vitalPanel.add(
                spo2Value
        );

        vitalPanel.add(
                temperatureValue
        );

        vitalPanel.add(
                bloodPressureValue
        );


        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 4;

        gbc.fill =
                GridBagConstraints.BOTH;

        gbc.weightx = 1.0;
        gbc.weighty = 0.0;

        formPanel.add(
                vitalPanel,
                gbc
        );


        // =====================================================
        // GENERATE VITALS BUTTON
        // =====================================================

        JButton generateVitalsButton =
                new JButton(
                        "GENERATE SIMULATED VITALS"
                );

        generateVitalsButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        generateVitalsButton.setFocusPainted(
                false
        );


        generateVitalsButton.addActionListener(
                e -> generateVitalSigns()
        );


        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 4;

        gbc.fill =
                GridBagConstraints.NONE;

        formPanel.add(
                generateVitalsButton,
                gbc
        );


        // =====================================================
        // VITAL STATUS
        // =====================================================

        vitalStatusLabel =
                new JLabel(
                        "Starting simulated vital monitoring..."
                );

        vitalStatusLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        vitalStatusLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 4;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        formPanel.add(
                vitalStatusLabel,
                gbc
        );


        mainPanel.add(
                new JScrollPane(formPanel),
                BorderLayout.CENTER
        );


        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                12
                        )
                );

        buttonPanel.setBackground(
                Color.WHITE
        );


        JButton sendButton =
                new JButton(
                        "SEND TO HOSPITAL"
                );

        sendButton.setPreferredSize(
                new Dimension(
                        190,
                        42
                )
        );

        sendButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        sendButton.setForeground(
                Color.WHITE
        );

        sendButton.setBackground(
                new Color(30, 120, 80)
        );

        sendButton.setFocusPainted(
                false
        );


        JButton clearButton =
                new JButton(
                        "CLEAR"
                );

        clearButton.setPreferredSize(
                new Dimension(
                        120,
                        42
                )
        );

        clearButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        clearButton.setFocusPainted(
                false
        );


        JButton closeButton =
                new JButton(
                        "CLOSE"
                );

        closeButton.setPreferredSize(
                new Dimension(
                        120,
                        42
                )
        );

        closeButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        closeButton.setFocusPainted(
                false
        );


        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        sendButton.addActionListener(
                e -> sendEmergencyCase()
        );


        clearButton.addActionListener(
                e -> clearFields()
        );


        closeButton.addActionListener(
                e -> {

                    stopVitalSimulation();

                    dispose();
                }
        );


        buttonPanel.add(
                sendButton
        );

        buttonPanel.add(
                clearButton
        );

        buttonPanel.add(
                closeButton
        );


        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        add(mainPanel);


        // =====================================================
        // START BACKGROUND VITAL SIMULATION
        // =====================================================

        startVitalSimulation();
    }


    // =====================================================
    // ADD FORM FIELD
    // =====================================================

    private void addField(
            JPanel panel,
            GridBagConstraints gbc,
            String labelText,
            JTextField field,
            int row,
            int column
    ) {

        JLabel label =
                new JLabel(labelText);

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );


        gbc.gridx = column;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0.0;

        panel.add(
                label,
                gbc
        );


        gbc.gridx = column + 1;
        gbc.gridy = row;
        gbc.weightx = 1.0;


        field.setPreferredSize(
                new Dimension(
                        220,
                        32
                )
        );


        panel.add(
                field,
                gbc
        );
    }


    // =====================================================
    // SECTION LABEL
    // =====================================================

    private JLabel createSectionLabel(
            String text
    ) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        label.setForeground(
                new Color(30, 90, 160)
        );

        return label;
    }


    // =====================================================
    // VITAL LABEL
    // =====================================================

    private JLabel createVitalLabel(
            String name,
            String value
    ) {

        JLabel label =
                new JLabel(
                        "<html><center>"
                                + name
                                + "<br><br>"
                                + "<b>"
                                + value
                                + "</b>"
                                + "</center></html>",
                        SwingConstants.CENTER
                );

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        return label;
    }


    // =====================================================
    // GENERATE SIMULATED VITAL SIGNS
    // =====================================================

    private void generateVitalSigns() {

        currentHeartRate =
                60 + random.nextInt(61);


        currentSpo2 =
                92 + random.nextInt(9);


        currentTemperature =
                36.0
                        + random.nextDouble() * 2.5;


        currentTemperature =
                Math.round(
                        currentTemperature * 10
                ) / 10.0;


        int systolic =
                100 + random.nextInt(41);


        int diastolic =
                60 + random.nextInt(21);


        currentBloodPressure =
                systolic
                        + "/"
                        + diastolic;


        updateVitalDisplay();
    }


    // =====================================================
    // UPDATE VITAL DISPLAY
    // =====================================================

    private void updateVitalDisplay() {

        SwingUtilities.invokeLater(() -> {

            heartRateValue.setText(
                    "<html><center>"
                            + "Heart Rate"
                            + "<br><br>"
                            + "<b>"
                            + currentHeartRate
                            + " bpm"
                            + "</b>"
                            + "</center></html>"
            );


            spo2Value.setText(
                    "<html><center>"
                            + "SpO₂"
                            + "<br><br>"
                            + "<b>"
                            + currentSpo2
                            + " %"
                            + "</b>"
                            + "</center></html>"
            );


            temperatureValue.setText(
                    "<html><center>"
                            + "Temperature"
                            + "<br><br>"
                            + "<b>"
                            + currentTemperature
                            + " °C"
                            + "</b>"
                            + "</center></html>"
            );


            bloodPressureValue.setText(
                    "<html><center>"
                            + "Blood Pressure"
                            + "<br><br>"
                            + "<b>"
                            + currentBloodPressure
                            + " mmHg"
                            + "</b>"
                            + "</center></html>"
            );


            updateVitalStatus();
        });
    }


    // =====================================================
    // UPDATE VITAL STATUS
    // =====================================================

    private void updateVitalStatus() {

        int systolic =
                Integer.parseInt(
                        currentBloodPressure
                                .split("/")[0]
                );


        int diastolic =
                Integer.parseInt(
                        currentBloodPressure
                                .split("/")[1]
                );


        if (
                currentHeartRate < 60
                        ||
                        currentHeartRate > 100
                        ||
                        currentSpo2 < 94
                        ||
                        currentTemperature > 38.0
                        ||
                        systolic < 90
                        ||
                        systolic > 140
                        ||
                        diastolic < 60
                        ||
                        diastolic > 90
        ) {

            vitalStatusLabel.setText(
                    "⚠ SIMULATED WARNING: Abnormal vital sign detected."
            );

            vitalStatusLabel.setForeground(
                    new Color(190, 60, 50)
            );

        } else {

            vitalStatusLabel.setText(
                    "✓ SIMULATED VITALS: Values currently within normal range."
            );

            vitalStatusLabel.setForeground(
                    new Color(30, 120, 80)
            );
        }
    }


    // =====================================================
    // START VITAL SIMULATION THREAD
    // =====================================================

    private void startVitalSimulation() {

        if (vitalSimulationRunning) {
            return;
        }


        vitalSimulationRunning = true;


        vitalSimulationThread =
                new Thread(() -> {

                    while (
                            vitalSimulationRunning
                    ) {

                        generateVitalSigns();


                        try {

                            Thread.sleep(
                                    3000
                            );

                        } catch (
                                InterruptedException e
                        ) {

                            Thread.currentThread()
                                    .interrupt();

                            break;
                        }
                    }

                });


        vitalSimulationThread.setName(
                "Vital-Simulation-Thread"
        );


        vitalSimulationThread.start();
    }


    // =====================================================
    // STOP VITAL SIMULATION THREAD
    // =====================================================

    private void stopVitalSimulation() {

        vitalSimulationRunning =
                false;


        if (
                vitalSimulationThread
                        != null
        ) {

            vitalSimulationThread.interrupt();

            vitalSimulationThread =
                    null;
        }
    }


    // =====================================================
    // SEND EMERGENCY CASE
    // =====================================================

    private void sendEmergencyCase() {

        String patientName =
                patientNameField
                        .getText()
                        .trim();


        String ageText =
                ageField
                        .getText()
                        .trim();


        String gender =
                genderField
                        .getText()
                        .trim();


        String bloodGroup =
                bloodGroupField
                        .getText()
                        .trim();


        String condition =
                conditionField
                        .getText()
                        .trim();


        String symptoms =
                symptomsField
                        .getText()
                        .trim();


        String ambulanceNumber =
                ambulanceNumberField
                        .getText()
                        .trim();


        String eta =
                etaField
                        .getText()
                        .trim();


        // =====================================================
        // VALIDATE INPUT
        // =====================================================

        if (
                patientName.isEmpty()
                        ||
                        ageText.isEmpty()
                        ||
                        gender.isEmpty()
                        ||
                        bloodGroup.isEmpty()
                        ||
                        condition.isEmpty()
                        ||
                        symptoms.isEmpty()
                        ||
                        ambulanceNumber.isEmpty()
                        ||
                        eta.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all patient and emergency details.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // =====================================================
        // CHECK VITALS
        // =====================================================

        if (
                currentHeartRate <= 0
                        ||
                        currentSpo2 <= 0
                        ||
                        currentTemperature <= 0
                        ||
                        currentBloodPressure == null
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please wait for simulated vital signs to be generated.",
                    "Vital Signs Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        try {

            int age =
                    Integer.parseInt(
                            ageText
                    );


            // =================================================
            // CURRENT SIMULATED VALUES
            // =================================================

            int heartRate =
                    currentHeartRate;


            int spo2 =
                    currentSpo2;


            double temperature =
                    currentTemperature;


            String bloodPressure =
                    currentBloodPressure;


            // =================================================
            // CREATE EMERGENCY CASE
            // =================================================

            EmergencyCase emergencyCase =
                    new EmergencyCase(
                            0,
                            patientName,
                            age,
                            gender,
                            condition,
                            symptoms,
                            bloodGroup,
                            ambulanceNumber,
                            eta,
                            "SENT",
                            heartRate,
                            spo2,
                            temperature,
                            bloodPressure,
                            null
                    );


            // =================================================
            // SAVE TO DATABASE
            // =================================================

            boolean success =
                    emergencyCaseService
                            .sendEmergencyCase(
                                    emergencyCase
                            );


            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Emergency case sent successfully to the hospital.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );


                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Failed to send emergency case.",
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }


        } catch (
                NumberFormatException e
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Age must be a valid number.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }


    // =====================================================
    // CLEAR FIELDS
    // =====================================================

    private void clearFields() {

        patientNameField.setText("");

        ageField.setText("");

        genderField.setText("");

        bloodGroupField.setText("");

        conditionField.setText("");

        symptomsField.setText("");

        ambulanceNumberField.setText("");

        etaField.setText("");


        // Reset simulated values

        currentHeartRate = 0;

        currentSpo2 = 0;

        currentTemperature = 0;

        currentBloodPressure = null;


        heartRateValue.setText(
                "<html><center>"
                        + "Heart Rate"
                        + "<br><br>"
                        + "<b>-- bpm</b>"
                        + "</center></html>"
        );


        spo2Value.setText(
                "<html><center>"
                        + "SpO₂"
                        + "<br><br>"
                        + "<b>-- %</b>"
                        + "</center></html>"
        );


        temperatureValue.setText(
                "<html><center>"
                        + "Temperature"
                        + "<br><br>"
                        + "<b>-- °C</b>"
                        + "</center></html>"
        );


        bloodPressureValue.setText(
                "<html><center>"
                        + "Blood Pressure"
                        + "<br><br>"
                        + "<b>-- / -- mmHg</b>"
                        + "</center></html>"
        );


        vitalStatusLabel.setText(
                "Waiting for simulated vital signs..."
        );

        vitalStatusLabel.setForeground(
                new Color(100, 110, 120)
        );
    }


    // =====================================================
    // MAIN METHOD
    // =====================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    new AmbulanceEmergencyFrame()
                            .setVisible(true);

                }
        );
    }
}