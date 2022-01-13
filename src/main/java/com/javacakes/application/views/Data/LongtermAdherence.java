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
@Route(value = "longtermadherence", layout = DefaultLayout.class)
@PageTitle("LongtermAdherence")
public class LongtermAdherence extends VerticalLayout {

    H2 heading = new H2("Long-term Medication Timing Adherence");

    // New chart
    Chart chart = new Chart(ChartType.COLUMN);
    Configuration conf = chart.getConfiguration();
    XAxis xaxis = new XAxis();
    YAxis yaxis = new YAxis();


    JavacakeService service;

    public LongtermAdherence(JavacakeService service) {
        // X-axis
        xaxis.setTitle("Pill Type");
        conf.addxAxis(xaxis);
        // Y-axis
        yaxis.setTitle("Long Term Percentage Adherence");
        conf.addyAxis(yaxis);

        // Plot data
        //-- Reference https://vaadin.com/docs/latest/flow/tutorial/navigation-and-layouts --
        this.service = service;
        DataSeries dataSeries = new DataSeries();
        service.findAllAdherence().forEach(adherence -> dataSeries.add(
                new DataSeriesItem(adherence.getMedication().getPillType(),
                        adherence.getLongtermAdherence())));
        chart.getConfiguration().setSeries(dataSeries);

        conf.getLegend().setEnabled(false);
        //-- End of reference --

        // Show pill type as x-axis labels
        conf.getxAxis().setType(AxisType.CATEGORY);

        add(heading, chart);
    }
}
