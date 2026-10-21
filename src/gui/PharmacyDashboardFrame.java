package gui;

import service.MedicineService;
import service.PrescriptionService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PharmacyDashboardFrame extends JFrame {

    private PrescriptionService prescriptionService;
    private MedicineService medicineService;

    private JLabel pendingCountLabel;
    private JLabel medicineCountLabel;
    private JLabel lowStockCountLabel;

    public PharmacyDashboardFrame() {

        prescriptionService =
                new PrescriptionService();

        medicineService =
                new MedicineService();

        setTitle("XYZ HOSPITAL - Pharmacy Dashboard");

        setSize(1100, 750);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setResizable(false);

        createGUI();

        loadDashboardData();
    }


    private void createGUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(
                new Color(245, 247, 250)
        );


        // =========================
        // HEADER
        // =========================

        JPanel headerPanel =
                new JPanel();

        headerPanel.setLayout(
                new BoxLayout(
                        headerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        headerPanel.setBackground(
                new Color(25, 118, 210)
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
                        "Arial",
                        Font.BOLD,
                        26
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
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        systemLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JLabel portalLabel =
                new JLabel(
                        "PHARMACY PORTAL"
                );

        portalLabel.setForeground(
                Color.WHITE
        );

        portalLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        portalLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        headerPanel.add(hospitalLabel);

        headerPanel.add(
                Box.createVerticalStrut(5)
        );

        headerPanel.add(systemLabel);

        headerPanel.add(
                Box.createVerticalStrut(8)
        );

        headerPanel.add(portalLabel);


        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );


        // =========================
        // CENTER CONTENT
        // =========================

        JPanel contentPanel =
                new JPanel();

        contentPanel.setLayout(
                new BoxLayout(
                        contentPanel,
                        BoxLayout.Y_AXIS
                )
        );

        contentPanel.setBackground(
                new Color(245, 247, 250)
        );

        contentPanel.setBorder(
                new EmptyBorder(
                        30,
                        40,
                        20,
                        40
                )
        );


        JLabel titleLabel =
                new JLabel(
                        "Pharmacy Dashboard"
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        titleLabel.setForeground(
                new Color(35, 45, 55)
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JLabel subtitleLabel =
                new JLabel(
                        "Manage prescriptions and medicine stock"
                );

        subtitleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        subtitleLabel.setForeground(
                new Color(100, 110, 120)
        );

        subtitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        contentPanel.add(titleLabel);

        contentPanel.add(
                Box.createVerticalStrut(5)
        );

        contentPanel.add(subtitleLabel);

        contentPanel.add(
                Box.createVerticalStrut(25)
        );


        // =========================
        // STAT CARDS
        // =========================

        JPanel statsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                0
                        )
                );

        statsPanel.setMaximumSize(
                new Dimension(
                        900,
                        130
                )
        );

        statsPanel.setBackground(
                new Color(245, 247, 250)
        );


        JPanel pendingCard =
                createStatCard(
                        "Pending Prescriptions",
                        "0"
                );

        pendingCountLabel =
                getCountLabel(
                        pendingCard
                );


        JPanel medicineCard =
                createStatCard(
                        "Total Medicines",
                        "0"
                );

        medicineCountLabel =
                getCountLabel(
                        medicineCard
                );


        JPanel lowStockCard =
                createStatCard(
                        "Low Stock Medicines",
                        "0"
                );

        lowStockCountLabel =
                getCountLabel(
                        lowStockCard
                );


        statsPanel.add(
                pendingCard
        );

        statsPanel.add(
                medicineCard
        );

        statsPanel.add(
                lowStockCard
        );


        contentPanel.add(
                statsPanel
        );

        contentPanel.add(
                Box.createVerticalStrut(35)
        );


        // =========================
        // ACTION CARDS
        // =========================

        JPanel actionPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                30,
                                0
                        )
                );

        actionPanel.setMaximumSize(
                new Dimension(
                        850,
                        220
                )
        );

        actionPanel.setBackground(
                new Color(245, 247, 250)
        );


        JPanel prescriptionCard =
                createActionCard(
                        "Pending Prescriptions",
                        "View and dispense medicines",
                        "VIEW PRESCRIPTIONS"
                );


        JButton prescriptionButton =
                (JButton)
                        prescriptionCard.getClientProperty(
                                "button"
                        );


        prescriptionButton.addActionListener(
                e -> {

                    new PharmacyPrescriptionFrame()
                            .setVisible(true);
                }
        );


        JPanel medicineActionCard =
                createActionCard(
                        "Medicine Stock",
                        "View available medicines and stock",
                        "VIEW MEDICINES"
                );


        JButton medicineButton =
                (JButton)
                        medicineActionCard.getClientProperty(
                                "button"
                        );


        medicineButton.addActionListener(
                e -> {

                    new PharmacyMedicineFrame()
                            .setVisible(true);
                }
        );


        actionPanel.add(
                prescriptionCard
        );

        actionPanel.add(
                medicineActionCard
        );


        contentPanel.add(
                actionPanel
        );


        contentPanel.add(
                Box.createVerticalGlue()
        );


        // =========================
        // LOGOUT BUTTON
        // =========================

        JButton logoutButton =
                new JButton(
                        "LOGOUT"
                );

        logoutButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        logoutButton.setForeground(
                Color.WHITE
        );

        logoutButton.setBackground(
                new Color(198, 40, 40)
        );

        logoutButton.setFocusPainted(false);

        logoutButton.setBorder(
                new EmptyBorder(
                        12,
                        30,
                        12,
                        30
                )
        );

        logoutButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        logoutButton.addActionListener(
                e -> {

                    new LoginFrame()
                            .setVisible(true);

                    dispose();
                }
        );


        contentPanel.add(
                logoutButton
        );


        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );


        add(mainPanel);
    }


    // =========================
    // STAT CARD
    // =========================

    private JPanel createStatCard(
            String title,
            String count
    ) {

        JPanel card =
                new JPanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                new LineBorder(
                        new Color(220, 225, 230),
                        1,
                        true
                )
        );


        JLabel titleLabel =
                new JLabel(
                        title
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        titleLabel.setForeground(
                new Color(80, 90, 100)
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JLabel countLabel =
                new JLabel(
                        count
                );

        countLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        32
                )
        );

        countLabel.setForeground(
                new Color(25, 118, 210)
        );

        countLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        card.add(
                Box.createVerticalGlue()
        );

        card.add(titleLabel);

        card.add(
                Box.createVerticalStrut(8)
        );

        card.add(countLabel);

        card.add(
                Box.createVerticalGlue()
        );


        card.putClientProperty(
                "countLabel",
                countLabel
        );


        return card;
    }


    private JLabel getCountLabel(
            JPanel card
    ) {

        return (JLabel)
                card.getClientProperty(
                        "countLabel"
                );
    }


    // =========================
    // ACTION CARD
    // =========================

    private JPanel createActionCard(
            String title,
            String description,
            String buttonText
    ) {

        JPanel card =
                new JPanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                new LineBorder(
                        new Color(220, 225, 230),
                        1,
                        true
                )
        );


        JLabel titleLabel =
                new JLabel(
                        title
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        titleLabel.setForeground(
                new Color(35, 45, 55)
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JLabel descriptionLabel =
                new JLabel(
                        description
                );

        descriptionLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        descriptionLabel.setForeground(
                new Color(100, 110, 120)
        );

        descriptionLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JButton button =
                new JButton(
                        buttonText
                );

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
                new Color(25, 118, 210)
        );

        button.setFocusPainted(false);

        button.setBorder(
                new EmptyBorder(
                        10,
                        20,
                        10,
                        20
                )
        );

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        card.add(
                Box.createVerticalGlue()
        );

        card.add(titleLabel);

        card.add(
                Box.createVerticalStrut(8)
        );

        card.add(descriptionLabel);

        card.add(
                Box.createVerticalStrut(20)
        );

        card.add(button);

        card.add(
                Box.createVerticalGlue()
        );


        card.putClientProperty(
                "button",
                button
        );


        return card;
    }


    // =========================
    // LOAD DASHBOARD DATA
    // =========================

    private void loadDashboardData() {

        int pendingCount =
                prescriptionService
                        .getPendingPrescriptions()
                        .size();

        int medicineCount =
                medicineService
                        .getMedicineCount();

        int lowStockCount =
                medicineService
                        .getLowStockCount();


        pendingCountLabel.setText(
                String.valueOf(
                        pendingCount
                )
        );

        medicineCountLabel.setText(
                String.valueOf(
                        medicineCount
                )
        );

        lowStockCountLabel.setText(
                String.valueOf(
                        lowStockCount
                )
        );
    }


    // =========================
    // MAIN METHOD
    // =========================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    new PharmacyDashboardFrame()
                            .setVisible(true);
                }
        );
    }
}