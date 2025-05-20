package ma.enset.gestionconsultation.dao;

import ma.enset.gestionconsultation.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//implementer l interface
public class PatientDao implements  IPatientDao{
    @Override
    public void create(Patient patient) throws SQLException {

    Connection connection = DBConnection.getConnection();
    PreparedStatement pstm = connection.prepareStatement("INSERT INTO patient(nom, prenom, telephone) VALUES (?, ?, ?)");
    pstm.setString(1,patient.getNom());
    pstm.setString(2, patient.getPrenom());
    pstm.setString(3, patient.getTel());
    pstm.executeUpdate();

    }

    @Override
    public void delete(Patient patient) throws SQLException{
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstmDelete = connection.prepareStatement("Delete  from patient where id_patient = ?");
        pstmDelete.setLong(1,patient.getId());
        pstmDelete.executeUpdate();

    }

    @Override
    public void update(Patient patient) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstmUpdate = connection.prepareStatement("Update patient set nom = ? , prenom = ? , telephone = ? where id_patient = ?");
        pstmUpdate.setString(1,patient.getNom());
        pstmUpdate.setString(2, patient.getPrenom());
        pstmUpdate.setString(3, patient.getTel());
        pstmUpdate.setLong(4,patient.getId());
        pstmUpdate.executeUpdate();
    }

    @Override
    public List<Patient> findAll() throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstmSelect = connection.prepareStatement("Select * from patient");
        ResultSet rs = pstmSelect.executeQuery();
        List<Patient> patients = new ArrayList<>();
        while(rs.next()){
        Patient patient = new Patient();
        patient.setId(rs.getLong("id_patient"));
        patient.setNom(rs.getString("nom"));
        patient.setPrenom(rs.getString("prenom"));
        patient.setTel(rs.getString("telephone"));
        patients.add(patient);
        }

        return patients;
    }

    @Override
    public Patient findById(Long id) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstmSelect = connection.prepareStatement("Select * from patient where id_patient = ?");
        pstmSelect.setLong(1,id);
        ResultSet rs = pstmSelect.executeQuery();
        Patient patient = new Patient();
        if (rs.next()){
            patient.setId(rs.getLong("id_patient"));
            patient.setNom(rs.getString("nom"));
            patient.setPrenom(rs.getString("prenom"));
            patient.setTel(rs.getString("telephone"));
        }

        return patient;
    }

    @Override
    public List<Patient> searchByQuery(String query) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstmSearch = connection.prepareStatement("Select * from patient where nom LIKE ? and prenom LIKE ?");
        pstmSearch.setString(1, "%"+query+"%");
        pstmSearch.setString(2, "%"+query+"%");
        ResultSet rs = pstmSearch.executeQuery();
        List<Patient> patients = new ArrayList<>();
        while (rs.next()) {
            Patient patient = new Patient();
            patient.setId(rs.getLong("id_patient"));
            patient.setNom(rs.getString("nom"));
            patient.setPrenom(rs.getString("prenom"));
            patient.setTel(rs.getString("telephone"));
            patients.add(patient);
        }

        return patients;
    }
}
