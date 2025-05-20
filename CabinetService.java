package ma.enset.gestionconsultation.service;

import ma.enset.gestionconsultation.dao.ConsultationDao;
import ma.enset.gestionconsultation.dao.IConsultationDao;
import ma.enset.gestionconsultation.dao.IPatientDao;
import ma.enset.gestionconsultation.entities.Consultation;
import ma.enset.gestionconsultation.entities.Patient;

import java.sql.SQLException;
import java.util.List;
//definir classe CabinetService
public class CabinetService implements  ICabinetService{
   private IPatientDao patientDao;
   private IConsultationDao consultationDao;

    public CabinetService(IPatientDao patientDao, IConsultationDao consultationDao) {
        this.patientDao = patientDao;
        this.consultationDao = consultationDao;
    }

    @Override
    public void addPatient(Patient patient) {
        try {
            patientDao.create(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePatient(Patient patient) {
        try {
            patientDao.delete(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePatient(Patient patient) {

        try {
            patientDao.update(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients ;
        try {
            patients = patientDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  patients;
    }

    @Override
    public Patient getPatientById(Long id) {
        Patient patient ;
        try {
            patient = patientDao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  patient;
    }

    @Override
    public List<Patient> searchByQuery(String query) {
        try {
            return  patientDao.searchByQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addConsultation(Consultation consultation) {
        try {
            consultationDao.create(consultation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteConsultation(Consultation consultation) {
        try {
            consultationDao.delete(consultation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateConsultation(Consultation consultation) {

    }

    @Override
    public List<Consultation> getAllConsultations() {
        try {
            return consultationDao.findAllConsultation();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Consultation getConsultationById(Long id) {
        return null;
    }
}
