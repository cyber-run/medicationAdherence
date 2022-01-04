package com.javacakes.application.views.Data;

import com.javacakes.application.views.DefaultLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PermitAll
@Route(value = "longtermadherence", layout = DefaultLayout.class)
@PageTitle("LongtermAdherence")
public class LongtermAdherence extends VerticalLayout {

    H2 heading = new H2("Long-term Medication Timing Adherence");

    public LongtermAdherence() {
        add(heading);
    }
}
