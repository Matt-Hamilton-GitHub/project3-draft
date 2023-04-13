package knightworld;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Draws a world consisting of knight-move holes.
 */
public class RandomWorldGenerator {


    private final int MAP_WIDTH;
    private final int MAP_HEIGHT;
    private final TETile[][] map;
    private final Random random;
    private int currX;
    private int currY;

    int nextX;
    int nextY;

    public RandomWorldGenerator(int width, int height, long seed) {
        MAP_WIDTH = width;
        MAP_HEIGHT = height;
        map = new TETile[MAP_HEIGHT][MAP_WIDTH];
        random = new Random(seed);
        currX = random.nextInt(2, 4);
        currY = random.nextInt(2, 4);
        nextY = currY;
        nextX = currX;
    }

    public void generate() {
        // Fill the entire map with walls
        for (int row = 0; row < MAP_HEIGHT; row++) {
            for (int col = 0; col < MAP_WIDTH; col++) {
                map[row][col] = Tileset.WALL;
            }
        }





        // Generate rooms
//      makeRoom();
//      makeHallways();
//        randomly();
   randomFigure();




    }

    public void generateRoom() {

//
//        int w = random.nextInt(3,17);
//        int h = random.nextInt(2,4);

//        int door = random.nextInt(0,w);
//
//        for (int x = 0; x <= w; x++){
//
//            map[currX + x][currY] = Tileset.WALL;
//            map[currX + x][currY + h] = Tileset.WALL;
//            if (door == x){
//                map[currX + x][currY + h]  = Tileset.FLOOR;
//            }
//
//        }
//
//        for (int x = 0; x <= h; x++){
//
//            map[currX ][currY + x] = Tileset.WALL;
//            map[currX + w ][currY + x] = Tileset.WALL;
//
//            if (map[currX ][currY + x] == Tileset.WALL ) {
//
//            } else if (map[currX + w ][currY + x] == Tileset.WALL){
//                map[currX + w ][currY + x] = Tileset.FLOOR;
//            }
//
//        }

//        if (currY + h > MAP_WIDTH) {
//            currX += 10;
//        }


    }


    public void makeHallways() {
        int w = random.nextInt(3,4);
        int h = random.nextInt(2,9);


        int door = random.nextInt(0,w);

        for (int x = 0; x <= h; x++){

            map[nextX][nextY + x] = Tileset.WALL;
            map[nextX + w ][nextY + x] = Tileset.WALL;

            if (map[nextX ][nextY + x] == Tileset.WALL ) {

            } else if (map[nextX+ w ][nextY + x] == Tileset.WALL){

            }

        }

        for (int x = 0; x <= w; x++){

            map[nextX + x][nextY] = Tileset.WALL;
            map[nextX + x][nextY + h] = Tileset.WALL;
            if (door == x){
                map[nextX + x][nextY + h]  = Tileset.FLOOR;
                nextX += x;
                nextY += h;
            }

        }



    }

    public void makeRoom() {
        int w = random.nextInt(4,13);
        int h = random.nextInt(4,12);


        int door = random.nextInt(0, w);



        for (int x = 0; x <= h; x++){

            if (map[nextX ][nextY + x] != Tileset.FLOOR) {
                map[nextX ][nextY + x] = Tileset.WALL;
            }

            if (map[nextX + w ][nextY+ x] != Tileset.FLOOR) {
                map[nextX + w ][nextY+ x] = Tileset.WALL;
            }


            if (map[nextX ][nextY + x] == Tileset.WALL ) {

            } else if (map[nextX + w ][nextY + x] == Tileset.WALL){

            }

        }

        for (int x = 0; x <= w; x++){
            if (map[nextX + x][nextY] != Tileset.FLOOR){
                map[nextX + x][nextY] = Tileset.WALL;
            }
            if ( map[nextX+ x][nextY+ h] != Tileset.FLOOR){
                map[nextX + x][nextY+ h] = Tileset.WALL;
            }

            if (door == x){
                map[nextX+ x][nextY + h] = Tileset.FLOOR;
                nextX += x;
                nextY += h;
            }

        }

//        currX += w;
        nextY += h ;

    }


    public void randomly() {
        for (int row = 2; row < MAP_HEIGHT - 2; row++) {
            for (int col = 2; col < MAP_WIDTH - 2; col++) {
                map[row][col] = Tileset.WALL;
            }
        }

        int w;
        int h;

        for (int row = 2; row < MAP_HEIGHT - 2; row++) {
            for (int col = 2; col < MAP_WIDTH - 2; col++) {
                h = random.nextInt(3,MAP_HEIGHT - 3 );
                w = random.nextInt(3,MAP_WIDTH - 3);
                if (col != row){
                    map[w][h] = Tileset.FLOOR;
                }
                if (map[w][h] == Tileset.WALL && map[w+1][h] == Tileset.WALL && map[w][h+1] == Tileset.WALL){
                    map[w][h] = Tileset.WALL;
                }
                if((row < 4 || col < 4) && col != row) {
                    map[w][h] = Tileset.FLOOR;
                }
            }
        }

        while(true) {
            w = random.nextInt(3,MAP_WIDTH - 3);
            h = random.nextInt(3,MAP_HEIGHT - 3 );
            if (map[w][h] == Tileset.WALL && map[w+ 1][h+ 1] != Tileset.WALL) {
                map[w][h] = Tileset.LOCKED_DOOR;
                break;
            }
        }

    }

    public void randomFigure(){
        int w;
        int h;

        for (int row = 2; row < MAP_HEIGHT - 2; row++) {
            for (int col = 2; col < MAP_WIDTH - 2; col++) {

                   buildRandomRoom(row, col);
            }
        }
    }

    public void buildRandomRoom(int x, int y){
        int w = random.nextInt(2,4);
        int h = random.nextInt(2,7);

        for(int i = 0; i <= w; i++){
            for(int j = 0; j <= h; j++){
                w = random.nextInt(5,6);
                h = random.nextInt(2,3);
            if (x % 5 == 0 && x + i < MAP_WIDTH) {
                map[x+i][y] = Tileset.FLOOR;
            }

                if (y % 5 == 0 &&  i < MAP_WIDTH) {
                    map[i][j] = Tileset.FLOOR;
                }
//                if(i ==j){
//                    buildCorridor(x,y);
//                }


            }
        }

    }

    public void buildCorridor(int x, int y){
        int w = random.nextInt(0,4);
        int h = random.nextInt(0,4);

        for(int i = w; i >= 0; i--){
            for(int j = h; j >= 0; j--) {
                map[i][j] = Tileset.FLOOR;
            }
            }
    }
    public TETile[][] getTiles() {
            return map;
    }



    public static void main(String[] args) {
        // Change these parameters as necessary
        int width = 50;
        int height = 50;
        int holeSize = 3;

        RandomWorldGenerator knightWorld = new RandomWorldGenerator(width, height, 46);

        knightWorld.generate();
        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        ter.renderFrame(knightWorld.getTiles());

    }



}

