package com.example.firstProject.config;

import com.example.firstProject.jersy.UserJersyController;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Jersy extends ResourceConfig {
    @PostConstruct
    public void JerseyConfig() {
        packages("com.example.firstProject.jersy");
//        register(UserJersyController.class);
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }
}