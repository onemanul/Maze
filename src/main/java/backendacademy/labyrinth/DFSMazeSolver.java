package backendacademy.labyrinth;

import java.util.ArrayList;
import java.util.Optional;

public class DFSMazeSolver extends MazeHelper {
    private DFSMazeSolver() {}

    public static ArrayList<Cell> solve(Maze trueMaze) {
        maze = mazeCopy(trueMaze.getMaze());
        ArrayList<Cell> path = new ArrayList<>();
        path.add(trueMaze.getStart());
        Optional<Cell> nextPoint;
        do {
            int x = path.getLast().getX();
            int y = path.getLast().getY();
            nextPoint = getNext(x, y);
            if (nextPoint.isPresent()) {
                maze[y][x] = PATH;
                path.add(nextPoint.get());
            } else {
                maze[y][x] = WALL;
                path.removeLast();
            }
        } while (!path.isEmpty() && (nextPoint.isEmpty() ||  !nextPoint.get().equals(trueMaze.getFinish())));
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
