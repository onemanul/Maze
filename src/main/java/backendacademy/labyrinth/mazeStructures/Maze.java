package backendacademy.labyrinth.mazeStructures;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Представляет лабиринт с заданной шириной и высотой.
 * Фактические ширина и высота лабиринта иные (для корректного поиска пути и последующего отображения стен)
 * Лабиринт содержит стартовую и финишную ячейки, а также методы для его отображения и модификации.
 * Непосредственно лабиринт представляет собой двумерный массив символов. В нём спользованы следующие обозначения:
 *   '+' - проход;
 *   '-' - стена;
 *   '*' - путь;
 *   's' - точка старта;
 *   'f' - точка финиша.
 */

public class Maze {
    private final int width;
    private final int height;
    private int actualMazeWidth;
    private int actualMazeHeight;
    private char[][] maze;
    private final Cell start;
    private final Cell finish;
    private ArrayList<Cell> path;
    private final double partOfWallsToDelete = 0.1;   // удаляем 0.1 всех стен

    public Maze(int height, int width, Cell start, Cell finish) {
        this.height = height;
        this.width = width;
        this.start = new Cell(actualCellCoordinate(start.getX()), actualCellCoordinate(start.getY()));
        this.finish = new Cell(actualCellCoordinate(finish.getX()), actualCellCoordinate(finish.getY()));
        this.maze = new char[height][width];
        this.path = new ArrayList<>();
    }

    public String showMaze() {
        StringBuilder mazeString = new StringBuilder();
        mazeString.append("\n");
        for (int i = 0; i < actualMazeHeight; ++i) {
            for (int j = 0; j < actualMazeWidth; ++j) {
                switch (maze[i][j]) {
                    case '+':   // проход
                        mazeString.append("   ");
                        break;
                    case '-':   // стена
                        mazeString.append("███");
                        break;
                    case '*':   // путь
                        mazeString.append("***");
                        break;
                    case 's':   // старт
                        mazeString.append("[S]");
                        break;
                    case 'f':   // финиш
                        mazeString.append("[F]");
                        break;
                    default: mazeString.append("ER");   // ошибка
                }
            }
            mazeString.append("\n");
        }
        return mazeString.toString();
    }

    public String showMazeWithWay() {
        if (path.isEmpty()) {
            return "Путь не найден или не существует.\n";
        }
        char[][] mazeTrue = new char[maze.length][];
        for (int i = 0; i < maze.length; i++) {
            mazeTrue[i] = Arrays.copyOf(maze[i], maze[i].length);
        }
        for (Cell c : path) {
            maze[c.getY()][c.getX()] = '*';
        }
        maze[start.getY()][start.getX()] = 's';
        maze[finish.getY()][finish.getX()] = 'f';
        String mazeString = showMaze();
        this.maze = mazeTrue;
        return mazeString;
    }

    /**
     * Удаляет случайным образом заданный процент существующих стен (не считая внешние стены лабиринта).
     */
    public void breakSomeWalls() {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int numberWallsToBreak = (int)
            (((actualMazeHeight - 2) * (actualMazeWidth - 2) - height * width * 2) * partOfWallsToDelete);
        List<Integer> directions = Arrays.asList(0, 1, 2, dx.length - 1);
        while (numberWallsToBreak != 0) {
            int x = new SecureRandom().nextInt(width) * 2 + 1;
            int y = new SecureRandom().nextInt(height) * 2 + 1;
            Collections.shuffle(directions);
            for (int i = 0; i < dx.length; i++) {
                int nextX = x + dx[i];
                int nextY = y + dy[i];
                if (wallCanBeBroken(nextX, nextY)) {
                    maze[nextY][nextX] = '+';
                    --numberWallsToBreak;
                    break;
                }
            }
        }
    }

    private boolean wallCanBeBroken(int x, int y) {
        return (x > 0) && (x < actualMazeWidth - 1) && (y > 0) && (y < actualMazeHeight - 1) && maze[y][x] == '-';
    }

    /**
     * Возвращает фактическую координаты ячейки в соответствии с изменённым фактическим размером лабиринта.
     */
    private int actualCellCoordinate(int x) {
        return (x - 1) * 2 + 1;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public char[][] getMaze() {
        return maze;
    }

    public void setMaze(char[][] maze) {
        this.maze = maze;
        this.actualMazeHeight = maze.length;
        this.actualMazeWidth = maze[0].length;
    }

    public Cell getStart() {
        return start;
    }

    public Cell getFinish() {
        return finish;
    }

    public void setPath(ArrayList<Cell> path) {
        this.path = path;
    }
}
