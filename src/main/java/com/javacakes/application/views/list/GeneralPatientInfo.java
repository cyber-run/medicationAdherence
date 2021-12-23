package com.javacakes.application.views.list;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("GeneralPatientInfo")
@Route(value = "generalpatientinfo")
public class GeneralPatientInfo extends VerticalLayout {
    public GeneralPatientInfo() {

        setSpacing(false);
        add(new H2("General Patient Info"));
        add(new Paragraph("Patient ID number:")); //make a variable add it here so it comes up after login
        add(new H2("Medication Adherence:")); //make percentage adherence so it comes up here - also have statements that come
//up based on percentage adherence percentage
        ProgressBar progressBar = new ProgressBar();
        progressBar.setValue(0.5); //set this to variable percentage

        Div progressBarLabelText = new Div();
        progressBarLabelText.setText("Percentage Adherence.xlsx");
        Div progressBarLabelValue = new Div();
        progressBarLabelValue.setText("50%"); //change this to the variable percentage
        FlexLayout progressBarLabel = new FlexLayout();
        progressBarLabel.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);
        progressBarLabel.add(progressBarLabelText, progressBarLabelValue);

        add(progressBarLabel, progressBar);

        add(new H2("Status:"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");


    }

}
