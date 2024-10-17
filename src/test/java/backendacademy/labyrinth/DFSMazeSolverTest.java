package backendacademy.labyrinth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DFSMazeSolverTest {
    private Maze maze;

    @BeforeEach
    void setUp() {
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

    @Test
    void testSolveMaze() {
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
}
