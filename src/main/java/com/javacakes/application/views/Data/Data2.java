package com.javacakes.application.views.Data;

import com.javacakes.application.views.DefaultLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PermitAll
@Route(value = "data2", layout = DefaultLayout.class)
@PageTitle("Data2")
public class Data2 extends VerticalLayout {

    H1 test = new H1("d2 test text");

    public Data2() {
        add(test);
    }
}
