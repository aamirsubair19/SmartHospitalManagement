package dao;

import model.Doctor;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    // Add a new doctor
    public void addDoctor(Doctor doctor) {

        String sql = """
            INSERT INTO doctor
            (name, specialization, phone)
            VALUES (?, ?, ?)
            """;

        try {
            Connection con = DatabaseConnection.getConnection();

            if (con == null) {
                System.out.println("Could not add doctor because database connection failed.");
                return;
            }

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, doctor.getName());
            pstmt.setString(2, doctor.getSpecialization());
            pstmt.setString(3, doctor.getPhone());

            pstmt.executeUpdate();

            System.out.println("Doctor added successfully.");

            pstmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Failed to add doctor.");
            System.out.println(e);
        }
    }


    // Search doctor by ID
    public Doctor getDoctorById(int doctorId) {

        String sql = """
                SELECT * FROM doctor WHERE doctor_id = ?
                """;

        try {
            Connection con = DatabaseConnection.getConnection();

            if (con == null) {
                System.out.println("Could not retrieve doctor because database connection failed.");
                return null;
            }

            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, doctorId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                Doctor doctor = new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("name"),
                        0,
                        rs.getString("specialization"),
                        rs.getString("phone")
                );

                rs.close();
                pstmt.close();
                con.close();

                return doctor;
            }

            System.out.println("Doctor not found.");

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println("Failed to retrieve doctor.");
            System.out.println(e);
        }

        return null;
    }


    // Get all doctors
    public List<Doctor> getAllDoctors() {

        List<Doctor> doctors = new ArrayList<>();

        String sql = """
                SELECT * FROM doctor
                ORDER BY name
                """;

        try {
            Connection con = DatabaseConnection.getConnection();

            if (con == null) {
                System.out.println("Could not retrieve doctors because database connection failed.");
                return doctors;
            }

            PreparedStatement pstmt = con.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Doctor doctor = new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("name"),
                        0,
                        rs.getString("specialization"),
                        rs.getString("phone")
                );

                doctors.add(doctor);
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println("Failed to retrieve doctors.");
            System.out.println(e);
        }

        return doctors;
    }
}