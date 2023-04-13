package knightworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class World {
    private static final char FLOOR_CHAR = '.';
    private static final char WALL_CHAR = '#';

    private final int[][] grid;
    private final int width;
    private final int height;
    private final List<Rectangle> rooms;

    public World(long seed, int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new int[height][width];
        this.rooms = new ArrayList<>();
        Random random = new Random(seed);

        // Generate the rooms
        int numRooms = random.nextInt(10) + 5;
        for (int i = 0; i < numRooms; i++) {
            int roomWidth = random.nextInt(10) + 5;
            int roomHeight = random.nextInt(10) + 5;
            int startX = random.nextInt(width - roomWidth - 2) + 1;
            int startY = random.nextInt(height - roomHeight - 2) + 1;
            Rectangle room = new Rectangle(startX, startY, roomWidth, roomHeight);
            if (isOverlap(room)) {
                i--;
                continue;
            }
            rooms.add(room);
            for (int y = startY; y < startY + roomHeight; y++) {
                for (int x = startX; x < startX + roomWidth; x++) {
                    grid[y][x] = 1;
                }
            }
        }

        // Connect the rooms with hallways
        for (int i = 0; i < rooms.size() - 1; i++) {
            Rectangle room1 = rooms.get(i);
            Rectangle room2 = rooms.get(i + 1);
            int startX = room1.centerX();
            int startY = room1.centerY();
            int endX = room2.centerX();
            int endY = room2.centerY();
            drawHallway(startX, startY, endX, endY);
        }
    }

    private boolean isOverlap(Rectangle rect) {
        for (Rectangle room : rooms) {
            if (rect.intersects(room)) {
                return true;
            }
        }
        return false;
    }

    private void drawHallway(int startX, int startY, int endX, int endY) {
        int dx = Integer.compare(endX, startX);
        int dy = Integer.compare(endY, startY);
        Random random = new Random();
        int x = startX;
        int y = startY;
        while (x != endX || y != endY) {
            if (grid[y][x] == 0) {
                grid[y][x] = 2;
            }
            if (x != endX && grid[y][x + dx] == 0) {
                x += dx;
            } else if (y != endY && grid[y + dy][x] == 0) {
                y += dy;
            } else if (random.nextBoolean()) {
                x += dx;
            } else {
                y += dy;
            }
        }
    }

    public void print() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                switch (grid[y][x]) {
                    case 0:
                        System.out.print(WALL_CHAR);
                        break;
                    case 1:
                        System.out.print(FLOOR_CHAR);
                        break;
                    case 2:
                        System.out.print(FLOOR_CHAR);
                        break;
                    default:
                        System.out.print(' ');
                }
            }
            System.out.println();
        }
    }

    private static class Rectangle {
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        public Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public int centerX() {
            return x + width / 2;
        }

        public int centerY() {
            return y + height / 2;
        }

        public boolean intersects(Rectangle other) {
            return x < other.x + other.width &&
                    x + width > other.x &&
                    y < other.y + other.height &&
                    y + height > other.y;
        }
    }
    public static void main(String[] args) {
        World world = new World(123456, 50, 20);
        world.print();
    }
}
