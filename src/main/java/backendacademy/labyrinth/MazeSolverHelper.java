package backendacademy.labyrinth;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Абстрактный класс, предоставляющий вспомогательные методы для поиска пути в лабиринте.
 * Реализует интерфейс MazeHelper, содержит абстрактный метод solver.
 */

public abstract class MazeSolverHelper implements MazeHelper {
    protected static char[][] maze;

    protected abstract ArrayList<Cell> solver(Maze trueMaze);

    protected static char[][] mazeCopy(char[][] trueMaze) {
        char[][] mazeCopy = new char[trueMaze.length][];
        for (int i = 0; i < trueMaze.length; i++) {
            mazeCopy[i] = Arrays.copyOf(trueMaze[i], trueMaze[i].length);
        }
        return mazeCopy;
    }

    protected static boolean cellIsValid(int x, int y) {
        return x > 0 && x < maze[0].length && y > 0 && y < maze.length && maze[y][x] == WAY;
    }
}
