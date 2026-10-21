package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    // =========================================================
    // 1. CREATE PATIENT TABLE
    // =========================================================

    public static void createPatientTable() {

        String sql = """
            CREATE TABLE IF NOT EXISTS patient (
                patient_id INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(100) NOT NULL,
                age INT,
                gender VARCHAR(20),
                phone VARCHAR(15),
                address VARCHAR(255)
            )
            """;

        try {
            Connection con = DatabaseConnection.getConnection();

            if (con == null) {
                System.out.println("Could not create patient table because database connection failed.");
                return;
            }

            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);

            System.out.println("Patient table created successfully.");

            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Failed to create patient table.");
            System.out.println(e);
        }
    }


    // =========================================================
    // 2. CREATE DOCTOR TABLE
    // =========================================================

    public static void createDoctorTable() {

        String sql = """
            CREATE TABLE IF NOT EXISTS doctor (
                doctor_id INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(100) NOT NULL,
                specialization VARCHAR(100),
                phone VARCHAR(15)
            )
            """;

        try {
            Connection con = DatabaseConnection.getConnection();

            if (con == null) {
                System.out.println("Could not create doctor table because database connection failed.");
                return;
            }

            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);

            System.out.println("Doctor table created successfully.");

            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Failed to create doctor table.");
            System.out.println(e);
        }
    }


    // =========================================================
    // 3. CREATE APPOINTMENT TABLE
    // =========================================================

    public static void createAppointmentTable() {

        String sql = """
            CREATE TABLE IF NOT EXISTS appointment (
                appointment_id INT PRIMARY KEY AUTO_INCREMENT,
                patient_id INT,
                doctor_id INT,
                appointment_date DATE,
                appointment_time TIME,
                status VARCHAR(30),

                FOREIGN KEY (patient_id) REFERENCES patient(patient_id),
                FOREIGN KEY (doctor_id) REFERENCES doctor(doctor_id)
            )
            """;

        try {
            Connection con = DatabaseConnection.getConnection();

            if (con == null) {
                System.out.println("Could not create appointment table because database connection failed.");
                return;
            }

            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);

            System.out.println("Appointment table created successfully.");

            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Failed to create appointment table.");
            System.out.println(e);
        }
    }


    // =========================================================
    // 4. CREATE USERS TABLE
    // =========================================================

    public static void createUsersTable() {

        String sql = """
            CREATE TABLE IF NOT EXISTS users (
                user_id INT PRIMARY KEY AUTO_INCREMENT,
                username VARCHAR(50) NOT NULL UNIQUE,
                password VARCHAR(100) NOT NULL,
                role VARCHAR(30) NOT NULL
            )
            """;

        try {
            Connection con = DatabaseConnection.getConnection();

            if (con == null) {
                System.out.println("Could not create users table because database connection failed.");
                return;
            }

            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);

            System.out.println("Users table created successfully.");

            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Failed to create users table.");
            System.out.println(e);
        }
    }


    // =========================================================
    // 5. CREATE MEDICINE TABLE
    // =========================================================

    public static void createMedicineTable() {

        String sql = """
            CREATE TABLE IF NOT EXISTS medicine (
                medicine_id INT PRIMARY KEY AUTO_INCREMENT,
                medicine_name VARCHAR(100) NOT NULL,
                quantity INT NOT NULL DEFAULT 0,
                price DECIMAL(10,2) NOT NULL
            )
            """;

        try {
            Connection con = DatabaseConnection.getConnection();

            if (con == null) {
                System.out.println("Could not create medicine table because database connection failed.");
                return;
            }

            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);

            System.out.println("Medicine table created successfully.");

            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Failed to create medicine table.");
            System.out.println(e);
        }
    }


    // =========================================================
    // 6. CREATE CONSULTATION TABLE
    // =========================================================

    public static void createConsultationTable() {

        String sql = """
            CREATE TABLE IF NOT EXISTS consultation (
                consultation_id INT PRIMARY KEY AUTO_INCREMENT,
                patient_id INT NOT NULL,
                doctor_id INT NOT NULL,
                appointment_id INT NOT NULL,
                symptoms VARCHAR(500),
                diagnosis VARCHAR(500),
                notes VARCHAR(1000),
                treatment VARCHAR(500),
                consultation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                FOREIGN KEY (patient_id) REFERENCES patient(patient_id),
                FOREIGN KEY (doctor_id) REFERENCES doctor(doctor_id),
                FOREIGN KEY (appointment_id) REFERENCES appointment(appointment_id)
            )
            """;

        try {
            Connection con = DatabaseConnection.getConnection();

            if (con == null) {
                System.out.println("Could not create consultation table because database connection failed.");
                return;
            }

            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);

            System.out.println("Consultation table created successfully.");

            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Failed to create consultation table.");
            System.out.println(e);
        }
    }


    // =========================================================
    // 7. CREATE PRESCRIPTION TABLE
    // =========================================================

    public static void createPrescriptionTable() {

        String sql = """
            CREATE TABLE IF NOT EXISTS prescription (
                prescription_id INT PRIMARY KEY AUTO_INCREMENT,
                consultation_id INT NOT NULL,
                patient_id INT NOT NULL,
                doctor_id INT NOT NULL,
                medicine_id INT NOT NULL,
                dosage VARCHAR(100),
                duration VARCHAR(100),
                instructions VARCHAR(500),
                prescription_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                FOREIGN KEY (consultation_id)
                    REFERENCES consultation(consultation_id),

                FOREIGN KEY (patient_id)
                    REFERENCES patient(patient_id),

                FOREIGN KEY (doctor_id)
                    REFERENCES doctor(doctor_id),

                FOREIGN KEY (medicine_id)
                    REFERENCES medicine(medicine_id)
            )
            """;

        try {
            Connection con = DatabaseConnection.getConnection();

            if (con == null) {
                System.out.println("Could not create prescription table because database connection failed.");
                return;
            }

            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);

            System.out.println("Prescription table created successfully.");

            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Failed to create prescription table.");
            System.out.println(e);
        }
    }


    // =========================================================
    // 8. CREATE EMERGENCY CASE TABLE
    // =========================================================

    public static void createEmergencyCaseTable() {

        String sql = """
            CREATE TABLE IF NOT EXISTS emergency_case (
                emergency_id INT PRIMARY KEY AUTO_INCREMENT,
                patient_name VARCHAR(100) NOT NULL,
                age INT,
                gender VARCHAR(20),
                condition_description VARCHAR(500),
                symptoms VARCHAR(500),
                blood_group VARCHAR(10),
                ambulance_number VARCHAR(50),
                eta VARCHAR(50),
                status VARCHAR(50),
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
            """;

        try {
            Connection con = DatabaseConnection.getConnection();

            if (con == null) {
                System.out.println("Could not create emergency_case table because database connection failed.");
                return;
            }

            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);

            System.out.println("Emergency case table created successfully.");

            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Failed to create emergency_case table.");
            System.out.println(e);
        }
    }


    // =========================================================
    // MAIN METHOD - RUN DATABASE SETUP
    // =========================================================

    public static void main(String[] args) {

        System.out.println("Starting Smart Hospital Database Setup...");
        System.out.println("------------------------------------------");

        // Existing tables
        createPatientTable();
        createDoctorTable();
        createAppointmentTable();

        // New tables
        createUsersTable();
        createMedicineTable();
        createConsultationTable();
        createPrescriptionTable();
        createEmergencyCaseTable();

        System.out.println("------------------------------------------");
        System.out.println("Database setup completed.");
    }
}