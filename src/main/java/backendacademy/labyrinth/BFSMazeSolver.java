package backendacademy.labyrinth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/*
Лабиринт представляет собой двумерный массив символов. Использованы следующие обозначения:
    '+' - проход (WAY);
    '-' - стена (WALL);
    '*' - путь (PATH).
 */

public class BFSMazeSolver {
    private BFSMazeSolver() {}

    private static final int[] DX = {1, 0, -1, 0};
    private static final int[] DY = {0, 1, 0, -1};
    private static final char WAY = '+';
    private static final char PATH = '*';
    private static char[][] mazeCopy;
    private static int widthOfMaze;
    private static int heightOfMaze;

    public static ArrayList<Cell> solve(Maze maze) {
        mazeCopy = new char[maze.getMaze().length][];
        for (int i = 0; i < maze.getMaze().length; i++) {
            mazeCopy[i] = Arrays.copyOf(maze.getMaze()[i], maze.getMaze()[i].length);
        }
        heightOfMaze = mazeCopy.length;
        widthOfMaze = mazeCopy[0].length;
        HashMap<Cell, Cell> predecessors = new HashMap<>(); // ключ - текущая ячейка, значение - её ячейка-предок
        LinkedList<Cell> cellsToView = new LinkedList<>();
        cellsToView.add(maze.getStart());
        mazeCopy[maze.getStart().getY()][maze.getStart().getX()] = PATH;
        while (!cellsToView.isEmpty()) {
            Cell current = cellsToView.getFirst();
            for (int i = 0; i < DX.length; i++) {
                int nextX = current.getX() + DX[i];
                int nextY = current.getY() + DY[i];
                if (cellIsValid(nextX, nextY)) {
                    Cell next = new Cell(nextX, nextY);
                    predecessors.put(next, current);
                    if (next.equals(maze.getFinish())) {
                        return reconstructPath(predecessors, maze.getFinish());
                    } else {
                        mazeCopy[nextY][nextX] = PATH;
                        cellsToView.add(next);
                    }
                }
            }
            cellsToView.removeFirst();
        }
        return new ArrayList<>();
    }

    private static ArrayList<Cell> reconstructPath(HashMap<Cell, Cell> predecessors, Cell finish) {
        ArrayList<Cell> path = new ArrayList<>();
        for (Cell at = finish; at != null; at = predecessors.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    private static boolean cellIsValid(int x, int y) {
        return x > 0 && x < widthOfMaze && y > 0 && y < heightOfMaze && mazeCopy[y][x] == WAY;
    }

}
