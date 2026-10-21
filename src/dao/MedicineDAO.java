package dao;

import model.Medicine;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO {

    // Add a new medicine
    public void addMedicine(Medicine medicine) {

        String sql = """
                INSERT INTO medicine
                (medicine_name, quantity, price)
                VALUES (?, ?, ?)
                """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {
                System.out.println(
                        "Could not add medicine because database connection failed."
                );
                return;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            pstmt.setString(
                    1,
                    medicine.getMedicineName()
            );

            pstmt.setInt(
                    2,
                    medicine.getQuantity()
            );

            pstmt.setDouble(
                    3,
                    medicine.getPrice()
            );

            pstmt.executeUpdate();

            System.out.println(
                    "Medicine added successfully."
            );

            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println(
                    "Failed to add medicine."
            );

            System.out.println(e);
        }
    }


    // Get all medicines
    public List<Medicine> getAllMedicines() {

        List<Medicine> medicines =
                new ArrayList<>();

        String sql = """
                SELECT *
                FROM medicine
                ORDER BY medicine_name
                """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {

                System.out.println(
                        "Could not retrieve medicines because database connection failed."
                );

                return medicines;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pstmt.executeQuery();

            while (rs.next()) {

                Medicine medicine =
                        new Medicine(
                                rs.getInt("medicine_id"),
                                rs.getString("medicine_name"),
                                rs.getInt("quantity"),
                                rs.getDouble("price")
                        );

                medicines.add(medicine);
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve medicines."
            );

            System.out.println(e);
        }

        return medicines;
    }


    // Search medicine by ID
    public Medicine getMedicineById(int medicineId) {

        String sql = """
                SELECT *
                FROM medicine
                WHERE medicine_id = ?
                """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {

                System.out.println(
                        "Could not retrieve medicine because database connection failed."
                );

                return null;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            pstmt.setInt(
                    1,
                    medicineId
            );

            ResultSet rs =
                    pstmt.executeQuery();

            if (rs.next()) {

                Medicine medicine =
                        new Medicine(
                                rs.getInt("medicine_id"),
                                rs.getString("medicine_name"),
                                rs.getInt("quantity"),
                                rs.getDouble("price")
                        );

                rs.close();
                pstmt.close();
                con.close();

                return medicine;
            }

            System.out.println(
                    "Medicine not found."
            );

            rs.close();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            System.out.println(
                    "Failed to retrieve medicine."
            );

            System.out.println(e);
        }

        return null;
    }


    // Update medicine stock
    public boolean updateStock(
            int medicineId,
            int quantity
    ) {

        String sql = """
                UPDATE medicine
                SET quantity = ?
                WHERE medicine_id = ?
                """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {

                System.out.println(
                        "Could not update stock because database connection failed."
                );

                return false;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            pstmt.setInt(
                    1,
                    quantity
            );

            pstmt.setInt(
                    2,
                    medicineId
            );

            int rows =
                    pstmt.executeUpdate();

            pstmt.close();
            con.close();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Failed to update medicine stock."
            );

            System.out.println(e);

            return false;
        }
    }


    // Get total number of medicines
    public int getMedicineCount() {

        String sql = """
                SELECT COUNT(*)
                AS medicine_count
                FROM medicine
                """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {
                return 0;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pstmt.executeQuery();

            if (rs.next()) {

                int count =
                        rs.getInt("medicine_count");

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
                    "Failed to get medicine count."
            );

            System.out.println(e);
        }

        return 0;
    }


    // Get number of low-stock medicines
    public int getLowStockCount() {

        String sql = """
                SELECT COUNT(*)
                AS low_stock_count
                FROM medicine
                WHERE quantity <= 10
                """;

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            if (con == null) {
                return 0;
            }

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            ResultSet rs =
                    pstmt.executeQuery();

            if (rs.next()) {

                int count =
                        rs.getInt("low_stock_count");

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
                    "Failed to get low-stock count."
            );

            System.out.println(e);
        }

        return 0;
    }
}