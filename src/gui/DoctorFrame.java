package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import model.Doctor;
import service.DoctorService;

public class DoctorFrame extends JFrame {

    // =====================================================
    // COLORS
    // =====================================================

    private final Color PRIMARY_BLUE = new Color(25, 118, 210);
    private final Color DARK_BLUE = new Color(13, 71, 161);
    private final Color LIGHT_BLUE = new Color(232, 245, 253);

    private final Color BACKGROUND = new Color(244, 248, 252);
    private final Color WHITE = Color.WHITE;

    private final Color TEXT_COLOR = new Color(45, 55, 65);
    private final Color SECONDARY_TEXT = new Color(105, 115, 125);

    private final Color BORDER_COLOR = new Color(215, 225, 235);

    private final Color SUCCESS_GREEN = new Color(0, 150, 100);
    private final Color ERROR_RED = new Color(190, 50, 50);


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public DoctorFrame() {

        // =================================================
        // FRAME SETTINGS
        // =================================================

        setTitle("XYZ HOSPITAL - Doctor");

        setSize(800, 750);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        getContentPane().setBackground(
                BACKGROUND
        );

        setLayout(
                new BorderLayout()
        );


        // =================================================
        // HEADER
        // =================================================

        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setBackground(
                PRIMARY_BLUE
        );

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        18, 28, 18, 28
                )
        );


        JLabel hospitalIcon =
                new JLabel("✚");

        hospitalIcon.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        32
                )
        );

        hospitalIcon.setForeground(
                Color.WHITE
        );


        JLabel hospitalName =
                new JLabel("XYZ HOSPITAL");

        hospitalName.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        hospitalName.setForeground(
                Color.WHITE
        );


        JLabel subtitle =
                new JLabel(
                        "Smart Hospital Management System"
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        subtitle.setForeground(
                new Color(220, 235, 250)
        );


        JPanel textArea =
                new JPanel();

        textArea.setOpaque(false);

        textArea.setLayout(
                new BoxLayout(
                        textArea,
                        BoxLayout.Y_AXIS
                )
        );

        textArea.add(
                hospitalName
        );

        textArea.add(
                Box.createVerticalStrut(3)
        );

        textArea.add(
                subtitle
        );


        JPanel titleArea =
                new JPanel();

        titleArea.setOpaque(false);

        titleArea.setLayout(
                new BoxLayout(
                        titleArea,
                        BoxLayout.X_AXIS
                )
        );

        titleArea.add(
                hospitalIcon
        );

        titleArea.add(
                Box.createHorizontalStrut(12)
        );

        titleArea.add(
                textArea
        );


        JLabel doctorLabel =
                new JLabel(
                        "DOCTOR PORTAL"
                );

        doctorLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        doctorLabel.setForeground(
                Color.WHITE
        );


        JLabel statusLabel =
                new JLabel(
                        "●  SYSTEM ONLINE"
                );

        statusLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        statusLabel.setForeground(
                new Color(
                        190,
                        255,
                        220
                )
        );


        JPanel rightHeader =
                new JPanel();

        rightHeader.setOpaque(false);

        rightHeader.setLayout(
                new BoxLayout(
                        rightHeader,
                        BoxLayout.Y_AXIS
                )
        );

        rightHeader.add(
                doctorLabel
        );

        rightHeader.add(
                Box.createVerticalStrut(8)
        );

        rightHeader.add(
                statusLabel
        );


        headerPanel.add(
                titleArea,
                BorderLayout.WEST
        );

        headerPanel.add(
                rightHeader,
                BorderLayout.EAST
        );


        add(
                headerPanel,
                BorderLayout.NORTH
        );


        // =================================================
        // MAIN PANEL
        // =================================================

        JPanel mainPanel =
                new JPanel();

        mainPanel.setBackground(
                BACKGROUND
        );

        mainPanel.setLayout(
                new BoxLayout(
                        mainPanel,
                        BoxLayout.Y_AXIS
                )
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        22, 35, 25, 35
                )
        );


        // =================================================
        // DOCTOR REGISTRATION CARD
        // =================================================

        JPanel registrationCard =
                new JPanel(
                        new BorderLayout()
                );

        registrationCard.setBackground(
                WHITE
        );

        registrationCard.setBorder(
                BorderFactory.createCompoundBorder(

                        new LineBorder(
                                BORDER_COLOR,
                                1,
                                true
                        ),

                        BorderFactory.createEmptyBorder(
                                22, 28, 22, 28
                        )
                )
        );


        // =================================================
        // REGISTRATION HEADING
        // =================================================

        JLabel registrationIcon =
                new JLabel("👨‍⚕️");

        registrationIcon.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        25
                )
        );


        JLabel registrationTitle =
                new JLabel(
                        "DOCTOR REGISTRATION"
                );

        registrationTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        registrationTitle.setForeground(
                DARK_BLUE
        );


        JLabel registrationSubtitle =
                new JLabel(
                        "Register a doctor in the hospital system"
                );

        registrationSubtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        registrationSubtitle.setForeground(
                SECONDARY_TEXT
        );


        JPanel registrationText =
                new JPanel();

        registrationText.setOpaque(false);

        registrationText.setLayout(
                new BoxLayout(
                        registrationText,
                        BoxLayout.Y_AXIS
                )
        );

        registrationText.add(
                registrationTitle
        );

        registrationText.add(
                Box.createVerticalStrut(4)
        );

        registrationText.add(
                registrationSubtitle
        );


        JPanel registrationHeading =
                new JPanel();

        registrationHeading.setOpaque(false);

        registrationHeading.setLayout(
                new BoxLayout(
                        registrationHeading,
                        BoxLayout.X_AXIS
                )
        );

        registrationHeading.add(
                registrationIcon
        );

        registrationHeading.add(
                Box.createHorizontalStrut(10)
        );

        registrationHeading.add(
                registrationText
        );


        registrationCard.add(
                registrationHeading,
                BorderLayout.NORTH
        );


        // =================================================
        // REGISTRATION FORM
        // =================================================

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setOpaque(false);

        formPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 0, 15, 0
                )
        );


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        8, 8, 8, 8
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;


        // -------------------------------------------------
        // NAME
        // -------------------------------------------------

        JLabel nameLabel =
                createFieldLabel(
                        "Doctor Name *"
                );

        JTextField nameField =
                createTextField();


        addFormRow(
                formPanel,
                gbc,
                0,
                nameLabel,
                nameField
        );


        // -------------------------------------------------
        // AGE
        // -------------------------------------------------

        JLabel ageLabel =
                createFieldLabel(
                        "Age"
                );

        JTextField ageField =
                createTextField();


        addFormRow(
                formPanel,
                gbc,
                1,
                ageLabel,
                ageField
        );


        // -------------------------------------------------
        // SPECIALIZATION
        // -------------------------------------------------

        JLabel specializationLabel =
                createFieldLabel(
                        "Specialization *"
                );

        JTextField specializationField =
                createTextField();


        addFormRow(
                formPanel,
                gbc,
                2,
                specializationLabel,
                specializationField
        );


        // -------------------------------------------------
        // PHONE
        // -------------------------------------------------

        JLabel phoneLabel =
                createFieldLabel(
                        "Phone Number *"
                );

        JTextField phoneField =
                createTextField();


        addFormRow(
                formPanel,
                gbc,
                3,
                phoneLabel,
                phoneField
        );


        registrationCard.add(
                formPanel,
                BorderLayout.CENTER
        );


        // =================================================
        // REGISTRATION BUTTONS
        // =================================================

        JButton registerButton =
                createPrimaryButton(
                        "REGISTER DOCTOR"
                );


        JButton clearButton =
                createSecondaryButton(
                        "CLEAR"
                );


        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                5
                        )
                );

        buttonPanel.setOpaque(false);

        buttonPanel.add(
                registerButton
        );

        buttonPanel.add(
                clearButton
        );


        registrationCard.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        // =================================================
        // SEARCH CARD
        // =================================================

        JPanel searchCard =
                new JPanel(
                        new BorderLayout()
                );

        searchCard.setBackground(
                WHITE
        );

        searchCard.setBorder(
                BorderFactory.createCompoundBorder(

                        new LineBorder(
                                BORDER_COLOR,
                                1,
                                true
                        ),

                        BorderFactory.createEmptyBorder(
                                20, 28, 20, 28
                        )
                )
        );


        // =================================================
        // SEARCH HEADING
        // =================================================

        JLabel searchIcon =
                new JLabel("🔎");

        searchIcon.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        23
                )
        );


        JLabel searchTitle =
                new JLabel(
                        "SEARCH DOCTOR"
                );

        searchTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        19
                )
        );

        searchTitle.setForeground(
                DARK_BLUE
        );


        JLabel searchSubtitle =
                new JLabel(
                        "Find an existing doctor using Doctor ID"
                );

        searchSubtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        searchSubtitle.setForeground(
                SECONDARY_TEXT
        );


        JPanel searchText =
                new JPanel();

        searchText.setOpaque(false);

        searchText.setLayout(
                new BoxLayout(
                        searchText,
                        BoxLayout.Y_AXIS
                )
        );

        searchText.add(
                searchTitle
        );

        searchText.add(
                Box.createVerticalStrut(4)
        );

        searchText.add(
                searchSubtitle
        );


        JPanel searchHeading =
                new JPanel();

        searchHeading.setOpaque(false);

        searchHeading.setLayout(
                new BoxLayout(
                        searchHeading,
                        BoxLayout.X_AXIS
                )
        );

        searchHeading.add(
                searchIcon
        );

        searchHeading.add(
                Box.createHorizontalStrut(10)
        );

        searchHeading.add(
                searchText
        );


        searchCard.add(
                searchHeading,
                BorderLayout.NORTH
        );


        // =================================================
        // SEARCH CONTROLS
        // =================================================

        JPanel searchControls =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                18
                        )
                );

        searchControls.setOpaque(false);


        JLabel searchLabel =
                createFieldLabel(
                        "Doctor ID:"
                );


        JTextField searchField =
                new JTextField();


        searchField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        searchField.setPreferredSize(
                new Dimension(
                        160,
                        38
                )
        );


        JButton searchButton =
                createPrimaryButton(
                        "SEARCH"
                );


        searchControls.add(
                searchLabel
        );

        searchControls.add(
                searchField
        );

        searchControls.add(
                searchButton
        );


        searchCard.add(
                searchControls,
                BorderLayout.CENTER
        );


        // =================================================
        // DOCTOR INFORMATION CARD
        // =================================================

        JPanel resultPanel =
                new JPanel(
                        new BorderLayout()
                );

        resultPanel.setBackground(
                new Color(
                        248,
                        251,
                        254
                )
        );

        resultPanel.setBorder(
                BorderFactory.createCompoundBorder(

                        new LineBorder(
                                new Color(
                                        225,
                                        232,
                                        240
                                ),
                                1,
                                true
                        ),

                        BorderFactory.createEmptyBorder(
                                15, 18, 15, 18
                        )
                )
        );


        // -------------------------------------------------
        // RESULT HEADER
        // -------------------------------------------------

        JLabel resultIcon =
                new JLabel("✓");

        resultIcon.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        resultIcon.setForeground(
                SUCCESS_GREEN
        );


        JLabel resultTitle =
                new JLabel(
                        "DOCTOR INFORMATION"
                );

        resultTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        resultTitle.setForeground(
                DARK_BLUE
        );


        JLabel resultId =
                new JLabel(
                        "No doctor selected"
                );

        resultId.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        resultId.setForeground(
                SECONDARY_TEXT
        );


        JPanel resultTitleArea =
                new JPanel();

        resultTitleArea.setOpaque(false);

        resultTitleArea.setLayout(
                new BoxLayout(
                        resultTitleArea,
                        BoxLayout.Y_AXIS
                )
        );

        resultTitleArea.add(
                resultTitle
        );

        resultTitleArea.add(
                Box.createVerticalStrut(3)
        );

        resultTitleArea.add(
                resultId
        );


        JPanel resultHeading =
                new JPanel();

        resultHeading.setOpaque(false);

        resultHeading.setLayout(
                new BoxLayout(
                        resultHeading,
                        BoxLayout.X_AXIS
                )
        );

        resultHeading.add(
                resultIcon
        );

        resultHeading.add(
                Box.createHorizontalStrut(10)
        );

        resultHeading.add(
                resultTitleArea
        );


        resultPanel.add(
                resultHeading,
                BorderLayout.NORTH
        );


        // =================================================
        // DOCTOR DETAILS
        // =================================================

        JPanel doctorDetailsPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                3,
                                20,
                                12
                        )
                );

        doctorDetailsPanel.setOpaque(false);

        doctorDetailsPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        18, 0, 5, 0
                )
        );


        JLabel doctorName =
                createDetailLabel(
                        "DOCTOR NAME",
                        "—"
                );


        JLabel doctorAge =
                createDetailLabel(
                        "AGE",
                        "—"
                );


        JLabel doctorSpecialization =
                createDetailLabel(
                        "SPECIALIZATION",
                        "—"
                );


        JLabel doctorPhone =
                createDetailLabel(
                        "PHONE NUMBER",
                        "—"
                );


        doctorDetailsPanel.add(
                doctorName
        );

        doctorDetailsPanel.add(
                doctorAge
        );

        doctorDetailsPanel.add(
                doctorSpecialization
        );

        doctorDetailsPanel.add(
                doctorPhone
        );

        doctorDetailsPanel.add(
                new JPanel()
        );

        doctorDetailsPanel.add(
                new JPanel()
        );


        resultPanel.add(
                doctorDetailsPanel,
                BorderLayout.CENTER
        );


        searchCard.add(
                resultPanel,
                BorderLayout.SOUTH
        );


        // =================================================
        // ADD CARDS
        // =================================================

        mainPanel.add(
                registrationCard
        );

        mainPanel.add(
                Box.createVerticalStrut(18)
        );

        mainPanel.add(
                searchCard
        );


        // =================================================
        // SCROLL PANE
        // =================================================

        JScrollPane scrollPane =
                new JScrollPane(
                        mainPanel
                );

        scrollPane.setBorder(null);

        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );


        add(
                scrollPane,
                BorderLayout.CENTER
        );


        // =================================================
        // DOCTOR SERVICE
        // =================================================

        DoctorService doctorService =
                new DoctorService();


        // =================================================
        // REGISTER BUTTON
        // =================================================

        registerButton.addActionListener(e -> {

            try {

                String name =
                        nameField.getText().trim();

                String ageText =
                        ageField.getText().trim();

                String specialization =
                        specializationField
                                .getText()
                                .trim();

                String phone =
                        phoneField.getText().trim();


                // -----------------------------------------
                // VALIDATION
                // -----------------------------------------

                if (name.isEmpty()
                        || specialization.isEmpty()
                        || phone.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please fill in all required fields.",
                            "Input Error",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }


                // -----------------------------------------
                // AGE
                // -----------------------------------------

                int age = 0;

                if (!ageText.isEmpty()) {

                    age =
                            Integer.parseInt(
                                    ageText
                            );
                }


                // -----------------------------------------
                // CREATE DOCTOR OBJECT
                // -----------------------------------------

                Doctor doctor =
                        new Doctor(
                                0,
                                name,
                                age,
                                specialization,
                                phone
                        );


                // -----------------------------------------
                // REGISTER
                // -----------------------------------------

                doctorService.registerDoctor(
                        doctor
                );


                // -----------------------------------------
                // SUCCESS
                // -----------------------------------------

                JOptionPane.showMessageDialog(
                        this,
                        "Doctor registered successfully!",
                        "Registration Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );


                // -----------------------------------------
                // CLEAR
                // -----------------------------------------

                clearForm(
                        nameField,
                        ageField,
                        specializationField,
                        phoneField
                );


            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Age must be a valid number.",
                        "Invalid Age",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        });


        // =================================================
        // CLEAR BUTTON
        // =================================================

        clearButton.addActionListener(e -> {

            clearForm(
                    nameField,
                    ageField,
                    specializationField,
                    phoneField
            );

        });


        // =================================================
        // SEARCH BUTTON
        // =================================================

        searchButton.addActionListener(e -> {

            try {

                String idText =
                        searchField.getText().trim();


                if (idText.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please enter a Doctor ID.",
                            "Search Error",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }


                int doctorId =
                        Integer.parseInt(
                                idText
                        );


                Doctor doctor =
                        doctorService.searchDoctor(
                                doctorId
                        );


                // -----------------------------------------
                // NOT FOUND
                // -----------------------------------------

                if (doctor == null) {

                    resultIcon.setText("!");

                    resultIcon.setForeground(
                            ERROR_RED
                    );

                    resultTitle.setText(
                            "DOCTOR NOT FOUND"
                    );

                    resultTitle.setForeground(
                            ERROR_RED
                    );

                    resultId.setText(
                            "No doctor exists with ID: "
                                    + doctorId
                    );


                    doctorName.setText(
                            createDetailHTML(
                                    "DOCTOR NAME",
                                    "Not available"
                            )
                    );

                    doctorAge.setText(
                            createDetailHTML(
                                    "AGE",
                                    "Not available"
                            )
                    );

                    doctorSpecialization.setText(
                            createDetailHTML(
                                    "SPECIALIZATION",
                                    "Not available"
                            )
                    );

                    doctorPhone.setText(
                            createDetailHTML(
                                    "PHONE NUMBER",
                                    "Not available"
                            )
                    );

                    return;
                }


                // -----------------------------------------
                // FOUND
                // -----------------------------------------

                resultIcon.setText("✓");

                resultIcon.setForeground(
                        SUCCESS_GREEN
                );


                resultTitle.setText(
                        "DOCTOR FOUND"
                );

                resultTitle.setForeground(
                        SUCCESS_GREEN
                );


                resultId.setText(
                        "Doctor ID: "
                                + doctor.getDoctorId()
                );


                doctorName.setText(
                        createDetailHTML(
                                "DOCTOR NAME",
                                doctor.getName()
                        )
                );


                doctorAge.setText(
                        createDetailHTML(
                                "AGE",
                                String.valueOf(
                                        doctor.getAge()
                                )
                        )
                );


                doctorSpecialization.setText(
                        createDetailHTML(
                                "SPECIALIZATION",
                                doctor.getSpecialization()
                        )
                );


                doctorPhone.setText(
                        createDetailHTML(
                                "PHONE NUMBER",
                                doctor.getPhone()
                        )
                );


            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Doctor ID must be a valid number.",
                        "Invalid Doctor ID",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        });


        // =================================================
        // SHOW FRAME
        // =================================================

        setVisible(true);
    }


    // =====================================================
    // ADD FORM ROW
    // =====================================================

    private void addFormRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            JLabel label,
            JComponent component
    ) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.2;

        panel.add(
                label,
                gbc
        );


        gbc.gridx = 1;
        gbc.weightx = 0.8;

        panel.add(
                component,
                gbc
        );
    }


    // =====================================================
    // CREATE FIELD LABEL
    // =====================================================

    private JLabel createFieldLabel(
            String text
    ) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        label.setForeground(
                TEXT_COLOR
        );

        return label;
    }


    // =====================================================
    // CREATE TEXT FIELD
    // =====================================================

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
                        400,
                        38
                )
        );


        field.setBorder(
                BorderFactory.createCompoundBorder(

                        new LineBorder(
                                BORDER_COLOR,
                                1,
                                true
                        ),

                        BorderFactory.createEmptyBorder(
                                5, 10, 5, 10
                        )
                )
        );


        return field;
    }


    // =====================================================
    // CREATE PRIMARY BUTTON
    // =====================================================

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


        button.setFocusPainted(
                false
        );


        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 20, 10, 20
                )
        );


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        return button;
    }


    // =====================================================
    // CREATE SECONDARY BUTTON
    // =====================================================

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
                DARK_BLUE
        );


        button.setBackground(
                LIGHT_BLUE
        );


        button.setFocusPainted(
                false
        );


        button.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 20, 10, 20
                )
        );


        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        return button;
    }


    // =====================================================
    // CREATE DETAIL LABEL
    // =====================================================

    private JLabel createDetailLabel(
            String heading,
            String value
    ) {

        JLabel label =
                new JLabel(
                        createDetailHTML(
                                heading,
                                value
                        )
                );


        label.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );


        return label;
    }


    // =====================================================
    // CREATE DETAIL HTML
    // =====================================================

    private String createDetailHTML(
            String heading,
            String value
    ) {

        return "<html>"
                + "<font color='#69737D'>"
                + heading
                + "</font>"
                + "<br>"
                + "<b><font color='#2D3741'>"
                + value
                + "</font></b>"
                + "</html>";
    }


    // =====================================================
    // CLEAR FORM
    // =====================================================

    private void clearForm(
            JTextField nameField,
            JTextField ageField,
            JTextField specializationField,
            JTextField phoneField
    ) {

        nameField.setText("");

        ageField.setText("");

        specializationField.setText("");

        phoneField.setText("");
    }
}