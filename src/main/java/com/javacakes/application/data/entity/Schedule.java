package com.javacakes.application.data.entity;

import com.javacakes.application.data.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Schedule extends AbstractEntity {

    //variable to the hold the time that med is scheduled to be taken
    private String time;

    //Map many schedules to one medication, associating correctly to built-in memory table
    @ManyToOne
    @JoinColumn(name = "medication_id")
    @NotNull
    private Medication medication;

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
