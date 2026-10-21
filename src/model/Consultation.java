package model;

import java.time.LocalDateTime;

public class Consultation {

    private int consultationId;
    private int patientId;
    private int doctorId;
    private int appointmentId;

    private String symptoms;
    private String diagnosis;
    private String notes;
    private String treatment;

    private LocalDateTime consultationDate;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public Consultation(
            int consultationId,
            int patientId,
            int doctorId,
            int appointmentId,
            String symptoms,
            String diagnosis,
            String notes,
            String treatment,
            LocalDateTime consultationDate
    ) {

        this.consultationId = consultationId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentId = appointmentId;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.notes = notes;
        this.treatment = treatment;
        this.consultationDate = consultationDate;
    }


    // =========================================================
    // GETTERS AND SETTERS
    // =========================================================

    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }


    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }


    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }


    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }


    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }


    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }


    public LocalDateTime getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(LocalDateTime consultationDate) {
        this.consultationDate = consultationDate;
    }
}
