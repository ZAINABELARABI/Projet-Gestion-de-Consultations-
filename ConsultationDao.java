package ma.enset.gestionconsultation.dao;

import ma.enset.gestionconsultation.entities.Consultation;
import ma.enset.gestionconsultation.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//implementer l interface
public class ConsultationDao implements IConsultationDao {
    @Override
    public void create(Consultation consultation) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO consultations(date_consultation, description, id_patient ) VALUES (?, ?, ?)");
        pstm.setDate(1,consultation.getDateConsultation());
        pstm.setString(2, consultation.getDescription());
        pstm.setLong(3, consultation.getPatient().getId());
        pstm.executeUpdate();
    }

    @Override
    public void delete(Consultation consultation) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstmDelete = connection.prepareStatement("Delete  from consultations where id_consultation = ?");
        pstmDelete.setLong(1,consultation.getId());
        pstmDelete.executeUpdate();
    }

    @Override
    public void update(Consultation consultation) {

    }

    @Override
    public List<Consultation> findAll() throws SQLException {
        return null;

    }


    @Override
    public Consultation findById(Long id) throws SQLException {
        return null;
    }


    @Override
    public List<Consultation> findAllConsultation() throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstmSelect = connection.prepareStatement("SELECT * FROM consultations");
        ResultSet rs = pstmSelect.executeQuery();
        List<Consultation> consultations = new ArrayList<>();

        PatientDao patientDAO = new PatientDao();

        while (rs.next()) {
            Consultation consultation = new Consultation();
            consultation.setId(rs.getLong("id_consultation"));
            consultation.setDateConsultation(rs.getDate("date_consultation"));
            consultation.setDescription(rs.getString("description"));

            Long patientId = rs.getLong("id_patient");
            Patient patient = patientDAO.findById(patientId); // 🔥 Corrigé ici

            if (patient != null) {
                consultation.setPatient(patient);
            } else {
                System.out.println("⚠️ Patient introuvable pour ID: " + patientId);
            }

            consultations.add(consultation);
        }

        return consultations;
    }
}
