package com.javacakes.application.views.Data;

import com.javacakes.application.data.entity.Medication;
import com.javacakes.application.data.entity.Pillbox;
import com.javacakes.application.data.service.JavacakeService;
import com.javacakes.application.views.DefaultLayout;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Objects;

@PermitAll
@Route(value = "longtermdelay", layout = DefaultLayout.class)
@PageTitle("LongtermDelay")
public class LongtermDelay extends VerticalLayout {

    // Page heading
    H2 heading = new H2("Long-term Time Delay");
    // New chart
    Chart chart = new Chart(ChartType.LINE);
    Configuration conf = chart.getConfiguration();
    XAxis xaxis = new XAxis();
    YAxis yaxis = new YAxis();

    JavacakeService service;

    long pillNum; // Number of pill types
    long entriesNum; // Number of entries in pillbox data table

    String pillName;
    String nameMatch;
    String delay;
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

            for (int j=0; j<entriesNum; j++){
                nameMatch = entries.get(j).getMedication().getPillType();
                if (Objects.equals(nameMatch, pillName)){
                    delay = entries.get(j).getDelay();
                    int x = Integer.valueOf(delay);
                    date = entries.get(j).getDate();
                    dataSeries.add(new DataSeriesItem(date, x));
                }
            }
            chart.getConfiguration().setSeries(dataSeries);
        }

        // Add header and chart to page
        add(heading, chart);
    }

}
