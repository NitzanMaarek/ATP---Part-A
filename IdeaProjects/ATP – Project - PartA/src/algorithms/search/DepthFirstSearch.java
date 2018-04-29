package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;


public class DepthFirstSearch extends ASearchingAlgorithm implements ISearchingAlgorithm {

    int[][] visited;    // 0 - White, 1 - Grey and 2 - Black.

    @Override
    public int getNumberOfNodesEvaluated() {
        return numOfEvaluatedNodes;
    }

    @Override
    public Solution solve(ISearchable searchable) {
        SearchableMaze domain = (SearchableMaze) searchable;
        initializeMembers(domain);
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

    protected void scanSearchableMaze(SearchableMaze domain, Position pos){
        visited[pos.getRowIndex()][pos.getColumnIndex()] = 1;       //Grey
        for (MazeState someState: domain.getAllPossibleStates(new MazeState(pos))) {
            if(visited[someState.getStatePosition().getRowIndex()][someState.getStatePosition().getColumnIndex()] == 0){
                solutionGrid[someState.getStatePosition().getRowIndex()][someState.getStatePosition().getColumnIndex()] = pos;
                scanSearchableMaze(domain,someState.getStatePosition());
            }
            visited[pos.getRowIndex()][pos.getColumnIndex()] = 2;   //Black
        }
    }

}
