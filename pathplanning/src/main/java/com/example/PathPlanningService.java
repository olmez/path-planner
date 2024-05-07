package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
@SpringBootApplication
public class PathPlanningService {

  public static void main(String[] args) {
    System.out.println("PathPlanningService has started successfully.");
    SpringApplication.run(PathPlanningService.class, args);
  }
}