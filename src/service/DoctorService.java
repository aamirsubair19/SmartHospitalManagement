package service;

import dao.DoctorDAO;
import model.Doctor;

import java.util.List;

public class DoctorService {

    private DoctorDAO doctorDAO;

    public DoctorService() {
        doctorDAO = new DoctorDAO();
    }

    public void registerDoctor(Doctor doctor) {
        doctorDAO.addDoctor(doctor);
    }

    public Doctor searchDoctor(int doctorId) {
        return doctorDAO.getDoctorById(doctorId);
    }

    public List<Doctor> getAllDoctors() {
        return doctorDAO.getAllDoctors();
    }
}