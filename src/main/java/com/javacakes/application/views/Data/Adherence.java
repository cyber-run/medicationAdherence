package com.javacakes.application.views.Data;

import com.javacakes.application.views.DefaultLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PermitAll
@Route(value = "adherence", layout = DefaultLayout.class)
@PageTitle("Adherence")
public class Adherence extends VerticalLayout {

    H1 heading = new H1("Medication Timing Adherence");

    public Adherence() {
        add(heading);
    }
}
