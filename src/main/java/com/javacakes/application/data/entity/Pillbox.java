package com.javacakes.application.data.entity;

import com.javacakes.application.data.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Pillbox extends AbstractEntity {

    private String Date;

    private String Time;

    private String Delay;

    //Map many schedules to one medication, associating correctly to built-in memory table
    @ManyToOne
    @JoinColumn(name = "medication_id")
    @NotNull
    private Medication medication;

    public Pillbox() { }

    public Pillbox(String Date, String Time, String Delay) {
        this.Date = Date;
        this.Time = Time;
        this.Delay = Delay;
    }

    public Medication getMedication() {
        return medication;
    }

    public void setMedication(Medication medication) {
        this.medication = medication;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getDelay() {
        return Delay;
    }

    public void setDelay(String Delay) {
        this.Delay = Delay;
    }
}
