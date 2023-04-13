package knightworld;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomeGenerator {
    private int mapWidth;
    private int mapHeight;

    // Number of rooms to generate
    private int numRooms;

    // List of rooms on the map
    private List<Room> rooms;

    // 2D array to represent the map
    private TETile[][] map;

    // Random number generator
    private Random rand;

    // Constructor
    public RandomeGenerator(int mapWidth, int mapHeight, int numRooms) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.numRooms = numRooms;
        this.rooms = new ArrayList<>();
        this.map = new TETile[mapHeight][mapWidth];
        this.rand = new Random();
    }

    // Method to generate the map
    public void generate() {
        // Initialize the map to all empty spaces
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                map[i][j] = Tileset.FLOOR;
            }
        }

        // Generate the rooms
        for (int i = 0; i < numRooms; i++) {
            int roomWidth = rand.nextInt(5) + 5;
            int roomHeight = rand.nextInt(5) + 5;
            int roomX = rand.nextInt(mapWidth - roomWidth - 1) + 1;
            int roomY = rand.nextInt(mapHeight - roomHeight - 1) + 1;
            Room room = new Room(roomX, roomY, roomWidth, roomHeight);
            rooms.add(room);

            // Mark the room on the map
            for (int j = roomY; j < roomY + roomHeight; j++) {
                for (int k = roomX; k < roomX + roomWidth; k++) {
                    map[j][k] = Tileset.WALL;
                }
            }
        }

        // Connect the rooms
        for (int i = 0; i < rooms.size() - 1; i++) {
            Room room1 = rooms.get(i);
            Room room2 = rooms.get(i+1);

            // Connect horizontally
            int startX = Math.min(room1.getX() + room1.getWidth(), room2.getX());
            int endX = Math.max(room1.getX() + room1.getWidth(), room2.getX());
            for (int j = startX; j < endX; j++) {
                map[room1.getY() + room1.getHeight()/2][j] = Tileset.WALL;
            }

            // Connect vertically
            int startY = Math.min(room1.getY() + room1.getHeight(), room2.getY());
            int endY = Math.max(room1.getY() + room1.getHeight(), room2.getY());
            for (int j = startY; j < endY; j++) {
                map[j][room2.getX() + room2.getWidth()/2] = Tileset.WALL;
            }
        }
    }

    // Method to print the map
    public void printMap() {
        for (int i = 0; i < mapHeight; i++) {
            for (int j = 0; j < mapWidth; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        int width = 80;
        int height = 30;
        long seed = 123456789;

        TERenderer ter = new TERenderer();
        ter.initialize(width, height);



        RandomeGenerator worldGen = new RandomeGenerator( width, height, 4);
        worldGen.generate();
        ter.renderFrame(worldGen.map);

    }


    // Class to represent a room
    private class Room {
        private int x;
        private int y;
        private int width;
        private int height;

        public Room(int x, int y, int width, int height){

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
}
    // Main method to test the algorithm

