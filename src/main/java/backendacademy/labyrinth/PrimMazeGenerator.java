package backendacademy.labyrinth;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class PrimMazeGenerator extends MazeGeneratorHelper {
    private PrimMazeGenerator() {}

    public static char[][] generate(int height, int width) {
        PrimMazeGenerator prim = new PrimMazeGenerator();
        return prim.generator(height, width);
    }

    protected char[][] generator(int height, int width) {
        maze = mazeFilledWithWalls(toActualMazeDimension(height), toActualMazeDimension(width));
        int x = toActualMazeDimension(new SecureRandom().nextInt(width));
        int y = toActualMazeDimension(new SecureRandom().nextInt(height));
        HashSet<Cell> pointToConnect = new HashSet<Cell>();
        pointToConnect.add(new Cell(x, y));

        while (!pointToConnect.isEmpty()) {
            Cell point = getRandomCell(pointToConnect);
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

    private static Cell getRandomCell(HashSet<Cell> pointToConnect) {
        List<Cell> cellList = new ArrayList<>(pointToConnect);
        int randomIndex = new SecureRandom().nextInt(cellList.size());
        return cellList.get(randomIndex);
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
