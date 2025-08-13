package com.P.common.model.Maps;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class GoTrack {
    public static ArrayList<Cell> cells = new ArrayList<>();
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    private static ArrayList<Cell> cellToTile(ArrayList<Tile> c) {
        ArrayList<Cell> arr = new ArrayList<>();
        for (Tile cell : c) {
            arr.add(new Cell(cell));
        }
        return arr;
    }

    public static Cell pathBFS(Cell src, Cell dest, ArrayList<Tile> arr) {
        for (Tile c : arr) {
            cells.add(new Cell(c));
        }
        boolean[][] visited = new boolean[75][50];
        Queue<Cell> queue = new PriorityQueue<>(new TileComparator());
        queue.add(src);
        visited[src.getCoordinate().getX()][src.getCoordinate().getY()] = true;
        while (!queue.isEmpty()) {
            Cell curr = queue.poll();
//            if (curr.equals(dest)) {
//                dest = curr;
//                break;
//            }
            for (int[] dir : DIRECTIONS) {
                int newX = curr.getCoordinate().getX() + dir[0];
                int newY = curr.getCoordinate().getY() + dir[1];
                if (newX >= 0 && newX < 75 && newY >= 0 && newY < 50 && !visited[newX][newY]) {
                    Cell neighbour = findCell(newX, newY);
                    if (!neighbour.getObjectOnCell().canWalk) {
                        continue;
                    }
                    neighbour.prev = curr;
                    neighbour.distance = neighbour.prev.distance + 1;
                    if (curr.prev == null) {
                        neighbour.turns = curr.turns;
                    } else if (neighbour.diffXPrev() == curr.diffXPrev() && neighbour.diffYPrev() == curr.diffYPrev()) {
                        neighbour.turns = neighbour.prev.turns;
                    } else {
                        neighbour.turns = neighbour.prev.turns + 1;
                    }
                    neighbour.energy = (neighbour.distance + 10 * neighbour.turns);
                    visited[neighbour.getCoordinate().getX()][neighbour.getCoordinate().getY()] = true;
                    queue.add(neighbour);
                }
            }
        }
        return dest;
    }

    public static class TileComparator implements Comparator<Cell> {
        @Override
        public int compare(Cell c1, Cell c2) {
            return Double.compare(c1.energy, c2.energy); // Lower cost = higher priority
        }
    }

    public static Cell findCell(int x, int y) {
        for (Cell cell : cells) {
            if (cell.getCoordinate().getX() == x && cell.getCoordinate().getY() == y) {
                return cell;
            }
        }
        return null;
    }
}
