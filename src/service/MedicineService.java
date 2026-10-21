package service;

import dao.MedicineDAO;
import model.Medicine;

import java.util.List;

public class MedicineService {

    private MedicineDAO medicineDAO;

    public MedicineService() {
        medicineDAO = new MedicineDAO();
    }

    public void addMedicine(Medicine medicine) {

        medicineDAO.addMedicine(medicine);
    }

    public List<Medicine> getAllMedicines() {

        return medicineDAO.getAllMedicines();
    }

    public Medicine searchMedicine(int medicineId) {

        return medicineDAO.getMedicineById(
                medicineId
        );
    }

    public boolean updateStock(
            int medicineId,
            int quantity
    ) {

        return medicineDAO.updateStock(
                medicineId,
                quantity
        );
    }

    public int getMedicineCount() {

        return medicineDAO.getMedicineCount();
    }

    public int getLowStockCount() {

        return medicineDAO.getLowStockCount();
    }
}