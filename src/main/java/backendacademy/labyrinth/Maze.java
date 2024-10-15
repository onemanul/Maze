package backendacademy.labyrinth;

import java.util.ArrayList;
import java.util.Arrays;

/*
Лабиринт представляет собой двумерный массив символов. Использованы следующие обозначения:
    '+' - проход;
    '-' - стена;
    '*' - путь;
    's' - точка старта;
    'f' - точка финиша.
 */

public class Maze {
    private final int width;
    private final int height;
    private int widthOfMaze;
    private int heightOfMaze;
    private char[][] maze;
    private final Cell start;
    private final Cell finish;
    private ArrayList<Cell> path;

    public Maze(int height, int width, Cell start, Cell finish) {
        this.height = height;
        this.width = width;
        this.start = start;
        this.finish = finish;
    }

    public String showMaze() {
        StringBuilder mazeString = new StringBuilder();
        mazeString.append("\n");
        for (int i = 0; i < heightOfMaze; ++i) {
            for (int j = 0; j < widthOfMaze; ++j) {
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
        this.heightOfMaze = maze.length;
        this.widthOfMaze = maze[0].length;
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
