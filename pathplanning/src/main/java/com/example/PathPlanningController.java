package com.example;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PathPlanningController {

  private final PathPlanningAlgorithm pathPlanningAlgorithm =
      new PathPlanningAlgorithm();

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping("/api/path-planning-endpoint")
  public PathResponse planPathMain(@RequestBody PathRequest request) {
    System.out.println("Received request for path planning");

    List<Location> path = pathPlanningAlgorithm.findClosestPath(
        request.getGridSizeX(), request.getGridSizeY(), request.getStart(),
        request.getEnd(), request.getBlackCells(), request.getCellSize());

    PathResponse response = new PathResponse(path);
    return response;
  }
}
