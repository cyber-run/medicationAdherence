package com.javacakes.application.views.Data;

import com.javacakes.application.views.DefaultLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PermitAll
@Route(value = "data1", layout = DefaultLayout.class)
@PageTitle("Data1")
public class Data1 extends VerticalLayout {

    H1 test = new H1("d1 test text");

    public Data1() {
        add(test);
    }
}
