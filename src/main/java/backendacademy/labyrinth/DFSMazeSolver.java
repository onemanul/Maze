package backendacademy.labyrinth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class DFSMazeSolver extends MazeSolver {
    private DFSMazeSolver() {}

    public static ArrayList<Cell> solve(Maze maze) {
        mazeCopy = new char[maze.getMaze().length][];
        for (int i = 0; i < maze.getMaze().length; i++) {
            mazeCopy[i] = Arrays.copyOf(maze.getMaze()[i], maze.getMaze()[i].length);
        }
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
}
