package com.javacakes.application.views.Data;

import com.javacakes.application.views.DefaultLayout;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PermitAll
@Route(value = "data1", layout = DefaultLayout.class)
@PageTitle("Data1")
public class Data1 extends VerticalLayout {

    // Page heading
    H1 test = new H1("Time delay");
    // New chart
    Chart chart1 = new Chart(ChartType.LINE);
    Configuration conf = chart1.getConfiguration();
    XAxis xaxis = new XAxis();
    YAxis yaxis = new YAxis();
    // Fake data
    String[] seriesNames = {"Pill type 1", "Pill type 2", "Pill type 3", "Total"};
    String[] dates = {"30/12", "31/12", "01/01", "02/01", "03/01"};
    int[][] timingDelay = {
            {43, 0, 2, 45},
            {61, 18, 4, 83},
            {27, 3, 15, 45},
            {4, 16, 47, 67},
            {2, 4, 3, 9}
    };

    public Data1() {
        // Set x-axis
        xaxis.setCategories(dates);
        conf.addxAxis(xaxis);
        // Set y-axis
        yaxis.setTitle("Timing delay (minutes)");
        conf.addyAxis(yaxis);

        // Add data to series
        for (int i=0; i<seriesNames.length; i++){
            ListSeries series = new ListSeries();
            series.setName(seriesNames[i]);
            for (int j=0; j<timingDelay.length; j++){
                series.addData(timingDelay[j][i]);
            }
            conf.addSeries(series);
        }

        // Add header and chart to page
        add(test, chart1);
    }
}
