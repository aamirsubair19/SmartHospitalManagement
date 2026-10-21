package service;

import dao.PrescriptionDAO;
import model.Prescription;

import java.util.List;

public class PrescriptionService {

    private PrescriptionDAO prescriptionDAO;

    public PrescriptionService() {

        prescriptionDAO =
                new PrescriptionDAO();
    }


    // Create prescription
    public boolean createPrescription(
            Prescription prescription
    ) {

        return prescriptionDAO.addPrescription(
                prescription
        );
    }


    // Search prescription by ID
    public Prescription searchPrescription(
            int prescriptionId
    ) {

        return prescriptionDAO.getPrescriptionById(
                prescriptionId
        );
    }


    // Get prescriptions of a patient
    public List<Prescription> getPatientPrescriptions(
            int patientId
    ) {

        return prescriptionDAO.getPrescriptionsByPatient(
                patientId
        );
    }


    // Get prescriptions of a doctor
    public List<Prescription> getDoctorPrescriptions(
            int doctorId
    ) {

        return prescriptionDAO.getPrescriptionsByDoctor(
                doctorId
        );
    }


    // Get all prescriptions
    public List<Prescription> getAllPrescriptions() {

        return prescriptionDAO.getAllPrescriptions();
    }


    // Get pending prescriptions for Pharmacy
    public List<Object[]> getPendingPrescriptions() {

        return prescriptionDAO.getPendingPrescriptions();
    }


    // Dispense prescription
    public boolean dispensePrescription(
            int prescriptionId
    ) {

        return prescriptionDAO.dispensePrescription(
                prescriptionId
        );
    }


    // Update prescription status
    public boolean updatePrescriptionStatus(
            int prescriptionId,
            String status
    ) {

        return prescriptionDAO.updatePrescriptionStatus(
                prescriptionId,
                status
        );
    }
}