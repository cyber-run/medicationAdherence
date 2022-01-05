package com.javacakes.application.views.Data;

import com.javacakes.application.data.service.JavacakeService;
import com.javacakes.application.views.DefaultLayout;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PermitAll
@Route(value = "longtermdelay", layout = DefaultLayout.class)
@PageTitle("LongtermDelay")
public class LongtermDelay extends VerticalLayout {

    String PillType;
    // Page heading
    H2 heading = new H2("Long-term Time Delay");
    // New chart
    Chart chart = new Chart(ChartType.LINE);
    Configuration conf = chart.getConfiguration();
    XAxis xaxis = new XAxis();
    YAxis yaxis = new YAxis();

    JavacakeService service;
    long count; // Number of medication types taken
    String[] pillTypeNames;
    String pillName;



    public LongtermDelay(JavacakeService service) {

        this.service = service;

        count = service.countMedication();

        // Set x-axis

        for (int i=0; i<count; i++){
            // Apparently the RHS of the assignment is a void, so gives a red error
            //pillTypeNames[i] =
            service.findAllMedication().forEach(medication -> medication.getPillType());
        }

        // Set y-axis
        yaxis.setTitle("Timing delay (minutes)");
        conf.addyAxis(yaxis);

        DataSeries dataSeries = new DataSeries();


        // Attempted string conversion, but apparently below expression is void and not a string
        //Integer.valueOf()
                service.findAllPillbox().forEach(pillbox ->pillbox.getDelay());


        // Need to get the JSON object? - and make three separate data series

        // Apparently both pillbox.getDate() and pillbox.getDelay() are strings,
        // and one of them needs to not be a string if we want stuff to be plotted as a data series
        //service.findAllPillbox().forEach(pillbox -> dataSeries.add(
             //   new DataSeriesItem(pillbox.getDate(), pillbox.getDelay())));


        chart.getConfiguration().setSeries(dataSeries);





        // Add header and chart to page
        add(heading, chart);
    }


}
