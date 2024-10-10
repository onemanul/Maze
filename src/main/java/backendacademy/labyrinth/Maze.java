package backendacademy.labyrinth;

public class Maze {
    private static int width;
    private static int height;
    private static int widthOfMaze;
    private static int heightOfMaze;
    private static boolean[][] maze;

    public Maze(int height, int width) {
        Maze.height = height;
        Maze.width = width;
        Maze.heightOfMaze = height;
        Maze.widthOfMaze = width;
        Maze.maze = new boolean[height][width];
    }

    public String showMaze() {
        StringBuilder mazeString = new StringBuilder();
        mazeString.append("\n");
        for (int i = 0; i < heightOfMaze; ++i) {
            for (int j = 0; j < widthOfMaze; ++j) {
                if (maze[i][j]) {
                    mazeString.append("  "); // path
                } else {
                    mazeString.append("██"); // wall
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

    public static void setMaze(boolean[][] maze) {
        Maze.maze = maze;
        Maze.heightOfMaze = maze.length;
        Maze.widthOfMaze = maze[0].length;
    }
}
