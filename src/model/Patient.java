package model;

public class Patient extends Person {
    private int patientId;
    private String gender;
    private String address;

    public Patient(int patientId,String name,int age,String gender,String phone,String address){
        super(name,age,phone);
        this.patientId=patientId;
        this.gender=gender;
        this.address=address;
    }
    public int getPatientId(){
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

