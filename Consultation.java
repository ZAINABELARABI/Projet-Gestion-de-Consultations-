package ma.enset.gestionconsultation.entities;


import java.sql.Date;
//classe Consultation
public class Consultation {
    private long id;
    private Date dateConsultation;
    private String description;
    private Patient patient;

    public Consultation() {
    }

    public Date getDateConsultation() {
        return dateConsultation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDateConsultation(Date dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
