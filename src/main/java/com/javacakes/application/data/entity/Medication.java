package com.javacakes.application.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.javacakes.application.data.AbstractEntity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Medication extends AbstractEntity {

    //var to contain medication name
    @NotEmpty
    private String PillType = "";

    @NotEmpty
    private String PillStrength = "";

    @NotEmpty
    private String PillDosage = "";

    @NotEmpty
    private String PillSchedule = "";

    @NotEmpty
    private String PillComment = "";

    //auto generated inverse attribute mapping
    @ManyToOne
    @JoinColumn(name = "patient_id")
    @NotNull
    @JsonIgnoreProperties({"patientMedication"})
    private Patient patient;

    //Mapping for table to associate 1 medication to many Pillbox (inverse seen in Pillbox)
    @OneToMany(mappedBy = "medication",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Pillbox> patientPillbox = new LinkedList<>();

    //Mapping for table to associate 1 medication to one adherence  (inverse seen in Adherence)
    @NotNull
    @OneToOne
    private Adherence adherence;

    //IDE generated getters and setters, as well as contructors
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Pillbox> getPillbox() {
        return patientPillbox;
    }

    public void setPillbox(List<Pillbox> patientPillbox) {
        this.patientPillbox = patientPillbox;
    }

    public Adherence getAdherence() {
        return adherence;
    }

    public void setAdherence(Adherence adherence) {
        this.adherence = adherence;
    }

    public String getPillType() {
        return PillType;
    }

    public void setPillType(String PillType) {
        this.PillType = PillType;
    }

    public String getPillStrength() {
        return PillStrength;
    }

    public void setPillStrength(String PillStrength) {
        this.PillStrength = PillStrength;
    }

    public String getPillDosage() {
        return PillDosage;
    }

    public void setPillDosage(String PillDosage) {
        this.PillDosage = PillDosage;
    }

    public String getPillSchedule() {
        return PillSchedule;
    }

    public void setPillSchedule(String PillSchedule) {
        this.PillSchedule = PillSchedule;
    }

    public String getPillComment() {
        return PillComment;
    }

    public void setPillComment(String PillComment) {
        this.PillComment = PillComment;
    }

    public Medication() {
    }

    public Medication(String PillType, String PillStrength, String PillDosage,
                      String PillSchedule, String PillComment, List<Pillbox> patientPillbox,
                      Adherence adherence) {
        this.PillType = PillType;
        this.PillStrength = PillStrength;
        this.PillDosage = PillDosage;
        this.PillSchedule = PillSchedule;
        this.PillComment = PillComment;
        this.adherence = adherence;
        this.patientPillbox = patientPillbox;
    }
}