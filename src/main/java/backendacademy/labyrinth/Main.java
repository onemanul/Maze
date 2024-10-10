package backendacademy.labyrinth;

import java.util.Scanner;
import java.util.logging.Logger;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int height = in.nextInt();
        int width = in.nextInt();
        Maze maze = new Maze(height, width);
        maze.setMaze(PrimMazeGenerator.generate(Maze.getHeight(), Maze.getWidth()));
        LOGGER.info(maze.showMaze());
    }
}
