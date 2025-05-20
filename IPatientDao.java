package ma.enset.gestionconsultation.dao;

import ma.enset.gestionconsultation.entities.Patient;

import java.sql.SQLException;
import java.util.List;
//interface patient
public interface IPatientDao extends Dao<Patient,Long>{
    List<Patient> searchByQuery(String query) throws SQLException;



}
