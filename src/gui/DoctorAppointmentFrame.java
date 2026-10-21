package gui;

import model.User;
import service.AppointmentService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DoctorAppointmentFrame extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================

    private final Color PRIMARY_BLUE =
            new Color(25, 118, 210);

    private final Color DARK_BLUE =
            new Color(13, 71, 161);

    private final Color LIGHT_BLUE =
            new Color(232, 240, 254);

    private final Color TEXT_DARK =
            new Color(40, 40, 40);

    private final Color BORDER_COLOR =
            new Color(210, 210, 210);


    // =========================================================
    // USER
    // =========================================================

    private User user;


    // =========================================================
    // COMPONENTS
    // =========================================================

    private JTable appointmentTable;

    private JLabel appointmentCountLabel;

    private AppointmentService appointmentService;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public DoctorAppointmentFrame(
            User user
    ) {

        this.user = user;

        appointmentService =
                new AppointmentService();


        setTitle(
                "XYZ HOSPITAL - Today's Appointments"
        );

        setSize(
                1000,
                650
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);


        createUI();

        loadAppointments();
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
                        22
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
                                15,
                                15
                        )
                );

        contentPanel.setBackground(
                Color.WHITE
        );

        contentPanel.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        20,
                        30
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
                        "Today's Appointments"
                );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        24
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
                        "Appointments scheduled for today"
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

        titlePanel.add(
                Box.createVerticalStrut(10)
        );


        // =====================================================
        // DATE
        // =====================================================

        JLabel dateLabel =
                new JLabel(
                        "Date: "
                                + LocalDate.now().format(
                                DateTimeFormatter.ofPattern(
                                        "dd MMMM yyyy"
                                )
                        )
                );

        dateLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        dateLabel.setForeground(
                DARK_BLUE
        );

        dateLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        titlePanel.add(
                dateLabel
        );


        // =====================================================
        // APPOINTMENT COUNT
        // =====================================================

        appointmentCountLabel =
                new JLabel(
                        "Appointments Today: 0"
                );

        appointmentCountLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        appointmentCountLabel.setForeground(
                PRIMARY_BLUE
        );

        appointmentCountLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        titlePanel.add(
                Box.createVerticalStrut(8)
        );

        titlePanel.add(
                appointmentCountLabel
        );


        contentPanel.add(
                titlePanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // TABLE
        // =====================================================

        String[] columnNames = {

                "Appointment ID",
                "Patient ID",
                "Patient Name",
                "Date",
                "Time",
                "Status"
        };


        appointmentTable =
                new JTable(
                        new Object[0][6],
                        columnNames
                );


        appointmentTable.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        appointmentTable.setRowHeight(
                35
        );

        appointmentTable.setGridColor(
                BORDER_COLOR
        );

        appointmentTable.setSelectionBackground(
                LIGHT_BLUE
        );

        appointmentTable.setSelectionForeground(
                TEXT_DARK
        );

        appointmentTable.setAutoCreateRowSorter(
                true
        );


        appointmentTable.getTableHeader()
                .setFont(
                        new Font(
                                "SansSerif",
                                Font.BOLD,
                                14
                        )
                );

        appointmentTable.getTableHeader()
                .setBackground(
                        PRIMARY_BLUE
                );

        appointmentTable.getTableHeader()
                .setForeground(
                        Color.WHITE
                );

        appointmentTable.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                40
                        )
                );


        JScrollPane scrollPane =
                new JScrollPane(
                        appointmentTable
                );

        scrollPane.setBorder(
                new LineBorder(
                        BORDER_COLOR,
                        1
                )
        );


        contentPanel.add(
                scrollPane,
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
                                5
                        )
                );

        buttonPanel.setBackground(
                Color.WHITE
        );


        JButton refreshButton =
                new JButton(
                        "REFRESH"
                );

        styleButton(
                refreshButton,
                PRIMARY_BLUE
        );


        refreshButton.addActionListener(
                e -> loadAppointments()
        );


        JButton closeButton =
                new JButton(
                        "CLOSE"
                );

        styleButton(
                closeButton,
                new Color(
                        100,
                        100,
                        100
                )
        );


        closeButton.addActionListener(
                e -> dispose()
        );


        buttonPanel.add(
                refreshButton
        );

        buttonPanel.add(
                closeButton
        );


        contentPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        mainPanel.add(
                contentPanel,
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
                        10,
                        25,
                        10,
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


        JLabel footerText =
                new JLabel(
                        "Doctor Appointment Management"
                );

        footerText.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        footerText.setForeground(
                Color.GRAY
        );


        footerPanel.add(
                statusLabel,
                BorderLayout.WEST
        );

        footerPanel.add(
                footerText,
                BorderLayout.EAST
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
    // LOAD APPOINTMENTS
    // =========================================================

    private void loadAppointments() {

        int doctorId =
                user.getDoctorId();


        if (doctorId <= 0) {

            appointmentCountLabel.setText(
                    "Doctor ID not assigned"
            );


            JOptionPane.showMessageDialog(
                    this,
                    "No doctor ID is assigned to this account.",
                    "Doctor Account Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        List<Object[]> appointments =
                appointmentService
                        .getTodaysAppointmentsWithPatientName(
                                doctorId
                        );


        Object[][] data =
                new Object[
                        appointments.size()
                        ][6];


        for (
                int i = 0;
                i < appointments.size();
                i++
        ) {

            Object[] appointment =
                    appointments.get(i);


            data[i][0] =
                    appointment[0];

            data[i][1] =
                    appointment[1];

            data[i][2] =
                    appointment[2];

            data[i][3] =
                    appointment[3];

            data[i][4] =
                    appointment[4];

            data[i][5] =
                    appointment[5];
        }


        String[] columnNames = {

                "Appointment ID",
                "Patient ID",
                "Patient Name",
                "Date",
                "Time",
                "Status"
        };


        appointmentTable.setModel(
                new javax.swing.table.DefaultTableModel(
                        data,
                        columnNames
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {

                        return false;
                    }
                }
        );


        appointmentTable.setRowHeight(
                35
        );


        appointmentTable.setAutoCreateRowSorter(
                true
        );


        appointmentTable.getTableHeader()
                .setFont(
                        new Font(
                                "SansSerif",
                                Font.BOLD,
                                14
                        )
                );


        appointmentTable.getTableHeader()
                .setBackground(
                        PRIMARY_BLUE
                );


        appointmentTable.getTableHeader()
                .setForeground(
                        Color.WHITE
                );


        appointmentTable.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                40
                        )
                );


        appointmentCountLabel.setText(
                "Appointments Today: "
                        + appointments.size()
        );
    }


    // =========================================================
    // STYLE BUTTON
    // =========================================================

    private void styleButton(
            JButton button,
            Color background
    ) {

        button.setPreferredSize(
                new Dimension(
                        130,
                        40
                )
        );

        button.setBackground(
                background
        );

        button.setForeground(
                Color.WHITE
        );

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(
                false
        );

        button.setBorder(
                new EmptyBorder(
                        8,
                        20,
                        8,
                        20
                )
        );
    }
}