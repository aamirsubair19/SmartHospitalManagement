package gui;

import model.Appointment;
import model.Doctor;
import model.Patient;

import service.AppointmentService;
import service.DoctorService;
import service.PatientService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.List;

public class ReceptionFrame extends JFrame {

    // =========================
    // COLORS
    // =========================

    private static final Color PRIMARY_BLUE = new Color(25, 118, 210);
    private static final Color DARK_BLUE = new Color(13, 71, 161);
    private static final Color LIGHT_BLUE = new Color(232, 245, 253);
    private static final Color BACKGROUND = new Color(244, 248, 252);
    private static final Color WHITE = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(45, 55, 65);
    private static final Color SECONDARY_TEXT = new Color(105, 115, 125);
    private static final Color BORDER_COLOR = new Color(215, 225, 235);

    // =========================
    // SERVICES
    // =========================

    private final PatientService patientService = new PatientService();
    private final AppointmentService appointmentService = new AppointmentService();
    private final DoctorService doctorService = new DoctorService();

    // =========================
    // PATIENT FIELDS
    // =========================

    private JTextField nameField;
    private JTextField ageField;
    private JComboBox<String> genderBox;
    private JTextField phoneField;
    private JTextField addressField;

    // =========================
    // SEARCH PATIENT
    // =========================

    private JTextField searchPatientIdField;

    // =========================
    // APPOINTMENT FIELDS
    // =========================

    private JTextField appointmentPatientIdField;
    private JComboBox<Doctor> doctorBox;
    private JTextField appointmentDateField;
    private JTextField appointmentTimeField;
    private JComboBox<String> statusBox;

    // =========================
    // SEARCH APPOINTMENT
    // =========================

    private JTextField searchAppointmentIdField;

    // =========================
    // APPOINTMENT MANAGEMENT
    // =========================

    private JTextField manageAppointmentIdField;
    private JComboBox<String> manageStatusBox;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ReceptionFrame() {

        setTitle("XYZ HOSPITAL - Reception");
        setSize(800, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(BACKGROUND);

        setLayout(new BorderLayout());

        // Header
        add(createHeader(), BorderLayout.NORTH);

        // Main content
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(
                new BoxLayout(
                        mainPanel,
                        BoxLayout.Y_AXIS
                )
        );

        mainPanel.setBackground(BACKGROUND);

        mainPanel.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        // Patient Registration
        mainPanel.add(
                createPatientRegistrationCard()
        );

        mainPanel.add(
                Box.createVerticalStrut(20)
        );

        // Search Patient
        mainPanel.add(
                createSearchPatientCard()
        );

        mainPanel.add(
                Box.createVerticalStrut(20)
        );

        // Create Appointment
        mainPanel.add(
                createAppointmentCard()
        );

        mainPanel.add(
                Box.createVerticalStrut(20)
        );

        // Search Appointment
        mainPanel.add(
                createSearchAppointmentCard()
        );

        mainPanel.add(
                Box.createVerticalStrut(20)
        );

        // Appointment Management
        mainPanel.add(
                createAppointmentManagementCard()
        );

        JScrollPane scrollPane =
                new JScrollPane(mainPanel);

        scrollPane.setBorder(null);

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        add(
                scrollPane,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // HEADER
    // =========================================================

    private JPanel createHeader() {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(DARK_BLUE);

        header.setBorder(
                new EmptyBorder(
                        18,
                        25,
                        18,
                        25
                )
        );

        JLabel title =
                new JLabel("XYZ HOSPITAL");

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Reception Management System"
                );

        subtitle.setForeground(
                new Color(
                        220,
                        230,
                        245
                )
        );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        JPanel textPanel =
                new JPanel();

        textPanel.setLayout(
                new BoxLayout(
                        textPanel,
                        BoxLayout.Y_AXIS
                )
        );

        textPanel.setOpaque(false);

        textPanel.add(title);

        textPanel.add(
                Box.createVerticalStrut(3)
        );

        textPanel.add(subtitle);

        header.add(
                textPanel,
                BorderLayout.WEST
        );

        return header;
    }

    // =========================================================
    // PATIENT REGISTRATION CARD
    // =========================================================

    private JPanel createPatientRegistrationCard() {

        JPanel card = createCard();

        JLabel heading =
                new JLabel(
                        "Patient Registration"
                );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        heading.setForeground(DARK_BLUE);

        JLabel description =
                new JLabel(
                        "Register a new patient in the hospital database."
                );

        description.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        description.setForeground(
                SECONDARY_TEXT
        );

        JPanel headingPanel =
                new JPanel();

        headingPanel.setLayout(
                new BoxLayout(
                        headingPanel,
                        BoxLayout.Y_AXIS
                )
        );

        headingPanel.setOpaque(false);

        headingPanel.add(heading);

        headingPanel.add(
                Box.createVerticalStrut(5)
        );

        headingPanel.add(description);

        card.add(
                headingPanel,
                BorderLayout.NORTH
        );

        // Form

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBackground(WHITE);

        formPanel.setBorder(
                new EmptyBorder(
                        20,
                        5,
                        10,
                        5
                )
        );

        nameField = createTextField();
        ageField = createTextField();
        phoneField = createTextField();
        addressField = createTextField();

        genderBox =
                new JComboBox<>(
                        new String[]{
                                "Male",
                                "Female",
                                "Other"
                        }
                );

        styleComboBox(genderBox);

        addFormRow(
                formPanel,
                0,
                "Patient Name",
                nameField
        );

        addFormRow(
                formPanel,
                1,
                "Age",
                ageField
        );

        addFormRow(
                formPanel,
                2,
                "Gender",
                genderBox
        );

        addFormRow(
                formPanel,
                3,
                "Phone No",
                phoneField
        );

        addFormRow(
                formPanel,
                4,
                "Address",
                addressField
        );

        card.add(
                formPanel,
                BorderLayout.CENTER
        );

        // Buttons

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        buttonPanel.setBackground(WHITE);

        JButton clearButton =
                createSecondaryButton(
                        "Clear"
                );

        JButton registerButton =
                createPrimaryButton(
                        "Register Patient"
                );

        clearButton.addActionListener(e -> {

            nameField.setText("");
            ageField.setText("");
            genderBox.setSelectedIndex(0);
            phoneField.setText("");
            addressField.setText("");
        });

        registerButton.addActionListener(e -> {

            String name =
                    nameField
                            .getText()
                            .trim();

            String ageText =
                    ageField
                            .getText()
                            .trim();

            String gender =
                    (String) genderBox
                            .getSelectedItem();

            String phone =
                    phoneField
                            .getText()
                            .trim();

            String address =
                    addressField
                            .getText()
                            .trim();

            if (
                    name.isEmpty()
                            || ageText.isEmpty()
                            || phone.isEmpty()
                            || address.isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            try {

                int age =
                        Integer.parseInt(
                                ageText
                        );

                Patient patient =
                        new Patient(
                                0,
                                name,
                                age,
                                gender,
                                phone,
                                address
                        );

                patientService
                        .registerPatient(
                                patient
                        );

                JOptionPane.showMessageDialog(
                        this,
                        "Patient registered successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                nameField.setText("");
                ageField.setText("");
                genderBox.setSelectedIndex(0);
                phoneField.setText("");
                addressField.setText("");

            } catch (
                    NumberFormatException ex
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Age must be a valid number.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        buttonPanel.add(clearButton);
        buttonPanel.add(registerButton);

        card.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        return card;
    }

    // =========================================================
    // SEARCH PATIENT CARD
    // =========================================================

    private JPanel createSearchPatientCard() {

        JPanel card = createCard();

        JLabel heading =
                new JLabel(
                        "Search Patient"
                );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        heading.setForeground(DARK_BLUE);

        JLabel description =
                new JLabel(
                        "Search for an existing patient using Patient ID."
                );

        description.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        description.setForeground(
                SECONDARY_TEXT
        );

        JPanel headingPanel =
                new JPanel();

        headingPanel.setLayout(
                new BoxLayout(
                        headingPanel,
                        BoxLayout.Y_AXIS
                )
        );

        headingPanel.setOpaque(false);

        headingPanel.add(heading);

        headingPanel.add(
                Box.createVerticalStrut(5)
        );

        headingPanel.add(description);

        card.add(
                headingPanel,
                BorderLayout.NORTH
        );

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBackground(WHITE);

        formPanel.setBorder(
                new EmptyBorder(
                        20,
                        5,
                        10,
                        5
                )
        );

        searchPatientIdField =
                createTextField();

        addFormRow(
                formPanel,
                0,
                "Patient ID",
                searchPatientIdField
        );

        card.add(
                formPanel,
                BorderLayout.CENTER
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        buttonPanel.setBackground(WHITE);

        JButton searchButton =
                createPrimaryButton(
                        "Search Patient"
                );

        searchButton.addActionListener(e -> {

            String idText =
                    searchPatientIdField
                            .getText()
                            .trim();

            if (idText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter a Patient ID.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            try {

                int patientId =
                        Integer.parseInt(
                                idText
                        );

                Patient patient =
                        patientService
                                .searchPatient(
                                        patientId
                                );

                if (patient != null) {

                    String details =
                            "<html>"
                                    + "<b>Patient ID:</b> "
                                    + patient.getPatientId()
                                    + "<br>"
                                    + "<b>Name:</b> "
                                    + patient.getName()
                                    + "<br>"
                                    + "<b>Age:</b> "
                                    + patient.getAge()
                                    + "<br>"
                                    + "<b>Gender:</b> "
                                    + patient.getGender()
                                    + "<br>"
                                    + "<b>Phone:</b> "
                                    + patient.getPhone()
                                    + "<br>"
                                    + "<b>Address:</b> "
                                    + patient.getAddress()
                                    + "</html>";

                    JOptionPane.showMessageDialog(
                            this,
                            details,
                            "Patient Details",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Patient not found.",
                            "Search Result",
                            JOptionPane.WARNING_MESSAGE
                    );
                }

            } catch (
                    NumberFormatException ex
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Patient ID must be a valid number.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        buttonPanel.add(searchButton);

        card.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        return card;
    }

    // =========================================================
    // CREATE APPOINTMENT CARD
    // =========================================================

    private JPanel createAppointmentCard() {

        JPanel card = createCard();

        JLabel heading =
                new JLabel(
                        "Create Appointment"
                );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        heading.setForeground(DARK_BLUE);

        JLabel description =
                new JLabel(
                        "Schedule an appointment for an existing patient."
                );

        description.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        description.setForeground(
                SECONDARY_TEXT
        );

        JPanel headingPanel =
                new JPanel();

        headingPanel.setLayout(
                new BoxLayout(
                        headingPanel,
                        BoxLayout.Y_AXIS
                )
        );

        headingPanel.setOpaque(false);

        headingPanel.add(heading);

        headingPanel.add(
                Box.createVerticalStrut(5)
        );

        headingPanel.add(description);

        card.add(
                headingPanel,
                BorderLayout.NORTH
        );

        // Form

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBackground(WHITE);

        formPanel.setBorder(
                new EmptyBorder(
                        20,
                        5,
                        10,
                        5
                )
        );

        appointmentPatientIdField =
                createTextField();

        doctorBox =
                new JComboBox<>();

        styleComboBox(doctorBox);

        loadDoctors(doctorBox);

        appointmentDateField =
                createTextField();

        appointmentDateField.setEditable(false);

        appointmentTimeField =
                createTextField();

        appointmentTimeField.setEditable(false);

        statusBox =
                new JComboBox<>(
                        new String[]{
                                "SCHEDULED",
                                "WAITING",
                                "COMPLETED",
                                "CANCELLED"
                        }
                );

        styleComboBox(statusBox);

        addFormRow(
                formPanel,
                0,
                "Patient ID",
                appointmentPatientIdField
        );

        addFormRow(
                formPanel,
                1,
                "Doctor",
                doctorBox
        );

        // Date

        JPanel datePanel =
                new JPanel(
                        new BorderLayout(8, 0)
                );

        datePanel.setBackground(WHITE);

        datePanel.add(
                appointmentDateField,
                BorderLayout.CENTER
        );

        JButton dateButton =
                new JButton("📅");

        dateButton.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        dateButton.setFocusPainted(false);

        dateButton.setBackground(
                LIGHT_BLUE
        );

        dateButton.setBorder(
                new LineBorder(
                        BORDER_COLOR
                )
        );

        dateButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        dateButton.addActionListener(
                e -> showDatePicker()
        );

        datePanel.add(
                dateButton,
                BorderLayout.EAST
        );

        addFormRow(
                formPanel,
                2,
                "Appointment Date",
                datePanel
        );

        // Time

        JPanel timePanel =
                new JPanel(
                        new BorderLayout(8, 0)
                );

        timePanel.setBackground(WHITE);

        timePanel.add(
                appointmentTimeField,
                BorderLayout.CENTER
        );

        JButton timeButton =
                new JButton("🕐");

        timeButton.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        timeButton.setFocusPainted(false);

        timeButton.setBackground(
                LIGHT_BLUE
        );

        timeButton.setBorder(
                new LineBorder(
                        BORDER_COLOR
                )
        );

        timeButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        timeButton.addActionListener(
                e -> showTimePicker()
        );

        timePanel.add(
                timeButton,
                BorderLayout.EAST
        );

        addFormRow(
                formPanel,
                3,
                "Appointment Time",
                timePanel
        );

        addFormRow(
                formPanel,
                4,
                "Status",
                statusBox
        );

        card.add(
                formPanel,
                BorderLayout.CENTER
        );

        // Buttons

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        buttonPanel.setBackground(WHITE);

        JButton clearButton =
                createSecondaryButton(
                        "Clear"
                );

        JButton createButton =
                createPrimaryButton(
                        "Create Appointment"
                );

        clearButton.addActionListener(e -> {

            appointmentPatientIdField
                    .setText("");

            appointmentDateField
                    .setText("");

            appointmentTimeField
                    .setText("");

            statusBox.setSelectedIndex(0);

            if (
                    doctorBox.getItemCount()
                            > 0
            ) {

                doctorBox.setSelectedIndex(0);
            }
        });

        // CREATE APPOINTMENT

        createButton.addActionListener(e -> {

            String patientIdText =
                    appointmentPatientIdField
                            .getText()
                            .trim();

            String dateText =
                    appointmentDateField
                            .getText()
                            .trim();

            String timeText =
                    appointmentTimeField
                            .getText()
                            .trim();

            Doctor selectedDoctor =
                    (Doctor)
                            doctorBox
                                    .getSelectedItem();

            String status =
                    (String)
                            statusBox
                                    .getSelectedItem();

            if (
                    patientIdText.isEmpty()
                            || dateText.isEmpty()
                            || timeText.isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all appointment fields.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if (selectedDoctor == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a doctor.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            try {

                int patientId =
                        Integer.parseInt(
                                patientIdText
                        );

                int doctorId =
                        selectedDoctor
                                .getDoctorId();

                LocalDate appointmentDate =
                        LocalDate.parse(
                                dateText
                        );

                LocalTime appointmentTime =
                        LocalTime.parse(
                                timeText
                        );

                Appointment appointment =
                        new Appointment(
                                0,
                                patientId,
                                doctorId,
                                appointmentDate,
                                appointmentTime,
                                status
                        );

                boolean success =
                        appointmentService
                                .createAppointment(
                                        appointment
                                );

                if (success) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Appointment created successfully.",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    appointmentPatientIdField
                            .setText("");

                    appointmentDateField
                            .setText("");

                    appointmentTimeField
                            .setText("");

                    statusBox.setSelectedIndex(0);

                    if (
                            doctorBox
                                    .getItemCount()
                                    > 0
                    ) {

                        doctorBox
                                .setSelectedIndex(0);
                    }

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Could not create appointment.\n"
                                    + "Please check that the Patient ID exists.",
                            "Appointment Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } catch (
                    NumberFormatException ex
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Patient ID must be a valid number.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

            } catch (
                    DateTimeParseException ex
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid date or time format.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        buttonPanel.add(clearButton);
        buttonPanel.add(createButton);

        card.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        return card;
    }

    // =========================================================
    // SEARCH APPOINTMENT CARD
    // =========================================================

    private JPanel createSearchAppointmentCard() {

        JPanel card = createCard();

        JLabel heading =
                new JLabel(
                        "Search Appointment"
                );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        heading.setForeground(DARK_BLUE);

        JLabel description =
                new JLabel(
                        "Search for an appointment using Appointment ID."
                );

        description.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        description.setForeground(
                SECONDARY_TEXT
        );

        JPanel headingPanel =
                new JPanel();

        headingPanel.setLayout(
                new BoxLayout(
                        headingPanel,
                        BoxLayout.Y_AXIS
                )
        );

        headingPanel.setOpaque(false);

        headingPanel.add(heading);

        headingPanel.add(
                Box.createVerticalStrut(5)
        );

        headingPanel.add(description);

        card.add(
                headingPanel,
                BorderLayout.NORTH
        );

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBackground(WHITE);

        formPanel.setBorder(
                new EmptyBorder(
                        20,
                        5,
                        10,
                        5
                )
        );

        searchAppointmentIdField =
                createTextField();

        addFormRow(
                formPanel,
                0,
                "Appointment ID",
                searchAppointmentIdField
        );

        card.add(
                formPanel,
                BorderLayout.CENTER
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        buttonPanel.setBackground(WHITE);

        JButton searchButton =
                createPrimaryButton(
                        "Search Appointment"
                );

        searchButton.addActionListener(e -> {

            String idText =
                    searchAppointmentIdField
                            .getText()
                            .trim();

            if (idText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter an Appointment ID.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            try {

                int appointmentId =
                        Integer.parseInt(
                                idText
                        );

                Appointment appointment =
                        appointmentService
                                .searchAppointment(
                                        appointmentId
                                );

                if (appointment != null) {

                    Patient patient =
                            patientService.searchPatient(
                                    appointment.getPatientId()
                            );

                    Doctor doctor =
                            doctorService.searchDoctor(
                                    appointment.getDoctorId()
                            );

                    String patientName =
                            patient != null
                                    ? patient.getName()
                                    : "Unknown";

                    String doctorDetails =
                            doctor != null
                                    ? "Dr. "
                                    + doctor.getName()
                                    + " - "
                                    + doctor.getSpecialization()
                                    : "Unknown";

                    String details =
                            "<html>"
                                    + "<b>Appointment ID:</b> "
                                    + appointment.getAppointmentId()
                                    + "<br><br>"

                                    + "<b>Patient:</b> "
                                    + patientName
                                    + "<br><br>"

                                    + "<b>Doctor:</b> "
                                    + doctorDetails
                                    + "<br><br>"

                                    + "<b>Date:</b> "
                                    + appointment.getAppointmentDate()
                                    + "<br>"

                                    + "<b>Time:</b> "
                                    + appointment.getAppointmentTime()
                                    + "<br>"

                                    + "<b>Status:</b> "
                                    + appointment.getStatus()
                                    + "</html>";

                    JOptionPane.showMessageDialog(
                            this,
                            details,
                            "Appointment Details",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Appointment not found.",
                            "Search Result",
                            JOptionPane.WARNING_MESSAGE
                    );
                }

            } catch (
                    NumberFormatException ex
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Appointment ID must be a valid number.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        buttonPanel.add(searchButton);

        card.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        return card;
    }

    // =========================================================
    // APPOINTMENT MANAGEMENT CARD
    // =========================================================

    private JPanel createAppointmentManagementCard() {

        JPanel card = createCard();

        JLabel heading =
                new JLabel(
                        "Appointment Management"
                );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        heading.setForeground(DARK_BLUE);

        JLabel description =
                new JLabel(
                        "Update appointment status, cancel appointments, or view all appointments."
                );

        description.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        description.setForeground(
                SECONDARY_TEXT
        );

        JPanel headingPanel =
                new JPanel();

        headingPanel.setLayout(
                new BoxLayout(
                        headingPanel,
                        BoxLayout.Y_AXIS
                )
        );

        headingPanel.setOpaque(false);

        headingPanel.add(heading);

        headingPanel.add(
                Box.createVerticalStrut(5)
        );

        headingPanel.add(description);

        card.add(
                headingPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // FORM
        // =====================================================

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBackground(WHITE);

        formPanel.setBorder(
                new EmptyBorder(
                        20,
                        5,
                        10,
                        5
                )
        );

        manageAppointmentIdField =
                createTextField();

        manageStatusBox =
                new JComboBox<>(
                        new String[]{
                                "SCHEDULED",
                                "WAITING",
                                "COMPLETED",
                                "CANCELLED"
                        }
                );

        styleComboBox(
                manageStatusBox
        );

        addFormRow(
                formPanel,
                0,
                "Appointment ID",
                manageAppointmentIdField
        );

        addFormRow(
                formPanel,
                1,
                "New Status",
                manageStatusBox
        );

        card.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        buttonPanel.setBackground(WHITE);

        JButton updateButton =
                createPrimaryButton(
                        "Update Status"
                );

        JButton cancelButton =
                createSecondaryButton(
                        "Cancel Appointment"
                );

        JButton viewAllButton =
                createPrimaryButton(
                        "View All Appointments"
                );

        // =====================================================
        // UPDATE STATUS
        // =====================================================

        updateButton.addActionListener(e -> {

            String idText =
                    manageAppointmentIdField
                            .getText()
                            .trim();

            String newStatus =
                    (String)
                            manageStatusBox
                                    .getSelectedItem();

            if (idText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter an Appointment ID.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            try {

                int appointmentId =
                        Integer.parseInt(
                                idText
                        );

                Appointment appointment =
                        appointmentService
                                .searchAppointment(
                                        appointmentId
                                );

                if (appointment == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Appointment not found.",
                            "Update Error",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }

                boolean success =
                        appointmentService
                                .updateAppointmentStatus(
                                        appointmentId,
                                        newStatus
                                );

                if (success) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Appointment status updated successfully.",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Could not update appointment status.",
                            "Update Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } catch (
                    NumberFormatException ex
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Appointment ID must be a valid number.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // =====================================================
        // CANCEL APPOINTMENT
        // =====================================================

        cancelButton.addActionListener(e -> {

            String idText =
                    manageAppointmentIdField
                            .getText()
                            .trim();

            if (idText.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter an Appointment ID.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            try {

                int appointmentId =
                        Integer.parseInt(
                                idText
                        );

                Appointment appointment =
                        appointmentService
                                .searchAppointment(
                                        appointmentId
                                );

                if (appointment == null) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Appointment not found.",
                            "Cancel Error",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }

                int choice =
                        JOptionPane.showConfirmDialog(
                                this,
                                "Are you sure you want to cancel Appointment ID "
                                        + appointmentId
                                        + "?",
                                "Confirm Cancellation",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE
                        );

                if (
                        choice
                                != JOptionPane.YES_OPTION
                ) {

                    return;
                }

                boolean success =
                        appointmentService
                                .cancelAppointment(
                                        appointmentId
                                );

                if (success) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Appointment cancelled successfully.",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    manageStatusBox.setSelectedItem(
                            "CANCELLED"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Could not cancel appointment.",
                            "Cancel Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } catch (
                    NumberFormatException ex
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Appointment ID must be a valid number.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        // =====================================================
        // VIEW ALL APPOINTMENTS
        // =====================================================

        viewAllButton.addActionListener(
                e -> showAllAppointments()
        );

        buttonPanel.add(updateButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(viewAllButton);

        card.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        return card;
    }

    // =========================================================
    // VIEW ALL APPOINTMENTS
    // =========================================================

    private void showAllAppointments() {

        List<Appointment> appointments =
                appointmentService
                        .getAllAppointments();

        if (appointments.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "No appointments found.",
                    "Appointments",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        StringBuilder table =
                new StringBuilder();

        table.append(
                "<html>"
                        + "<table border='1' "
                        + "cellpadding='8' "
                        + "cellspacing='0'>"
        );

        // Table heading

        table.append(
                "<tr>"
                        + "<th>ID</th>"
                        + "<th>Patient</th>"
                        + "<th>Doctor</th>"
                        + "<th>Date</th>"
                        + "<th>Time</th>"
                        + "<th>Status</th>"
                        + "</tr>"
        );

        // Table rows

        for (Appointment appointment : appointments) {

            Patient patient =
                    patientService.searchPatient(
                            appointment.getPatientId()
                    );

            Doctor doctor =
                    doctorService.searchDoctor(
                            appointment.getDoctorId()
                    );

            String patientName =
                    patient != null
                            ? patient.getName()
                            : "Unknown";

            String doctorDetails =
                    doctor != null
                            ? "Dr. "
                            + doctor.getName()
                            + " - "
                            + doctor.getSpecialization()
                            : "Unknown";

            table.append(
                    "<tr>"
                            + "<td>"
                            + appointment.getAppointmentId()
                            + "</td>"

                            + "<td>"
                            + patientName
                            + "</td>"

                            + "<td>"
                            + doctorDetails
                            + "</td>"

                            + "<td>"
                            + appointment.getAppointmentDate()
                            + "</td>"

                            + "<td>"
                            + appointment.getAppointmentTime()
                            + "</td>"

                            + "<td>"
                            + appointment.getStatus()
                            + "</td>"

                            + "</tr>"
            );
        }

        table.append(
                "</table>"
                        + "</html>"
        );

        JLabel tableLabel =
                new JLabel(
                        table.toString()
                );

        JScrollPane scrollPane =
                new JScrollPane(
                        tableLabel
                );

        scrollPane.setPreferredSize(
                new Dimension(
                        900,
                        350
                )
        );

        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "All Appointments",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // LOAD DOCTORS
    // =========================================================

    private void loadDoctors(
            JComboBox<Doctor> doctorBox
    ) {

        List<Doctor> doctors =
                doctorService.getAllDoctors();

        doctorBox.removeAllItems();

        for (Doctor doctor : doctors) {

            doctorBox.addItem(doctor);
        }
    }

    // =========================================================
    // DATE PICKER
    // =========================================================

    private void showDatePicker() {

        JDialog dialog =
                new JDialog(
                        this,
                        "Select Appointment Date",
                        true
                );

        dialog.setSize(
                400,
                400
        );

        dialog.setLocationRelativeTo(this);

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        mainPanel.setBackground(WHITE);

        mainPanel.setBorder(
                new EmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );

        Calendar calendar =
                Calendar.getInstance();

        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );

        topPanel.setBackground(WHITE);

        JButton previousButton =
                new JButton("◀");

        JButton nextButton =
                new JButton("▶");

        JLabel monthLabel =
                new JLabel(
                        "",
                        SwingConstants.CENTER
                );

        monthLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        previousButton.setFocusPainted(false);
        nextButton.setFocusPainted(false);

        topPanel.add(
                previousButton,
                BorderLayout.WEST
        );

        topPanel.add(
                monthLabel,
                BorderLayout.CENTER
        );

        topPanel.add(
                nextButton,
                BorderLayout.EAST
        );

        mainPanel.add(
                topPanel,
                BorderLayout.NORTH
        );

        JPanel calendarPanel =
                new JPanel(
                        new GridLayout(
                                0,
                                7,
                                5,
                                5
                        )
                );

        calendarPanel.setBackground(WHITE);

        mainPanel.add(
                calendarPanel,
                BorderLayout.CENTER
        );

        Runnable updateCalendar =
                new Runnable() {

                    @Override
                    public void run() {

                        calendarPanel.removeAll();

                        String[] days = {
                                "Sun",
                                "Mon",
                                "Tue",
                                "Wed",
                                "Thu",
                                "Fri",
                                "Sat"
                        };

                        for (String day : days) {

                            JLabel label =
                                    new JLabel(
                                            day,
                                            SwingConstants.CENTER
                                    );

                            label.setFont(
                                    new Font(
                                            "Arial",
                                            Font.BOLD,
                                            12
                                    )
                            );

                            label.setForeground(
                                    SECONDARY_TEXT
                            );

                            calendarPanel.add(
                                    label
                            );
                        }

                        int year =
                                calendar.get(
                                        Calendar.YEAR
                                );

                        int month =
                                calendar.get(
                                        Calendar.MONTH
                                );

                        monthLabel.setText(
                                new java.text.SimpleDateFormat(
                                        "MMMM yyyy"
                                ).format(
                                        calendar.getTime()
                                )
                        );

                        Calendar firstDay =
                                Calendar.getInstance();

                        firstDay.clear();

                        firstDay.set(
                                year,
                                month,
                                1
                        );

                        int firstDayOfWeek =
                                firstDay.get(
                                        Calendar.DAY_OF_WEEK
                                );

                        int daysInMonth =
                                calendar.getActualMaximum(
                                        Calendar.DAY_OF_MONTH
                                );

                        for (
                                int i = 1;
                                i < firstDayOfWeek;
                                i++
                        ) {

                            calendarPanel.add(
                                    new JLabel("")
                            );
                        }

                        for (
                                int day = 1;
                                day <= daysInMonth;
                                day++
                        ) {

                            final int selectedDay =
                                    day;

                            JButton dayButton =
                                    new JButton(
                                            String.valueOf(
                                                    day
                                            )
                                    );

                            dayButton.setFocusPainted(
                                    false
                            );

                            dayButton.setBackground(
                                    WHITE
                            );

                            dayButton.setBorder(
                                    new LineBorder(
                                            BORDER_COLOR
                                    )
                            );

                            dayButton.addActionListener(
                                    e -> {

                                        LocalDate selectedDate =
                                                LocalDate.of(
                                                        year,
                                                        month + 1,
                                                        selectedDay
                                                );

                                        appointmentDateField
                                                .setText(
                                                        selectedDate
                                                                .toString()
                                                );

                                        dialog.dispose();
                                    }
                            );

                            calendarPanel.add(
                                    dayButton
                            );
                        }

                        calendarPanel.revalidate();
                        calendarPanel.repaint();
                    }
                };

        previousButton.addActionListener(e -> {

            calendar.add(
                    Calendar.MONTH,
                    -1
            );

            updateCalendar.run();
        });

        nextButton.addActionListener(e -> {

            calendar.add(
                    Calendar.MONTH,
                    1
            );

            updateCalendar.run();
        });

        updateCalendar.run();

        dialog.add(mainPanel);

        dialog.setVisible(true);
    }

    // =========================================================
    // TIME PICKER
    // =========================================================

    private void showTimePicker() {

        JDialog dialog =
                new JDialog(
                        this,
                        "Select Appointment Time",
                        true
                );

        dialog.setSize(
                350,
                250
        );

        dialog.setLocationRelativeTo(this);

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );

        panel.setBackground(WHITE);

        panel.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        JComboBox<Integer> hourBox =
                new JComboBox<>();

        JComboBox<Integer> minuteBox =
                new JComboBox<>();

        JComboBox<String> amPmBox =
                new JComboBox<>(
                        new String[]{
                                "AM",
                                "PM"
                        }
                );

        for (
                int i = 1;
                i <= 12;
                i++
        ) {

            hourBox.addItem(i);
        }

        for (
                int i = 0;
                i < 60;
                i++
        ) {

            minuteBox.addItem(i);
        }

        styleComboBox(hourBox);
        styleComboBox(minuteBox);
        styleComboBox(amPmBox);

        addTimeRow(
                panel,
                0,
                "Hour",
                hourBox
        );

        addTimeRow(
                panel,
                1,
                "Minute",
                minuteBox
        );

        addTimeRow(
                panel,
                2,
                "AM / PM",
                amPmBox
        );

        JButton selectButton =
                createPrimaryButton(
                        "Select Time"
                );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;

        gbc.insets =
                new Insets(
                        15,
                        5,
                        5,
                        5
                );

        panel.add(
                selectButton,
                gbc
        );

        selectButton.addActionListener(e -> {

            int hour =
                    (Integer)
                            hourBox
                                    .getSelectedItem();

            int minute =
                    (Integer)
                            minuteBox
                                    .getSelectedItem();

            String amPm =
                    (String)
                            amPmBox
                                    .getSelectedItem();

            int hour24 = hour;

            if (
                    amPm.equals("AM")
            ) {

                if (hour == 12) {

                    hour24 = 0;
                }

            } else {

                if (hour != 12) {

                    hour24 =
                            hour + 12;
                }
            }

            LocalTime selectedTime =
                    LocalTime.of(
                            hour24,
                            minute
                    );

            appointmentTimeField
                    .setText(
                            selectedTime.toString()
                    );

            dialog.dispose();
        });

        dialog.add(panel);

        dialog.setVisible(true);
    }

    // =========================================================
    // CREATE CARD
    // =========================================================

    private JPanel createCard() {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        card.setBackground(WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                20,
                                20,
                                15,
                                20
                        )
                )
        );

        return card;
    }

    // =========================================================
    // ADD FORM ROW
    // =========================================================

    private void addFormRow(
            JPanel panel,
            int row,
            String labelText,
            JComponent component
    ) {

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        7,
                        5,
                        7,
                        5
                );

        gbc.anchor =
                GridBagConstraints.WEST;

        // Label

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;

        JLabel label =
                createFormLabel(
                        labelText
                );

        panel.add(
                label,
                gbc
        );

        // Component

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        panel.add(
                component,
                gbc
        );
    }

    // =========================================================
    // ADD TIME ROW
    // =========================================================

    private void addTimeRow(
            JPanel panel,
            int row,
            String labelText,
            JComponent component
    ) {

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        5,
                        5,
                        5,
                        5
                );

        gbc.anchor =
                GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = row;

        JLabel label =
                createFormLabel(
                        labelText
                );

        panel.add(
                label,
                gbc
        );

        gbc.gridx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        panel.add(
                component,
                gbc
        );
    }

    // =========================================================
    // FORM LABEL
    // =========================================================

    private JLabel createFormLabel(
            String text
    ) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(
                TEXT_COLOR
        );

        return label;
    }

    // =========================================================
    // TEXT FIELD
    // =========================================================

    private JTextField createTextField() {

        JTextField field =
                new JTextField();

        field.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        field.setPreferredSize(
                new Dimension(
                        300,
                        38
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER_COLOR
                        ),
                        new EmptyBorder(
                                5,
                                10,
                                5,
                                10
                        )
                )
        );

        field.setBackground(WHITE);

        return field;
    }

    // =========================================================
    // COMBO BOX STYLE
    // =========================================================

    private void styleComboBox(
            JComboBox<?> comboBox
    ) {

        comboBox.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        comboBox.setPreferredSize(
                new Dimension(
                        300,
                        38
                )
        );

        comboBox.setBackground(WHITE);

        comboBox.setForeground(
                TEXT_COLOR
        );

        comboBox.setBorder(
                new LineBorder(
                        BORDER_COLOR
                )
        );
    }

    // =========================================================
    // PRIMARY BUTTON
    // =========================================================

    private JButton createPrimaryButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                PRIMARY_BLUE
        );

        button.setFocusPainted(false);

        button.setBorder(
                new EmptyBorder(
                        10,
                        18,
                        10,
                        18
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // SECONDARY BUTTON
    // =========================================================

    private JButton createSecondaryButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(
                TEXT_COLOR
        );

        button.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        button.setFocusPainted(false);

        button.setBorder(
                new LineBorder(
                        BORDER_COLOR
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(() -> {

            ReceptionFrame frame =
                    new ReceptionFrame();

            frame.setVisible(true);
        });
    }
}