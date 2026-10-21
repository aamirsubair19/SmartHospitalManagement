package dao;
import model.Patient;
import util.DatabaseConnection;
import java.sql.*;

public class PatientDAO {
    public void addPatient(Patient patient){
        String sql= """
                INSERT INTO patient (name,age,gender,phone,address) VALUES (?,?,?,?,?)
                """;
        try {
            Connection con=DatabaseConnection.getConnection();
            if (con==null){
                System.out.println("Could not add patient because database connection failed.");
                return;
            }
            PreparedStatement pstmt=con.prepareStatement(sql);
            pstmt.setString(1, patient.getName());
            pstmt.setInt(2, patient.getAge());
            pstmt.setString(3, patient.getGender());
            pstmt.setString(4, patient.getPhone());
            pstmt.setString(5, patient.getAddress());

            pstmt.executeUpdate();
            System.out.println("Patient added successfully.");

            pstmt.close();
            con.close();
        }
        catch(SQLException e){
            System.out.println("Failed to add Patient");
            System.out.println(e);
        }
    }
    public Patient getPatientById(int patientId) {
        String sql = "SELECT * FROM patient WHERE patient_id = ?";
        try {
            Connection con = DatabaseConnection.getConnection();
            if (con == null) {
                System.out.println("Could not retrieve patient because database connection failed.");
                return null;
            }
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, patientId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Patient patient = new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("address")
                );

                rs.close();
                pstmt.close();
                con.close();

                return patient;
            }

            System.out.println("Patient not found.");

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Failed to retrieve patient.");
            System.out.println(e);
        }

        return null;
    }
}
