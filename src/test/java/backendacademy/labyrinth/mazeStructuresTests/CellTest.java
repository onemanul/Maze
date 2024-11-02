package backendacademy.labyrinth.mazeStructuresTests;

import backendacademy.labyrinth.mazeStructures.Cell;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    @Test
    void testConstructor() {
        Cell cell = new Cell(3, 4);
        assertEquals(3, cell.getX());
        assertEquals(4, cell.getY());
    }

    @Test
    void testEquals_SameCoordinates() {
        Cell cell1 = new Cell(2, 5);
        Cell cell2 = new Cell(2, 5);
        assertEquals(cell1, cell2);
    }

    @Test
    void testEquals_DifferentCoordinates() {
        Cell cell1 = new Cell(1, 2);
        Cell cell2 = new Cell(1, 3);
        assertNotEquals(cell1, cell2);
    }

    @Test
    void testEquals_NullObject() {
        Cell cell = new Cell(1, 1);
        assertNotEquals(cell, null);
    }

    @Test
    void testEquals_DifferentClass() {
        Cell cell = new Cell(1, 1);
        String notACell = "Not a Cell";
        assertNotEquals(cell, notACell);
    }

    @Test
    void testHashCode_SameCoordinates() {
        Cell cell1 = new Cell(1, 2);
        Cell cell2 = new Cell(1, 2);
        assertEquals(cell1.hashCode(), cell2.hashCode());
    }

    @Test
    void testHashCode_DifferentCoordinates() {
        Cell cell1 = new Cell(1, 2);
        Cell cell2 = new Cell(2, 1);
        assertNotEquals(cell1.hashCode(), cell2.hashCode());
    }
}
