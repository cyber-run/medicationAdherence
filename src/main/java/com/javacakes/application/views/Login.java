package com.javacakes.application.views;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

//add url route and page title to login page
@Route("login")
@PageTitle("Login")
public class Login extends VerticalLayout implements BeforeEnterListener {

    private LoginForm loginForm = new LoginForm();
    LoginOverlay loginOverlay = new LoginOverlay();


    public Login() {
        //add class name for potential css formatting in future and size/alignment parameters
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        //call the Vaadin login component
        loginForm.setAction("login");

        //declare image variable with logo for adding login view
        //Image img = new Image("images/pill.png", "Pill logo");
        //img.setWidth("300px");

        //Add login overlay (a header to the login form)

        loginOverlay.setTitle("Medication Adherence");
        loginOverlay.setDescription("A monitoring app for carers");
        loginOverlay.setOpened(true);

        loginForm.getElement().getThemeList();

        //add the image, a heading and the login form to page
        add(
            //img,
            //new H1("Medication Adherence"),
            loginOverlay,
            loginForm
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("error"))
        {
            loginOverlay.setError(true);
        }
    }
}
