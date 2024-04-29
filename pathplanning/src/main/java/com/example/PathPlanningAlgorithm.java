package com.example;

import java.util.*;

public class PathPlanningAlgorithm {
  public List<Location> findClosestPath(int gridSizeX, int gridSizeY,
                                        Location start, Location end,
                                        List<Location> blackCells,
                                        int cellSize) {
    List<Location> path = new ArrayList<>();

    int x = start.getX();
    int y = start.getY();

    Location prevLocation = start;

    while (x != end.getX() || y != end.getY()) {
      Location currentLocation = new Location(x, y);

      if (isInBlackCells(currentLocation, blackCells)) {
        Location nextLocation = findNextValidLocation(
            gridSizeX, gridSizeY, prevLocation.getX(), prevLocation.getY(),
            end.getX(), end.getY(), blackCells, cellSize);
        if (nextLocation != null &&
            isInBorders(nextLocation, gridSizeX, gridSizeY)) {
          path.add(nextLocation);
          prevLocation.setX(x);
          prevLocation.setY(y);
          x = nextLocation.getX();
          y = nextLocation.getY();
        } else {
          return path;
        }
      } else {
        path.add(currentLocation);
        prevLocation.setX(x);
        prevLocation.setY(y);
        x += (x != end.getX()) ? Math.min(cellSize, Math.abs(end.getX() - x)) *
                                     Integer.signum(end.getX() - x)
                               : 0;
        y += (y != end.getY()) ? Math.min(cellSize, Math.abs(end.getY() - y)) *
                                     Integer.signum(end.getY() - y)
                               : 0;
      }
    }

    path.add(end);
    return path;
  }

  private Location findNextValidLocation(int gridSizeX, int gridSizeY, int x,
                                         int y, int targetX, int targetY,
                                         List<Location> blackCells,
                                         int cellSize) {
    PriorityQueue<Location> nextLocations =
        new PriorityQueue<>(Comparator.comparingDouble(
            loc -> distance(loc.getX(), loc.getY(), targetX, targetY)));
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (i == 0 && j == 0)
          continue; // Skip the current cell
        int nextX = x + i * cellSize;
        int nextY = y + j * cellSize;
        Location nextLocation = new Location(nextX, nextY);
        if (!isInBlackCells(nextLocation, blackCells)) {
          nextLocations.offer(nextLocation);
        }
      }
    }

    return nextLocations.peek();
  }

  private double distance(int x1, int y1, int x2, int y2) {
    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
  }

  private boolean isInBlackCells(Location cell, List<Location> blackCells) {
    return blackCells.stream().anyMatch(blackCell
                                        -> blackCell.getX() == cell.getX() &&
                                               blackCell.getY() == cell.getY());
  }

  private boolean isInBorders(Location cell, int gridSizeX, int gridSizeY) {
    return cell.getX() >= 0 && cell.getX() < gridSizeX && cell.getY() >= 0 &&
        cell.getY() < gridSizeY;
  }
}
