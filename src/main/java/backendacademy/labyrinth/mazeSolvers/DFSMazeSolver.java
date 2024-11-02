package backendacademy.labyrinth.mazeSolvers;

import backendacademy.labyrinth.mazeHelpers.MazeSolverHelper;
import backendacademy.labyrinth.mazeStructures.Cell;
import backendacademy.labyrinth.mazeStructures.Maze;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Решатель лабиринтов на основе алгоритма поиска в глубину (DFS).
 * Наследует функциональность от MazeSolverHelper.
 */

public class DFSMazeSolver extends MazeSolverHelper {
    private DFSMazeSolver() {}

    public static ArrayList<Cell> solve(Maze trueMaze) {
        DFSMazeSolver dfs = new DFSMazeSolver();
        return dfs.solver(trueMaze);
    }

    /**
     * Реализация алгоритма:
     *  1. Добавить стартовую клетку в список (путь).
     *  2. До тех пор, пока список не пуст или пока новая клетка не финиш:
     *      2.1. Получить из списка последнюю клетку.
     *      2.2. Выбрать из её соседей случайную клетку-проход, не отмеченную пройденной.
     *          2.2.1. Если такой сосед существует, отметить текущую клетку пройденной, а соседнюю добавить в список.
     *          2.2.2. Если такого соседа нет, заблокировать текущую клетку (отметить стеной) и удалить её из списка.
     *
     * @return ArrayList клеток пути от старта до финиша. Если путь не найден, возвращает пустой ArrayList.
     */
    protected ArrayList<Cell> solver(Maze trueMaze) {
        maze = mazeCopy(trueMaze.getMaze());
        ArrayList<Cell> path = new ArrayList<>();
        path.add(trueMaze.getStart());
        Optional<Cell> nextPoint;
        do {
            int x = path.getLast().getX();
            int y = path.getLast().getY();
            nextPoint = getNext(x, y);
            if (nextPoint.isPresent()) {
                maze[y][x] = PATH;
                path.add(nextPoint.get());
            } else {
                maze[y][x] = WALL;
                path.removeLast();
            }
        } while (!path.isEmpty() && (nextPoint.isEmpty() ||  !nextPoint.get().equals(trueMaze.getFinish())));
        return path;
    }

    private static Optional<Cell> getNext(int x, int y) {
        for (int i = 0; i < DX.length; ++i) {
            int nextX = x + DX[i];
            int nextY = y + DY[i];
            if (cellIsValid(nextX, nextY)) {
                return Optional.of(new Cell(nextX, nextY));
            }
        }
        return Optional.empty();
    }
}
