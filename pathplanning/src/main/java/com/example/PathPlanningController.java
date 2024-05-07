package com.example;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PathPlanningController {
  private final PathRequestRepository pathRequestRepository;
  private final PathResponseRepository pathResponseRepository;
  private final PathPlanningAlgorithm pathPlanningAlgorithm;

  @Autowired
  public PathPlanningController(PathRequestRepository pathRequestRepository,
                                PathResponseRepository pathResponseRepository) {
    this.pathRequestRepository = pathRequestRepository;
    this.pathResponseRepository = pathResponseRepository;
    this.pathPlanningAlgorithm = new PathPlanningAlgorithm();
  }

  @CrossOrigin(origins = "http://localhost:3000")
  @PostMapping("/api/path-planning-endpoint")
  public PathResponse planPathMain(@RequestBody PathRequest request) {
    System.out.println("Received request for path planning");

    List<Location> path = pathPlanningAlgorithm.findClosestPath(
        request.getGridSizeX(), request.getGridSizeY(), request.getStart(),
        request.getEnd(), request.getBlackCells(), request.getCellSize());

    PathRequestEntity pathRequestEntity = new PathRequestEntity();
    pathRequestEntity.setGridSizeX(request.getGridSizeX());
    pathRequestEntity.setGridSizeY(request.getGridSizeY());
    pathRequestEntity.setStart(request.getStart());
    pathRequestEntity.setEnd(request.getEnd());
    pathRequestEntity.setBlackCells(request.getBlackCells());
    pathRequestEntity.setCellSize(request.getCellSize());
    pathRequestRepository.save(pathRequestEntity);

    PathResponseEntity pathResponseEntity = new PathResponseEntity();
    pathResponseEntity.setPlannedPath(path);
    pathResponseRepository.save(pathResponseEntity);

    return new PathResponse(path);
  }
}
