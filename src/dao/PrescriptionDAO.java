package dao;

import model.Prescription;
import util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {

    // =========================================================
    // ADD PRESCRIPTION
    // =========================================================

    public boolean addPrescription(
            Prescription prescription
    ) {

        String sql = """
                INSERT INTO prescription
                (
                    consultation_id,
                    patient_id,
                    doctor_id,
                    medicine_id,
                    dosage,
                    duration,
                    instructions
                )
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {

                System.out.println(
                        "Could not add prescription because database connection failed."
                );

                return false;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            pstmt.setInt(
                    1,
                    prescription.getConsultationId()
            );

            pstmt.setInt(
                    2,
                    prescription.getPatientId()
            );

            pstmt.setInt(
                    3,
                    prescription.getDoctorId()
            );

            pstmt.setInt(
                    4,
                    prescription.getMedicineId()
            );

            pstmt.setString(
                    5,
                    prescription.getDosage()
            );

            pstmt.setString(
                    6,
                    prescription.getDuration()
            );

            pstmt.setString(
                    7,
                    prescription.getInstructions()
            );

            pstmt.executeUpdate();

            pstmt.close();
            con.close();

            System.out.println(
                    "Prescription added successfully."
            );

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Failed to add prescription."
            );

            System.out.println(e);

            return false;
        }
    }


    // =========================================================
    // GET PRESCRIPTION BY ID
    // =========================================================

    public Prescription getPrescriptionById(
            int prescriptionId
    ) {

        String sql = """
                SELECT *
                FROM prescription
                WHERE prescription_id = ?
                """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {
                return null;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            pstmt.setInt(
                    1,
                    prescriptionId
            );

            ResultSet rs =
                    pstmt.executeQuery();

            if (rs.next()) {

                Prescription prescription =
                        createPrescriptionFromResultSet(
                                rs
                        );

                rs.close();
                pstmt.close();
                con.close();

                return prescription;
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve prescription."
            );

            System.out.println(e);
        }

        return null;
    }


    // =========================================================
    // GET PRESCRIPTIONS BY PATIENT
    // =========================================================

    public List<Prescription> getPrescriptionsByPatient(
            int patientId
    ) {

        List<Prescription> prescriptions =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM prescription
                WHERE patient_id = ?
                ORDER BY prescription_date DESC
                """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {
                return prescriptions;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            pstmt.setInt(
                    1,
                    patientId
            );

            ResultSet rs =
                    pstmt.executeQuery();

            while (rs.next()) {

                prescriptions.add(
                        createPrescriptionFromResultSet(
                                rs
                        )
                );
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve patient prescriptions."
            );

            System.out.println(e);
        }

        return prescriptions;
    }


    // =========================================================
    // GET PRESCRIPTIONS BY DOCTOR
    // =========================================================

    public List<Prescription> getPrescriptionsByDoctor(
            int doctorId
    ) {

        List<Prescription> prescriptions =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM prescription
                WHERE doctor_id = ?
                ORDER BY prescription_date DESC
                """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {
                return prescriptions;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            pstmt.setInt(
                    1,
                    doctorId
            );

            ResultSet rs =
                    pstmt.executeQuery();

            while (rs.next()) {

                prescriptions.add(
                        createPrescriptionFromResultSet(
                                rs
                        )
                );
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve doctor prescriptions."
            );

            System.out.println(e);
        }

        return prescriptions;
    }


    // =========================================================
    // GET PENDING PRESCRIPTIONS
    // =========================================================

    public List<Object[]> getPendingPrescriptions() {

        List<Object[]> prescriptions =
                new ArrayList<>();

        String sql = """
                SELECT
                    p.prescription_id,
                    p.consultation_id,
                    p.patient_id,
                    pt.name AS patient_name,
                    p.doctor_id,
                    d.name AS doctor_name,
                    p.medicine_id,
                    m.medicine_name,
                    m.quantity,
                    p.dosage,
                    p.duration,
                    p.instructions,
                    p.status,
                    p.prescription_date
                FROM prescription p
                JOIN patient pt
                    ON p.patient_id = pt.patient_id
                JOIN doctor d
                    ON p.doctor_id = d.doctor_id
                JOIN medicine m
                    ON p.medicine_id = m.medicine_id
                WHERE p.status = 'PENDING'
                ORDER BY p.prescription_date DESC
                """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {
                return prescriptions;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pstmt.executeQuery();

            while (rs.next()) {

                Object[] prescription = {

                        rs.getInt(
                                "prescription_id"
                        ),

                        rs.getInt(
                                "consultation_id"
                        ),

                        rs.getInt(
                                "patient_id"
                        ),

                        rs.getString(
                                "patient_name"
                        ),

                        rs.getInt(
                                "doctor_id"
                        ),

                        rs.getString(
                                "doctor_name"
                        ),

                        rs.getInt(
                                "medicine_id"
                        ),

                        rs.getString(
                                "medicine_name"
                        ),

                        rs.getInt(
                                "quantity"
                        ),

                        rs.getString(
                                "dosage"
                        ),

                        rs.getString(
                                "duration"
                        ),

                        rs.getString(
                                "instructions"
                        ),

                        rs.getString(
                                "status"
                        ),

                        rs.getTimestamp(
                                "prescription_date"
                        )
                };

                prescriptions.add(
                        prescription
                );
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve pending prescriptions."
            );

            System.out.println(e);
        }

        return prescriptions;
    }


    // =========================================================
    // GET ALL PRESCRIPTIONS
    // =========================================================

    public List<Prescription> getAllPrescriptions() {

        List<Prescription> prescriptions =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM prescription
                ORDER BY prescription_date DESC
                """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {
                return prescriptions;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pstmt.executeQuery();

            while (rs.next()) {

                prescriptions.add(
                        createPrescriptionFromResultSet(
                                rs
                        )
                );
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve prescriptions."
            );

            System.out.println(e);
        }

        return prescriptions;
    }


    // =========================================================
    // DISPENSE PRESCRIPTION
    // =========================================================

    public boolean dispensePrescription(
            int prescriptionId
    ) {

        Connection con = null;

        try {

            con =
                    DatabaseConnection.getConnection();

            if (con == null) {

                System.out.println(
                        "Database connection failed."
                );

                return false;
            }


            // Start transaction

            con.setAutoCommit(false);


            // -------------------------------------------------
            // Find prescription and medicine
            // -------------------------------------------------

            String prescriptionSQL = """
                    SELECT
                        medicine_id,
                        status
                    FROM prescription
                    WHERE prescription_id = ?
                    FOR UPDATE
                    """;

            PreparedStatement prescriptionStmt =
                    con.prepareStatement(
                            prescriptionSQL
                    );

            prescriptionStmt.setInt(
                    1,
                    prescriptionId
            );

            ResultSet prescriptionRS =
                    prescriptionStmt.executeQuery();


            if (!prescriptionRS.next()) {

                prescriptionRS.close();
                prescriptionStmt.close();

                con.rollback();

                return false;
            }


            int medicineId =
                    prescriptionRS.getInt(
                            "medicine_id"
                    );

            String status =
                    prescriptionRS.getString(
                            "status"
                    );


            prescriptionRS.close();
            prescriptionStmt.close();


            // -------------------------------------------------
            // Check status
            // -------------------------------------------------

            if (
                    !"PENDING".equalsIgnoreCase(
                            status
                    )
            ) {

                System.out.println(
                        "Prescription has already been dispensed."
                );

                con.rollback();

                return false;
            }


            // -------------------------------------------------
            // Check medicine stock
            // -------------------------------------------------

            String stockSQL = """
                    SELECT quantity
                    FROM medicine
                    WHERE medicine_id = ?
                    FOR UPDATE
                    """;

            PreparedStatement stockStmt =
                    con.prepareStatement(
                            stockSQL
                    );

            stockStmt.setInt(
                    1,
                    medicineId
            );

            ResultSet stockRS =
                    stockStmt.executeQuery();


            if (!stockRS.next()) {

                stockRS.close();
                stockStmt.close();

                con.rollback();

                return false;
            }


            int quantity =
                    stockRS.getInt(
                            "quantity"
                    );


            stockRS.close();
            stockStmt.close();


            // -------------------------------------------------
            // Check stock availability
            // -------------------------------------------------

            if (quantity <= 0) {

                System.out.println(
                        "Medicine is out of stock."
                );

                con.rollback();

                return false;
            }


            // -------------------------------------------------
            // Reduce stock by 1
            // -------------------------------------------------

            String updateMedicineSQL = """
                    UPDATE medicine
                    SET quantity = quantity - 1
                    WHERE medicine_id = ?
                    """;

            PreparedStatement medicineStmt =
                    con.prepareStatement(
                            updateMedicineSQL
                    );

            medicineStmt.setInt(
                    1,
                    medicineId
            );

            medicineStmt.executeUpdate();

            medicineStmt.close();


            // -------------------------------------------------
            // Mark prescription as dispensed
            // -------------------------------------------------

            String updatePrescriptionSQL = """
                    UPDATE prescription
                    SET status = 'DISPENSED'
                    WHERE prescription_id = ?
                    """;

            PreparedStatement updatePrescriptionStmt =
                    con.prepareStatement(
                            updatePrescriptionSQL
                    );

            updatePrescriptionStmt.setInt(
                    1,
                    prescriptionId
            );

            updatePrescriptionStmt.executeUpdate();

            updatePrescriptionStmt.close();


            // -------------------------------------------------
            // Commit
            // -------------------------------------------------

            con.commit();

            con.setAutoCommit(true);

            System.out.println(
                    "Prescription dispensed successfully."
            );

            con.close();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Failed to dispense prescription."
            );

            System.out.println(e);


            try {

                if (con != null) {

                    con.rollback();
                    con.setAutoCommit(true);
                    con.close();
                }

            } catch (SQLException rollbackException) {

                System.out.println(
                        "Rollback failed."
                );

                System.out.println(
                        rollbackException
                );
            }

            return false;
        }
    }


    // =========================================================
    // UPDATE PRESCRIPTION STATUS
    // =========================================================

    public boolean updatePrescriptionStatus(
            int prescriptionId,
            String status
    ) {

        String sql = """
                UPDATE prescription
                SET status = ?
                WHERE prescription_id = ?
                """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {
                return false;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            pstmt.setString(
                    1,
                    status
            );

            pstmt.setInt(
                    2,
                    prescriptionId
            );

            int rows =
                    pstmt.executeUpdate();

            pstmt.close();
            con.close();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Failed to update prescription status."
            );

            System.out.println(e);

            return false;
        }
    }


    // =========================================================
    // CREATE PRESCRIPTION FROM RESULT SET
    // =========================================================

    private Prescription createPrescriptionFromResultSet(
            ResultSet rs
    ) throws SQLException {

        Prescription prescription =
                new Prescription(

                        rs.getInt(
                                "prescription_id"
                        ),

                        rs.getInt(
                                "consultation_id"
                        ),

                        rs.getInt(
                                "patient_id"
                        ),

                        rs.getInt(
                                "doctor_id"
                        ),

                        rs.getInt(
                                "medicine_id"
                        ),

                        rs.getString(
                                "dosage"
                        ),

                        rs.getString(
                                "duration"
                        ),

                        rs.getString(
                                "instructions"
                        ),

                        rs.getTimestamp(
                                "prescription_date"
                        ) != null
                                ? rs.getTimestamp(
                                "prescription_date"
                        ).toLocalDateTime()
                                : null
                );


        prescription.setStatus(
                rs.getString("status")
        );


        return prescription;
    }
}