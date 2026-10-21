package model;

import java.time.LocalDateTime;

public class EmergencyCase {

    private int emergencyId;

    private String patientName;
    private int age;
    private String gender;

    private String conditionDescription;
    private String symptoms;
    private String bloodGroup;

    private String ambulanceNumber;
    private String eta;
    private String status;

    // Simulated vital signs
    private int heartRate;
    private int spo2;
    private double temperature;
    private String bloodPressure;

    private LocalDateTime createdAt;


    public EmergencyCase(
            int emergencyId,
            String patientName,
            int age,
            String gender,
            String conditionDescription,
            String symptoms,
            String bloodGroup,
            String ambulanceNumber,
            String eta,
            String status,
            int heartRate,
            int spo2,
            double temperature,
            String bloodPressure,
            LocalDateTime createdAt
    ) {

        this.emergencyId = emergencyId;
        this.patientName = patientName;
        this.age = age;
        this.gender = gender;
        this.conditionDescription = conditionDescription;
        this.symptoms = symptoms;
        this.bloodGroup = bloodGroup;
        this.ambulanceNumber = ambulanceNumber;
        this.eta = eta;
        this.status = status;

        this.heartRate = heartRate;
        this.spo2 = spo2;
        this.temperature = temperature;
        this.bloodPressure = bloodPressure;

        this.createdAt = createdAt;
    }


    public int getEmergencyId() {
        return emergencyId;
    }

    public void setEmergencyId(int emergencyId) {
        this.emergencyId = emergencyId;
    }


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getConditionDescription() {
        return conditionDescription;
    }

    public void setConditionDescription(
            String conditionDescription
    ) {
        this.conditionDescription =
                conditionDescription;
    }


    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }


    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }


    public String getAmbulanceNumber() {
        return ambulanceNumber;
    }

    public void setAmbulanceNumber(
            String ambulanceNumber
    ) {
        this.ambulanceNumber =
                ambulanceNumber;
    }


    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    // =========================
    // VITAL SIGN GETTERS/SETTERS
    // =========================

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }


    public int getSpo2() {
        return spo2;
    }

    public void setSpo2(int spo2) {
        this.spo2 = spo2;
    }


    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }


    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(
            String bloodPressure
    ) {
        this.bloodPressure =
                bloodPressure;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(
            LocalDateTime createdAt
    ) {
        this.createdAt = createdAt;
    }


    @Override
    public String toString() {

        return "Emergency Case #" +
                emergencyId +
                " - " +
                patientName +
                " - " +
                status;
    }
}