package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PathPlanningService {

  public static void main(String[] args) {
    System.out.println("PathPlanningService has started successfully.");
    SpringApplication.run(PathPlanningService.class, args);
  }
}