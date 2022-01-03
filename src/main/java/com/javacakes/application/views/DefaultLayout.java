package com.javacakes.application.views;

import com.javacakes.application.security.SecurityLogout;
import com.javacakes.application.views.Data.*;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class DefaultLayout extends AppLayout {
    private final SecurityLogout securityLogout;

    //Default layout constructor to contain header and button to toggle side-bar nav drawer

    public DefaultLayout(SecurityLogout securityLogout) {
        this.securityLogout = securityLogout;

        buildDrawer();
        buildHeader();
    }

    private void buildDrawer() {
        //Page links created here
        RouterLink homeView = new RouterLink("Home", Home.class);
        RouterLink data1View = new RouterLink("Adherence", Adherence.class);
        RouterLink data2View = new RouterLink("Long-term Adherence", LongtermAdherence.class);
        RouterLink data3View = new RouterLink("Time Delay", TimeDelay.class);
        RouterLink data4View = new RouterLink("Long-term Delay", LongtermDelay.class);
        RouterLink data5View = new RouterLink("Weekly Breakdown", WeeklyBreakdown.class);

        //use vaadin highlight condition on each link, so it is highlighted when it is current page
        homeView.setHighlightCondition(HighlightConditions.sameLocation());
        data1View.setHighlightCondition(HighlightConditions.sameLocation());
        data2View.setHighlightCondition(HighlightConditions.sameLocation());
        data3View.setHighlightCondition(HighlightConditions.sameLocation());
        data4View.setHighlightCondition(HighlightConditions.sameLocation());
        data5View.setHighlightCondition(HighlightConditions.sameLocation());

        //Create drawer using vertical layout
        VerticalLayout drawer = new VerticalLayout(homeView,data1View,data2View,data3View,data4View,data5View);

        addToDrawer(drawer);
    }

    private void buildHeader() {
        //Create Vaadin flow components to build header
        Image logo = new Image("images/pill.png", "placeholder plant");
        logo.setWidth("50px");
        H1 heading = new H1("Medication Adherence");
        Button logoutButton = new Button("Log Out", buttonClickEvent -> securityLogout.logout());


        //Start new horizontal layout for header
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(),logo,heading,logoutButton);

        //Style header to vertically centre all components, fill page width and expand internal components to fill space
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidthFull();
        header.expand(heading);
        //configure padding in header
        header.addClassName("px-m");

        //Vaadin considers top bar, the nav bar so must add header to nav bar
        //We will then use drawer for the nav of site
        addToNavbar(header);
    }
}
