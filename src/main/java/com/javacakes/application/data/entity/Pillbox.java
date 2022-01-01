package com.javacakes.application.data.entity;


import com.javacakes.application.data.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class Pillbox extends AbstractEntity {

    private String date;
    private String pillbox_time;

    public Pillbox() {}

    public Pillbox(String date, String pillbox_time) {
        this.date = date;
        this.pillbox_time = pillbox_time;
    }

    public String getDate() {
        return date;
    }

    public String getPillbox_time() {
        return pillbox_time;
    }
}
