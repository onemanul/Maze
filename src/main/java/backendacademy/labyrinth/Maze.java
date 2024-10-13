package backendacademy.labyrinth;

/*
Лабиринт представляет собой двумерный массив символов. Использованы следующие обозначения:
    '+' - проход;
    '-' - стена;
    '*' - путь;
    's' - точка старта;
    'f' - точка финиша.
 */

public class Maze {
    private static int width;
    private static int height;
    private static int widthOfMaze;
    private static int heightOfMaze;
    private static char[][] maze;

    public Maze(int height, int width) {
        Maze.height = height;
        Maze.width = width;
        Maze.heightOfMaze = height;
        Maze.widthOfMaze = width;
        Maze.maze = new char[height][width];
    }

    public String showMaze() {
        StringBuilder mazeString = new StringBuilder();
        mazeString.append("\n");
        for (int i = 0; i < heightOfMaze; ++i) {
            for (int j = 0; j < widthOfMaze; ++j) {
                switch (maze[i][j]) {
                    case '+':   // проход
                        mazeString.append("  ");
                        break;
                    case '-':   // стена
                        mazeString.append("██");
                        break;
                    case '*':   // путь
                        mazeString.append("**");
                        break;
                    case 's':   // старт
                        mazeString.append("ST");
                        break;
                    case 'f':   // финиш
                        mazeString.append("FI");
                        break;
                    default: mazeString.append("ER");   // ошибка
                }
            }
            mazeString.append("\n");
        }
        return mazeString.toString();
    }

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

    public static void setMaze(char[][] maze) {
        Maze.maze = maze;
        Maze.heightOfMaze = maze.length;
        Maze.widthOfMaze = maze[0].length;
    }
}
