package dao;

import model.Consultation;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class ConsultationDAO {


    // =========================================================
    // ADD CONSULTATION
    // =========================================================

    public boolean addConsultation(Consultation consultation) {

        String sql = """
            INSERT INTO consultation
            (
                patient_id,
                doctor_id,
                appointment_id,
                symptoms,
                diagnosis,
                notes,
                treatment
            )
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {

                System.out.println(
                        "Could not add consultation because database connection failed."
                );

                return false;
            }


            PreparedStatement pstmt =
                    con.prepareStatement(sql);


            pstmt.setInt(
                    1,
                    consultation.getPatientId()
            );

            pstmt.setInt(
                    2,
                    consultation.getDoctorId()
            );

            pstmt.setInt(
                    3,
                    consultation.getAppointmentId()
            );

            pstmt.setString(
                    4,
                    consultation.getSymptoms()
            );

            pstmt.setString(
                    5,
                    consultation.getDiagnosis()
            );

            pstmt.setString(
                    6,
                    consultation.getNotes()
            );

            pstmt.setString(
                    7,
                    consultation.getTreatment()
            );


            int rowsAffected =
                    pstmt.executeUpdate();


            pstmt.close();
            con.close();


            if (rowsAffected > 0) {

                System.out.println(
                        "Consultation added successfully."
                );

                return true;
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to add consultation."
            );

            System.out.println(e);
        }


        return false;
    }


    // =========================================================
    // SEARCH CONSULTATION BY ID
    // =========================================================

    public Consultation getConsultationById(
            int consultationId
    ) {

        String sql = """
            SELECT *
            FROM consultation
            WHERE consultation_id = ?
            """;


        try {

            Connection con =
                    DatabaseConnection.getConnection();


            if (con == null) {

                System.out.println(
                        "Could not retrieve consultation because database connection failed."
                );

                return null;
            }


            PreparedStatement pstmt =
                    con.prepareStatement(sql);


            pstmt.setInt(
                    1,
                    consultationId
            );


            ResultSet rs =
                    pstmt.executeQuery();


            if (rs.next()) {

                Consultation consultation =
                        createConsultationFromResultSet(rs);


                rs.close();
                pstmt.close();
                con.close();


                return consultation;
            }


            System.out.println(
                    "Consultation not found."
            );


            rs.close();
            pstmt.close();
            con.close();


        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve consultation."
            );

            System.out.println(e);
        }


        return null;
    }


    // =========================================================
    // GET CONSULTATIONS BY PATIENT
    // =========================================================

    public List<Consultation> getConsultationsByPatient(
            int patientId
    ) {

        List<Consultation> consultations =
                new ArrayList<>();


        String sql = """
            SELECT *
            FROM consultation
            WHERE patient_id = ?
            ORDER BY consultation_date DESC
            """;


        try {

            Connection con =
                    DatabaseConnection.getConnection();


            if (con == null) {

                System.out.println(
                        "Could not retrieve consultations because database connection failed."
                );

                return consultations;
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

                Consultation consultation =
                        createConsultationFromResultSet(rs);

                consultations.add(
                        consultation
                );
            }


            rs.close();
            pstmt.close();
            con.close();


        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve patient consultations."
            );

            System.out.println(e);
        }


        return consultations;
    }


    // =========================================================
    // GET CONSULTATIONS BY DOCTOR
    // =========================================================

    public List<Consultation> getConsultationsByDoctor(
            int doctorId
    ) {

        List<Consultation> consultations =
                new ArrayList<>();


        String sql = """
            SELECT *
            FROM consultation
            WHERE doctor_id = ?
            ORDER BY consultation_date DESC
            """;


        try {

            Connection con =
                    DatabaseConnection.getConnection();


            if (con == null) {

                System.out.println(
                        "Could not retrieve consultations because database connection failed."
                );

                return consultations;
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

                Consultation consultation =
                        createConsultationFromResultSet(rs);

                consultations.add(
                        consultation
                );
            }


            rs.close();
            pstmt.close();
            con.close();


        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve doctor consultations."
            );

            System.out.println(e);
        }


        return consultations;
    }


    // =========================================================
    // CREATE OBJECT FROM RESULT SET
    // =========================================================

    private Consultation createConsultationFromResultSet(
            ResultSet rs
    ) throws SQLException {

        Timestamp timestamp =
                rs.getTimestamp("consultation_date");


        LocalDateTime consultationDate = null;


        if (timestamp != null) {

            consultationDate =
                    timestamp.toLocalDateTime();
        }


        return new Consultation(

                rs.getInt("consultation_id"),

                rs.getInt("patient_id"),

                rs.getInt("doctor_id"),

                rs.getInt("appointment_id"),

                rs.getString("symptoms"),

                rs.getString("diagnosis"),

                rs.getString("notes"),

                rs.getString("treatment"),

                consultationDate
        );
    }
}
