package service;
import dao.PatientDAO;
import model.Patient;

public class PatientService {
    private PatientDAO patientDAO;
    public PatientService() {
        patientDAO = new PatientDAO();
    }
    public void registerPatient(Patient patient) {
        patientDAO.addPatient(patient);
    }
    public Patient searchPatient(int patientId) {
        return patientDAO.getPatientById(patientId);
    }
}
