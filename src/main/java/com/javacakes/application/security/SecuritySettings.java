//this class in security package allows the default spring security page to be configured to our own settings
package com.javacakes.application.security;

import com.javacakes.application.views.Login;
import com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class SecuritySettings extends VaadinWebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**"); //ignore the default images passed from spring security
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        //Set the spring login view to view built in our own Login class in views folder, requires http config
        setLoginView(http, Login.class);
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        //as this is not a production deployment, use in memory storage to store a test user/pass
        //this is not secure and can be replaced with a secure account authentification service for deployment
        //use no operation as not concerned with encrypting the stored password for test deployment
        return new InMemoryUserDetailsManager(User.withUsername("martin").password("{noop}nettles").roles("Carer").build());
    }
}
