package com.example;

import java.util.List;

public class PathResponse {

  public PathResponse(List<Location> plannedPath) {
    this.plannedPath = plannedPath;
  }

  public List<Location> getPlannedPath() { return plannedPath; }

  private List<Location> plannedPath;
}
