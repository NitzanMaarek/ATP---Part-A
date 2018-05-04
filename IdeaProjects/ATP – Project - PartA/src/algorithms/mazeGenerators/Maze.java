package algorithms.mazeGenerators;


import algorithms.search.MazeState;
import algorithms.search.Solution;

public class Maze {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private int[][] m_Board;
    private int m_Width, m_Height;
    private Position m_StartPosition, m_GoalPosition;

    public Maze(int width, int height, Position startPosition, Position goalPosition){
        m_Board = new int[width][height];
        m_Width = width;
        m_Height = height;
        m_StartPosition = startPosition;
        m_GoalPosition = goalPosition;
    }

    public Maze(int[][] array, Position startPosition, Position goalPosition) {
        m_Board = array;
        m_Width = m_Board[0].length;
        m_Height = m_Board.length;
        m_StartPosition = startPosition;
        m_GoalPosition = goalPosition;
    }

    public Position getStartPosition(){
        return m_StartPosition;
    }

    public Position getGoalPosition(){
        return m_GoalPosition;
    }

    /**
     * Prints the maze using chars instead of 0's and 1's for easier observation.
     */
    public void printMazeAlternative(){
        char WALL_CHAR = '@';
        char PASSAGE_CHAR = ' ';
        final StringBuffer b = new StringBuffer();
        for (int y = 0; y < m_Width + 2; y++)
            b.append(WALL_CHAR);
        b.append('\n');
        for (int x = 0; x < m_Height; x++) {
            b.append(WALL_CHAR);
            for (int y = 0; y < m_Width; y++) {
                if (m_Board[x][y] == 1) {
                    b.append(WALL_CHAR);
                } else if (m_Board[x][y] == 0) {
                    b.append(PASSAGE_CHAR);
                } else {
                    b.append(m_Board[x][y]);
                }
//                b.append(grid[x][y] == 1 ? WALL_CHAR : PASSAGE_CHAR);
            }
            b.append(WALL_CHAR);
            b.append('\n');
        }
        for (int y = 0; y < m_Width + 2; y++)
            b.append(WALL_CHAR);
        b.append('\n');
        System.out.println(b.toString());
    }

    /**
     * prints the solution in yellow and @ in a graphic looking maze.
     * @param solutionToPrint
     */
    public void printMazeAlternative(Solution solutionToPrint){
        char WALL_CHAR = '▓';
        char PASSAGE_CHAR = ' ';
        final StringBuffer b = new StringBuffer();
        for (int y = 0; y < m_Width + 2; y++)
            b.append(WALL_CHAR);
        b.append('\n');
        for (int x = 0; x < m_Height; x++) {
            b.append(WALL_CHAR);
            for (int y = 0; y < m_Width; y++) {
                if (m_Board[x][y] == 1) {
                    b.append(WALL_CHAR);
                } else if (m_Board[x][y] == 0) {
                    if(solutionToPrint.getSolutionPath().contains(new MazeState(new Position(x, y)))){  //If current position is part of solution, color cell in yellow.
                        b.append(ANSI_YELLOW + "@" + ANSI_RESET);
                    }
                    else{
                        b.append(PASSAGE_CHAR);
                    }
                } else {
                    b.append(m_Board[x][y]);
                }
//                b.append(grid[x][y] == 1 ? WALL_CHAR : PASSAGE_CHAR);
            }
            b.append(WALL_CHAR);
            b.append('\n');
        }
        for (int y = 0; y < m_Width + 2; y++)
            b.append(WALL_CHAR);
        b.append('\n');
        System.out.println(b.toString());
    }


        public int[][] getBoard() {
        return m_Board;
    }

    public int getWidth() {
        return m_Width;
    }

    public int getHeight() {
        return m_Height;
    }

    /**
     * Prints the maze by representing 0's as passages and 1's as walls.
     */
    public void print(){
        for (int i = 0; i < m_Board.length; i++){
            for (int j = 0; j < m_Board[i].length; j++){
                if (j == m_Board[i].length - 1){
                    System.out.println(String.valueOf(m_Board[i][j]) + ")");
                }
                else if (j == 0){
                    System.out.print("(" + String.valueOf(m_Board[i][j]) + ", ");
                }
                else {
                    System.out.print(String.valueOf(m_Board[i][j]) + ", ");
                }
            }
        }
    }
}
