package model;

import java.time.LocalDateTime;

public class Prescription {

    private int prescriptionId;
    private int consultationId;
    private int patientId;
    private int doctorId;
    private int medicineId;

    private String dosage;
    private String duration;
    private String instructions;

    private String status;

    private LocalDateTime prescriptionDate;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public Prescription(
            int prescriptionId,
            int consultationId,
            int patientId,
            int doctorId,
            int medicineId,
            String dosage,
            String duration,
            String instructions,
            LocalDateTime prescriptionDate
    ) {

        this.prescriptionId = prescriptionId;
        this.consultationId = consultationId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.medicineId = medicineId;
        this.dosage = dosage;
        this.duration = duration;
        this.instructions = instructions;
        this.prescriptionDate = prescriptionDate;

        this.status = "PENDING";
    }


    // =========================================================
    // GETTERS AND SETTERS
    // =========================================================

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(
            int prescriptionId
    ) {
        this.prescriptionId = prescriptionId;
    }


    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(
            int consultationId
    ) {
        this.consultationId = consultationId;
    }


    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(
            int patientId
    ) {
        this.patientId = patientId;
    }


    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(
            int doctorId
    ) {
        this.doctorId = doctorId;
    }


    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(
            int medicineId
    ) {
        this.medicineId = medicineId;
    }


    public String getDosage() {
        return dosage;
    }

    public void setDosage(
            String dosage
    ) {
        this.dosage = dosage;
    }


    public String getDuration() {
        return duration;
    }

    public void setDuration(
            String duration
    ) {
        this.duration = duration;
    }


    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(
            String instructions
    ) {
        this.instructions = instructions;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(
            String status
    ) {
        this.status = status;
    }


    public LocalDateTime getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(
            LocalDateTime prescriptionDate
    ) {
        this.prescriptionDate = prescriptionDate;
    }
}