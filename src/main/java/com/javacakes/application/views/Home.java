package com.javacakes.application.views;

import com.javacakes.application.data.entity.Adherence;
import com.javacakes.application.data.entity.Medication;
import com.javacakes.application.data.entity.Patient;
import com.javacakes.application.data.service.JavacakeService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.gridpro.GridPro;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.List;

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
    H2 heading = new H2("Medication Overview");
    GridPro<Medication> grid = new GridPro<>(Medication.class);
    Paragraph pgph = new Paragraph();

    // Creating a persistent notification if adherence threshold drops below 85%
    Notification notification = new Notification();
    Div text = new Div();
    Button closeButton = new Button(new Icon("lumo", "cross"));


    double pctAdherence = 85.00;
    double adherenceValue;
    long count;
    int patientsNum;
    String pilltype;
    String patientID;
    List<Adherence> values;
    List<Patient> patientList;

    //Method for home page & pass in service
    public Home(JavacakeService service) {

        this.service = service;
        values = service.findAllAdherence();
        count = service.countAdherence();
        patientList = service.findAllPatient();

        // Display patientID,
        pgph.add("Patient ID: ");
        if(!patientList.isEmpty()){
            patientID = patientList.get(0).getPatientID();
            pgph.add(new Text(patientID));
        }

        setSizeFull();
        setGrid();

        for(int i=0; i<count; i++){
            adherenceValue = values.get(i).getWeeklyAdherence();
            pilltype = values.get(i).getMedication().getPillType();
            if(adherenceValue <pctAdherence){
                displayNotification(pilltype);
            }
        }


        add(
                heading,
                pgph,
                grid
        );
    }

    private void displayNotification(String adHere) {
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

        text.add(adHere);
        text.add(new Text("'s timing adherence has fallen below 85%! "));
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
