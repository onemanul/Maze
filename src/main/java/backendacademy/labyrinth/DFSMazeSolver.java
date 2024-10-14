package backendacademy.labyrinth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/*
Лабиринт представляет собой двумерный массив символов. Использованы следующие обозначения:
    '+' - проход (WAY);
    '-' - стена (WALL);
    '*' - путь (PATH).
 */

public class DFSMazeSolver {
    private DFSMazeSolver() {}

    private static final int[] DX = {1, 0, -1, 0};
    private static final int[] DY = {0, 1, 0, -1};
    private static final char WAY = '+';
    private static final char WALL = '-';
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
        ArrayList<Cell> path = new ArrayList<>();
        path.add(maze.getStart());
        Optional<Cell> nextPoint;
        do {
            int x = path.getLast().getX();
            int y = path.getLast().getY();
            nextPoint = getNext(x, y);
            if (nextPoint.isPresent()) {
                mazeCopy[y][x] = PATH;
                path.add(nextPoint.get());
            } else {
                mazeCopy[y][x] = WALL;
                path.removeLast();
            }
        } while (!path.isEmpty() && (nextPoint.isEmpty() ||  !nextPoint.get().equals(maze.getFinish())));
        return path;
    }

    private static Optional<Cell> getNext(int x, int y) {
        for (int i = 0; i < DX.length; ++i) {
            int nextX = x + DX[i];
            int nextY = y + DY[i];
            if (cellIsValid(nextX, nextY)) {
                return Optional.of(new Cell(nextX, nextY));
            }
        }
        return Optional.empty();
    }

    private static boolean cellIsValid(int x, int y) {
        return x > 0 && x < widthOfMaze && y > 0 && y < heightOfMaze && mazeCopy[y][x] == WAY;
    }
}
