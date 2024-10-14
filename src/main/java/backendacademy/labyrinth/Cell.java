package backendacademy.labyrinth;

public class Cell {
    private int x;
    private int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Cell cell = (Cell) obj;
        return x == cell.getX() && y == cell.getY();
    }

    @Override
    public int hashCode() {
        return 17 * x + 31 * y + 37 * (x + y);
    }
}


