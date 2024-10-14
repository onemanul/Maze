package backendacademy.labyrinth;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/*
Лабиринт представляет собой двумерный массив символов. Использованы следующие обозначения:
    '+' - проход (WAY);
    '-' - стена (WALL).
 */

public class PrimMazeGenerator {
    private PrimMazeGenerator() {}

    private static int widthOfMaze;
    private static int heightOfMaze;
    private static char[][] maze;
    private static final int[] DX = {1, 0, -1, 0};
    private static final int[] DY = {0, 1, 0, -1};
    private static final char WAY = '+';
    private static final char WALL = '-';

    public static char[][] generate(int height, int width) {
        PrimMazeGenerator.heightOfMaze = height * 2 + 1;
        PrimMazeGenerator.widthOfMaze = width * 2 + 1;
        PrimMazeGenerator.maze = new char[heightOfMaze][widthOfMaze];
        for (char[] row : maze) {
            Arrays.fill(row, WALL);      // заполняем весь лабиринт стенами
        }

        int x = new SecureRandom().nextInt(width) * 2 + 1;
        int y = new SecureRandom().nextInt(height) * 2 + 1;
        HashSet<Cell> pointToConnect = new HashSet<Cell>();
        pointToConnect.add(new Cell(x, y));

        while (!pointToConnect.isEmpty()) {
            Cell point = getRandomElement(pointToConnect);
            pointToConnect.remove(point);
            x = point.getX();
            y = point.getY();
            maze[y][x] = WAY;
            connectWithSomeone(x, y);
            addPointsToVisit(pointToConnect, x, y);
        }
        return maze;
    }

    private static void connectWithSomeone(int x, int y) {
        List<Integer> directions = Arrays.asList(0, 1, 2, DX.length - 1);
        Collections.shuffle(directions);
        for (Integer i : directions) {
            int neighborX = x + DX[i] * 2;
            int neighborY = y + DY[i] * 2;
            if (cellInMaze(neighborX, neighborY) && cellIsVisited(neighborX, neighborY)) {
                int connectorX = x + DX[i];
                int connectorY = y + DY[i];
                maze[connectorY][connectorX] = WAY;
                return;
            }
        }
    }

    private static Cell getRandomElement(HashSet<Cell> set) {
        List<Cell> cellList = new ArrayList<>(set);
        int randomIndex = new SecureRandom().nextInt(cellList.size());
        return cellList.get(randomIndex);
    }

    private static boolean cellInMaze(int x, int y) {
        return (x % 2 == 1) && x < widthOfMaze && (y % 2 == 1) && y < heightOfMaze;
    }

    private static boolean cellIsVisited(int x, int y) {
        return maze[y][x] == WAY;
    }

    private static void addPointsToVisit(HashSet<Cell> pointToConnect, int x, int y) {
        for (int i = 0; i < DX.length; ++i) {
            int neighborX = x + DX[i] * 2;
            int neighborY = y + DY[i] * 2;
            if (cellInMaze(neighborX, neighborY) && !cellIsVisited(neighborX, neighborY)) {
                pointToConnect.add(new Cell(neighborX, neighborY));
            }
        }
    }
}
