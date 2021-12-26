package com.javacakes.application.views;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

//add url route and page title to login page
@Route("login")
@PageTitle("Login")
public class Login extends VerticalLayout implements BeforeEnterListener {

    private LoginForm loginForm = new LoginForm();
    public Login() {
        //add class name for potential css formatting in future and size/alignment parameters
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        //call the Vaadin login component
        loginForm.setAction("login");

        //declare image variable with logo for adding login view
        Image img = new Image("images/pill.png", "placeholder plant");
        img.setWidth("300px");
        //add the image, a heading and the login form to page
        add(
            img,
            new H1("Medication Adherence"),
            loginForm
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("error"))
        {
            loginForm.setError(true);
        }
    }
}
