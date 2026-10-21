package gui;

import model.EmergencyCase;
import service.EmergencyCaseService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AmbulanceCaseFrame extends JFrame {

    private EmergencyCaseService emergencyCaseService;

    private JTable caseTable;

    private DefaultTableModel tableModel;

    private JLabel countLabel;


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public AmbulanceCaseFrame() {

        emergencyCaseService =
                new EmergencyCaseService();

        setTitle(
                "XYZ HOSPITAL - Sent Emergency Cases"
        );

        setSize(
                1250,
                650
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);


        // =====================================================
        // MAIN PANEL
        // =====================================================

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
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
                new Color(
                        30,
                        90,
                        160
                )
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


        JLabel titleLabel =
                new JLabel(
                        "SENT EMERGENCY CASES"
                );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        titleLabel.setForeground(
                new Color(
                        220,
                        235,
                        255
                )
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        headerPanel.add(
                hospitalLabel
        );

        headerPanel.add(
                Box.createVerticalStrut(8)
        );

        headerPanel.add(
                titleLabel
        );


        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // TOP INFORMATION PANEL
        // =====================================================

        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );

        topPanel.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        topPanel.setBorder(
                new EmptyBorder(
                        15,
                        20,
                        10,
                        20
                )
        );


        JLabel pageTitle =
                new JLabel(
                        "Emergency Cases"
                );

        pageTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );

        pageTitle.setForeground(
                new Color(
                        35,
                        45,
                        55
                )
        );


        countLabel =
                new JLabel(
                        "Total Cases: 0"
                );

        countLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        countLabel.setForeground(
                new Color(
                        100,
                        110,
                        120
                )
        );


        topPanel.add(
                pageTitle,
                BorderLayout.WEST
        );

        topPanel.add(
                countLabel,
                BorderLayout.EAST
        );


        mainPanel.add(
                topPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // TABLE
        // =====================================================

        String[] columns = {

                "Emergency ID",
                "Patient Name",
                "Age",
                "Gender",
                "Blood Group",
                "Condition",
                "Ambulance No",
                "ETA",
                "Heart Rate",
                "SpO₂",
                "Temperature",
                "Blood Pressure",
                "Status"
        };


        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {

                        return false;
                    }
                };


        caseTable =
                new JTable(
                        tableModel
                );


        caseTable.setRowHeight(
                30
        );

        caseTable.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        caseTable.getTableHeader()
                .setFont(
                        new Font(
                                "SansSerif",
                                Font.BOLD,
                                12
                        )
                );

        caseTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );


        // =====================================================
        // TABLE SCROLL PANE
        // =====================================================

        JScrollPane scrollPane =
                new JScrollPane(
                        caseTable
                );

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        20,
                        10,
                        20
                )
        );


        mainPanel.add(
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
                                12
                        )
                );

        buttonPanel.setBackground(
                Color.WHITE
        );


        JButton refreshButton =
                new JButton(
                        "REFRESH"
                );

        refreshButton.setPreferredSize(
                new Dimension(
                        130,
                        40
                )
        );

        refreshButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        refreshButton.setFocusPainted(
                false
        );


        JButton closeButton =
                new JButton(
                        "CLOSE"
                );

        closeButton.setPreferredSize(
                new Dimension(
                        120,
                        40
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

        refreshButton.addActionListener(
                e -> loadEmergencyCases()
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


        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        add(mainPanel);


        // =====================================================
        // LOAD DATA
        // =====================================================

        loadEmergencyCases();
    }


    // =====================================================
    // LOAD EMERGENCY CASES
    // =====================================================

    private void loadEmergencyCases() {

        tableModel.setRowCount(0);


        List<EmergencyCase> cases =
                emergencyCaseService
                        .getAllEmergencyCases();


        for (
                EmergencyCase emergencyCase
                : cases
        ) {

            Object[] row = {

                    emergencyCase.getEmergencyId(),

                    emergencyCase.getPatientName(),

                    emergencyCase.getAge(),

                    emergencyCase.getGender(),

                    emergencyCase.getBloodGroup(),

                    emergencyCase
                            .getConditionDescription(),

                    emergencyCase
                            .getAmbulanceNumber(),

                    emergencyCase.getEta(),

                    emergencyCase
                            .getHeartRate()
                            + " bpm",

                    emergencyCase
                            .getSpo2()
                            + " %",

                    emergencyCase
                            .getTemperature()
                            + " °C",

                    emergencyCase
                            .getBloodPressure()
                            + " mmHg",

                    emergencyCase.getStatus()
            };


            tableModel.addRow(
                    row
            );
        }


        countLabel.setText(
                "Total Cases: "
                        + cases.size()
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

                    new AmbulanceCaseFrame()
                            .setVisible(true);

                }
        );
    }
}