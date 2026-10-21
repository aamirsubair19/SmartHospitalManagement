package service;

import dao.AppointmentDAO;
import model.Appointment;

import java.util.List;

public class AppointmentService {

    private AppointmentDAO appointmentDAO;


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public AppointmentService() {

        appointmentDAO =
                new AppointmentDAO();
    }


    // =========================================================
    // CREATE APPOINTMENT
    // =========================================================

    public boolean createAppointment(
            Appointment appointment
    ) {

        return appointmentDAO.addAppointment(
                appointment
        );
    }


    // =========================================================
    // SEARCH APPOINTMENT
    // =========================================================

    public Appointment searchAppointment(
            int appointmentId
    ) {

        return appointmentDAO.getAppointmentById(
                appointmentId
        );
    }


    // =========================================================
    // GET ALL APPOINTMENTS
    // =========================================================

    public List<Appointment> getAllAppointments() {

        return appointmentDAO.getAllAppointments();
    }


    // =========================================================
    // GET TODAY'S APPOINTMENTS
    // =========================================================

    public List<Appointment> getTodaysAppointments(
            int doctorId
    ) {

        return appointmentDAO.getTodaysAppointments(
                doctorId
        );
    }


    // =========================================================
    // GET WAITING PATIENTS
    // =========================================================

    public List<Appointment> getWaitingAppointments(
            int doctorId
    ) {

        return appointmentDAO.getWaitingAppointments(
                doctorId
        );
    }


    // =========================================================
    // GET TODAY'S APPOINTMENT COUNT
    // =========================================================

    public int getTodaysAppointmentCount(
            int doctorId
    ) {

        return appointmentDAO.getTodaysAppointmentCount(
                doctorId
        );
    }


    // =========================================================
    // GET WAITING PATIENT COUNT
    // =========================================================

    public int getWaitingPatientCount(
            int doctorId
    ) {

        return appointmentDAO.getWaitingPatientCount(
                doctorId
        );
    }


    // =========================================================
    // GET TODAY'S APPOINTMENTS WITH PATIENT DETAILS
    // =========================================================

    public List<Object[]> getTodaysAppointmentsWithPatientName(
            int doctorId
    ) {

        return appointmentDAO.getTodaysAppointmentsWithPatientName(
                doctorId
        );
    }


    // =========================================================
    // UPDATE APPOINTMENT STATUS
    // =========================================================

    public boolean updateAppointmentStatus(
            int appointmentId,
            String status
    ) {

        return appointmentDAO.updateAppointmentStatus(
                appointmentId,
                status
        );
    }


    // =========================================================
    // CANCEL APPOINTMENT
    // =========================================================

    public boolean cancelAppointment(
            int appointmentId
    ) {

        return appointmentDAO.updateAppointmentStatus(
                appointmentId,
                "CANCELLED"
        );
    }
}