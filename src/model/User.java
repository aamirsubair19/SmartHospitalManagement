package model;

public class User {

    private int userId;
    private String username;
    private String password;
    private String role;
    private int doctorId;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public User(
            int userId,
            String username,
            String password,
            String role
    ) {

        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.doctorId = 0;
    }


    // =========================================================
    // CONSTRUCTOR WITH DOCTOR ID
    // =========================================================

    public User(
            int userId,
            String username,
            String password,
            String role,
            int doctorId
    ) {

        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.doctorId = doctorId;
    }


    // =========================================================
    // USER ID
    // =========================================================

    public int getUserId() {

        return userId;
    }

    public void setUserId(int userId) {

        this.userId = userId;
    }


    // =========================================================
    // USERNAME
    // =========================================================

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }


    // =========================================================
    // PASSWORD
    // =========================================================

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }


    // =========================================================
    // ROLE
    // =========================================================

    public String getRole() {

        return role;
    }

    public void setRole(String role) {

        this.role = role;
    }


    // =========================================================
    // DOCTOR ID
    // =========================================================

    public int getDoctorId() {

        return doctorId;
    }

    public void setDoctorId(int doctorId) {

        this.doctorId = doctorId;
    }
}