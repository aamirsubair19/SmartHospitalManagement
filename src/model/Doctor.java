package model;

public class Doctor extends Person {

    private int doctorId;
    private String specialization;

    public Doctor(int doctorId, String name, int age,
                  String specialization, String phone) {

        super(name, age, phone);

        this.doctorId = doctorId;
        this.specialization = specialization;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Dr. " + getName() + " - " + getSpecialization();
    }
}