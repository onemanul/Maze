package backendacademy.labyrinth;

/*
Лабиринт представляет собой двумерный массив символов. Использованы следующие обозначения:
    '+' - проход (WAY);
    '-' - стена (WALL);
    '*' - путь (PATH).
 */

public interface MazeHelper {
    int[] DX = {1, 0, -1, 0};
    int[] DY = {0, 1, 0, -1};
    char WAY = '+';
    char WALL = '-';
    char PATH = '*';
}
