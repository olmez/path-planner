package com.example;

import java.util.List;

public class PathRequest {
  private int gridSizeX;
  private int gridSizeY;
  private int cellSize;
  private Location start;
  private Location end;
  private List<Location> blackCells;

  public int getGridSizeX() { return gridSizeX; }

  public int getGridSizeY() { return gridSizeY; }

  public int getCellSize() { return cellSize; }

  public Location getStart() { return start; }

  public Location getEnd() { return end; }

  public List<Location> getBlackCells() { return blackCells; }
}
