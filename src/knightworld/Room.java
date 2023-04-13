package knightworld;

public class Room {
    private int x;
    private int y;
    private int width;
    private int height;

    public Room(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX1() {
        return x;
    }

    public int getY1() {
        return y;
    }

    public int getX2() {
        return x + width - 1;
    }

    public int getY2() {
        return y + height - 1;
    }

    public int getCenterX() {
        return (x + getX2()) / 2;
    }

    public int getCenterY() {
        return (y + getY2()) / 2;
    }

    public boolean overlaps(Room other) {
        return getX1() <= other.getX2() && getX2() >= other.getX1() &&
                getY1() <= other.getY2() && getY2() >= other.getY1();
    }
}
