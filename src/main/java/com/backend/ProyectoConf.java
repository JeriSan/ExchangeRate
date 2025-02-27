package com.backend;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = {"com.backend"})
public class ProyectoConf {
    @Bean
    @Primary
    public Module datatypeHibernateModule() {
        return new Hibernate6Module();
    }

    public static void main(String[] args) {
        SpringApplication.run(ProyectoApp.class, args);
    }
}