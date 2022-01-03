package com.javacakes.application.data.entity;

import com.javacakes.application.data.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Adherence extends AbstractEntity {

    private Double WeeklyAdherence;

    private Double LongtermAdherence;

    //Map many schedules to one medication, associating correctly to built-in memory table
    @OneToOne
    @JoinColumn(name = "medication_id")
    @NotNull
    private Medication medication;

    public Adherence() { }

    public Adherence(Double WeeklyAdherence, Double LongtermAdherence) {
        this.WeeklyAdherence = WeeklyAdherence;
        this.LongtermAdherence = LongtermAdherence;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public Double getWeeklyAdherence() {
        return WeeklyAdherence;
    }

    public void setWeeklyAdherence(Double WeeklyAdherence) {
        this.WeeklyAdherence = WeeklyAdherence;
    }

    public Double getLongtermAdherence() {
        return LongtermAdherence;
    }

    public void setTime(Double LongtermAdherence) {
        this.LongtermAdherence = LongtermAdherence;
    }
}