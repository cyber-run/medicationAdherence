package com.javacakes.application.views.Data;

import com.javacakes.application.views.DefaultLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PermitAll
@Route(value = "data5", layout = DefaultLayout.class)
@PageTitle("Data5")
public class Data5 extends VerticalLayout {

    H1 test = new H1("d5 test text");

    public Data5() {
        add(test);
    }
}
