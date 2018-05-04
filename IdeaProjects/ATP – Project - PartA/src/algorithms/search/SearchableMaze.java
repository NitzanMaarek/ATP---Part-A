package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class SearchableMaze implements ISearchable {
    private HashMap<Integer, Integer[]> adjacencyList;
    private HashSet<Integer> nodesIDs;
    private Maze maze;
    private int[][] m_Board;
    private int m_Height, m_Width;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
        adjacencyList = new HashMap<Integer, Integer[]>();
        this.m_Board = maze.getBoard();
        nodesIDs = new HashSet<Integer>();
        this.m_Height = maze.getHeight();
        this.m_Width = maze.getWidth();
        createAdjacencyListWithDiagonal();
    }

    public HashMap<Integer, Integer[]> getAdjacencyList() {
        return adjacencyList;
    }

    public int getHeight() {
        return m_Height;
    }

    public int getWidth() {
        return m_Width;
    }

    public Maze getMaze() {
        return maze;
    }

    /**
     * Function checks all possible states from a given states
     * @param givenState - obvious...
     * @return - array of possible states.
     */
    public MazeState[] getAllPossibleStates(MazeState givenState){
        int counter = 0;
        if(!adjacencyList.containsKey(getCellID(givenState.getStatePosition().getRowIndex(), givenState.getStatePosition().getColumnIndex()))){
            return null;
        }
        int numOfStates = adjacencyList.get(getCellID(givenState.getStatePosition().getRowIndex(), givenState.getStatePosition().getColumnIndex())).length;
        if(numOfStates <= 0){   //no neighbors.
            return null;
        }
        MazeState[] ans = new MazeState[numOfStates];
        int givenStateHashed = getCellID(givenState.getStatePosition().getRowIndex(), givenState.getStatePosition().getColumnIndex());
        for (int iNeighbor: adjacencyList.get(givenStateHashed)) {
            if(!fromIntToPosition(iNeighbor, m_Width).equals(givenState.getStatePosition())) {
                ans[counter] = new MazeState(fromIntToPosition(iNeighbor, m_Width));
                counter++;
            }
        }
        ans = setPossibleStatesCost(ans, givenStateHashed);
        return ans;
    }

    /**
     * Sets the cost for each possible MazeState for the current node. diagonal is 5 and straight is 10 since diagonal will ALWAYS be shorter in a way
     * and if the diagonal is not shorter and is off track, it can come back to the same route using another diagonal with the same number of steps.
     * @param possibleStates
     * @param currentNodeHashed - The current node we're setting its' possible states.
     * @return - Array of MazeStates with costs in each MazeState.
     */
    private MazeState[] setPossibleStatesCost(MazeState[] possibleStates, int currentNodeHashed){
        Position currentPosition = fromIntToPosition(currentNodeHashed, maze.getWidth());   //current state position
        Position iPosition;
        for (MazeState iMazeState: possibleStates) {        //foreach state in possible states
            iPosition = iMazeState.getStatePosition();
            if(Math.abs(iPosition.getRowIndex() - currentPosition.getRowIndex()) != 0 && Math.abs(iPosition.getColumnIndex() - currentPosition.getColumnIndex()) != 0 ){
                iMazeState.setStateCost(10); //If the next maze state is diagonal, set its' cost to 5.
            }
            else{
                iMazeState.setStateCost(10); //If not diagonal cost is 10.
            }
        }
        return possibleStates;
    }

    /**
     * We want to decrypt the hash code for each node to know its' position: Hash = ((RowIndex * MazeWidth) + ColumnIndex)
     * @param positionHash - Node's hash code
     * @param mazeWidth - The mazes width
     * @return - Position of node.
     */
    private Position fromIntToPosition(int positionHash, int mazeWidth){
        int row = positionHash / mazeWidth;
        int column = positionHash - mazeWidth*row;      //same as %
        return new Position(row, column);
    }

    public Integer getCellID(int y, int x) {
        return (y * m_Width) + x;
    }

    private Integer[] getNodeNeighbors(int y, int x) {
        LinkedList<Integer> neighborsList = new LinkedList<Integer>();
        neighborsList.add(getCellID(y, x));
        int delta_x_minus = 2;
        int delta_x_plus = 2;
        int delta_y = 2;

        if (x == m_Width - 1){
            delta_x_minus = 1;
            delta_x_plus = 1;
            delta_y = 1;
        }
        else if (x == m_Width - 2){
            delta_x_plus = 1;
        }

        if (x >= 2 && m_Board[y][x - delta_x_minus] == 0 && m_Board[y][x - delta_x_minus + 1] == 0){
            Integer newID = getCellID(y, x - delta_x_minus);
            neighborsList.add(newID);
            nodesIDs.add(newID);
        }

        if (x <= m_Width - 2 && m_Board[y][x + delta_x_plus] == 0 && m_Board[y][x + delta_x_plus - 1] == 0){
            Integer newID = getCellID(y, x + delta_x_plus);
            neighborsList.add(newID);
            nodesIDs.add(newID);
        }

        if (y >= 2 && m_Board[y - delta_y][x] == 0 && m_Board[y - delta_x_minus + 1][x] == 0){
            Integer newID = getCellID(y - delta_x_minus, x);
            neighborsList.add(newID);
            nodesIDs.add(newID);
        }

        if (y < m_Height - 2 && m_Board[y + delta_y][x] == 0 && m_Board[y + delta_y - 1][x] == 0){
            Integer newID = getCellID(y + delta_y, x);
            neighborsList.add(newID);
            nodesIDs.add(newID);
        }

        return neighborsList.toArray(new Integer[neighborsList.size()]);
    }

    private void checkAndAddNode(int nodeID) {
        if (!nodesIDs.contains(nodeID)){
            nodesIDs.add(nodeID);
        }
    }

    private void createAdjacencyList() {
        for (int i = 0; i < m_Width; i++) {
            for (int j = 0; j < m_Height; j++) {
                if (m_Board[j][i] == 0) {
                    if (!(nodesIDs.contains(getCellID(j, i - 1)) || nodesIDs.contains(getCellID(j, i + 1)) ||
                            nodesIDs.contains(getCellID(j - 1, i)) || nodesIDs.contains(getCellID(j + 1, i)))
                            || i == m_Width - 1) {

                        int nodeID = getCellID(j, i);
                        checkAndAddNode(nodeID);
                        Integer[] neighbors = getNodeNeighbors(j, i);
                        adjacencyList.put(nodeID, neighbors);
                        }
                    }
                }
            }
        }

    private void createAdjacencyListWithDiagonal() {
        for (int i = 0; i < m_Width; i++) {
            for (int j = 0; j < m_Height; j++) {
                if (m_Board[j][i] == 0) {
                    int nodeID = getCellID(j, i);
                    checkAndAddNode(nodeID);
                    LinkedList<Integer> neighborsList = new LinkedList<Integer>();
                    for (int x = -1; x < 2; x++){ // Check all the adjacent cells
                        for (int y = -1; y < 2; y++)
                            if (x != 0 || y != 0) {
                                if (i + x >= 0 && j + y >= 0 && j + y < m_Board.length && i + x < m_Board[0].length && m_Board[j + y][i + x] == 0) {
                                    if (x != 0 && y != 0) { // If its diagonal to the original cell
                                        if (m_Board[j + y][i] == 0 || m_Board[j][i + x] == 0){ // If one of the adjacent cells are 0
                                            neighborsList.add(getCellID(j + y,i + x));
                                        }
                                    }
                                    else { // If its not diagonal to the original cell
                                        neighborsList.add(getCellID(j + y,i + x));
                                    }
                                }
                            }
                    }
                    Integer[] neighbors = neighborsList.toArray(new Integer[neighborsList.size()]);
                    adjacencyList.put(nodeID, neighbors);
                }
            }
        }
    }

    public void printList(){
        for (Integer key :
             adjacencyList.keySet()) {
            System.out.print(key + ": ");
            Integer[] neighors = adjacencyList.get(key);
            for (int i = 0; i < neighors.length; i++) {
             if (i == neighors.length - 1) {
                System.out.println(neighors[i].toString());
            }
            else {
                System.out.print(neighors[i].toString() + ", ");
                }
            }
        }
    }

}