package gui;

import model.EmergencyCase;
import service.EmergencyCaseService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class CasualtyDashboardFrame extends JFrame {

    private EmergencyCaseService emergencyCaseService;

    private JLabel incomingValue;
    private JLabel receivedValue;
    private JLabel arrivedValue;
    private JLabel criticalValue;

    // ================= MULTITHREADING =================

    private Thread emergencyMonitorThread;
    private volatile boolean monitoring = false;

    private int previousIncomingCount = -1;

    // ================= CONSTRUCTOR =================

    public CasualtyDashboardFrame() {

        emergencyCaseService =
                new EmergencyCaseService();

        setTitle("XYZ HOSPITAL - Casualty Portal");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        buildUI();

        loadStatistics();

        // Start background monitoring
        startEmergencyMonitoring();

        // Stop thread when window closes
        addWindowListener(
                new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(
                            java.awt.event.WindowEvent e
                    ) {

                        stopEmergencyMonitoring();
                    }
                }
        );
    }

    // =====================================================
    // BUILD USER INTERFACE
    // =====================================================

    private void buildUI() {

        setLayout(new BorderLayout());

        // ================= HEADER =================

        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setBackground(
                new Color(25, 45, 75)
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
                new JLabel("✚ XYZ HOSPITAL");

        hospitalLabel.setForeground(
                Color.WHITE
        );

        hospitalLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        JLabel portalLabel =
                new JLabel("CASUALTY PORTAL");

        portalLabel.setForeground(
                Color.WHITE
        );

        portalLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        headerPanel.add(
                hospitalLabel,
                BorderLayout.WEST
        );

        headerPanel.add(
                portalLabel,
                BorderLayout.EAST
        );

        add(
                headerPanel,
                BorderLayout.NORTH
        );

        // ================= MAIN PANEL =================

        JPanel mainPanel =
                new JPanel();

        mainPanel.setLayout(
                new BoxLayout(
                        mainPanel,
                        BoxLayout.Y_AXIS
                )
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        30,
                        40,
                        30,
                        40
                )
        );

        // ================= TITLE =================

        JLabel titleLabel =
                new JLabel(
                        "Casualty Dashboard"
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Monitor incoming emergency patients"
                );

        subtitleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        subtitleLabel.setForeground(
                Color.DARK_GRAY
        );

        subtitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        mainPanel.add(titleLabel);

        mainPanel.add(
                Box.createVerticalStrut(5)
        );

        mainPanel.add(subtitleLabel);

        mainPanel.add(
                Box.createVerticalStrut(35)
        );

        // ================= STATISTICS PANEL =================

        JPanel statsPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                20,
                                0
                        )
                );

        incomingValue =
                createStatCard(
                        statsPanel,
                        "INCOMING CASES"
                );

        receivedValue =
                createStatCard(
                        statsPanel,
                        "RECEIVED"
                );

        arrivedValue =
                createStatCard(
                        statsPanel,
                        "ARRIVED"
                );

        criticalValue =
                createStatCard(
                        statsPanel,
                        "CRITICAL"
                );

        mainPanel.add(
                statsPanel
        );

        mainPanel.add(
                Box.createVerticalStrut(50)
        );

        // ================= BUTTON PANEL =================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                20,
                                10
                        )
                );

        JButton viewButton =
                new JButton(
                        "VIEW INCOMING CASES"
                );

        JButton refreshButton =
                new JButton(
                        "REFRESH"
                );

        JButton logoutButton =
                new JButton(
                        "LOGOUT"
                );

        viewButton.setPreferredSize(
                new Dimension(
                        220,
                        50
                )
        );

        refreshButton.setPreferredSize(
                new Dimension(
                        140,
                        50
                )
        );

        logoutButton.setPreferredSize(
                new Dimension(
                        140,
                        50
                )
        );

        // ================= VIEW CASES =================

        viewButton.addActionListener(e -> {

            new CasualtyEmergencyFrame()
                    .setVisible(true);

        });

        // ================= MANUAL REFRESH =================

        refreshButton.addActionListener(e -> {

            loadStatistics();

        });

        // ================= LOGOUT =================

        logoutButton.addActionListener(e -> {

            stopEmergencyMonitoring();

            dispose();

            new LoginFrame()
                    .setVisible(true);

        });

        buttonPanel.add(
                viewButton
        );

        buttonPanel.add(
                refreshButton
        );

        buttonPanel.add(
                logoutButton
        );

        mainPanel.add(
                buttonPanel
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }

    // =====================================================
    // CREATE STATISTICS CARD
    // =====================================================

    private JLabel createStatCard(
            JPanel parent,
            String title
    ) {

        JPanel card =
                new JPanel();

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        210,
                                        210,
                                        210
                                )
                        ),
                        new EmptyBorder(
                                20,
                                15,
                                20,
                                15
                        )
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel valueLabel =
                new JLabel("0");

        valueLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        32
                )
        );

        valueLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        card.add(
                titleLabel
        );

        card.add(
                Box.createVerticalStrut(15)
        );

        card.add(
                valueLabel
        );

        parent.add(
                card
        );

        return valueLabel;
    }

    // =====================================================
    // LOAD STATISTICS
    // =====================================================

    private void loadStatistics() {

        List<EmergencyCase> cases =
                emergencyCaseService
                        .getAllEmergencyCases();

        int incoming = 0;
        int received = 0;
        int arrived = 0;
        int critical = 0;

        for (
                EmergencyCase emergencyCase :
                cases
        ) {

            String status =
                    emergencyCase
                            .getStatus()
                            .toUpperCase();

            // ================= INCOMING =================

            if (
                    status.equals("SENT")
                            || status.equals("IN_TRANSIT")
                            || status.equals("INCOMING")
            ) {

                incoming++;
            }

            // ================= RECEIVED =================

            if (
                    status.equals("RECEIVED")
            ) {

                received++;
            }

            // ================= ARRIVED =================

            if (
                    status.equals("ARRIVED")
            ) {

                arrived++;
            }

            // ================= CRITICAL =================

            if (
                    emergencyCase.getHeartRate() < 60
                            || emergencyCase.getHeartRate() > 100
                            || emergencyCase.getSpo2() < 94
                            || emergencyCase.getTemperature() > 38
                            || isAbnormalBloodPressure(
                            emergencyCase.getBloodPressure()
                    )
            ) {

                critical++;
            }
        }

        /*
         * Create final copies because these values
         * are used inside a lambda expression.
         */

        final int finalIncoming =
                incoming;

        final int finalReceived =
                received;

        final int finalArrived =
                arrived;

        final int finalCritical =
                critical;

        // Update Swing GUI safely

        SwingUtilities.invokeLater(() -> {

            incomingValue.setText(
                    String.valueOf(
                            finalIncoming
                    )
            );

            receivedValue.setText(
                    String.valueOf(
                            finalReceived
                    )
            );

            arrivedValue.setText(
                    String.valueOf(
                            finalArrived
                    )
            );

            criticalValue.setText(
                    String.valueOf(
                            finalCritical
                    )
            );
        });
    }

    // =====================================================
    // BLOOD PRESSURE CHECK
    // =====================================================

    private boolean isAbnormalBloodPressure(
            String bloodPressure
    ) {

        try {

            if (
                    bloodPressure == null
                            || !bloodPressure.contains("/")
            ) {

                return false;
            }

            String[] parts =
                    bloodPressure.split("/");

            int systolic =
                    Integer.parseInt(
                            parts[0].trim()
                    );

            int diastolic =
                    Integer.parseInt(
                            parts[1].trim()
                    );

            return systolic < 90
                    || systolic > 140
                    || diastolic < 60
                    || diastolic > 90;

        } catch (Exception e) {

            return false;
        }
    }

    // =====================================================
    // START EMERGENCY MONITORING
    // =====================================================

    private void startEmergencyMonitoring() {

        monitoring = true;

        emergencyMonitorThread =
                new Thread(() -> {

                    while (monitoring) {

                        try {

                            checkForNewEmergencyCases();

                            /*
                             * Check the database every
                             * 5 seconds.
                             */

                            Thread.sleep(5000);

                        } catch (
                                InterruptedException e
                        ) {

                            Thread.currentThread()
                                    .interrupt();

                            break;
                        }
                    }

                });

        emergencyMonitorThread.setName(
                "Emergency-Monitoring-Thread"
        );

        emergencyMonitorThread.start();
    }

    // =====================================================
    // CHECK FOR NEW EMERGENCY CASES
    // =====================================================

    private void checkForNewEmergencyCases() {

        List<EmergencyCase> cases =
                emergencyCaseService
                        .getAllEmergencyCases();

        int incoming = 0;

        for (
                EmergencyCase emergencyCase :
                cases
        ) {

            String status =
                    emergencyCase
                            .getStatus()
                            .toUpperCase();

            if (
                    status.equals("SENT")
                            || status.equals("IN_TRANSIT")
                            || status.equals("INCOMING")
            ) {

                incoming++;
            }
        }

        // ================= FIRST CHECK =================

        if (
                previousIncomingCount == -1
        ) {

            previousIncomingCount =
                    incoming;

            loadStatistics();

            return;
        }

        // ================= NEW CASE DETECTED =================

        if (
                incoming > previousIncomingCount
        ) {

            int newCases =
                    incoming -
                            previousIncomingCount;

            previousIncomingCount =
                    incoming;

            loadStatistics();

            SwingUtilities.invokeLater(() -> {

                JOptionPane.showMessageDialog(
                        this,
                        "🚨 "
                                + newCases
                                + " new emergency case received!",
                        "Emergency Alert",
                        JOptionPane.INFORMATION_MESSAGE
                );

            });

        } else {

            previousIncomingCount =
                    incoming;

            loadStatistics();
        }
    }

    // =====================================================
    // STOP EMERGENCY MONITORING
    // =====================================================

    private void stopEmergencyMonitoring() {

        monitoring = false;

        if (
                emergencyMonitorThread != null
                        && emergencyMonitorThread.isAlive()
        ) {

            emergencyMonitorThread.interrupt();
        }
    }
}