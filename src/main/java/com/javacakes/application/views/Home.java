package com.javacakes.application.views;

import com.javacakes.application.data.entity.Medication;
import com.javacakes.application.data.service.JavacakeService;
import com.vaadin.flow.component.gridpro.GridPro;
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
    //adding comment here to main to test auto deployment on heroku via git push

    GridPro<Medication> grid = new GridPro<>(Medication.class);

    //declare service
    JavacakeService service;

    //declare heading
    H1 heading = new H1("Medication Overview");

    //Method for home page & pass in service
    public Home(JavacakeService service) {

        this.service = service;

        setSizeFull();
        setGrid();

        add(
                heading,
                grid
        );
    }

    //method to configure the grid, called in home constructor
    //can be edited as needed, poss replace with more suitable component
    private void setGrid() {
        grid.setSizeFull();
        grid.setColumns();
        grid.addColumn(Medication::getPillType).setHeader("Medication name");
        grid.addColumn(Medication::getPillStrength).setHeader("Strength");
        grid.addColumn(Medication::getPillDosage).setHeader("Dosage");
        grid.addColumn(Medication::getPillSchedule).setHeader("Schedule");
        grid.addColumn(Medication::getPillComment).setHeader("Comment");
        grid.getColumns().forEach(adherenceColumn -> adherenceColumn.setAutoWidth(true));
        grid.setItems(service.findAllMedication());
    }
}
