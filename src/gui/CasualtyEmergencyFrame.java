package gui;

import model.EmergencyCase;
import service.EmergencyCaseService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CasualtyEmergencyFrame extends JFrame {

    private EmergencyCaseService emergencyCaseService;

    private JTable caseTable;

    private DefaultTableModel tableModel;

    public CasualtyEmergencyFrame() {

        emergencyCaseService =
                new EmergencyCaseService();

        setTitle(
                "XYZ HOSPITAL - Incoming Emergency Cases"
        );

        setSize(
                1300,
                700
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);

        // ==========================================
        // MAIN PANEL
        // ==========================================

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

        // ==========================================
        // HEADER
        // ==========================================

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

        JLabel portalLabel =
                new JLabel(
                        "CASUALTY - INCOMING EMERGENCY CASES"
                );

        portalLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        portalLabel.setForeground(
                new Color(
                        220,
                        235,
                        255
                )
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

        // ==========================================
        // TITLE PANEL
        // ==========================================

        JPanel titlePanel =
                new JPanel(
                        new BorderLayout()
                );

        titlePanel.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        titlePanel.setBorder(
                new EmptyBorder(
                        18,
                        20,
                        10,
                        20
                )
        );

        JLabel titleLabel =
                new JLabel(
                        "Incoming Emergency Patients"
                );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );

        titleLabel.setForeground(
                new Color(
                        35,
                        45,
                        55
                )
        );

        JLabel infoLabel =
                new JLabel(
                        "Emergency information received from ambulance"
                );

        infoLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        infoLabel.setForeground(
                new Color(
                        100,
                        110,
                        120
                )
        );

        titlePanel.add(
                titleLabel,
                BorderLayout.WEST
        );

        titlePanel.add(
                infoLabel,
                BorderLayout.EAST
        );

        // ==========================================
        // CENTER PANEL
        // ==========================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout()
                );

        centerPanel.setBackground(
                new Color(
                        245,
                        247,
                        250
                )
        );

        centerPanel.add(
                titlePanel,
                BorderLayout.NORTH
        );

        // ==========================================
        // TABLE
        // ==========================================

        String[] columns = {

                "Emergency ID",
                "Patient",
                "Age",
                "Gender",
                "Blood Group",
                "Condition",
                "Symptoms",
                "Ambulance",
                "ETA",
                "Heart Rate",
                "SpO₂",
                "Temperature",
                "BP",
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
                32
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

        // ==========================================
        // COLUMN WIDTHS
        // ==========================================

        caseTable.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(80);

        caseTable.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(130);

        caseTable.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(50);

        caseTable.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(70);

        caseTable.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(80);

        caseTable.getColumnModel()
                .getColumn(5)
                .setPreferredWidth(150);

        caseTable.getColumnModel()
                .getColumn(6)
                .setPreferredWidth(170);

        caseTable.getColumnModel()
                .getColumn(7)
                .setPreferredWidth(90);

        caseTable.getColumnModel()
                .getColumn(8)
                .setPreferredWidth(70);

        caseTable.getColumnModel()
                .getColumn(9)
                .setPreferredWidth(90);

        caseTable.getColumnModel()
                .getColumn(10)
                .setPreferredWidth(70);

        caseTable.getColumnModel()
                .getColumn(11)
                .setPreferredWidth(100);

        caseTable.getColumnModel()
                .getColumn(12)
                .setPreferredWidth(100);

        caseTable.getColumnModel()
                .getColumn(13)
                .setPreferredWidth(90);

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

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // ==========================================
        // BUTTON PANEL
        // ==========================================

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

        JButton receivedButton =
                new JButton(
                        "MARK RECEIVED"
                );

        receivedButton.setPreferredSize(
                new Dimension(
                        160,
                        40
                )
        );

        receivedButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        receivedButton.setFocusPainted(
                false
        );

        JButton arrivedButton =
                new JButton(
                        "MARK ARRIVED"
                );

        arrivedButton.setPreferredSize(
                new Dimension(
                        150,
                        40
                )
        );

        arrivedButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        arrivedButton.setFocusPainted(
                false
        );

        JButton treatedButton =
                new JButton(
                        "MARK TREATED"
                );

        treatedButton.setPreferredSize(
                new Dimension(
                        150,
                        40
                )
        );

        treatedButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        treatedButton.setFocusPainted(
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

        // ==========================================
        // BUTTON ACTIONS
        // ==========================================

        refreshButton.addActionListener(
                e -> loadEmergencyCases()
        );

        receivedButton.addActionListener(
                e -> updateSelectedStatus(
                        "RECEIVED"
                )
        );

        arrivedButton.addActionListener(
                e -> updateSelectedStatus(
                        "ARRIVED"
                )
        );

        treatedButton.addActionListener(
                e -> updateSelectedStatus(
                        "TREATED"
                )
        );

        closeButton.addActionListener(
                e -> dispose()
        );

        buttonPanel.add(
                refreshButton
        );

        buttonPanel.add(
                receivedButton
        );

        buttonPanel.add(
                arrivedButton
        );

        buttonPanel.add(
                treatedButton
        );

        buttonPanel.add(
                closeButton
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(
                mainPanel
        );

        // ==========================================
        // LOAD DATA
        // ==========================================

        loadEmergencyCases();
    }

    // ==========================================
    // LOAD INCOMING CASES
    // ==========================================

    private void loadEmergencyCases() {

        tableModel.setRowCount(
                0
        );

        List<EmergencyCase> cases =
                emergencyCaseService
                        .getIncomingEmergencyCases();

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
                            .getSymptoms(),

                    emergencyCase
                            .getAmbulanceNumber(),

                    emergencyCase
                            .getEta(),

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

                    emergencyCase
                            .getStatus()

            };

            tableModel.addRow(
                    row
            );
        }
    }

    // ==========================================
    // UPDATE STATUS
    // ==========================================

    private void updateSelectedStatus(
            String newStatus
    ) {

        int selectedRow =
                caseTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select an emergency case first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int emergencyId =
                Integer.parseInt(
                        caseTable
                                .getValueAt(
                                        selectedRow,
                                        0
                                )
                                .toString()
                );

        boolean updated =
                emergencyCaseService
                        .updateEmergencyStatus(
                                emergencyId,
                                newStatus
                        );

        if (updated) {

            JOptionPane.showMessageDialog(
                    this,
                    "Emergency case status updated to "
                            + newStatus
                            + ".",
                    "Status Updated",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadEmergencyCases();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Could not update emergency case status.",
                    "Update Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ==========================================
    // MAIN
    // ==========================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    new CasualtyEmergencyFrame()
                            .setVisible(true);

                }
        );
    }
}