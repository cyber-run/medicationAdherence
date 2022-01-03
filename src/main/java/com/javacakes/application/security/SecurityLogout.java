package com.javacakes.application.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinServletRequest;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

@Component  //create new security component to handle logout service
public class SecurityLogout {

    //crate logout service method
    public void logout () {
        UI.getCurrent().getPage().setLocation("/");
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler(); //create logout handler method
        logoutHandler.logout(VaadinServletRequest.getCurrent().getHttpServletRequest(),null,null);
    }
}
