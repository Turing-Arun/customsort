package com.example.customsort;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Customsort Spring Boot application. This class contains the main method
 * which starts the application.
 *
 * <p>The {@code @SpringBootApplication} annotation indicates a configuration class that declares
 * one or more {@code @Bean} methods and also triggers auto-configuration and component scanning.
 * It's a convenience annotation that combines {@code @Configuration},
 * {@code @EnableAutoConfiguration}, and {@code @ComponentScan}.
 *
 * <p>The {@code main} method uses {@code SpringApplication.run} to launch the application.
 */
@SpringBootApplication
public class CustomsortApplication {

  public static void main(String[] args) {
    SpringApplication.run(CustomsortApplication.class, args);
  }
}
