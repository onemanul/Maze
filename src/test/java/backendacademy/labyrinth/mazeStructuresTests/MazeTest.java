package backendacademy.labyrinth.mazeStructuresTests;

import backendacademy.labyrinth.mazeStructures.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MazeTest {
    private Maze maze;

    @BeforeEach
    void setUp() {
        Cell start = new Cell(1, 1);
        Cell finish = new Cell(3, 3);
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
    void testConstructor() {
        assertEquals(3, maze.getHeight());
        assertEquals(3, maze.getWidth());
        assertEquals(new Cell (1, 1), maze.getStart());
        assertEquals(new Cell (5, 5), maze.getFinish());
        assertNotNull(maze.getMaze());
    }

    @Test
    void testGetters() {
        assertEquals(new Cell (1, 1), maze.getStart());
        assertEquals(new Cell (5, 5), maze.getFinish());
        assertEquals(3, maze.getHeight());
        assertEquals(3, maze.getWidth());
    }

    @Test
    void testShowMaze() {
        assertEquals("\n█████████████████████\n" +
                                "███         ███   ███\n" +
                                "█████████   ███   ███\n" +
                                "███               ███\n" +
                                "███   █████████   ███\n" +
                                "███   ███         ███\n" +
                                "█████████████████████\n", maze.showMaze());
    }

    @Test
    void testShowMazeWithWay_CorrectWay() {
        ArrayList<Cell> pathToSet = new ArrayList<>(List.of(
            new Cell(1,1),
            new Cell(2,1),
            new Cell(3,1),
            new Cell(3,2),
            new Cell(3,3),
            new Cell(4,3),
            new Cell(5,3),
            new Cell(5,4),
            new Cell(5,5)));
        maze.setPath(pathToSet);
        assertEquals("\n█████████████████████\n" +
                                "███[S]******███   ███\n" +
                                "█████████***███   ███\n" +
                                "███      *********███\n" +
                                "███   █████████***███\n" +
                                "███   ███      [F]███\n" +
                                "█████████████████████\n", maze.showMazeWithWay());
    }

    @Test
    void testShowMazeWithWay_EmptyWay() {
        ArrayList<Cell> pathToSet = new ArrayList<>();
        maze.setPath(pathToSet);
        assertEquals("Путь не найден или не существует.\n", maze.showMazeWithWay());
    }
}
