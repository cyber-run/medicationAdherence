package com.javacakes.application.views;

import com.javacakes.application.data.entity.Medication;
import com.javacakes.application.data.service.JavacakeService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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


    //declare service
    JavacakeService service;

    // Declare page elements
    H1 heading = new H1("Medication Overview");
    GridPro<Medication> grid = new GridPro<>(Medication.class);

    // Creating a persistent notification if adherence threshold drops below 85%
    Notification notification = new Notification();
    Div text = new Div();
    Button closeButton = new Button(new Icon("lumo", "cross"));

    // Hard-coded adherence
    double pctAdherence = 85.00;
    Double[] adhere = {80.95,85.71,42.86};

    //Method for home page & pass in service
    public Home(JavacakeService service) {

        this.service = service;

        setSizeFull();
        setGrid();

        //service.findAllAdherence().forEach();

        for(double flag:adhere){
            if(flag<pctAdherence){
                displayNotification(flag);
            }
        }

        add(
                heading,
                grid
        );
    }

    private void displayNotification(double adHere) {
        //-- Reference: https://vaadin.com/docs/latest/ds/components/notification/#error --
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        closeButton.getElement().setAttribute("aria-label", "Close");
        closeButton.addClickListener(event -> {
            notification.close();
        });

        HorizontalLayout layout = new HorizontalLayout(text, closeButton);
        layout.setAlignItems(Alignment.CENTER);

        notification.add(layout);
        notification.open();
        //-- End of reference --
        notification.setPosition(Notification.Position.TOP_CENTER);

        text.add(String.valueOf(adHere));
        text.add(new Text(" has fallen below 85%! "));
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
