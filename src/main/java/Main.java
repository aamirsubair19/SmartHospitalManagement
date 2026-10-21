import util.DatabaseConnection;
import util.DatabaseSetup;
import java.sql.Connection;
import dao.*;
import model.*;
import service.*;
import java.time.LocalDate;
import gui.*;

public class Main {

    public static void main(String[] args) {

        Connection con = DatabaseConnection.getConnection();

        if (con != null) {
            System.out.println("Database connected successfully!");

            /*DatabaseSetup.createPatientTable();
            DatabaseSetup.createDoctorTable();
            DatabaseSetup.createAppointmentTable();*/

        } else {
            System.out.println("Database connection failed!");
        }

        Patient patient=new Patient(0,
                "Test Patient",
                25,
                "Male",
                "9000000000",
                "Chennai");
        /*PatientDAO patientDAO = new PatientDAO();
        patientDAO.addPatient(patient);
        patient =patientDAO.getPatientById(1);
        if (patient != null) {
            System.out.println("Patient Found:");
            System.out.println("Name: " + patient.getName());
            System.out.println("Age: " + patient.getAge());
            System.out.println("Gender: " + patient.getGender());
            System.out.println("Phone: " + patient.getPhone());
            System.out.println("Address: " + patient.getAddress());
        }*/

        /*PatientService patientService = new PatientService();

        patientService.registerPatient(patient);

        Patient foundPatient = patientService.searchPatient(1);

        if (foundPatient != null) {

            System.out.println("\nPatient Found:");
            System.out.println("Name: " + foundPatient.getName());
            System.out.println("Age: " + foundPatient.getAge());
            System.out.println("Gender: " + foundPatient.getGender());
            System.out.println("Phone: " + foundPatient.getPhone());
            System.out.println("Address: " + foundPatient.getAddress());
        }*/

        Doctor doctor=new Doctor(0,
                "Dr. Test",
                40,
                "Cardiology",
                "9000000001");
        DoctorDAO doctorDAO = new DoctorDAO();
        doctorDAO.addDoctor(doctor);
        Doctor doctorFound=doctorDAO.getDoctorById(1);
        if (doctorFound != null) {
            System.out.println("Doctor Found:");
            System.out.println("Name: " + doctorFound.getName());
            System.out.println("Age: " + doctorFound.getAge());
            System.out.println("Specialization: " + doctorFound.getSpecialization());
            System.out.println("Phone: " + doctorFound.getPhone());
        }

        /*Appointment appointment = new Appointment(
                0,
                1,
                1,
                LocalDate.of(2026, 9, 22),
                "Scheduled"
        );*/

        /*AppointmentDAO appointmentDAO = new AppointmentDAO();
        appointmentDAO.addAppointment(appointment);
        Appointment appointmentFound=appointmentDAO.getAppointmentById(1);
        if(appointmentFound!=null){
            System.out.println("Appointment Found:");
            System.out.println("Appointment ID: " + appointmentFound.getAppointmentId());
            System.out.println("Patient ID: " + appointmentFound.getPatientId());
            System.out.println("Doctor ID: " + appointmentFound.getDoctorId());
            System.out.println("Date: " + appointmentFound.getAppointmentDate());
            System.out.println("Status: " + appointmentFound.getStatus());
        }*/

        new ReceptionFrame();

        // Adding new User

        /*UserService userService = new UserService();

        userService.registerUser(
                new User(0, "reception1", "1234", "RECEPTION")
        );

        userService.registerUser(
                new User(0, "doctor1", "1234", "DOCTOR")
        );

        userService.registerUser(
                new User(0, "pharmacy1", "1234", "PHARMACY")
        );

        userService.registerUser(
                new User(0, "ambulance1", "1234", "AMBULANCE")
        );

        userService.registerUser(
                new User(0, "casualty1", "1234", "CASUALTY")
        );*/
    }
}