package com.javacakes.application.data.entity;

import com.javacakes.application.data.AbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient extends AbstractEntity {

    //Var to contain anonymous patient ID
    @NotEmpty
    private String patientID = "";

    //Table mapping for database ie a link of meds, include cascade all so all changes will affect associated branches of table
    //Orphan removal to prevent possible memory leakage if parent ie patient is deleted, maybe change later
    @OneToMany(mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Medication> patientMedication = new ArrayList<>();

    //IDE generated getters, setters and constructors
    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public Patient() {
    }

    public Patient(String patientID, List<Medication> patientMedication) {
        this.patientID = patientID;
        this.patientMedication = patientMedication;
    }

}
