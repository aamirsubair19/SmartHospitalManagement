package dao;

import model.User;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {


    // =========================================================
    // LOGIN
    // =========================================================

    public User login(
            String username,
            String password
    ) {

        String sql = """
            SELECT
                user_id,
                username,
                password,
                role,
                doctor_id
            FROM users
            WHERE username = ?
            AND password = ?
            """;


        try {

            Connection con =
                    DatabaseConnection.getConnection();


            if (con == null) {

                System.out.println(
                        "Could not login because database connection failed."
                );

                return null;
            }


            PreparedStatement pstmt =
                    con.prepareStatement(sql);


            pstmt.setString(
                    1,
                    username
            );

            pstmt.setString(
                    2,
                    password
            );


            ResultSet rs =
                    pstmt.executeQuery();


            if (rs.next()) {

                int doctorId =
                        rs.getInt("doctor_id");


                User user =
                        new User(
                                rs.getInt("user_id"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("role"),
                                doctorId
                        );


                rs.close();
                pstmt.close();
                con.close();


                return user;
            }


            rs.close();
            pstmt.close();
            con.close();


        } catch (SQLException e) {

            System.out.println(
                    "Login failed."
            );

            System.out.println(e);
        }


        return null;
    }


    // =========================================================
    // ADD USER
    // =========================================================

    public void addUser(
            User user
    ) {

        String sql = """
            INSERT INTO users
            (username, password, role, doctor_id)
            VALUES (?, ?, ?, ?)
            """;


        try {

            Connection con =
                    DatabaseConnection.getConnection();


            if (con == null) {

                System.out.println(
                        "Could not add user because database connection failed."
                );

                return;
            }


            PreparedStatement pstmt =
                    con.prepareStatement(sql);


            pstmt.setString(
                    1,
                    user.getUsername()
            );

            pstmt.setString(
                    2,
                    user.getPassword()
            );

            pstmt.setString(
                    3,
                    user.getRole()
            );


            if (
                    user.getDoctorId() > 0
                            && user.getRole().equalsIgnoreCase("DOCTOR")
            ) {

                pstmt.setInt(
                        4,
                        user.getDoctorId()
                );

            } else {

                pstmt.setNull(
                        4,
                        java.sql.Types.INTEGER
                );
            }


            pstmt.executeUpdate();


            System.out.println(
                    "User added successfully: "
                            + user.getUsername()
            );


            pstmt.close();
            con.close();


        } catch (SQLException e) {

            System.out.println(
                    "Failed to add user."
            );

            System.out.println(e);
        }
    }
}