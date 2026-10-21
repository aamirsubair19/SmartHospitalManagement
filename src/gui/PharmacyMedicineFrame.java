package gui;

import model.Medicine;
import service.MedicineService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;

public class PharmacyMedicineFrame
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

    private MedicineService medicineService;


    // =========================================================
    // TABLE
    // =========================================================

    private JTable medicineTable;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public PharmacyMedicineFrame() {

        medicineService =
                new MedicineService();


        setTitle(
                "XYZ HOSPITAL - Medicine Stock"
        );

        setSize(
                850,
                600
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);


        createUI();

        loadMedicines();
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
                        "Medicine Stock"
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
        // TABLE
        // =====================================================

        medicineTable =
                new JTable();


        medicineTable.setRowHeight(
                35
        );

        medicineTable.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        medicineTable.setAutoCreateRowSorter(
                true
        );


        medicineTable
                .getTableHeader()
                .setFont(
                        new Font(
                                "SansSerif",
                                Font.BOLD,
                                13
                        )
                );

        medicineTable
                .getTableHeader()
                .setBackground(
                        PRIMARY_BLUE
                );

        medicineTable
                .getTableHeader()
                .setForeground(
                        Color.WHITE
                );

        medicineTable
                .getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                40
                        )
                );


        JScrollPane scrollPane =
                new JScrollPane(
                        medicineTable
                );

        scrollPane.setBorder(
                new LineBorder(
                        CARD_BORDER
                )
        );


        mainPanel.add(
                scrollPane,
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
                e -> loadMedicines()
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
    // LOAD MEDICINES
    // =========================================================

    private void loadMedicines() {

        List<Medicine> medicines =
                medicineService
                        .getAllMedicines();


        Object[][] data =
                new Object[
                        medicines.size()
                        ][4];


        for (
                int i = 0;
                i < medicines.size();
                i++
        ) {

            Medicine medicine =
                    medicines.get(i);


            data[i][0] =
                    medicine.getMedicineId();

            data[i][1] =
                    medicine.getMedicineName();

            data[i][2] =
                    medicine.getQuantity();

            data[i][3] =
                    "₹ "
                            + String.format(
                            "%.2f",
                            medicine.getPrice()
                    );
        }


        String[] columns = {

                "Medicine ID",
                "Medicine Name",
                "Available Stock",
                "Price"
        };


        medicineTable.setModel(
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
    }


    // =========================================================
    // MAIN - TESTING
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    new PharmacyMedicineFrame()
                            .setVisible(true);
                }
        );
    }
}