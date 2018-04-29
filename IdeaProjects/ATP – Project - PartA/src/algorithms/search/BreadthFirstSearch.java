package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.HashMap;
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
     * @param searchable - Searchable maze
     * @return - Solution
     */
    @Override
    public Solution solve(ISearchable searchable) {
        SearchableMaze domain = (SearchableMaze) searchable;
        initializeMembers(domain);
        Solution ans = traceSolution(domain.getMaze().getGoalPosition());
        return ans;
    }
//
//    /**
//     * function to trace back solution from goal position.
//     * @param goalPos - maze goal position
//     * @return - a solution instance.
//     */
//    protected Solution traceSolution(Position goalPos){
//        Position currentNode = goalPos;
//        LinkedList<AState> listSolutionStates = new LinkedList<>();
//        listSolutionStates.add(new MazeState(currentNode)); //Adding to list the goal position.
//        while(solutionGrid[currentNode.getRowIndex()][currentNode.getColumnIndex()] != null){
//            currentNode = solutionGrid[currentNode.getRowIndex()][currentNode.getColumnIndex()];    //Next node in path
//            listSolutionStates.add(new MazeState(currentNode));     //Add that node to list.
//        }
//        return new Solution(reverseLinkedList(listSolutionStates));
//    }


    /**
     * This function implements BFS.
     * @param domain - Searchable maze.
     */
    protected void scanSearchableMaze(SearchableMaze domain){
        int currentNode = 0;
        numOfEvaluatedNodes = 1;
        Position currentPosition;
        HashMap <Integer, Integer[]> adjacencyList = new HashMap<>();
//        LinkedList<Integer[]> adjacencyList = domain.getAdjacencyList();
        visited[0][0] = true;
        bfsQueue.add(0);
        while(!bfsQueue.isEmpty()){     //As long as we have nodes to check.
            currentNode = bfsQueue.remove();
            for (MazeState someState: domain.getAllPossibleStates(new MazeState(fromIntToPosition(currentNode, domain.getWidth())))) {   //Foreach node from currentNode's neighbors  //NEED HERE getAllPossibleStates
                numOfEvaluatedNodes++;
                currentPosition = someState.getStatePosition();
                if(!visited[currentPosition.getRowIndex()][currentPosition.getColumnIndex()]){
                    visited[currentPosition.getRowIndex()][currentPosition.getColumnIndex()] = true;    //Mark as visited.
                    solutionGrid[currentPosition.getRowIndex()][currentPosition.getColumnIndex()] = fromIntToPosition(currentNode, domain.getWidth());  //Set path
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
    private void initializeMembers(SearchableMaze domain){
        solutionGrid = new Position[domain.getHeight()][domain.getWidth()];
        visited = new boolean[domain.getHeight()][domain.getWidth()];
        bfsQueue = new LinkedList<>();
        for(int i=0; i < domain.getHeight(); i++){      //Loops to initialize helping matrix.
            for(int j=0; j < domain.getWidth(); j++){
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
