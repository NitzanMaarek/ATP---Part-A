package algorithms.mazeGenerators;
import java.util.Random;

import java.util.Random;

public class SimpleMazeGenerator
    extends AMazeGenerator {
    @Override
    public Maze generate(int x, int y) {

        if (x < 5){
            x = 5;
        }

        if (y < 5){
            y = 5;
        }

        int[][] mazeArray = new int[y][x];
        Random random = new Random();

        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++) {
                if (i == 0 || j == 0 || i == x - 1 || j == y -1){
                    mazeArray[j][i] = 1;
                }
                else {
                    mazeArray[j][i] = random.nextInt(2);
                }
            }
        }

        int entrance_x, entrance_y, exit_x, exit_y, curr_x, curr_y;
        entrance_x = 0;
        entrance_y = random.nextInt(y - 1) + 1;
        exit_x = x - 1;
        exit_y = random.nextInt(y - entrance_y) + entrance_y;
        curr_x = entrance_x;
        curr_y = entrance_y;
        mazeArray[entrance_y][entrance_x] = 0;
        mazeArray[exit_y][exit_x] = 0;

        while (curr_x < exit_x || curr_y < exit_y) {
            int downOrRight = random.nextInt(2);
            if (curr_x == 0){
                curr_x++;
                mazeArray[curr_y][curr_x] = 0;
            }
            else if (curr_y == y - 2 && curr_x != x - 1){
                curr_x++;
                mazeArray[curr_y][curr_x] = 0;
            }
            else if (curr_x < exit_x && (downOrRight == 0 || curr_y == exit_y)){
                curr_x++;
                mazeArray[curr_y][curr_x] = 0;
            }
            else {
                curr_y++;
                mazeArray[curr_y][curr_x] = 0;
            }
        }
        Position entrance = new Position(entrance_y, entrance_x);
        Position exit = new Position(exit_y, exit_x);
        
        Maze maze = new Maze(mazeArray, entrance, exit);
        return maze;
    }


}
