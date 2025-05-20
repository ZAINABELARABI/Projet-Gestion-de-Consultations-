package ma.enset.gestionconsultation.dao;

import ma.enset.gestionconsultation.entities.Consultation;

import java.sql.SQLException;
import java.util.List;
////implementer l interface
public interface IConsultationDao extends Dao<Consultation,Long>{
    List<Consultation> findAllConsultation() throws SQLException;
}
