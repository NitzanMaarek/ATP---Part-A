package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm implements ISearchingAlgorithm {

    boolean[][] visited;    // F - not visited, T - visited.
    Queue<Integer> bfsQueue;   //Queue for neighbors.

    @Override
    public int getNumberOfNodesEvaluated() {
        return numOfEvaluatedNodes;
    }

    /**
     * Solves the given searchable maze
     * @param domain - Searchable maze
     * @return - Solution
     */
    @Override
    public Solution solve(ISearchable domain) {
        initializeMembers(domain);
        Solution ans = traceSolution(domain.getMaze().getGoalPosition());
        return ans;
    }

    /**
     * function to trace back solution from goal position.
     * @param goalPos - maze goal position
     * @return - a solution instance.
     */
    protected Solution traceSolution(Position goalPos){
        Position currentNode = goalPos;
        LinkedList<AState> listSolutionStates = new LinkedList<>();
        listSolutionStates.add(new MazeState(currentNode)); //Adding to list the goal position.
        while(solutionGrid[currentNode.getRowIndex()][currentNode.getColumnIndex()] != null){
            currentNode = solutionGrid[currentNode.getRowIndex()][currentNode.getColumnIndex()];    //Next node in path
            listSolutionStates.add(new MazeState(currentNode));     //Add that node to list.
        }
        return new Solution(reverseLinkedList(listSolutionStates));
    }

    /**
     * Since we trace the solution from goal to start we need to reverse the state list.
     * @param reverseMe - LinkedList to reverse.
     * @return - reversed LinkedList.
     */
    private LinkedList<AState> reverseLinkedList(LinkedList<AState> reverseMe){
        LinkedList<AState> ans = new LinkedList<>();
        for(int i = reverseMe.size() - 1; i >= 0; i--){
            ans.add(reverseMe.get(i));
        }
        return ans;
    }

    /**
     * This function implements BFS.
     * @param domain - Searchable maze.
     */
    protected void scanSearchableMaze(ISearchable domain){
        int currentNode = 0;
        numOfEvaluatedNodes = 1;
        Position currentPosition;
        LinkedList<Integer[]> adjacencyList = domain.getSearchableMaze();
        visited[0][0] = true;
        bfsQueue.add(0);
        while(!bfsQueue.isEmpty()){     //As long as we have nodes to check.
            currentNode = bfsQueue.remove();
            for (int iNode: adjacencyList.get(currentNode)) {   //Foreach node from currentNode's neighbors  //NEED HERE getAllPossibleStates
                numOfEvaluatedNodes ++;
                currentPosition = fromIntToPosition(iNode, domain.getMazeWidth());
                if(!visited[currentPosition.getRowIndex()][currentPosition.getColumnIndex()]){
                    visited[currentPosition.getRowIndex()][currentPosition.getColumnIndex()] = true;    //Mark as visited.
                    solutionGrid[currentPosition.getRowIndex()][currentPosition.getColumnIndex()] = fromIntToPosition(currentNode, domain.getMazeWidth());  //Set path
                    if(currentPosition.equals(domain.getMaze().getGoalPosition())){  //If we've reached goal position.
                        return; //quit the search.
                    }//if
                }//if
            }//for
        }//while
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

    /**
     * Since our constructor is required to have no arguments here we initialize members that require some args.
     * @param domain - Searchable maze.
     */
    private void initializeMembers(ISearchable domain){
        solutionGrid = new Position[domain.getMazeHeight()][domain.getMazeWidth()];
        visited = new boolean[domain.getMazeHeight()][domain.getMazeWidth()];
        bfsQueue = new LinkedList<>();
        for(int i=0; i < domain.getMazeHeight(); i++){      //Loops to initialize helping matrix.
            for(int j=0; j < domain.getMazeWidth(); j++){
                visited[i][j] = false;
                solutionGrid[i][j] = new Position();
            }
        }
    }

    /**
     * get name of this scan
     * @return - Name of class.
     */
    @Override
    public String getName() {
        return "Breadth First Search.";
    }
}
