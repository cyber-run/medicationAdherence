package com.javacakes.application.views.Data;
import com.javacakes.application.data.entity.Medication;
import com.vaadin.flow.component.gridpro.GridPro;
import com.javacakes.application.data.service.JavacakeService;
import com.javacakes.application.views.DefaultLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.List;

@PermitAll
@Route(value = "weeklybreakdown", layout = DefaultLayout.class)
@PageTitle("WeeklyBreakdown")
public class WeeklyBreakdown extends VerticalLayout {
    GridPro<Medication> grid = new GridPro<>(Medication.class);

    //declare service
    JavacakeService service;
    List<Medication> pillTypes; // Lazy loading because direct methods were causing errors
    //declare heading
    H1 heading = new H1("Weekly Breakdown");

    //Method for home page & pass in service
    public WeeklyBreakdown(JavacakeService service) {

        this.service = service;
        pillTypes =  service.findAllMedication();

        setSizeFull();
        setGrid();


        add(
                heading,
                grid
        );
    }

    private void setGrid() {
        grid.setSizeFull();
        grid.setColumns();
        grid.addColumn(Medication::getPillType).setHeader("Medication name");
        grid.addColumn(Medication::getPillSchedule).setHeader("Last Seven Days");
        grid.getColumns().forEach(adherenceColumn -> adherenceColumn.setAutoWidth(true));
        grid.setItems(service.findAllMedication());
    }
}

