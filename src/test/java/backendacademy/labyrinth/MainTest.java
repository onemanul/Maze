package backendacademy.labyrinth;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void testCheckForInt_CorrectInput() {
        Optional<Integer> result = Main.checkForInt("20");
        assertTrue(result.isPresent());
        assertEquals(20, result.get());
    }

    @Test
    void testCheckForInt_IncorrectInput() {
        Optional<Integer> result = Main.checkForInt("abc");
        assertFalse(result.isPresent());
    }

    @Test
    void testCorrectSizeInput() {
        Scanner scanner = new Scanner("0\n-1\nabc\n3\n5");
        Main.in = scanner;
        assertEquals(3, Main.correctSizeInput());
        assertEquals(5, Main.correctSizeInput());
    }

    @Test
    void testCorrectCoordinateInput() {
        Scanner scanner = new Scanner("0\n-1\nabc\n3\n7\n5");
        Main.in = scanner;
        assertEquals(3, Main.correctCoordinateInput(5));
        assertEquals(5, Main.correctCoordinateInput(5));
    }

    @Test
    void testChooseMazeGenerator() {
        Scanner scanner = new Scanner("0\n-1\nabc\n2\n5\n1");
        Main.in = scanner;
        assertEquals(2, Main.chooseMazeGenerator());
        assertEquals(1, Main.chooseMazeGenerator());
    }
}
