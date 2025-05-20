package ma.enset.gestionconsultation.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.gestionconsultation.dao.ConsultationDao;
import ma.enset.gestionconsultation.dao.PatientDao;
import ma.enset.gestionconsultation.entities.Consultation;
import ma.enset.gestionconsultation.entities.Patient;
import ma.enset.gestionconsultation.service.CabinetService;
import ma.enset.gestionconsultation.service.ICabinetService;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable {

    @FXML private TableView<Consultation> tableConsultation;
    @FXML private TableColumn<Consultation,Long> idConsultation;
    @FXML private TableColumn<Consultation,String> colonneDate;
    @FXML private TableColumn<Consultation,String> colonneDesc;
    @FXML private TableColumn<Consultation,String> colonnePatient;
    @FXML private ComboBox<Patient> comboPatient;
    @FXML private TextArea textFieldDesc;
    @FXML private DatePicker dateConsultation;

    private ICabinetService service;
    private ObservableList<Patient> patients = FXCollections.observableArrayList();
    private ObservableList<Consultation> consultations = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        service = new CabinetService(new PatientDao(),new ConsultationDao());
        comboPatient.setItems(patients);
        patients.setAll(service.getAllPatients());
        idConsultation.setCellValueFactory(new PropertyValueFactory<>("id"));
        colonneDate.setCellValueFactory(new PropertyValueFactory<>("dateConsultation"));
        colonneDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colonnePatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        tableConsultation.setItems(tableConsultation.getItems());
        loadConsultations();


    }

    public void addConsultation(){
        Consultation consultation = new Consultation();
        consultation.setDescription(textFieldDesc.getText());
        consultation.setDateConsultation(Date.valueOf(dateConsultation.getValue()));
        consultation.setPatient(comboPatient.getSelectionModel().getSelectedItem());
        service.addConsultation(consultation);
        loadConsultations();


    }
    private void loadConsultations() {
        List<Consultation> liste = service.getAllConsultations();
        consultations.setAll(liste);
        tableConsultation.setItems(consultations);
    }

    public void DeleteConsultation() {
        Consultation consultation = tableConsultation.getSelectionModel().getSelectedItem();
        service.deleteConsultation(consultation);
        loadConsultations();
    }

}
