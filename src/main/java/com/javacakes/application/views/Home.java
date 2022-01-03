package com.javacakes.application.views;

import com.javacakes.application.data.entity.Adherence;
import com.vaadin.flow.component.grid.Grid;
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

    Grid<Adherence> grid = new Grid<>(Adherence.class);

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
        grid.addColumn(adherence -> adherence.getMedication().getPillType()).setHeader("Medication name");
        grid.addColumn(Adherence::getWeeklyAdherence).setHeader("Weekly Adherence overview");
        grid.getColumns().forEach(adherenceColumn -> adherenceColumn.setAutoWidth(true));
    }
}
