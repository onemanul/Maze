package backendacademy.labyrinth.mazeSolvers;

import backendacademy.labyrinth.mazeHelpers.MazeSolverHelper;
import backendacademy.labyrinth.mazeStructures.Cell;
import backendacademy.labyrinth.mazeStructures.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Решатель лабиринтов на основе алгоритма поиска в ширину (BFS).
 * Наследует функциональность от MazeSolverHelper.
 */

public class BFSMazeSolver extends MazeSolverHelper {
    private BFSMazeSolver() {}

    public static ArrayList<Cell> solve(Maze trueMaze) {
        BFSMazeSolver bfs = new BFSMazeSolver();
        return bfs.solver(trueMaze);
    }

    /**
     * Для реализации алгоритма используются:
     *  1. HashMap<> predecessors, где ключом является сама клетка, а её значением - клетка-предок.
     *  2. LinkedList<> cellsToView, содержащий клетки, которые необходимо рассмотреть.
     *  <p>
     * Реализация алгоритма:
     *  1. Отметить стартовую клетку как пройденную и добавить её в список клеткок для просмотра.
     *  2. Пока список не пуст:
     *      2.1. Извлечь первую клетку из списка.
     *      2.2. Каждую её соседнюю клетку, не являющуюся стеной и не отмеченную пройденной:
     *          2.2.1. Добавить в HashMap, указав текущую клетку значением (клеткой-предком).
     *          2.2.2. Если эта соседняя клетка является финишем, реконструировать путь от предка к предку и завершить
     *                 алгоритм. Если не является - отметить её как пройденную и добавить в список для просмотра.
     *
     * @return ArrayList клеток пути от старта до финиша. Если путь не найден, возвращается пустой ArrayList.
     */
    protected ArrayList<Cell> solver(Maze trueMaze) {
        maze = mazeCopy(trueMaze.getMaze());
        HashMap<Cell, Cell> predecessors = new HashMap<>(); // ключ - текущая клетка, значение - её клетка-предок
        LinkedList<Cell> cellsToView = new LinkedList<>();
        cellsToView.add(trueMaze.getStart());
        maze[trueMaze.getStart().getY()][trueMaze.getStart().getX()] = PATH;
        while (!cellsToView.isEmpty()) {
            Cell current = cellsToView.getFirst();
            for (int i = 0; i < DX.length; i++) {
                int nextX = current.getX() + DX[i];
                int nextY = current.getY() + DY[i];
                if (cellIsValid(nextX, nextY)) {
                    Cell next = new Cell(nextX, nextY);
                    predecessors.put(next, current);
                    if (next.equals(trueMaze.getFinish())) {
                        return reconstructPath(predecessors, trueMaze.getFinish());
                    } else {
                        maze[nextY][nextX] = PATH;
                        cellsToView.add(next);
                    }
                }
            }
            cellsToView.removeFirst();
        }
        return new ArrayList<>();
    }

    private static ArrayList<Cell> reconstructPath(HashMap<Cell, Cell> predecessors, Cell finish) {
        ArrayList<Cell> path = new ArrayList<>();
        for (Cell at = finish; at != null; at = predecessors.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
