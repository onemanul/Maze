package backendacademy.labyrinth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MazeSolverHelperTest extends MazeSolverHelper {
    @Override
    protected ArrayList<Cell> solver(Maze trueMaze) {
        return null;
    }

    void setUp() {
        maze = new char[][] {
            {'-', '-', '-'},
            {'-', '+', '-'},
            {'-', '-', '-'}};
    }

    void setUp_big_Size() {
        maze = new char[][] {
            { '-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-'},
        { '-','+','-','+','+','+','-','+','-','+','-','+','+','+','-','+','+','+','-','+','-' },
        {'-','+','-','-','-','+','-','+','-','+','-','+','-','-','-','-','-','+','-','+','-' },
        { '-','+','+','+','-','+','-','+','-','+','-','+','+','+','+','+','-','+','-','+','-' },
        { '-','+','-','+','-','+','-','+','-','+','-','+','-','-','-','-','-','+','-','+','-' },
        { '-','+','-','+','+','+','+','+','+','+','+','+','-','+','-','+','-','+','+','+','-' },
        { '-','-','-','-','-','-','-','+','-','+','-','-','-','+','-','+','-','-','-','+','-' },
        { '-','+','-','+','+','+','-','+','-','+','+','+','-','+','-','+','-','+','-','+','-' },
        { '-','+','-','-','-','+','-','-','-','+','-','+','-','+','-','+','-','+','-','+','-' },
        { '-','+','+','+','-','+','+','+','-','+','-','+','+','+','+','+','+','+','+','+','-' },
        { '-','-','-','+','-','+','-','-','-','-','-','+','-','-','-','+','-','+','-','+','-' },
        { '-','+','+','+','+','+','+','+','+','+','+','+','-','+','+','+','-','+','-','+','-' },
        { '-','-','-','+','-','+','-','+','-','-','-','-','-','-','-','-','-','-','-','+','-' },
        { '-','+','+','+','-','+','-','+','-','+','+','+','+','+','-','+','+','+','+','+','-' },
        { '-','-','-','+','-','+','-','-','-','-','-','-','-','+','-','-','-','-','-','+','-' },
        { '-','+','+','+','-','+','+','+','-','+','+','+','+','+','+','+','+','+','+','+','-' },
        { '-','+','-','+','-','-','-','+','-','+','-','+','-','-','-','-','-','+','-','+','-' },
        { '-','+','-','+','+','+','-','+','-','+','-','+','-','+','+','+','+','+','-','+','-' },
        { '-','+','-','-','-','+','-','+','-','-','-','+','-','-','-','+','-','-','-','+','-' },
        { '-','+','+','+','-','+','-','+','-','+','+','+','-','+','+','+','-','+','+','+','-' },
        { '-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-' }};
    }

    @Test
    void testMazeCopy() {
        setUp();
        char[][] copiedMaze = MazeSolverHelper.mazeCopy(maze);
        assertArrayEquals(maze, copiedMaze);
        copiedMaze[1][1] = '*';
        assertNotEquals(maze[1][1], copiedMaze[1][1]);
    }

    @Test
    void testCellIsValid() {
        setUp();
        assertTrue(MazeSolverHelper.cellIsValid(1, 1));
        assertFalse(MazeSolverHelper.cellIsValid(0, 0));
        assertFalse(MazeSolverHelper.cellIsValid(1, 0));
        assertFalse(MazeSolverHelper.cellIsValid(5, 2));
    }

    @Test
    void testSolvers() {
        setUp_big_Size();
        Maze mazeTrue = new Maze(10,10, new Cell(1, 1), new Cell(10, 10));
        mazeTrue.setMaze(maze);
        ArrayList<Cell> pathBFS = BFSMazeSolver.solve(mazeTrue);
        ArrayList<Cell> pathDFS = DFSMazeSolver.solve(mazeTrue);
        assertEquals(pathDFS.getFirst(), pathBFS.getFirst());
        assertEquals(pathDFS.getLast(), pathBFS.getLast());
        assertEquals(pathBFS.size(), pathDFS.size());
        for (int i = 0; i < pathBFS.size(); ++i) {
            assertEquals(pathBFS.get(i), pathDFS.get(i));
        }
    }
}
