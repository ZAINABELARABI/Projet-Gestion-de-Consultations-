package ma.enset.gestionconsultation.controllers;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.gestionconsultation.dao.ConsultationDao;
import ma.enset.gestionconsultation.dao.PatientDao;
import ma.enset.gestionconsultation.entities.Patient;
import ma.enset.gestionconsultation.service.CabinetService;
import ma.enset.gestionconsultation.service.ICabinetService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
//controller patient
public class PatientController implements Initializable {
    @FXML private TextField TextFieldNom;
    @FXML private TextField TextFieldPrenom;
    @FXML private TextField TextFieldTele;
    @FXML private TextField TextFieldSearch;
    @FXML private TableView<Patient> TablePatient;
    @FXML private TableColumn<Patient,Long> colonneId;
    @FXML private TableColumn<Patient,String> colonneNom;
    @FXML private TableColumn<Patient,String> colonnePrenom;
    @FXML private TableColumn<Patient,String> colonneTele;
    private Patient selectedPatient;
    private ICabinetService service;
    private ObservableList<Patient> patients = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        service = new CabinetService(new PatientDao(),new ConsultationDao());
        colonneId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colonneNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colonnePrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colonneTele.setCellValueFactory(new PropertyValueFactory<>("tel"));
        TablePatient.setItems(patients);
        loadPatients();
        TextFieldSearch.textProperty().addListener((observableValue, oldValue, newValue) ->{
            patients.setAll(service.searchByQuery(newValue));
        });
        TablePatient.getSelectionModel().selectedItemProperty().addListener((observableValue, oldPatient, newPatient) ->{
            TextFieldNom.setText(newPatient.getNom());
            TextFieldPrenom.setText(newPatient.getPrenom());
            TextFieldTele.setText(newPatient.getTel());
            selectedPatient = newPatient;

                }
        );
    }
    public  void addPatient(){

        Patient patient = new Patient();
        patient.setNom(TextFieldNom.getText());
        patient.setPrenom(TextFieldPrenom.getText());
        patient.setTel(TextFieldTele.getText());
        try {
            service.addPatient(patient);
            loadPatients();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePatient() {
         Patient patient = TablePatient.getSelectionModel().getSelectedItem();
        try {
            service.deletePatient(patient);
            loadPatients();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePatient() {
        selectedPatient.setNom(TextFieldNom.getText());
        selectedPatient.setPrenom(TextFieldPrenom.getText());
        selectedPatient.setTel(TextFieldTele.getText());
        service.updatePatient(selectedPatient);
        loadPatients();
    }
    private void loadPatients(){
        patients.setAll(service.getAllPatients());
    }

}
