package backendacademy.labyrinth.mazeSolversTests;

import backendacademy.labyrinth.mazeSolvers.DFSMazeSolver;
import backendacademy.labyrinth.mazeStructures.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DFSMazeSolverTest {
    private Maze maze;

    void setUp_IsWay() {
        Cell start = new Cell(1, 1);
        Cell finish = new Cell(3, 3);
        maze = new Maze(3, 3, start, finish);
        char[][] mazeToSet = {
            {'-','-','-','-','-','-','-'},
            {'-','+','+','+','-','+','-'},
            {'-','-','-','+','-','+','-'},
            {'-','+','+','+','+','+','-'},
            {'-','+','-','-','-','+','-'},
            {'-','+','-','+','+','+','-'},
            {'-','-','-','-','-','-','-'}};
        maze.setMaze(mazeToSet);
    }

    void setUp_NoWay() {
        Cell start = new Cell(1, 1);
        Cell finish = new Cell(3, 3);
        maze = new Maze(3, 3, start, finish);
        char[][] mazeToSet = {
            {'-','-','-','-','-','-','-'},
            {'-','+','+','+','-','+','-'},
            {'-','-','-','-','-','+','-'},
            {'-','+','+','+','+','+','-'},
            {'-','+','-','-','-','+','-'},
            {'-','+','-','+','+','+','-'},
            {'-','-','-','-','-','-','-'}};
        maze.setMaze(mazeToSet);
    }

    void setUp_SameStartAndFinish() {
        Cell start = new Cell(1, 1);
        maze = new Maze(3, 3, start, start);
        char[][] mazeToSet = {{'-','-','-','-','-','-','-'},
            {'-','+','+','+','-','+','-'},
            {'-','-','-','+','-','+','-'},
            {'-','+','+','+','+','+','-'},
            {'-','+','-','-','-','+','-'},
            {'-','+','-','+','+','+','-'},
            {'-','-','-','-','-','-','-'}};
        maze.setMaze(mazeToSet);
    }

    void setUp_StartNearFinish() {
        Cell start = new Cell(1, 1);
        Cell finish = new Cell(2, 1);
        maze = new Maze(3, 3, start, finish);
        char[][] mazeToSet = {{'-','-','-','-','-','-','-'},
            {'-','+','+','+','-','+','-'},
            {'-','-','-','+','-','+','-'},
            {'-','+','+','+','+','+','-'},
            {'-','+','-','-','-','+','-'},
            {'-','+','-','+','+','+','-'},
            {'-','-','-','-','-','-','-'}};
        maze.setMaze(mazeToSet);
    }

    @Test
    void testSolveMaze_IsWay() {
        setUp_IsWay();
        ArrayList<Cell> pathTest = DFSMazeSolver.solve(maze);
        ArrayList<Cell> pathTrue = new ArrayList<>(List.of(
            new Cell(1,1),
            new Cell(2,1),
            new Cell(3,1),
            new Cell(3,2),
            new Cell(3,3),
            new Cell(4,3),
            new Cell(5,3),
            new Cell(5,4),
            new Cell(5,5)));
        assertEquals(maze.getStart(), pathTest.getFirst());
        assertEquals(maze.getFinish(), pathTest.getLast());
        assertEquals(pathTrue.size(), pathTest.size());
        for (int i = 0; i < pathTrue.size(); ++i) {
            assertEquals(pathTrue.get(i), pathTest.get(i));
        }
    }

    @Test
    void testSolveMaze_NoWay() {
        setUp_NoWay();
        ArrayList<Cell> pathTest = DFSMazeSolver.solve(maze);
        assertTrue(pathTest.isEmpty());
    }

    @Test
    void testSolveMaze_SameStartAndFinish() {
        setUp_SameStartAndFinish();
        ArrayList<Cell> pathTest = DFSMazeSolver.solve(maze);
        assertTrue(pathTest.isEmpty());
    }

    @Test
    void testSolveMaze_StartNearFinish() {
        setUp_StartNearFinish();
        ArrayList<Cell> pathTest = DFSMazeSolver.solve(maze);
        assertEquals(3, pathTest.size());
        maze.setPath(pathTest);
        assertEquals("\n█████████████████████\n" +
            "███[S]***[F]███   ███\n" +
            "█████████   ███   ███\n" +
            "███               ███\n" +
            "███   █████████   ███\n" +
            "███   ███         ███\n" +
            "█████████████████████\n", maze.showMazeWithWay());
    }
}
