package com.javacakes.application.views;

import com.javacakes.application.data.entity.Medication;
import com.javacakes.application.data.entity.Patient;
import com.javacakes.application.data.entity.Schedule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import javax.annotation.security.PermitAll;

//Set route to no url extension to make home page default after login
//Add default layout class to implement header and drawer for navigation
//Add permit all tag to allow access after login
@Route(value = "", layout = DefaultLayout.class)
@PageTitle("Home")
@PermitAll
public class Home extends VerticalLayout {

    Grid<Schedule> grid = new Grid<>(Schedule.class);

    //Method for home page
    public Home() {

        setSizeFull();
        setGrid();

        add(
                grid
        );
    }

    //method to configure the grid, called in home constructor
    //can be edited as needed, poss replace with more suitable component
    private void setGrid() {
        grid.setSizeFull();
        grid.setColumns();
        grid.addColumn(schedule -> schedule.getMedication().getMedicationName()).setHeader("Medication");
        grid.addColumn(Schedule::getTime).setHeader("Scheduled Time");
        grid.getColumns().forEach(scheduleColumn -> scheduleColumn.setAutoWidth(true));
    }
}
