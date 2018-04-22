package algorithms.mazeGenerators;
import java.util.Random;

import java.util.Random;

public class SimpleMazeGenerator
    extends AMazeGenerator {
    @Override
    public Maze generate(int x, int y) {
        int[][] mazeArray = new int[x][y];
        Random random = new Random();

        for (int i = 0; i < x; i++){
            for (int j = 0; j < y; j++) {
                if (i == 0 || j == 0 || i == x - 1 || j == y -1){
                    mazeArray[i][j] = 1;
                }
                else {
                    mazeArray[i][j] = random.nextInt(2);
                }
            }
        }

        int entrance_x, entrance_y, exit_x, exit_y, curr_x, curr_y;
        entrance_x = 0;
        entrance_y = random.nextInt(y - 1) + 1;
//        exit_x = random.nextInt(x - entrance_x) + entrance_x;
        exit_x = x - 1;
        exit_y = random.nextInt(x - entrance_y) + entrance_y;
        curr_x = entrance_x;
        curr_y = entrance_y;
        mazeArray[entrance_x][entrance_y] = 0;
        mazeArray[exit_x][exit_y] = 0;

        while (curr_x < exit_x || curr_y < exit_y) {
            int downOrRight = random.nextInt(2);
            if (curr_x == 0){
                curr_x++;
                mazeArray[curr_x][curr_y] = 0;
            }
            else if (curr_y == y - 2 && curr_x != x - 1){
                curr_x++;
                mazeArray[curr_x][curr_y] = 0;
            }
            else if (curr_x < exit_x && (downOrRight == 0 || curr_y == exit_y)){
                curr_x++;
                mazeArray[curr_x][curr_y] = 0;
            }
            else {
                curr_y++;
                mazeArray[curr_x][curr_y] = 0;
            }
        }

        Maze maze = new Maze(mazeArray);
        return maze;
    }


}
