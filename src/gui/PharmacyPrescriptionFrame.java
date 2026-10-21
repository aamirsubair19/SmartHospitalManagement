package gui;

import service.PrescriptionService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;

public class PharmacyPrescriptionFrame
        extends JFrame {


    // =========================================================
    // COLORS
    // =========================================================

    private final Color PRIMARY_BLUE =
            new Color(25, 118, 210);

    private final Color TEXT_DARK =
            new Color(40, 40, 40);

    private final Color CARD_BORDER =
            new Color(220, 220, 220);


    // =========================================================
    // SERVICE
    // =========================================================

    private PrescriptionService prescriptionService;


    // =========================================================
    // TABLE
    // =========================================================

    private JTable prescriptionTable;

    private JLabel countLabel;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public PharmacyPrescriptionFrame() {

        prescriptionService =
                new PrescriptionService();


        setTitle(
                "XYZ HOSPITAL - Pending Prescriptions"
        );

        setSize(
                1200,
                650
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);


        createUI();

        loadPrescriptions();
    }


    // =========================================================
    // CREATE UI
    // =========================================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        mainPanel.setBackground(
                Color.WHITE
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        20,
                        25
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
                PRIMARY_BLUE
        );

        headerPanel.setBorder(
                new EmptyBorder(
                        15,
                        20,
                        15,
                        20
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


        JLabel titleLabel =
                new JLabel(
                        "Pending Prescriptions"
                );

        titleLabel.setForeground(
                Color.WHITE
        );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        headerPanel.add(
                hospitalLabel
        );

        headerPanel.add(
                Box.createVerticalStrut(5)
        );

        headerPanel.add(
                titleLabel
        );


        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // COUNT
        // =====================================================

        countLabel =
                new JLabel(
                        "Pending Prescriptions: 0"
                );

        countLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        countLabel.setForeground(
                TEXT_DARK
        );


        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );

        topPanel.setBackground(
                Color.WHITE
        );

        topPanel.add(
                countLabel,
                BorderLayout.WEST
        );


        // =====================================================
        // TABLE
        // =====================================================

        prescriptionTable =
                new JTable();


        prescriptionTable.setRowHeight(
                35
        );

        prescriptionTable.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        prescriptionTable.setAutoCreateRowSorter(
                true
        );


        prescriptionTable
                .getTableHeader()
                .setFont(
                        new Font(
                                "SansSerif",
                                Font.BOLD,
                                12
                        )
                );

        prescriptionTable
                .getTableHeader()
                .setBackground(
                        PRIMARY_BLUE
                );

        prescriptionTable
                .getTableHeader()
                .setForeground(
                        Color.WHITE
                );

        prescriptionTable
                .getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                40
                        )
                );


        JScrollPane scrollPane =
                new JScrollPane(
                        prescriptionTable
                );

        scrollPane.setBorder(
                new LineBorder(
                        CARD_BORDER
                )
        );


        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        centerPanel.setBackground(
                Color.WHITE
        );

        centerPanel.add(
                topPanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // BUTTONS
        // =====================================================

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

        refreshButton.setBackground(
                PRIMARY_BLUE
        );

        refreshButton.setForeground(
                Color.WHITE
        );

        refreshButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        refreshButton.setFocusPainted(
                false
        );


        refreshButton.addActionListener(
                e -> loadPrescriptions()
        );


        JButton dispenseButton =
                new JButton(
                        "DISPENSE MEDICINE"
                );

        dispenseButton.setPreferredSize(
                new Dimension(
                        190,
                        40
                )
        );

        dispenseButton.setBackground(
                new Color(
                        46,
                        125,
                        50
                )
        );

        dispenseButton.setForeground(
                Color.WHITE
        );

        dispenseButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        dispenseButton.setFocusPainted(
                false
        );


        dispenseButton.addActionListener(
                e -> dispenseSelectedPrescription()
        );


        JButton closeButton =
                new JButton(
                        "CLOSE"
                );

        closeButton.setPreferredSize(
                new Dimension(
                        100,
                        40
                )
        );

        closeButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        closeButton.setFocusPainted(
                false
        );


        closeButton.addActionListener(
                e -> dispose()
        );


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


        buttonPanel.add(
                refreshButton
        );

        buttonPanel.add(
                dispenseButton
        );

        buttonPanel.add(
                closeButton
        );


        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        setContentPane(
                mainPanel
        );
    }


    // =========================================================
    // LOAD PRESCRIPTIONS
    // =========================================================

    private void loadPrescriptions() {

        List<Object[]> prescriptions =
                prescriptionService
                        .getPendingPrescriptions();


        Object[][] data =
                new Object[
                        prescriptions.size()
                        ][10];


        for (
                int i = 0;
                i < prescriptions.size();
                i++
        ) {

            Object[] prescription =
                    prescriptions.get(i);


            data[i][0] =
                    prescription[0];

            data[i][1] =
                    prescription[2];

            data[i][2] =
                    prescription[3];

            data[i][3] =
                    prescription[5];

            data[i][4] =
                    prescription[7];

            data[i][5] =
                    prescription[8];

            data[i][6] =
                    prescription[9];

            data[i][7] =
                    prescription[10];

            data[i][8] =
                    prescription[11];

            data[i][9] =
                    prescription[12];
        }


        String[] columns = {

                "Prescription ID",
                "Patient ID",
                "Patient Name",
                "Doctor",
                "Medicine",
                "Stock",
                "Dosage",
                "Duration",
                "Instructions",
                "Status"
        };


        prescriptionTable.setModel(
                new DefaultTableModel(
                        data,
                        columns
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


        countLabel.setText(
                "Pending Prescriptions: "
                        + prescriptions.size()
        );
    }


    // =========================================================
    // DISPENSE SELECTED PRESCRIPTION
    // =========================================================

    private void dispenseSelectedPrescription() {

        int selectedRow =
                prescriptionTable.getSelectedRow();


        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a prescription first.",
                    "Selection Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        int modelRow =
                prescriptionTable
                        .convertRowIndexToModel(
                                selectedRow
                        );


        int prescriptionId =
                Integer.parseInt(
                        prescriptionTable
                                .getModel()
                                .getValueAt(
                                        modelRow,
                                        0
                                )
                                .toString()
                );


        String medicine =
                prescriptionTable
                        .getModel()
                        .getValueAt(
                                modelRow,
                                4
                        )
                        .toString();


        String patient =
                prescriptionTable
                        .getModel()
                        .getValueAt(
                                modelRow,
                                2
                        )
                        .toString();


        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Dispense " + medicine
                                + " to patient "
                                + patient
                                + "?",
                        "Confirm Dispensing",
                        JOptionPane.YES_NO_OPTION
                );


        if (
                choice !=
                        JOptionPane.YES_OPTION
        ) {

            return;
        }


        boolean success =
                prescriptionService
                        .dispensePrescription(
                                prescriptionId
                        );


        if (success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Medicine dispensed successfully.\n"
                            + "Prescription marked as DISPENSED.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );


            loadPrescriptions();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Medicine could not be dispensed.\n\n"
                            + "Possible reasons:\n"
                            + "• Medicine is out of stock\n"
                            + "• Prescription is already dispensed\n"
                            + "• Database error",
                    "Dispensing Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================================================
    // MAIN - TESTING
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    new PharmacyPrescriptionFrame()
                            .setVisible(true);
                }
        );
    }
}