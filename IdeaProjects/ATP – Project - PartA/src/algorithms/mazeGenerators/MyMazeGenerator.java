package algorithms.mazeGenerators;

import java.util.ArrayList;
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
        if(width < 5 && height < 5){        //If given bad dimensions, set default to 5x5.
            width = height = 5;
        }
        int grid[][] = new int[height][width];
        for(int i=0; i<grid.length; i++){               //Initialize grid with walls(=1)
            for(int j=0; j<grid[i].length; j++){
                grid[i][j] = 1;
            }
        }
        final LinkedList<int[]> frontiers = new LinkedList<>();
        final Random roulette = new Random();
        int x = 0;      //Setting starting position
        int y = 0;          // as (0,0)
        frontiers.add(new int[]{x,y,x,y});
        System.out.println("Starting Position is: " + x + ", " + y);
        while ( !frontiers.isEmpty() ){
            final int[] f = frontiers.remove( roulette.nextInt( frontiers.size() ) );
            x = f[2];
            y = f[3];
            if ( grid[x][y] == 1 )
            {
                grid[f[0]][f[1]] = grid[x][y] = 0;
                if ( x >= 2 && grid[x-2][y] == 1 )
                    frontiers.add( new int[]{x-1,y,x-2,y} );
                if ( y >= 2 && grid[x][y-2] == 1 )
                    frontiers.add( new int[]{x,y-1,x,y-2} );
                if ( x < height - 2 && grid[x+2][y] == 1 )
                    frontiers.add( new int[]{x+1,y,x+2,y} );
                if ( y < width -2 && grid[x][y+2] == 1 )
                    frontiers.add( new int[]{x,y+1,x,y+2} );
            }
        }
        fixMazeWalls(grid, wallIndicator(grid));    //Fix any full walls on edges.
        Position goalPosition = getGoalPositions(grid);    //Choose randomly a candidate for goal position.
        Maze ansMaze = new Maze(grid, new Position(0, 0), goalPosition);
        return ansMaze;
    }

    /**
     * Checks 25% cells of the most left wall at the bottom for possible goal positions.
     * @param grid - the maze board
     * @return - Goal Position.
     */
    private Position getGoalPositions(int[][] grid) {
        Position ans;
        int newHeight = (int) (0.25 * grid.length);     //25% of height.
        int width = grid[0].length;
        int height = grid.length;
        ArrayList<Position> goalPositions = new ArrayList<>();  //list for possible goal positions.
        Random roulette = new Random();
        for(int i = height - 1; i > height - newHeight - 1; i--){
            if(grid[i][width - 1] == 0){
                goalPositions.add(new Position(i, width - 1));
            }
        }
        ans = goalPositions.get(roulette.nextInt(goalPositions.size()));     //pick random goal position.
        return ans;
    }

    /**
     * Our algorithm may create edges of complete walls, this function detects which walls are they.
     * @param grid - the maze board
     * @return - sums of all walls.
     */
    private int[] wallIndicator(int[][] grid){
        int ans = -1;                   // 0 - Indicates to look for starting position in bottom left corner and goal in top right.  1 - Indicates to look for starting position in top left corner and goal in bottom right.
        int[] wallSum = new int[]{0,0,0,0};
        for(int i=0; i< grid.length; i++){
            wallSum[0] = wallSum[0] +  grid[i][0];                    //Sum all cells on left wall.
            wallSum[1] = wallSum[1] + grid[i][grid[0].length - 1];    //sum all cells on right wall.
        }
        for(int i=0; i < grid[0].length; i++){
            wallSum[2] = wallSum[2] + grid[0][i];            //sum all cells on top wall
            wallSum[3] = wallSum[3] + grid[grid.length - 1][i];     //sum all cells on bottom wall
        }
        return wallSum;
    }

    /**
     * Fixes the edges of complete walls in a way to fit the maze.
     * @param grid - the maze board
     * @param wallSum - array of indicators for edges.
     */
    private void fixMazeWalls(int[][] grid, int[] wallSum){
        Random roulette = new Random();
        for(int i=0; i < grid.length; i++){          //Loop to check and fix right and left walls
            if(wallSum[0] == grid.length && grid[i][0] == 1 && grid[i][1] == 0 ){
                grid[i][0] = roulette.nextInt(2);
            }
            if( wallSum[1] == grid.length && grid[i][grid[0].length - 1] == 1 && grid[i][grid[0].length -2 ] == 0){
                grid[i][grid[0].length - 1] = roulette.nextInt(2);
            }
        }
        for(int i=0; i< grid[0].length; i++){       //Loop to check and fix ceiling and floor.
            if(wallSum[2] == grid[0].length && grid[0][i] == 1 && grid [1][i] == 0){
                grid[0][i] = roulette.nextInt(2);
            }
            if(wallSum[3] == grid[0].length && grid[grid.length - 1][i] == 1 && grid[grid.length -2][i] == 0){
                grid[grid.length - 1][i] = roulette.nextInt(2);
            }
        }
    }



}
