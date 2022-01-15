package com.javacakes.application.views.Data;

import com.javacakes.application.data.entity.Medication;
import com.javacakes.application.data.entity.Pillbox;
import com.javacakes.application.data.service.JavacakeService;
import com.javacakes.application.views.DefaultLayout;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.security.PermitAll;
import java.util.List;

@PermitAll
@Route(value = "weeklybreakdown", layout = DefaultLayout.class)
@PageTitle("WeeklyBreakdown")
public class WeeklyBreakdown extends VerticalLayout {

    // Page heading
    H2 heading = new H2("Weekly Breakdown");
    // Comment
    Paragraph pgph = new Paragraph("Notes: "+
            "Only the data for one pill is shown here."+
            "If pill was not taken, the delay will be taken as 180 minutes.");

    // New chart
    Chart chart = new Chart(ChartType.HEATMAP);
    Configuration conf = chart.getConfiguration();
    XAxis xaxis = new XAxis();
    YAxis yaxis = new YAxis();


    JavacakeService service;

    //MISC VARS
    long pillNum; // Number of pill types
    long entriesNum; // Number of entries in pillbox data table
    int delayNum; // Number of elements in each delay entry
    int delayMins; // Delay in minutes
    long weekEntries; // Number of entries to make a week
    int weekStart; // Index of first entry of the last 7 days
    int indexStart; // First index of for loop

    String delay;
    String[] delaySplit;
    List<Medication> pillTypes; // Lazy loading because direct methods were causing errors
    List<Pillbox> entries;

    public WeeklyBreakdown(JavacakeService service) {
        this.service = service;
        pillTypes = service.findAllMedication();
        entries = service.findAllPillbox();
        pillNum = service.countMedication();
        entriesNum = service.countPillbox();

        //chart config
        conf.getChart().setMarginTop(40);
        conf.getChart().setMarginBottom(40);

        conf.getLegend().setLayout(LayoutDirection.VERTICAL);
        conf.getLegend().setAlign(HorizontalAlign.RIGHT);
        conf.getLegend().setMargin(0);
        conf.getLegend().setVerticalAlign(VerticalAlign.TOP);
        conf.getLegend().setY(25);
        conf.getLegend().setSymbolHeight(320);

        // Set colors for the extremes
        conf.getColorAxis().setMinColor(SolidColor.GREEN);
        conf.getColorAxis().setMaxColor(SolidColor.RED);

        PlotOptionsHeatmap plotOptionsHeatmap = new PlotOptionsHeatmap();
        plotOptionsHeatmap.setDataLabels(new DataLabels());
        plotOptionsHeatmap.getDataLabels().setEnabled(true);

        SeriesTooltip tooltip = new SeriesTooltip();
        tooltip.setHeaderFormat("{series.name}<br/>");
        tooltip.setPointFormat("Amount: <b>{point.value}</b> ");
        plotOptionsHeatmap.setTooltip(tooltip);
        conf.setPlotOptions(plotOptionsHeatmap);

        //LABELS
        // Set the category labels on the X axis
        xaxis.setTitle("");
        xaxis.setCategories("Day 1", "Day 2", "Day 3",
                "Day 4", "Day 5", "Day 6", "Day 7");
        conf.addxAxis(xaxis);

        // Set the category labels on the Y axis
        yaxis.setTitle("");
        yaxis.setCategories("Morning", "Afternoon", "Evening");
        conf.addyAxis(yaxis);

        HeatSeries series = new HeatSeries();

        series = getData(1, service);

        conf.addSeries(series);

        // Add header and chart to page
        add(heading, pgph, chart);
    }

    //function for grabbing data from table
    //can be passed MED ID for search, always 1 during test build
    private HeatSeries getData(int medID, JavacakeService service) {
        //Declare heat series datatype
        HeatSeries data = new HeatSeries();

        int x = 2;

        entries = service.findAllPillbox();
        entriesNum = service.countPillbox();

        // Check if there are more than 7 days of entries in pillbox
        weekEntries = pillNum*7;
        weekStart = (int) (entriesNum-weekEntries);
        if (entriesNum > weekEntries) {
            indexStart = weekStart;
        }
        else{
            indexStart = 0;
        }

        for (int j=indexStart; j<entriesNum; j++) {
            int idMatch = entries.get(j).getMedication().getId();
            if (medID == idMatch) {
                delay = entries.get(j).getDelay();
                delaySplit = delay.split(",");
                delayNum = delaySplit.length;

                int y = 0;
                for (int k=0; k<delayNum; k++){
                    if(StringUtils.isNumeric(delaySplit[k])){
                        delayMins = Integer.valueOf(delaySplit[k]);
                    }
                    else {
                        delayMins = 180;
                    }
                    data.addHeatPoint(x,y,delayMins);
                    y++;
                    }
                x--;
                }
            }
        return data;
    }
}