package service;

import dao.EmergencyCaseDAO;
import model.EmergencyCase;

import java.util.List;

public class EmergencyCaseService {

    private EmergencyCaseDAO emergencyCaseDAO;

    public EmergencyCaseService() {

        emergencyCaseDAO =
                new EmergencyCaseDAO();
    }


    // =====================================================
    // CREATE / SEND EMERGENCY CASE
    // =====================================================

    public boolean sendEmergencyCase(
            EmergencyCase emergencyCase
    ) {

        return emergencyCaseDAO.addEmergencyCase(
                emergencyCase
        );
    }


    // =====================================================
    // SEARCH EMERGENCY CASE
    // =====================================================

    public EmergencyCase searchEmergencyCase(
            int emergencyId
    ) {

        return emergencyCaseDAO.getEmergencyCaseById(
                emergencyId
        );
    }


    // =====================================================
    // GET ALL EMERGENCY CASES
    // =====================================================

    public List<EmergencyCase> getAllEmergencyCases() {

        return emergencyCaseDAO.getAllEmergencyCases();
    }


    // =====================================================
    // GET INCOMING CASES
    // =====================================================

    public List<EmergencyCase> getIncomingEmergencyCases() {

        return emergencyCaseDAO
                .getIncomingEmergencyCases();
    }


    // =====================================================
    // UPDATE STATUS
    // =====================================================

    public boolean updateEmergencyStatus(
            int emergencyId,
            String status
    ) {

        return emergencyCaseDAO.updateEmergencyStatus(
                emergencyId,
                status
        );
    }


    // =====================================================
    // UPDATE ETA
    // =====================================================

    public boolean updateETA(
            int emergencyId,
            String eta
    ) {

        return emergencyCaseDAO.updateETA(
                emergencyId,
                eta
        );
    }


    // =====================================================
    // UPDATE VITAL SIGNS
    // =====================================================

    public boolean updateVitalSigns(
            int emergencyId,
            int heartRate,
            int spo2,
            double temperature,
            String bloodPressure
    ) {

        return emergencyCaseDAO.updateVitalSigns(
                emergencyId,
                heartRate,
                spo2,
                temperature,
                bloodPressure
        );
    }
}