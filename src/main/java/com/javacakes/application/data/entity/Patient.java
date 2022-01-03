package com.javacakes.application.data.entity;

import com.javacakes.application.data.AbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Patient extends AbstractEntity {

    //Var to contain anonymous patient ID
    @NotBlank
    private String Patient_ID;

    //Table mapping for database ie a link of meds, include cascade all so all changes will affect associated branches of table
    //Orphan removal to prevent possible memory leakage if parent ie patient is deleted, maybe change later
    @OneToMany(mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Medication> patientMedication = new LinkedList<>();

    //IDE generated getters, setters and constructors
    public String getPatientID() {
        return Patient_ID;
    }

    public void setPatientID(String Patient_ID) {
        this.Patient_ID = Patient_ID;
    }

    public List<Medication> getMedication() {
        return patientMedication;
    }

    public void setMedication(List<Medication> patientMedication) {
        this.patientMedication = patientMedication;
    }

    public Patient() {
    }

    public Patient(String Patient_ID, List<Medication> patientMedication) {
        this.Patient_ID = Patient_ID;
        this.patientMedication = patientMedication;
    }
}
