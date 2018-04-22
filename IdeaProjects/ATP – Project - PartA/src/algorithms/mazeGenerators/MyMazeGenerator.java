package algorithms.mazeGenerators;

import java.util.LinkedList;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator  {

    /**
     * Generates maze by using Prim's algorithm
     * @param width - width of the maze
     * @param height - height of the maze
     * @return - Maze object that holds a 2d int array that represents the maze. 0 = Passage, 1 = Wall.
     */
    @Override
    public Maze generate(int width, int height) {
        int grid[][] = new int[width][height];
        Random roulette = new Random();
        for(int i=0; i<grid.length; i++){               //Initialize grid with walls(=1)
            for(int j=0; j<grid[i].length; j++){
                grid[i][j] = 1;
            }
        }
        final LinkedList<int[]> frontiers = new LinkedList<>();
        final Random random = new Random();
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        frontiers.add(new int[]{x,y,x,y});
        System.out.println("Starting Position is: " + x + ", " + y);
        while ( !frontiers.isEmpty() ){
            final int[] f = frontiers.remove( random.nextInt( frontiers.size() ) );
            x = f[2];
            y = f[3];
            if ( grid[x][y] == 1 )
            {
                grid[f[0]][f[1]] = grid[x][y] = 0;
                if ( x >= 2 && grid[x-2][y] == 1 )
                    frontiers.add( new int[]{x-1,y,x-2,y} );
                if ( y >= 2 && grid[x][y-2] == 1 )
                    frontiers.add( new int[]{x,y-1,x,y-2} );
                if ( x < width-2 && grid[x+2][y] == 1 )
                    frontiers.add( new int[]{x+1,y,x+2,y} );
                if ( y < height-2 && grid[x][y+2] == 1 )
                    frontiers.add( new int[]{x,y+1,x,y+2} );
            }
        }
        Maze ansMaze = new Maze(grid);
        return ansMaze;
    }
}
