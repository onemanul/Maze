package backendacademy.labyrinth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MazeHelperTest extends MazeHelper {
    @BeforeEach
    void setUp() {
        maze = new char[][] {
            {'-', '-', '-'},
            {'-', '+', '-'},
            {'-', '-', '-'}};
    }

    @Test
    void testMazeCopy() {
        char[][] copiedMaze = MazeHelper.mazeCopy(maze);
        assertArrayEquals(maze, copiedMaze);
        copiedMaze[1][1] = '*';
        assertNotEquals(maze[1][1], copiedMaze[1][1]);
    }

    @Test
    void testMazeFilledWithWalls() {
        char[][] filledMaze = MazeHelper.mazeFilledWithWalls(3, 3);
        for (char[] row : filledMaze) {
            for (char cell : row) {
                assertEquals('-', cell);
            }
        }
    }

    @Test
    void testCellIsValid() {
        assertTrue(MazeHelper.cellIsValid(1, 1));
        assertFalse(MazeHelper.cellIsValid(0, 0));
        assertFalse(MazeHelper.cellIsValid(1, 0));
        assertFalse(MazeHelper.cellIsValid(5, 2));
    }

    @Test
    void testCellInMaze() {
        assertTrue(MazeHelper.cellInMaze(1, 1));
        assertFalse(MazeHelper.cellInMaze(0, 0));
        assertFalse(MazeHelper.cellInMaze(2, 5));
        assertFalse(MazeHelper.cellInMaze(1, 0));
    }

    @Test
    void testCellIsVisited() {
        assertTrue(MazeHelper.cellIsVisited(1, 1));
        assertFalse(MazeHelper.cellIsVisited(0, 0));
    }
}
