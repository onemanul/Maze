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
    private static final int MAX_MAZE_SIZE = 300;
    private static final int MIN_MAZE_SIZE = 3;

    public static void main(String[] args) { // 13 42
        LOGGER.info("Попробуем построить лабиринты и найти в них пути!\n\n"
                        + "Введите ширину лабиринта (от 3 до 300):");
        int width = correctSizeInput();     // x
        LOGGER.info("Введите высоту лабиринта (от 3 до 300):");
        int height = correctSizeInput();    // y
        LOGGER.info("Координаты точки старта (X и Y):");
        Cell start = new Cell(correctCoordinateInput(width), correctCoordinateInput(height));
        LOGGER.info("Координаты точки финиша (X и Y):");
        Cell finish = new Cell(correctCoordinateInput(width), correctCoordinateInput(height));

        switch (chooseMazeGenerator()) {
            case 1:
                Maze mazePrim = new Maze(height, width, start, finish);
                mazePrim.setMaze(PrimMazeGenerator.generate(mazePrim.getHeight(), mazePrim.getWidth()));
                LOGGER.info("Лабиринт, построенный с помощью алгоритма Прима\n" + mazePrim.showMaze());
                findAndShowPath(mazePrim);
                break;
            case 2:
                Maze mazeDFS = new Maze(height, width, start, finish);
                mazeDFS.setMaze(DFSMazeGenerator.generate(mazeDFS.getHeight(), mazeDFS.getWidth()));
                LOGGER.info("Лабиринт, построенный с помощью алгоритма DFS\n" + mazeDFS.showMaze());
                findAndShowPath(mazeDFS);
                break;
            default:
                LOGGER.warning("Что-то пошло не так и алгоритм не выбран. Увы!\n");
        }
    }

    public static int chooseMazeGenerator() {
        LOGGER.info("""
                Выберите алгоритм генерации лабиринта (введите номер):
                    1. Алгоритм Прима
                    2. Алгоритм поиска в глубину
                """);
        Optional<Integer> optChoice = checkForInt(in.nextLine());
        while (optChoice.isEmpty() || optChoice.get() < 1 || optChoice.get() > 2) {
            LOGGER.info("Введите 1 (Прим) или 2 (поиск в глубину)");
            optChoice = checkForInt(in.nextLine());
        }
        return optChoice.get();
    }

    public static void findAndShowPath(Maze maze) {
        maze.breakSomeWalls();
        if (maze.getStart().equals(maze.getFinish())) {
            LOGGER.info("Вы ввели одинаковые точки старта и финиша. Пути не будет, "
                + "но вы можете посмотреть на лабиринт с несколькими удалёнными стенами "
                + "(удалёнными при условии, что лабиринт не минимального размера)\n" + maze.showMaze());
        } else {
            LOGGER.info("Удалим несколько стен (если лабиринт не был минимального размера)\n" + maze.showMaze());
            maze.setPath(DFSMazeSolver.solve(maze));
            LOGGER.info("Решение 1 (алгоритм DFS)\n" + maze.showMazeWithWay());
            maze.setPath(BFSMazeSolver.solve(maze));
            LOGGER.info("Решение 2 (алгоритм BFS)\n" + maze.showMazeWithWay());
        }
    }

    public static int correctSizeInput() {
        Optional<Integer> optSize = checkForInt(in.nextLine());
        while (optSize.isEmpty() || optSize.get() < MIN_MAZE_SIZE || optSize.get() > MAX_MAZE_SIZE) {
            LOGGER.info("Введите число от 3 до 300");
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
