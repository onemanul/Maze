package backendacademy.labyrinth.mazeGenerators;

import backendacademy.labyrinth.mazeHelpers.MazeGeneratorHelper;
import backendacademy.labyrinth.mazeStructures.Cell;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

/**
 * Генератор лабиринтов на основе алгоритма поиска в глубину (DFS).
 * Наследует функциональность от MazeGeneratorHelper.
 */

public class DFSMazeGenerator extends MazeGeneratorHelper {
    private DFSMazeGenerator() {}

    public static char[][] generate(int height, int width) {
        DFSMazeGenerator dfs = new DFSMazeGenerator();
        return dfs.generator(height, width);
    }

    /**
     * Реализация алгоритма:
     *  1. Создать лабиринт, полностью заполненный стенами.
     *  2. Выбрать случайную клетку лабиринта и добавить её в стек.
     *  3. Пока стек не пуст:
     *      3.1. Получить клетку из стека и отметить как "проход".
     *      3.2. Выбрать случайного ещё не посещённого соседа текущей клетки и соединить их проходом. Добавить
     *           соседа в стек.
     *      3.3. Если подходящий сосед не найден, удалить текущую клетку из стека.
     *
     * @return двумерный массив символов, где '+' - проход (WAY), '-' - стена (WALL).
     */
    protected char[][] generator(int height, int width) {
        maze = mazeFilledWithWalls(toActualMazeDimension(height), toActualMazeDimension(width));
        int x = toActualMazeDimension(new SecureRandom().nextInt(width));
        int y = toActualMazeDimension(new SecureRandom().nextInt(height));
        Stack<Cell> cellStack = new Stack<>();
        cellStack.add(new Cell(x, y));

        while (!cellStack.isEmpty()) {
            x = cellStack.peek().getX();
            y = cellStack.peek().getY();
            maze[y][x] = WAY;
            Optional<Cell> nextPoint = getNeighbor(x, y);
            if (nextPoint.isPresent()) {
                connectCells(cellStack.peek(), nextPoint.get());
                cellStack.add(nextPoint.get());
            } else {
                cellStack.pop();
            }
        }
        return maze;
    }

    private static Optional<Cell> getNeighbor(int x, int y) {
        List<Integer> directions = Arrays.asList(0, 1, 2, DX.length - 1);
        Collections.shuffle(directions);
        for (Integer i : directions) {
            int neighborX = x + DX[i] * 2;
            int neighborY = y + DY[i] * 2;
            if (cellInMaze(neighborX, neighborY) && !cellIsVisited(neighborX, neighborY)) {
                return Optional.of(new Cell(neighborX, neighborY));
            }
        }
        return Optional.empty();
    }

    private static void connectCells(Cell first, Cell second) {
        int connectorX = (first.getX() + second.getX()) / 2;
        int connectorY = (first.getY() + second.getY()) / 2;
        maze[connectorY][connectorX] = WAY;
    }
}
