package dao;

import model.EmergencyCase;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmergencyCaseDAO {

    // ==========================================
    // ADD EMERGENCY CASE
    // ==========================================

    public boolean addEmergencyCase(
            EmergencyCase emergencyCase
    ) {

        String sql = """
            INSERT INTO emergency_case
            (
                patient_name,
                age,
                gender,
                condition_description,
                symptoms,
                blood_group,
                ambulance_number,
                eta,
                status,
                heart_rate,
                spo2,
                temperature,
                blood_pressure
            )
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
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
                    emergencyCase.getPatientName()
            );

            pstmt.setInt(
                    2,
                    emergencyCase.getAge()
            );

            pstmt.setString(
                    3,
                    emergencyCase.getGender()
            );

            pstmt.setString(
                    4,
                    emergencyCase.getConditionDescription()
            );

            pstmt.setString(
                    5,
                    emergencyCase.getSymptoms()
            );

            pstmt.setString(
                    6,
                    emergencyCase.getBloodGroup()
            );

            pstmt.setString(
                    7,
                    emergencyCase.getAmbulanceNumber()
            );

            pstmt.setString(
                    8,
                    emergencyCase.getEta()
            );

            pstmt.setString(
                    9,
                    emergencyCase.getStatus()
            );

            pstmt.setInt(
                    10,
                    emergencyCase.getHeartRate()
            );

            pstmt.setInt(
                    11,
                    emergencyCase.getSpo2()
            );

            pstmt.setDouble(
                    12,
                    emergencyCase.getTemperature()
            );

            pstmt.setString(
                    13,
                    emergencyCase.getBloodPressure()
            );

            int rows =
                    pstmt.executeUpdate();

            pstmt.close();
            con.close();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Failed to add emergency case."
            );

            System.out.println(e);
        }

        return false;
    }

    // ==========================================
    // GET EMERGENCY CASE BY ID
    // ==========================================

    public EmergencyCase getEmergencyCaseById(
            int emergencyId
    ) {

        String sql = """
            SELECT *
            FROM emergency_case
            WHERE emergency_id = ?
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
                    emergencyId
            );

            ResultSet rs =
                    pstmt.executeQuery();

            if (rs.next()) {

                EmergencyCase emergencyCase =
                        createEmergencyCaseFromResultSet(
                                rs
                        );

                rs.close();
                pstmt.close();
                con.close();

                return emergencyCase;
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println(
                    "Failed to search emergency case."
            );

            System.out.println(e);
        }

        return null;
    }

    // ==========================================
    // GET ALL EMERGENCY CASES
    // ==========================================

    public List<EmergencyCase> getAllEmergencyCases() {

        List<EmergencyCase> cases =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM emergency_case
            ORDER BY emergency_id DESC
            """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {
                return cases;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pstmt.executeQuery();

            while (rs.next()) {

                cases.add(
                        createEmergencyCaseFromResultSet(
                                rs
                        )
                );
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println(
                    "Failed to load emergency cases."
            );

            System.out.println(e);
        }

        return cases;
    }

    // ==========================================
    // GET INCOMING EMERGENCY CASES
    // ==========================================

    public List<EmergencyCase> getIncomingEmergencyCases() {

        List<EmergencyCase> cases =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM emergency_case
            WHERE status IN (
                'IN_TRANSIT',
                'INCOMING',
                'SENT',
                'RECEIVED',
                'ARRIVED',
                'TREATED'
            )
            ORDER BY emergency_id DESC
            """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {
                return cases;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pstmt.executeQuery();

            while (rs.next()) {

                cases.add(
                        createEmergencyCaseFromResultSet(
                                rs
                        )
                );
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println(
                    "Failed to load incoming emergency cases."
            );

            System.out.println(e);
        }

        return cases;
    }

    // ==========================================
    // UPDATE EMERGENCY STATUS
    // ==========================================

    public boolean updateEmergencyStatus(
            int emergencyId,
            String status
    ) {

        String sql = """
            UPDATE emergency_case
            SET status = ?
            WHERE emergency_id = ?
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
                    emergencyId
            );

            int rows =
                    pstmt.executeUpdate();

            pstmt.close();
            con.close();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Failed to update emergency status."
            );

            System.out.println(e);
        }

        return false;
    }

    // ==========================================
    // UPDATE ETA
    // ==========================================

    public boolean updateETA(
            int emergencyId,
            String eta
    ) {

        String sql = """
            UPDATE emergency_case
            SET eta = ?
            WHERE emergency_id = ?
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
                    eta
            );

            pstmt.setInt(
                    2,
                    emergencyId
            );

            int rows =
                    pstmt.executeUpdate();

            pstmt.close();
            con.close();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Failed to update ETA."
            );

            System.out.println(e);
        }

        return false;
    }

    // ==========================================
    // UPDATE VITAL SIGNS
    // ==========================================

    public boolean updateVitalSigns(
            int emergencyId,
            int heartRate,
            int spo2,
            double temperature,
            String bloodPressure
    ) {

        String sql = """
            UPDATE emergency_case
            SET
                heart_rate = ?,
                spo2 = ?,
                temperature = ?,
                blood_pressure = ?
            WHERE emergency_id = ?
            """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {
                return false;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            pstmt.setInt(
                    1,
                    heartRate
            );

            pstmt.setInt(
                    2,
                    spo2
            );

            pstmt.setDouble(
                    3,
                    temperature
            );

            pstmt.setString(
                    4,
                    bloodPressure
            );

            pstmt.setInt(
                    5,
                    emergencyId
            );

            int rows =
                    pstmt.executeUpdate();

            pstmt.close();
            con.close();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Failed to update vital signs."
            );

            System.out.println(e);
        }

        return false;
    }

    // ==========================================
    // CREATE OBJECT FROM RESULT SET
    // ==========================================

    private EmergencyCase createEmergencyCaseFromResultSet(
            ResultSet rs
    ) throws SQLException {

        Timestamp timestamp =
                rs.getTimestamp(
                        "created_at"
                );

        return new EmergencyCase(

                rs.getInt(
                        "emergency_id"
                ),

                rs.getString(
                        "patient_name"
                ),

                rs.getInt(
                        "age"
                ),

                rs.getString(
                        "gender"
                ),

                rs.getString(
                        "condition_description"
                ),

                rs.getString(
                        "symptoms"
                ),

                rs.getString(
                        "blood_group"
                ),

                rs.getString(
                        "ambulance_number"
                ),

                rs.getString(
                        "eta"
                ),

                rs.getString(
                        "status"
                ),

                rs.getInt(
                        "heart_rate"
                ),

                rs.getInt(
                        "spo2"
                ),

                rs.getDouble(
                        "temperature"
                ),

                rs.getString(
                        "blood_pressure"
                ),

                timestamp != null
                        ? timestamp.toLocalDateTime()
                        : null
        );
    }
}