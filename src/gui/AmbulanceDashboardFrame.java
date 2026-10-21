package gui;

import model.EmergencyCase;
import service.EmergencyCaseService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class AmbulanceDashboardFrame extends JFrame {

    private EmergencyCaseService emergencyCaseService;

    private JLabel totalCasesLabel;
    private JLabel activeCasesLabel;


    public AmbulanceDashboardFrame() {

        emergencyCaseService =
                new EmergencyCaseService();

        setTitle("XYZ HOSPITAL - Ambulance Portal");

        setSize(1100, 750);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(false);


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
                        25,
                        30,
                        25,
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
                        26
                )
        );

        hospitalLabel.setForeground(Color.WHITE);

        hospitalLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JLabel systemLabel =
                new JLabel(
                        "Smart Hospital Management System"
                );

        systemLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        16
                )
        );

        systemLabel.setForeground(Color.WHITE);

        systemLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JLabel portalLabel =
                new JLabel(
                        "AMBULANCE PORTAL"
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


        headerPanel.add(hospitalLabel);

        headerPanel.add(
                Box.createVerticalStrut(5)
        );

        headerPanel.add(systemLabel);

        headerPanel.add(
                Box.createVerticalStrut(10)
        );

        headerPanel.add(portalLabel);


        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // CENTER PANEL
        // =====================================================

        JPanel centerPanel =
                new JPanel();

        centerPanel.setLayout(
                new BoxLayout(
                        centerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        centerPanel.setBackground(
                new Color(245, 247, 250)
        );

        centerPanel.setBorder(
                new EmptyBorder(
                        30,
                        50,
                        30,
                        50
                )
        );


        // =====================================================
        // PAGE TITLE
        // =====================================================

        JLabel titleLabel =
                new JLabel(
                        "Ambulance Dashboard"
                );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
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
                        "Send emergency patient information to the hospital"
                );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
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


        centerPanel.add(titleLabel);

        centerPanel.add(
                Box.createVerticalStrut(8)
        );

        centerPanel.add(subtitleLabel);

        centerPanel.add(
                Box.createVerticalStrut(30)
        );


        // =====================================================
        // STAT CARDS
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

        statsPanel.setMaximumSize(
                new Dimension(
                        650,
                        120
                )
        );

        statsPanel.setBackground(
                new Color(245, 247, 250)
        );


        // Total cases

        JPanel totalCard =
                createStatCard(
                        "TOTAL EMERGENCY CASES",
                        "0"
                );

        totalCasesLabel =
                (JLabel) totalCard.getClientProperty(
                        "valueLabel"
                );


        // Active cases

        JPanel activeCard =
                createStatCard(
                        "ACTIVE CASES",
                        "0"
                );

        activeCasesLabel =
                (JLabel) activeCard.getClientProperty(
                        "valueLabel"
                );


        statsPanel.add(totalCard);

        statsPanel.add(activeCard);


        centerPanel.add(statsPanel);

        centerPanel.add(
                Box.createVerticalStrut(35)
        );


        // =====================================================
        // ACTION CARDS
        // =====================================================

        JPanel actionsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                30,
                                0
                        )
                );

        actionsPanel.setMaximumSize(
                new Dimension(
                        850,
                        220
                )
        );

        actionsPanel.setBackground(
                new Color(245, 247, 250)
        );


        // Send emergency case

        JPanel sendPanel =
                createActionCard(
                        "SEND EMERGENCY CASE",
                        "Send patient details, emergency information and simulated vital signs.",
                        "SEND CASE"
                );


        JButton sendButton =
                (JButton)
                        sendPanel.getClientProperty(
                                "actionButton"
                        );


        sendButton.addActionListener(e -> {

            new AmbulanceEmergencyFrame()
                    .setVisible(true);

        });


        // View cases

        JPanel viewPanel =
                createActionCard(
                        "VIEW SENT CASES",
                        "View emergency cases already sent to the hospital.",
                        "VIEW CASES"
                );


        JButton viewButton =
                (JButton)
                        viewPanel.getClientProperty(
                                "actionButton"
                        );


        viewButton.addActionListener(e -> {

            new AmbulanceCaseFrame()
                    .setVisible(true);

        });


        actionsPanel.add(sendPanel);

        actionsPanel.add(viewPanel);


        centerPanel.add(actionsPanel);


        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // FOOTER
        // =====================================================

        JPanel footerPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                20,
                                15
                        )
                );

        footerPanel.setBackground(
                Color.WHITE
        );


        JButton logoutButton =
                new JButton("LOGOUT");

        logoutButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        logoutButton.setFocusPainted(false);

        logoutButton.setPreferredSize(
                new Dimension(
                        130,
                        40
                )
        );


        logoutButton.addActionListener(e -> {

            new LoginFrame()
                    .setVisible(true);

            dispose();

        });


        footerPanel.add(
                logoutButton
        );


        mainPanel.add(
                footerPanel,
                BorderLayout.SOUTH
        );


        add(mainPanel);


        // =====================================================
        // LOAD DASHBOARD DATA
        // =====================================================

        loadDashboardData();
    }


    // =====================================================
    // LOAD DASHBOARD DATA
    // =====================================================

    private void loadDashboardData() {

        List<EmergencyCase> cases =
                emergencyCaseService
                        .getAllEmergencyCases();

        int totalCases =
                cases.size();

        int activeCases = 0;


        for (
                EmergencyCase emergencyCase
                : cases
        ) {

            String status =
                    emergencyCase.getStatus();

            if (
                    status != null
                            &&
                            (
                                    status.equalsIgnoreCase(
                                            "IN_TRANSIT"
                                    )
                                            ||
                                            status.equalsIgnoreCase(
                                                    "INCOMING"
                                            )
                                            ||
                                            status.equalsIgnoreCase(
                                                    "SENT"
                                            )
                            )
            ) {

                activeCases++;
            }
        }


        totalCasesLabel.setText(
                String.valueOf(totalCases)
        );

        activeCasesLabel.setText(
                String.valueOf(activeCases)
        );
    }


    // =====================================================
    // CREATE STAT CARD
    // =====================================================

    private JPanel createStatCard(
            String title,
            String value
    ) {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 225, 230)
                        ),
                        new EmptyBorder(
                                15,
                                20,
                                15,
                                20
                        )
                )
        );


        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        titleLabel.setForeground(
                new Color(100, 110, 120)
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        32
                )
        );

        valueLabel.setForeground(
                new Color(30, 90, 160)
        );

        valueLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        panel.add(titleLabel);

        panel.add(
                Box.createVerticalStrut(8)
        );

        panel.add(valueLabel);


        panel.putClientProperty(
                "valueLabel",
                valueLabel
        );


        return panel;
    }


    // =====================================================
    // CREATE ACTION CARD
    // =====================================================

    private JPanel createActionCard(
            String title,
            String description,
            String buttonText
    ) {

        JPanel panel =
                new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBackground(
                Color.WHITE
        );

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 225, 230)
                        ),
                        new EmptyBorder(
                                20,
                                25,
                                20,
                                25
                        )
                )
        );


        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        titleLabel.setForeground(
                new Color(35, 45, 55)
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JTextArea descriptionArea =
                new JTextArea(description);

        descriptionArea.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        descriptionArea.setForeground(
                new Color(100, 110, 120)
        );

        descriptionArea.setBackground(
                Color.WHITE
        );

        descriptionArea.setWrapStyleWord(true);

        descriptionArea.setLineWrap(true);

        descriptionArea.setEditable(false);

        descriptionArea.setFocusable(false);

        descriptionArea.setOpaque(false);

        descriptionArea.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        JButton actionButton =
                new JButton(buttonText);

        actionButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        actionButton.setForeground(
                Color.WHITE
        );

        actionButton.setBackground(
                new Color(30, 90, 160)
        );

        actionButton.setFocusPainted(false);

        actionButton.setPreferredSize(
                new Dimension(
                        150,
                        40
                )
        );

        actionButton.setMaximumSize(
                new Dimension(
                        180,
                        40
                )
        );

        actionButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );


        panel.add(titleLabel);

        panel.add(
                Box.createVerticalStrut(12)
        );

        panel.add(descriptionArea);

        panel.add(
                Box.createVerticalStrut(20)
        );

        panel.add(actionButton);


        panel.putClientProperty(
                "actionButton",
                actionButton
        );


        return panel;
    }


    // =====================================================
    // MAIN METHOD FOR TESTING
    // =====================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new AmbulanceDashboardFrame()
                    .setVisible(true);

        });
    }
}