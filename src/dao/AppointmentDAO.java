package dao;

import model.Appointment;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    // =========================================================
    // ADD APPOINTMENT
    // =========================================================

    public boolean addAppointment(Appointment appointment) {

        String sql = """
            INSERT INTO appointment
            (patient_id, doctor_id, appointment_date, appointment_time, status)
            VALUES (?, ?, ?, ?, ?)
            """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {

                System.out.println(
                        "Could not add appointment because database connection failed."
                );

                return false;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            pstmt.setInt(
                    1,
                    appointment.getPatientId()
            );

            pstmt.setInt(
                    2,
                    appointment.getDoctorId()
            );

            pstmt.setDate(
                    3,
                    Date.valueOf(
                            appointment.getAppointmentDate()
                    )
            );

            pstmt.setTime(
                    4,
                    Time.valueOf(
                            appointment.getAppointmentTime()
                    )
            );

            pstmt.setString(
                    5,
                    appointment.getStatus()
            );

            int rowsAffected =
                    pstmt.executeUpdate();

            pstmt.close();
            con.close();

            if (rowsAffected > 0) {

                System.out.println(
                        "Appointment added successfully."
                );

                return true;
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to add appointment."
            );

            System.out.println(e);
        }

        return false;
    }


    // =========================================================
    // SEARCH APPOINTMENT BY ID
    // =========================================================

    public Appointment getAppointmentById(
            int appointmentId
    ) {

        String sql = """
            SELECT *
            FROM appointment
            WHERE appointment_id = ?
            """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {

                System.out.println(
                        "Could not retrieve appointment because database connection failed."
                );

                return null;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            pstmt.setInt(
                    1,
                    appointmentId
            );

            ResultSet rs =
                    pstmt.executeQuery();

            if (rs.next()) {

                Appointment appointment =
                        createAppointmentFromResultSet(rs);

                rs.close();
                pstmt.close();
                con.close();

                return appointment;
            }

            System.out.println(
                    "Appointment not found."
            );

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve appointment."
            );

            System.out.println(e);
        }

        return null;
    }


    // =========================================================
    // GET ALL APPOINTMENTS
    // =========================================================

    public List<Appointment> getAllAppointments() {

        List<Appointment> appointments =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM appointment
            ORDER BY appointment_date, appointment_time
            """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {

                System.out.println(
                        "Could not retrieve appointments because database connection failed."
                );

                return appointments;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pstmt.executeQuery();

            while (rs.next()) {

                Appointment appointment =
                        createAppointmentFromResultSet(rs);

                appointments.add(
                        appointment
                );
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve appointments."
            );

            System.out.println(e);
        }

        return appointments;
    }


    // =========================================================
    // GET TODAY'S APPOINTMENTS FOR A DOCTOR
    // =========================================================

    public List<Appointment> getTodaysAppointments(
            int doctorId
    ) {

        List<Appointment> appointments =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM appointment
            WHERE doctor_id = ?
            AND appointment_date = CURDATE()
            ORDER BY appointment_time
            """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {

                System.out.println(
                        "Could not retrieve today's appointments because database connection failed."
                );

                return appointments;
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

                Appointment appointment =
                        createAppointmentFromResultSet(rs);

                appointments.add(
                        appointment
                );
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve today's appointments."
            );

            System.out.println(e);
        }

        return appointments;
    }


    // =========================================================
    // GET WAITING PATIENTS FOR A DOCTOR
    // =========================================================

    public List<Appointment> getWaitingAppointments(
            int doctorId
    ) {

        List<Appointment> appointments =
                new ArrayList<>();

        String sql = """
            SELECT *
            FROM appointment
            WHERE doctor_id = ?
            AND appointment_date = CURDATE()
            AND status = 'WAITING'
            ORDER BY appointment_time
            """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {

                System.out.println(
                        "Could not retrieve waiting patients because database connection failed."
                );

                return appointments;
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

                Appointment appointment =
                        createAppointmentFromResultSet(rs);

                appointments.add(
                        appointment
                );
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve waiting patients."
            );

            System.out.println(e);
        }

        return appointments;
    }


    // =========================================================
    // GET TODAY'S APPOINTMENT COUNT
    // =========================================================

    public int getTodaysAppointmentCount(
            int doctorId
    ) {

        String sql = """
            SELECT COUNT(*)
            FROM appointment
            WHERE doctor_id = ?
            AND appointment_date = CURDATE()
            """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {

                System.out.println(
                        "Could not retrieve appointment count because database connection failed."
                );

                return 0;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            pstmt.setInt(
                    1,
                    doctorId
            );

            ResultSet rs =
                    pstmt.executeQuery();

            if (rs.next()) {

                int count =
                        rs.getInt(1);

                rs.close();
                pstmt.close();
                con.close();

                return count;
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve today's appointment count."
            );

            System.out.println(e);
        }

        return 0;
    }


    // =========================================================
    // GET WAITING PATIENT COUNT
    // =========================================================

    public int getWaitingPatientCount(
            int doctorId
    ) {

        String sql = """
            SELECT COUNT(*)
            FROM appointment
            WHERE doctor_id = ?
            AND appointment_date = CURDATE()
            AND status = 'WAITING'
            """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {

                System.out.println(
                        "Could not retrieve waiting patient count because database connection failed."
                );

                return 0;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            pstmt.setInt(
                    1,
                    doctorId
            );

            ResultSet rs =
                    pstmt.executeQuery();

            if (rs.next()) {

                int count =
                        rs.getInt(1);

                rs.close();
                pstmt.close();
                con.close();

                return count;
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve waiting patient count."
            );

            System.out.println(e);
        }

        return 0;
    }


    // =========================================================
    // GET TODAY'S APPOINTMENTS WITH PATIENT DETAILS
    // =========================================================

    public List<Object[]> getTodaysAppointmentsWithPatientName(
            int doctorId
    ) {

        List<Object[]> appointments =
                new ArrayList<>();

        String sql = """
            SELECT
                a.appointment_id,
                a.patient_id,
                p.name,
                a.appointment_date,
                a.appointment_time,
                a.status
            FROM appointment a
            JOIN patient p
                ON a.patient_id = p.patient_id
            WHERE a.doctor_id = ?
            AND a.appointment_date = CURDATE()
            ORDER BY a.appointment_time
            """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {

                System.out.println(
                        "Could not retrieve today's appointments."
                );

                return appointments;
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

                Object[] appointment = {

                        rs.getInt("appointment_id"),

                        rs.getInt("patient_id"),

                        rs.getString("name"),

                        rs.getDate("appointment_date"),

                        rs.getTime("appointment_time"),

                        rs.getString("status")
                };

                appointments.add(
                        appointment
                );
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve today's appointments with patient details."
            );

            System.out.println(e);
        }

        return appointments;
    }


    // =========================================================
    // UPDATE APPOINTMENT STATUS
    // =========================================================

    public boolean updateAppointmentStatus(
            int appointmentId,
            String status
    ) {

        String sql = """
            UPDATE appointment
            SET status = ?
            WHERE appointment_id = ?
            """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {

                System.out.println(
                        "Could not update appointment because database connection failed."
                );

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
                    appointmentId
            );

            int rowsAffected =
                    pstmt.executeUpdate();

            pstmt.close();
            con.close();

            if (rowsAffected > 0) {

                System.out.println(
                        "Appointment status updated successfully."
                );

                return true;
            }

            System.out.println(
                    "Appointment not found."
            );

        } catch (SQLException e) {

            System.out.println(
                    "Failed to update appointment status."
            );

            System.out.println(e);
        }

        return false;
    }


    // =========================================================
    // CREATE APPOINTMENT OBJECT FROM RESULT SET
    // =========================================================

    private Appointment createAppointmentFromResultSet(
            ResultSet rs
    ) throws SQLException {

        return new Appointment(

                rs.getInt(
                        "appointment_id"
                ),

                rs.getInt(
                        "patient_id"
                ),

                rs.getInt(
                        "doctor_id"
                ),

                rs.getDate(
                        "appointment_date"
                ).toLocalDate(),

                rs.getTime(
                        "appointment_time"
                ).toLocalTime(),

                rs.getString(
                        "status"
                )
        );
    }
}