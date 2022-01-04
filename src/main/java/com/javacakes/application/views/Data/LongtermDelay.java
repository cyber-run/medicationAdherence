package com.javacakes.application.views.Data;

import com.javacakes.application.views.DefaultLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PermitAll
@Route(value = "longtermdelay", layout = DefaultLayout.class)
@PageTitle("LongtermDelay")
public class LongtermDelay extends VerticalLayout {

    H1 heading = new H1("Long-term Time Delay");

    public LongtermDelay() {
        add(heading);
    }
}
