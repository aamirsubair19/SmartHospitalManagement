package model;

public class Medicine {

    private int medicineId;
    private String medicineName;
    private int quantity;
    private double price;

    public Medicine(
            int medicineId,
            String medicineName,
            int quantity,
            double price
    ) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return medicineId + " - "
                + medicineName
                + " | Stock: "
                + quantity
                + " | Price: ₹"
                + price;
    }
}