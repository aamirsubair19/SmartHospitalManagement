package service;

import dao.ConsultationDAO;
import model.Consultation;
import java.time.LocalDateTime;

import java.util.List;

public class ConsultationService {

    private ConsultationDAO consultationDAO;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ConsultationService() {

        consultationDAO =
                new ConsultationDAO();
    }


    // =========================================================
    // CREATE CONSULTATION
    // =========================================================

    public boolean createConsultation(
            Consultation consultation
    ) {

        return consultationDAO.addConsultation(
                consultation
        );
    }


    // =========================================================
    // SEARCH CONSULTATION
    // =========================================================

    public Consultation searchConsultation(
            int consultationId
    ) {

        return consultationDAO.getConsultationById(
                consultationId
        );
    }


    // =========================================================
    // GET PATIENT CONSULTATION HISTORY
    // =========================================================

    public List<Consultation> getPatientConsultations(
            int patientId
    ) {

        return consultationDAO.getConsultationsByPatient(
                patientId
        );
    }


    // =========================================================
    // GET DOCTOR CONSULTATION HISTORY
    // =========================================================

    public List<Consultation> getDoctorConsultations(
            int doctorId
    ) {

        return consultationDAO.getConsultationsByDoctor(
                doctorId
        );
    }
}
