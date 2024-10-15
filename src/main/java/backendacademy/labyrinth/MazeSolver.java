package backendacademy.labyrinth;

/*
Лабиринт представляет собой двумерный массив символов. Использованы следующие обозначения:
    '+' - проход (WAY);
    '-' - стена (WALL);
    '*' - путь (PATH).
 */

public abstract class MazeSolver {
    protected final static int[] DX = {1, 0, -1, 0};
    protected final static int[] DY = {0, 1, 0, -1};
    protected final static char WAY = '+';
    protected final static char WALL = '-';
    protected final static char PATH = '*';
    protected static char[][] mazeCopy;

    public static boolean cellIsValid(int x, int y) {
        return x > 0 && x < mazeCopy[0].length && y > 0 && y < mazeCopy.length && mazeCopy[y][x] == WAY;
    }
}
