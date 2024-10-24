package backendacademy.labyrinth;

import java.util.Arrays;

/**
 * Абстрактный класс, предоставляющий вспомогательные методы для генерации лабиринта.
 * Реализует интерфейс MazeHelper, содержит абстрактный метод generator.
 */

public abstract class MazeGeneratorHelper implements MazeHelper {
    protected static char[][] maze;

    protected abstract char[][] generator(int height, int width);

    protected static int toActualMazeDimension(int d) {
        return d * 2 + 1;
    }

    protected static char[][] mazeFilledWithWalls(int heightOfMaze, int widthOfMaze) {
        char[][] mazeOfWalls = new char[heightOfMaze][widthOfMaze];
        for (char[] row : mazeOfWalls) {
            Arrays.fill(row, WALL);      // заполняем весь лабиринт стенами
        }
        return mazeOfWalls;
    }

    protected static boolean cellInMaze(int x, int y) {
        return (x % 2 == 1) && x < maze[0].length && (y % 2 == 1) && y < maze.length;
    }

    protected static boolean cellIsVisited(int x, int y) {
        return maze[y][x] == WAY;
    }
}
