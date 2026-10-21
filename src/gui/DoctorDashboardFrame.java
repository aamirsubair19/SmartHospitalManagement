package gui;

import model.User;
import service.AppointmentService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DoctorDashboardFrame extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================

    private final Color PRIMARY_BLUE =
            new Color(25, 118, 210);

    private final Color DARK_BLUE =
            new Color(13, 71, 161);

    private final Color TEXT_DARK =
            new Color(40, 40, 40);

    private final Color CARD_BORDER =
            new Color(220, 220, 220);


    // =========================================================
    // LOGGED-IN USER
    // =========================================================

    private User user;


    // =========================================================
    // SERVICES
    // =========================================================

    private AppointmentService appointmentService;


    // =========================================================
    // DASHBOARD COUNT LABELS
    // =========================================================

    private JLabel appointmentsCountLabel;

    private JLabel waitingPatientsCountLabel;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public DoctorDashboardFrame(
            User user
    ) {

        this.user = user;


        appointmentService =
                new AppointmentService();


        setTitle(
                "XYZ HOSPITAL - Doctor Dashboard"
        );

        setSize(
                1100,
                750
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);


        createUI();


        // Load actual values from database

        loadDashboardData();
    }


    // =========================================================
    // CREATE USER INTERFACE
    // =========================================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                Color.WHITE
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
                PRIMARY_BLUE
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

        hospitalLabel.setForeground(
                Color.WHITE
        );

        hospitalLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        24
                )
        );

        hospitalLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JLabel systemLabel =
                new JLabel(
                        "Smart Hospital Management System"
                );

        systemLabel.setForeground(
                Color.WHITE
        );

        systemLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        systemLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JLabel portalLabel =
                new JLabel(
                        "DOCTOR PORTAL"
                );

        portalLabel.setForeground(
                Color.WHITE
        );

        portalLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        portalLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JLabel welcomeLabel =
                new JLabel(
                        "Welcome, "
                                + user.getUsername()
                );

        welcomeLabel.setForeground(
                Color.WHITE
        );

        welcomeLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        welcomeLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        headerPanel.add(
                hospitalLabel
        );

        headerPanel.add(
                Box.createVerticalStrut(5)
        );

        headerPanel.add(
                systemLabel
        );

        headerPanel.add(
                Box.createVerticalStrut(8)
        );

        headerPanel.add(
                portalLabel
        );

        headerPanel.add(
                Box.createVerticalStrut(8)
        );

        headerPanel.add(
                welcomeLabel
        );


        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // CONTENT PANEL
        // =====================================================

        JPanel contentPanel =
                new JPanel(
                        new BorderLayout(
                                20,
                                20
                        )
                );

        contentPanel.setBackground(
                Color.WHITE
        );

        contentPanel.setBorder(
                new EmptyBorder(
                        25,
                        40,
                        20,
                        40
                )
        );


        // =====================================================
        // TITLE SECTION
        // =====================================================

        JPanel titlePanel =
                new JPanel();

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        titlePanel.setBackground(
                Color.WHITE
        );


        JLabel titleLabel =
                new JLabel(
                        "Doctor Dashboard"
                );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28
                )
        );

        titleLabel.setForeground(
                TEXT_DARK
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JLabel subtitleLabel =
                new JLabel(
                        "Manage appointments, patients and consultations"
                );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        subtitleLabel.setForeground(
                Color.GRAY
        );

        subtitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        titlePanel.add(
                titleLabel
        );

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        titlePanel.add(
                subtitleLabel
        );


        contentPanel.add(
                titlePanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // MAIN CENTER PANEL
        // =====================================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                20,
                                20
                        )
                );

        centerPanel.setBackground(
                Color.WHITE
        );


        // =====================================================
        // TOP STAT CARDS
        // =====================================================

        JPanel statsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                25,
                                0
                        )
                );

        statsPanel.setBackground(
                Color.WHITE
        );


        JPanel appointmentsCard =
                createStatCard(
                        "Today's Appointments",
                        "0",
                        "Scheduled for today"
                );


        appointmentsCountLabel =
                findValueLabel(
                        appointmentsCard
                );


        JPanel waitingCard =
                createStatCard(
                        "Waiting Patients",
                        "0",
                        "Patients currently waiting"
                );


        waitingPatientsCountLabel =
                findValueLabel(
                        waitingCard
                );


        statsPanel.add(
                appointmentsCard
        );

        statsPanel.add(
                waitingCard
        );


        // =====================================================
        // ACTION PANELS
        // =====================================================

        JPanel actionsPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                20,
                                20
                        )
                );

        actionsPanel.setBackground(
                Color.WHITE
        );


        actionsPanel.add(
                createActionPanel(
                        "Today's Appointments",
                        "View and manage today's appointments",
                        "VIEW APPOINTMENTS"
                )
        );


        actionsPanel.add(
                createActionPanel(
                        "Patient Information",
                        "Search and view patient details",
                        "VIEW PATIENTS"
                )
        );


        actionsPanel.add(
                createActionPanel(
                        "Consultation",
                        "Record patient consultation details",
                        "START CONSULTATION"
                )
        );


        actionsPanel.add(
                createActionPanel(
                        "Prescriptions",
                        "Create and manage prescriptions",
                        "MANAGE PRESCRIPTIONS"
                )
        );


        // =====================================================
        // ADD STATS AND ACTIONS
        // =====================================================

        centerPanel.add(
                statsPanel,
                BorderLayout.NORTH
        );


        centerPanel.add(
                actionsPanel,
                BorderLayout.CENTER
        );


        contentPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // FOOTER
        // =====================================================

        JPanel footerPanel =
                new JPanel(
                        new BorderLayout()
                );

        footerPanel.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        footerPanel.setBorder(
                new EmptyBorder(
                        12,
                        25,
                        12,
                        25
                )
        );


        JLabel statusLabel =
                new JLabel(
                        "● SYSTEM ONLINE"
                );

        statusLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        statusLabel.setForeground(
                new Color(
                        46,
                        125,
                        50
                )
        );


        JButton logoutButton =
                new JButton(
                        "LOGOUT"
                );

        logoutButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        logoutButton.setForeground(
                Color.WHITE
        );

        logoutButton.setBackground(
                new Color(
                        198,
                        40,
                        40
                )
        );

        logoutButton.setFocusPainted(
                false
        );

        logoutButton.setBorder(
                new EmptyBorder(
                        8,
                        18,
                        8,
                        18
                )
        );


        logoutButton.addActionListener(
                e -> logout()
        );


        footerPanel.add(
                statusLabel,
                BorderLayout.WEST
        );

        footerPanel.add(
                logoutButton,
                BorderLayout.EAST
        );


        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                footerPanel,
                BorderLayout.SOUTH
        );


        setContentPane(
                mainPanel
        );
    }


    // =========================================================
    // LOAD DASHBOARD DATA
    // =========================================================

    private void loadDashboardData() {

        int doctorId =
                user.getDoctorId();


        // Check doctor ID

        if (doctorId <= 0) {

            appointmentsCountLabel.setText(
                    "0"
            );

            waitingPatientsCountLabel.setText(
                    "0"
            );


            JOptionPane.showMessageDialog(
                    this,
                    "No doctor ID is assigned to this account.",
                    "Doctor Account Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        // =====================================================
        // TODAY'S APPOINTMENTS
        // =====================================================

        int appointmentCount =
                appointmentService
                        .getTodaysAppointmentCount(
                                doctorId
                        );


        appointmentsCountLabel.setText(
                String.valueOf(
                        appointmentCount
                )
        );


        // =====================================================
        // WAITING PATIENTS
        // =====================================================

        int waitingCount =
                appointmentService
                        .getWaitingPatientCount(
                                doctorId
                        );


        waitingPatientsCountLabel.setText(
                String.valueOf(
                        waitingCount
                )
        );
    }


    // =========================================================
    // CREATE STAT CARD
    // =========================================================

    private JPanel createStatCard(
            String title,
            String value,
            String description
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                new LineBorder(
                        CARD_BORDER,
                        1,
                        true
                )
        );


        JPanel innerPanel =
                new JPanel();

        innerPanel.setLayout(
                new BoxLayout(
                        innerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        innerPanel.setBackground(
                Color.WHITE
        );

        innerPanel.setBorder(
                new EmptyBorder(
                        15,
                        20,
                        15,
                        20
                )
        );


        JLabel titleLabel =
                new JLabel(
                        title
                );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );

        titleLabel.setForeground(
                TEXT_DARK
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JLabel valueLabel =
                new JLabel(
                        value
                );

        valueLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        30
                )
        );

        valueLabel.setForeground(
                PRIMARY_BLUE
        );

        valueLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JLabel descriptionLabel =
                new JLabel(
                        description
                );

        descriptionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        descriptionLabel.setForeground(
                Color.GRAY
        );

        descriptionLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        innerPanel.add(
                titleLabel
        );

        innerPanel.add(
                Box.createVerticalStrut(6)
        );

        innerPanel.add(
                valueLabel
        );

        innerPanel.add(
                Box.createVerticalStrut(4)
        );

        innerPanel.add(
                descriptionLabel
        );


        card.add(
                innerPanel,
                BorderLayout.CENTER
        );


        return card;
    }


    // =========================================================
    // FIND VALUE LABEL
    // =========================================================

    private JLabel findValueLabel(
            JPanel card
    ) {

        Component[] components =
                card.getComponents();


        if (
                components.length == 0
        ) {

            return new JLabel("0");
        }


        JPanel innerPanel =
                (JPanel) components[0];


        Component[] innerComponents =
                innerPanel.getComponents();


        for (
                Component component :
                innerComponents
        ) {

            if (
                    component instanceof JLabel
                            && ((JLabel) component)
                            .getFont()
                            .getSize() == 30
            ) {

                return (JLabel) component;
            }
        }


        return new JLabel("0");
    }


    // =========================================================
    // CREATE ACTION PANEL
    // =========================================================

    private JPanel createActionPanel(
            String title,
            String description,
            String buttonText
    ) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                5
                        )
                );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                new LineBorder(
                        CARD_BORDER,
                        1,
                        true
                )
        );


        // =====================================================
        // TEXT PANEL
        // =====================================================

        JPanel textPanel =
                new JPanel();

        textPanel.setLayout(
                new BoxLayout(
                        textPanel,
                        BoxLayout.Y_AXIS
                )
        );

        textPanel.setBackground(
                Color.WHITE
        );

        textPanel.setBorder(
                new EmptyBorder(
                        15,
                        20,
                        5,
                        20
                )
        );


        JLabel titleLabel =
                new JLabel(
                        title
                );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        titleLabel.setForeground(
                TEXT_DARK
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        // =====================================================
        // DESCRIPTION
        // =====================================================

        JLabel descriptionLabel =
                new JLabel(
                        "<html>"
                                + "<div style='text-align:center;'>"
                                + description
                                + "</div>"
                                + "</html>"
                );

        descriptionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        descriptionLabel.setForeground(
                Color.GRAY
        );

        descriptionLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        textPanel.add(
                titleLabel
        );

        textPanel.add(
                Box.createVerticalStrut(7)
        );

        textPanel.add(
                descriptionLabel
        );


        // =====================================================
        // BUTTON
        // =====================================================

        JButton actionButton =
                new JButton(
                        buttonText
                );

        actionButton.setPreferredSize(
                new Dimension(
                        190,
                        38
                )
        );

        actionButton.setBackground(
                PRIMARY_BLUE
        );

        actionButton.setForeground(
                Color.WHITE
        );

        actionButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        actionButton.setFocusPainted(
                false
        );

        actionButton.setBorder(
                new EmptyBorder(
                        8,
                        15,
                        8,
                        15
                )
        );


        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        if (
                buttonText.equals(
                        "VIEW APPOINTMENTS"
                )
        ) {

            actionButton.addActionListener(
                    e -> {

                        new DoctorAppointmentFrame(
                                user
                        ).setVisible(true);
                    }
            );
        }


        else if (
                buttonText.equals(
                        "VIEW PATIENTS"
                )
        ) {

            actionButton.addActionListener(
                    e -> {

                        new DoctorPatientFrame()
                                .setVisible(true);
                    }
            );
        }


        else if (
                buttonText.equals(
                        "START CONSULTATION"
                )
        ) {

            actionButton.addActionListener(
                    e -> {

                        new DoctorConsultationFrame()
                                .setVisible(true);
                    }
            );
        }


        else if (
                buttonText.equals(
                        "MANAGE PRESCRIPTIONS"
                )
        ) {

            actionButton.addActionListener(
                    e -> {

                        new DoctorPrescriptionFrame()
                                .setVisible(true);
                    }
            );
        }


        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER
                        )
                );

        buttonPanel.setBackground(
                Color.WHITE
        );

        buttonPanel.setBorder(
                new EmptyBorder(
                        0,
                        10,
                        12,
                        10
                )
        );


        buttonPanel.add(
                actionButton
        );


        // =====================================================
        // ADD COMPONENTS
        // =====================================================

        panel.add(
                textPanel,
                BorderLayout.CENTER
        );

        panel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        return panel;
    }


    // =========================================================
    // LOGOUT
    // =========================================================

    private void logout() {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );


        if (
                choice ==
                        JOptionPane.YES_OPTION
        ) {

            new LoginFrame()
                    .setVisible(true);

            dispose();
        }
    }


    // =========================================================
    // MAIN - TESTING ONLY
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    /*
                     * TEST USER
                     *
                     * doctor_id = 1
                     *
                     * Make sure doctor1 in the users table
                     * is associated with doctor_id = 1.
                     */

                    User testUser =
                            new User(
                                    1,
                                    "doctor1",
                                    "1234",
                                    "DOCTOR",
                                    1
                            );


                    new DoctorDashboardFrame(
                            testUser
                    ).setVisible(true);
                }
        );
    }
}