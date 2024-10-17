package backendacademy.labyrinth;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DFSMazeGeneratorTest {
    private final char[][] maze = DFSMazeGenerator.generate(3, 3);

    @Test
    void testGenerateMazeDimensions() {
        assertEquals(7, maze.length);
        assertEquals(7, maze[0].length);
    }

    @Test
    void testGenerateMazeCellsAreWayBordersAreWalls() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (i % 2 == 1 && j % 2 == 1) {
                    assertEquals('+', maze[i][j]);
                }
                if (i == 0 || i == (maze.length - 1) || j == 0 || j == (maze[i].length - 1)) {
                    assertEquals('-', maze[i][j]);
                }
            }
        }
    }

    @Test
    void testGenerateMazeCountOfConnection() {
        int pathCount = 0;
        for (int i = 1; i < maze.length - 1; i++) {
            for (int j = 1; j < maze[i].length - 1; j++) {
                if ((i % 2 == 0 || j % 2 == 0) && maze[i][j] == '+') {
                    pathCount++;
                }
            }
        }
        assertEquals(3 * 3 - 1, pathCount);
    }

}
