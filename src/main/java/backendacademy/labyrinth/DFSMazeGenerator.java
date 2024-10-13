package backendacademy.labyrinth;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

/*
Лабиринт представляет собой двумерный массив символов. Использованы следующие обозначения:
    '+' - проход (WAY);
    '-' - стена (WALL).
 */

public class DFSMazeGenerator {
    private DFSMazeGenerator() {}

    private static int widthOfMaze;
    private static int heightOfMaze;
    private static char[][] maze;
    private static final int[] DX = {1, 0, -1, 0};
    private static final int[] DY = {0, 1, 0, -1};
    private static final char WAY = '+';
    private static final char WALL = '-';

    public static char[][] generate(int height, int width) {
        DFSMazeGenerator.heightOfMaze = height * 2 + 1;
        DFSMazeGenerator.widthOfMaze = width * 2 + 1;
        DFSMazeGenerator.maze = new char[heightOfMaze][widthOfMaze];
        for (char[] row : maze) {
            Arrays.fill(row, WALL);      // заполняем весь лабиринт стенами
        }

        int x = new SecureRandom().nextInt(width) * 2 + 1;
        int y = new SecureRandom().nextInt(height) * 2 + 1;
        Stack<Cell> cellStack = new Stack<>();
        cellStack.add(new Cell(x, y));

        while (!cellStack.isEmpty()) {
            x = cellStack.peek().getX();
            y = cellStack.peek().getY();
            maze[y][x] = WAY;
            Optional<Cell> nextPoint = getNeighbor(x, y);
            if (nextPoint.isPresent()) {
                connectCells(cellStack.peek(), nextPoint.get());
                cellStack.add(nextPoint.get());
            } else {
                cellStack.pop();
            }
        }
        return maze;
    }

    private static Optional<Cell> getNeighbor(int x, int y) {
        List<Integer> directions = Arrays.asList(0, 1, 2, DX.length - 1);
        Collections.shuffle(directions);
        for (Integer i : directions) {
            int neighborX = x + DX[i] * 2;
            int neighborY = y + DY[i] * 2;
            if (cellInMaze(neighborX, neighborY) && !cellIsVisited(neighborX, neighborY)) {
                return Optional.of(new Cell(neighborX, neighborY));
            }
        }
        return Optional.empty();
    }

    private static void connectCells(Cell first, Cell second) {
        int connectorX = (first.getX() + second.getX()) / 2;
        int connectorY = (first.getY() + second.getY()) / 2;
        maze[connectorY][connectorX] = WAY;
    }

    private static boolean cellInMaze(int x, int y) {
        return (x % 2 == 1) && x < widthOfMaze && (y % 2 == 1) && y < heightOfMaze;
    }

    private static boolean cellIsVisited(int x, int y) {
        return maze[y][x] == WAY;
    }
}
