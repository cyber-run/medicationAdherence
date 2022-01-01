package com.javacakes.application.data.entity;

import com.javacakes.application.data.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class Adherence extends AbstractEntity {

    private String time_delay;
    private String weekly_adherence;

    public Adherence() {}

    public Adherence(String time_delay, String weekly_adherence) {
        this.time_delay = time_delay;
        this.weekly_adherence = weekly_adherence;
    }

    public String getTime_delay() {
        return time_delay;
    }

    public String getWeekly_adherence() {
        return weekly_adherence;
    }
}
