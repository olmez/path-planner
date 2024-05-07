package com.example;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pathRequests")
class PathRequestEntity {
  @Id private String id;
  private int gridSizeX;
  private int gridSizeY;
  private int cellSize;
  private Location start;
  private Location end;
  private List<Location> blackCells;

  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public int getGridSizeX() { return gridSizeX; }

  public void setGridSizeX(int gridSizeX) { this.gridSizeX = gridSizeX; }

  public int getGridSizeY() { return gridSizeY; }

  public void setGridSizeY(int gridSizeY) { this.gridSizeY = gridSizeY; }

  public int getCellSize() { return cellSize; }

  public void setCellSize(int cellSize) { this.cellSize = cellSize; }

  public Location getStart() { return start; }

  public void setStart(Location start) { this.start = start; }

  public Location getEnd() { return end; }

  public void setEnd(Location end) { this.end = end; }

  public List<Location> getBlackCells() { return blackCells; }

  public void setBlackCells(List<Location> blackCells) {
    this.blackCells = blackCells;
  }
}

@Document(collection = "pathResponses")
class PathResponseEntity {
  @Id private String id;
  private List<Location> plannedPath;

  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public List<Location> getPlannedPath() { return plannedPath; }

  public void setPlannedPath(List<Location> plannedPath) {
    this.plannedPath = plannedPath;
  }
}
