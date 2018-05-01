package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.LinkedList;

public class BestFirstSearch extends BreadthFirstSearch implements  ISearchingAlgorithm {

    /**
     * Numbers of nodes that we've looked at during the search process.
     * @return - the number. duh.
     */
    @Override
    public int getNumberOfNodesEvaluated() {
        return numOfEvaluatedNodes;
    }

    /**
     *
     * @param searchable
     * @return
     */
    @Override
    public Solution solve(ISearchable searchable) {
        SearchableMaze domain = (SearchableMaze) searchable;
        super.initializeMembers(domain);
        super.scanSearchableMaze(domain);
        Solution ans = traceSolution(domain.getMaze().getGoalPosition());
        ans = fixDiagonalPath(ans);
        return ans;
    }


    /**
     * This function fixes diagonal paths for shortest path.
     * @param givenSolution - Solution AFTER the jumps of 2 was fixed!
     * @return - correct solution with diagonal paths.
     */
    private Solution fixDiagonalPath(Solution givenSolution){
        LinkedList<AState> givenPath = new LinkedList<>(givenSolution.getSolutionPath());
        LinkedList<AState> fastestPath = new LinkedList<>();
        Position currPos, nextPos;
        MazeState currMazeState = null, nextMazeState = null;
        for(int i=0; i < givenPath.size() - 2; i++){
            currMazeState = (MazeState) givenPath.get(i);
            nextMazeState = (MazeState) givenPath.get(i + 2);
            currPos = currMazeState.getStatePosition();
            nextPos = nextMazeState.getStatePosition();
            if(currPos.getRowIndex() != nextPos.getRowIndex() && currPos.getColumnIndex() != nextPos.getColumnIndex()){
                givenPath.remove(i + 1);    //Just remove the step in between.
            }
        }
        return new Solution(givenPath);
    }
    @Override
    public String getName() {
        return "Best First Search.";
    }
}
