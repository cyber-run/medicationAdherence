package com.javacakes.application.data.entity;

import com.javacakes.application.data.AbstractEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Medication extends AbstractEntity {

    //var to contain medication name
    private String medicationName;

    //Mapping for table to associate 1 medication to many schedules (inverse seen in schedule) as list of timings
    @OneToMany(mappedBy = "medication",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Schedule> medicationSchedule = new ArrayList<>();

    //auto generated inverse attribute mapping
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    //IDE generated getters and setters, as well as contructors
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public List<Schedule> getMedicationSchedule() {
        return medicationSchedule;
    }

    public void setMedicationSchedule(List<Schedule> medicationSchedule) {
        this.medicationSchedule = medicationSchedule;
    }

    public Medication() {
    }

    public Medication(String medicationName, List<Schedule> medicationSchedule) {
        this.medicationName = medicationName;
        this.medicationSchedule = medicationSchedule;
    }
}
