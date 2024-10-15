package backendacademy.labyrinth;

/*
Лабиринт представляет собой двумерный массив символов. Использованы следующие обозначения:
    '+' - проход (WAY);
    '-' - стена (WALL);
    '*' - путь (PATH).
 */

import java.util.Arrays;

public abstract class MazeHelper {
    protected final static int[] DX = {1, 0, -1, 0};
    protected final static int[] DY = {0, 1, 0, -1};
    protected final static char WAY = '+';
    protected final static char WALL = '-';
    protected final static char PATH = '*';
    protected static char[][] maze;

    protected static char[][] mazeCopy(char[][] trueMaze) {
        char[][] mazeCopy = new char[trueMaze.length][];
        for (int i = 0; i < trueMaze.length; i++) {
            mazeCopy[i] = Arrays.copyOf(trueMaze[i], trueMaze[i].length);
        }
        return mazeCopy;
    }

    protected static char[][] mazeFilledWithWalls(int heightOfMaze, int widthOfMaze) {
        char[][] mazeOfWalls = new char[heightOfMaze][widthOfMaze];
        for (char[] row : mazeOfWalls) {
            Arrays.fill(row, WALL);      // заполняем весь лабиринт стенами
        }
        return mazeOfWalls;
    }

    protected static boolean cellIsValid(int x, int y) {
        return x > 0 && x < maze[0].length && y > 0 && y < maze.length && maze[y][x] == WAY;
    }

    protected static boolean cellInMaze(int x, int y) {
        return (x % 2 == 1) && x < maze[0].length && (y % 2 == 1) && y < maze.length;
    }

    protected static boolean cellIsVisited(int x, int y) {
        return maze[y][x] == WAY;
    }
}
