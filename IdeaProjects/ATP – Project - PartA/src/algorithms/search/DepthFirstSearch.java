package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.LinkedList;
import java.util.Queue;

public class DepthFirstSearch extends ASearchingAlgorithm implements ISearchingAlgorithm {

    int[][] visited;    // 0 - White, 1 - Grey and 2 - Black.

    @Override
    public int getNumberOfNodesEvaluated() {
        return numOfEvaluatedNodes;
    }

    @Override
    public Solution solve(ISearchable domain) {
        initializeMembers(domain);

    }

    /**
     * Since our constructor is required to have no arguments here we initialize members that require some args.
     * @param domain - Searchable maze.
     */
    private void initializeMembers(ISearchable domain){
        solutionGrid = new Position[domain.getMazeHeight()][domain.getMazeWidth()];
        visited = new boolean[domain.getMazeHeight()][domain.getMazeWidth()];
        for(int i=0; i < domain.getMazeHeight(); i++){      //Loops to initialize helping matrix.
            for(int j=0; j < domain.getMazeWidth(); j++){
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
    protected void scanSearchableMaze(ISearchable domain) {
        scanSearchableMaze(domain, domain.getMaze().getStartPosition());
    }

    protected void scanSearchableMaze(ISearchable domain, Position pos){
        visited[pos.getRowIndex()][pos.getColumnIndex()] = 1;       //Grey
        for (MazeState someState: domain.getAllPossibleStates(pos)) {
            if(visited[someState.getStatePosition().getRowIndex()][someState.getStatePosition().getColumnIndex()] == 0){
                solutionGrid[someState.getStatePosition().getRowIndex()][someState.getStatePosition().getColumnIndex()] = pos;
                scanSearchableMaze(domain,someState.getStatePosition());
            }
            visited[pos.getRowIndex()][pos.getColumnIndex()] = 2;   //Black
        }
    }

    @Override
    protected Solution traceSolution(Position goalPos) {
        return null;
    }
}
