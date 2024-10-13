package backendacademy.labyrinth;

import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Logger;
import lombok.experimental.UtilityClass;

/*
Лабиринт представляет собой двумерный массив символов. Использованы следующие обозначения:
    '+' - проход;
    '-' - стена;
    '*' - путь;
    's' - точка старта;
    'f' - точка финиша.
 */

@UtilityClass
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) { // 13 42
        LOGGER.info("Попробуем построить лабиринты и найти в них пути!\n\nВведите высоту лабиринта:");
        int height = correctSizeInput();    // y
        LOGGER.info("Введите ширину лабиринта:");
        int width = correctSizeInput();     // x
        /*LOGGER.info("Координаты точки старта (X и Y):");
        Cell start = new Cell(correctCoordinateInput(width), correctCoordinateInput(height));
        LOGGER.info("Координаты точки финиша (X и Y):");
        Cell finish = new Cell(correctCoordinateInput(width), correctCoordinateInput(height));*/

        Maze maze = new Maze(height, width);
        maze.setMaze(PrimMazeGenerator.generate(Maze.getHeight(), Maze.getWidth()));
        LOGGER.info(maze.showMaze());
        maze.setMaze(DFSMazeGenerator.generate(Maze.getHeight(), Maze.getWidth()));
        LOGGER.info(maze.showMaze());
    }

    public static int correctSizeInput() {
        Optional<Integer> optSize = checkForInt(in.nextLine());
        while (optSize.isEmpty() || optSize.get() < 1) {
            LOGGER.info("Введите положительное число");
            optSize = checkForInt(in.nextLine());
        }
        return optSize.get();
    }

    public static int correctCoordinateInput(int max) {
        Optional<Integer> optCoordinate;
        do  {
            LOGGER.info("Введите число от 1 до " + max + ':');
            optCoordinate = checkForInt(in.nextLine());
        } while (optCoordinate.isEmpty() || optCoordinate.get() < 1 || optCoordinate.get() > max);
        return optCoordinate.get();
    }

    public static Optional<Integer> checkForInt(String str) {
        try {
            return Optional.of(Integer.parseInt(str.trim()));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
