package com.lidy.estetica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.lidy.estetica"})
public class EsteticaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EsteticaApplication.class, args);
    }
}
