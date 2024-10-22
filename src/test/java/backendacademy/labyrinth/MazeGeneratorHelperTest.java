package backendacademy.labyrinth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MazeGeneratorHelperTest extends MazeGeneratorHelper {
    @Override
    protected char[][] generator(int height, int width) {
        return null;
    }

    @BeforeEach
    void setUp() {
        maze = new char[][] {
            {'-', '-', '-'},
            {'-', '+', '-'},
            {'-', '-', '-'}};
    }

    @Test
    void testToActualMazeDimension() {
        assertEquals(7, MazeGeneratorHelper.toActualMazeDimension(3));
        assertEquals(1, MazeGeneratorHelper.toActualMazeDimension(0));
        assertEquals(11, MazeGeneratorHelper.toActualMazeDimension(5));
    }

    @Test
    void testMazeFilledWithWalls() {
        char[][] filledMaze = MazeGeneratorHelper.mazeFilledWithWalls(3, 3);
        for (char[] row : filledMaze) {
            for (char cell : row) {
                assertEquals('-', cell);
            }
        }
    }

    @Test
    void testCellInMaze() {
        assertTrue(MazeGeneratorHelper.cellInMaze(1, 1));
        assertFalse(MazeGeneratorHelper.cellInMaze(0, 0));
        assertFalse(MazeGeneratorHelper.cellInMaze(2, 5));
        assertFalse(MazeGeneratorHelper.cellInMaze(1, 0));
    }

    @Test
    void testCellIsVisited() {
        assertTrue(MazeGeneratorHelper.cellIsVisited(1, 1));
        assertFalse(MazeGeneratorHelper.cellIsVisited(0, 0));
    }
}
