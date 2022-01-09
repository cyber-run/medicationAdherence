package com.javacakes.application.views.Data;

import com.javacakes.application.data.entity.Medication;
import com.javacakes.application.data.entity.Pillbox;
import com.javacakes.application.data.service.JavacakeService;
import com.javacakes.application.views.DefaultLayout;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Objects;

@PermitAll
@Route(value = "longtermdelay", layout = DefaultLayout.class)
@PageTitle("LongtermDelay")
public class LongtermDelay extends VerticalLayout {

    // Page heading
    H2 heading = new H2("Long-term Time Delay");
    // Comment
    Paragraph pgph = new Paragraph("Notes: " +
            "If pill was taken more than once a day, the mean is displayed. " +
            "If pill was not taken, the delay will be taken as 180 minutes.");
    // New chart
    Chart chart = new Chart(ChartType.LINE);
    Configuration conf = chart.getConfiguration();
    YAxis yaxis = new YAxis();

    JavacakeService service;

    long pillNum; // Number of pill types
    long entriesNum; // Number of entries in pillbox data table
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

    public LongtermDelay(JavacakeService service) {

        this.service = service;
        pillTypes =  service.findAllMedication();
        entries = service.findAllPillbox();
        pillNum = service.countMedication();
        entriesNum = service.countPillbox();

        // Set y-axis
        yaxis.setTitle("Timing delay (minutes)");
        conf.addyAxis(yaxis);

        for (int i=0; i<pillNum; i++){
            // Set legend
            pillName = pillTypes.get(i).getPillType();
            DataSeries dataSeries = new DataSeries();
            dataSeries.setName(pillName);

            // Plot data
            for (int j=0; j<entriesNum; j++){
                nameMatch = entries.get(j).getMedication().getPillType();
                if (Objects.equals(nameMatch, pillName)){
                    // Check if each delay entry has more than one element
                    // i.e. if pill was taken more than once in the same day
                    delay = entries.get(j).getDelay();
                    delaySplit = delay.split(",");
                    delayNum = delaySplit.length;
                    for (int k=0; k<delayNum; k++){
                        if(StringUtils.isNumeric(delaySplit[k])){
                            delayMins = Integer.valueOf(delaySplit[k]);
                        }
                        else{ // If delayMins is null or contains "x" (i.e. pill not taken),
                            // then plot this data point as a 180-minute delay
                            delayMins = 180;
                        }
                        // Plot mean of delay if multiple entries for same pill in one day
                        delaySum = 0;
                        delaySum = delaySum+delayMins;
                    }
                    delayMean = delaySum/delayNum;
                    date = entries.get(j).getDate();
                    dataSeries.add(new DataSeriesItem(date, delayMean));
                }
            }
            chart.getConfiguration().setSeries(dataSeries);
        }

        // Add header and chart to page
        add(heading, pgph, chart);
    }

}
