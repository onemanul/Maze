package backendacademy.labyrinth;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PrimMazeGenerator {
    private PrimMazeGenerator() {}

    private static int widthOfMaze;
    private static int heightOfMaze;
    private static boolean[][] maze;
    private static final int[] DX = {1, 0, -1, 0};
    private static final int[] DY = {0, 1, 0, -1};

    public static boolean[][] generate(int height, int width) {
        PrimMazeGenerator.heightOfMaze = height * 2 + 1;
        PrimMazeGenerator.widthOfMaze = width * 2 + 1;
        PrimMazeGenerator.maze = new boolean[heightOfMaze][widthOfMaze];

        int x = new SecureRandom().nextInt(width) * 2 + 1;
        int y = new SecureRandom().nextInt(height) * 2 + 1;
        HashMap<String, Cell> pointToConnect = new HashMap<String, Cell>();
        pointToConnect.put(Integer.toString(x) + Integer.toString(y), new Cell(x, y));

        while (!pointToConnect.isEmpty()) {
            Cell point = getRandomElement(pointToConnect);
            pointToConnect.remove(Integer.toString(point.getX()) + Integer.toString(point.getY()));
            x = point.getX();
            y = point.getY();
            maze[y][x] = true;
            connectWithSomeone(pointToConnect, x, y);
            addPointsToVisit(pointToConnect, x, y);
        }
        return maze;
    }

    private static void connectWithSomeone(HashMap<String, Cell> pointToConnect, int x, int y) {
        List<Integer> directions = Arrays.asList(0, 1, 2, DX.length - 1);
        Collections.shuffle(directions);
        for (Integer i : directions) {
            int neighborX = x + DX[i] * 2;
            int neighborY = y + DY[i] * 2;
            if (cellInMaze(neighborX, neighborY) && maze[neighborY][neighborX]) {
                int connectorX = x + DX[i];
                int connectorY = y + DY[i];
                maze[connectorY][connectorX] = true;
                return;
            }
        }
    }

    private static Cell getRandomElement(HashMap<String, Cell> map) { // object
        List<String> keys = new ArrayList<>(map.keySet());
        int randomIndex = new SecureRandom().nextInt(keys.size());
        return map.get(keys.get(randomIndex));
    }

    private static boolean cellInMaze(int x, int y) {
        return x >= 0 && x < widthOfMaze && y >= 0 && y < heightOfMaze;
    }

    private static void addPointsToVisit(HashMap<String, Cell> pointToConnect, int x, int y) {
        for (int i = 0; i < DX.length; ++i) {
            int neighborX = x + DX[i] * 2;
            int neighborY = y + DY[i] * 2;
            if (cellInMaze(neighborX, neighborY) && !maze[neighborY][neighborX]) {
                pointToConnect.put(Integer.toString(neighborX) + Integer.toString(neighborY),
                    new Cell(neighborX, neighborY));
            }
        }
    }
}
