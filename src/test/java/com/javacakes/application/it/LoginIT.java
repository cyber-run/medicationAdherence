package com.javacakes.application.it;

import com.javacakes.application.it.elements.LoginViewElement;
import com.vaadin.flow.component.login.testbench.LoginFormElement;
import org.junit.Assert;
import org.junit.Test;

public class LoginIT extends AbstractTest{

    public LoginIT() {
        super("");
    }

    @Test
    public void ValidUserLogin(){
        LoginViewElement loginView = $(LoginViewElement.class).onPage().first();
        Assert.assertTrue(loginView.login("user", "userpass"));
    }

    @Test public void InvalidUserLogin(){
        LoginViewElement loginView = $(LoginViewElement.class).onPage().first();
        Assert.assertFalse(loginView.login("user", "other"));
    }
}
