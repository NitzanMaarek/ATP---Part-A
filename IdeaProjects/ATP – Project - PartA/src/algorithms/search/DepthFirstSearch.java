package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;


public class DepthFirstSearch extends ASearchingAlgorithm implements ISearchingAlgorithm {

    int[][] visited;    // 0 - White, 1 - Grey and 2 - Black.
    boolean scanFinished = false;

    @Override
    public int getNumberOfNodesEvaluated() {
        return numOfEvaluatedNodes;
    }

    /**
     * This function solves the given Searchable object.
     * @param searchable - and object that can be searched
     * @return - Solution object.
     */
    @Override
    public Solution solve(ISearchable searchable) {
        SearchableMaze domain = (SearchableMaze) searchable;
        initializeMembers(domain);
        scanSearchableMaze(domain);
        Solution ans = traceSolution(domain.getMaze().getGoalPosition());
        return ans;
    }

    /**
     * Since our constructor is required to have no arguments here we initialize members that require some args.
     * @param domain - Searchable maze.
     */
    private void initializeMembers(SearchableMaze domain){
        solutionGrid = new Position[domain.getHeight()][domain.getWidth()];
        visited = new int[domain.getHeight()][domain.getWidth()];
        for(int i=0; i < domain.getHeight(); i++){      //Loops to initialize helping matrix.
            for(int j=0; j < domain.getWidth(); j++){
                visited[i][j] = 0;
                solutionGrid[i][j] = new Position();
            }
        }
    }

    @Override
    public String getName() {
        return "Depth First Search.";
    }

    @Override
    protected void scanSearchableMaze(SearchableMaze domain) {
        scanSearchableMaze(domain, domain.getMaze().getStartPosition());
    }

    /**
     * THis function implements the DFS - recursive function.
     * @param domain - The searchable maze to search at
     * @param pos - the current position we are in
     */
    protected void scanSearchableMaze(SearchableMaze domain, Position pos){
        if(!scanFinished) {
            visited[pos.getRowIndex()][pos.getColumnIndex()] = 1;       //Grey
            MazeState[] possibleStates = domain.getAllPossibleStates(new MazeState(pos));
            if(possibleStates == null){ //If there are no neighbors = no possible move states
                visited[pos.getRowIndex()][pos.getColumnIndex()] = 2;   //Black and exit recursive call.
            }
            else {
                for (MazeState someState : possibleStates) {        //For each possible state
                    if (visited[someState.getStatePosition().getRowIndex()][someState.getStatePosition().getColumnIndex()] == 0) {   //If node is white
                        solutionGrid[someState.getStatePosition().getRowIndex()][someState.getStatePosition().getColumnIndex()] = pos;  //Set field "pie" to trace back.
                        numOfEvaluatedNodes++;
                        if (domain.getMaze().getGoalPosition().equals(someState.getStatePosition())) {    //Found goal position.
                            scanFinished = true;
                        }
                        scanSearchableMaze(domain, someState.getStatePosition());
                    }
                    visited[pos.getRowIndex()][pos.getColumnIndex()] = 2;   //Black
                }
            }
        }
    }

}
