package algorithms.mazeGenerators;

public class Maze {
    private int[][] m_Board;
    private int m_Width, m_Height;
    private Position m_StartPosition, m_GoalPosition;

    public Maze(int x, int y, Position startPosition, Position goalPosition){
        m_Board = new int[x][y];
        m_Width = x;
        m_Height = y;
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

    public Position getM_StartPosition(){
        return m_StartPosition;
    }

    public Position getM_GoalPosition(){
        return m_GoalPosition;
    }

    /**
     * Prints the maze using chars instead of 0's and 1's for easier observation.
     */
    public void printMazeAlternative(){
        char wall_char = '▓';
        char passage_char = ' ';
        final StringBuffer b = new StringBuffer();
        for (int x = 0; x < m_Width + 2; x++ )
            b.append( wall_char );
        b.append( '\n' );
        for (int y = 0; y < m_Height; y++ ){
            b.append( wall_char );
            for (int x = 0; x < m_Width; x++ )
                b.append( m_Board[x][y] == 1 ? wall_char : passage_char );
            b.append( wall_char );
            b.append( '\n' );
        }
        for (int x = 0; x < m_Width + 2; x++ )
            b.append( wall_char );
        b.append( '\n' );
        System.out.println(b.toString());
    }

    /**
     * Prints the maze by representing 0's as passages and 1's as walls.
     */
    public void printMaze(){
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
