package backendacademy.labyrinth;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

public class DFSMazeGenerator extends MazeHelper {
    private DFSMazeGenerator() {}

    public static char[][] generate(int height, int width) {
        maze = mazeFilledWithWalls(height * 2 + 1, width * 2 + 1);
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
}
