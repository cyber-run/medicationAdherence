package com.javacakes.application.views.Data;

import com.javacakes.application.data.entity.Medication;
import com.javacakes.application.data.entity.Pillbox;
import com.javacakes.application.data.service.JavacakeService;
import com.javacakes.application.views.DefaultLayout;
import com.sun.xml.internal.messaging.saaj.soap.ver1_1.Header1_1Impl;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Objects;

@PermitAll
@Route(value = "weeklybreakdown", layout = DefaultLayout.class)
@PageTitle("WeeklyBreakdown")
public class WeeklyBreakdown extends VerticalLayout {

    // Page heading
    H1 heading = new H1("Weekly Breakdown");

    // New chart
    Chart chart = new Chart(ChartType.HEATMAP);
    Configuration conf = chart.getConfiguration();
    XAxis xaxis = new XAxis();
    YAxis yaxis = new YAxis();


    JavacakeService service;

    long pillNum; // Number of pill types
    long entriesNum; // Number of entries in pillbox data table
    long weekEntries; // Number of entries to make a week
    int weekStart; // Index of first entry of the last 7 days
    int indexStart; // First index of for loop
    int delayNum; // Number of elements in each delay entry
    int delaySum; // Sum of delays in each delay entry
    int delayMean; // Mean of delays in each delay entry
    int delayMins; // Delay in minutes

    String pillName;
    String nameMatch;
    String delay;
    String[] delaySplit;
    String date;
    List<Medication> pillTypes; // Lazy loading because direct methods were causing errors
    List<Pillbox> entries;
    public WeeklyBreakdown(JavacakeService service) {
        this.service = service;
        pillTypes =  service.findAllMedication();
        entries = service.findAllPillbox();
        pillNum = service.countMedication();
        entriesNum = service.countPillbox();
        chart.setWidth("1400px");
        chart.setHeight("600px");
        conf.setTitle("Weekly Breakdown");

        // Set the category labels on the X axis
        xaxis.setTitle("Pill Type");
        xaxis.setCategories("Day 1", "Day 2", "Day 3",
                "Day 4", "Day 5", "Day 6", "Day 7");
        conf.addxAxis(xaxis);

        // Set the category labels on the Y axis
        yaxis.setTitle("");
        yaxis.setCategories("Morning", "Afternoon", "Evening");
        conf.addyAxis(yaxis);

        // Set up border and data labels
        PlotOptionsHeatmap plotOptions = new PlotOptionsHeatmap();
        plotOptions.setBorderColor(SolidColor.WHITE);
        plotOptions.setBorderWidth(2);
        plotOptions.setDataLabels(new DataLabels(true));
        conf.setPlotOptions(plotOptions);

        // Set colors for the extremes
        conf.getColorAxis().setMinColor(SolidColor.GREEN);
        conf.getColorAxis().setMaxColor(SolidColor.RED);

        // Check if there are more than 7 days of entries in pillbox
        weekEntries = pillNum*7;
        weekStart = (int) (entriesNum-weekEntries+1);
        if (entriesNum > weekEntries) {
            indexStart = weekStart;
        }
        else{
            indexStart = 0;
        }
}