package com.javacakes.application.views.list;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.Duration;
import java.time.LocalTime;

@PageTitle("SetMedicationAdherence")
@Route(value = "SetMedicationAdherence")
public class SetMedicationSchedule extends VerticalLayout{
public SetMedicationSchedule(){
    setSpacing(false);
    add(new H2("Set Medication Adherence"));

    TimePicker timePicker1 = new TimePicker();
    timePicker1.setLabel("First Dosage Time");
    timePicker1.setStep(Duration.ofMinutes(30));
    timePicker1.setValue(LocalTime.of(12,30));
    add(timePicker1);

    TimePicker timePicker2 = new TimePicker();
    timePicker2.setLabel("Second Dosage Time");
    timePicker2.setStep(Duration.ofMinutes(30));
    timePicker2.setValue(LocalTime.of(12,30));
    add(timePicker2);

    TimePicker timePicker3 = new TimePicker();
    timePicker3.setLabel("Third Dosage Time");
    timePicker3.setStep(Duration.ofMinutes(30));
    timePicker3.setValue(LocalTime.of(12,30));
    add(timePicker3);

    setSizeFull();
    setJustifyContentMode(JustifyContentMode.CENTER);
    setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    getStyle().set("text-align", "center");
}
    }







