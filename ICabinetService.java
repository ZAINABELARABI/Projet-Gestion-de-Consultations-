package ma.enset.gestionconsultation.service;

import ma.enset.gestionconsultation.entities.Consultation;
import ma.enset.gestionconsultation.entities.Patient;

import java.sql.SQLException;
import java.util.List;
//definir l interface de notre classe CabinetService
public interface ICabinetService {
    void addPatient(Patient patient) throws SQLException;
    void deletePatient(Patient patient) throws SQLException;
    void updatePatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    List<Patient>  searchByQuery(String query);
    void addConsultation(Consultation consultation);
    void deleteConsultation(Consultation consultation);
    void updateConsultation(Consultation consultation);
    List<Consultation> getAllConsultations();
    Consultation getConsultationById(Long id);

}
