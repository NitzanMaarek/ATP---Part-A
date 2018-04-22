package algorithms.mazeGenerators;

public class Maze {
    private int[][] maze;

    public Maze(int x, int y){
        maze = new int[x][y];
    }

    public Maze(int[][] array) {
        maze = array;
    }

    public void printMaze(){
        for (int i = 0; i < maze.length; i++){
            for (int j = 0; j < maze[i].length; j++){
                if (j == maze[i].length - 1){
                    System.out.println(String.valueOf(maze[i][j]) + ")");
                }
                else if (j == 0){
                    System.out.print("(" + String.valueOf(maze[i][j]) + ", ");
                }
                else {
                    System.out.print(String.valueOf(maze[i][j]) + ", ");
                }
            }
        }
    }
}
