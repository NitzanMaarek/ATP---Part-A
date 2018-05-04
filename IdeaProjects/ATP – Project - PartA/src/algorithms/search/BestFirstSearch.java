package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Comparator;

public class BestFirstSearch extends BreadthFirstSearch implements  ISearchingAlgorithm {

    Queue<MazeState> bestQueue;

    public BestFirstSearch(){
        bestQueue = new PriorityQueue<>(bfsComperator);
    }

    /**
     * Overriding comparator to compate a MazeState based on its' cost.
     */
    private static Comparator<MazeState> bfsComperator = new Comparator<MazeState>() {
        @Override
        public int compare(MazeState o1, MazeState o2) {
            return (o1.getStateCost() - o2.getStateCost());
        }
    };

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
        scanSearchableMaze(domain);
        Solution ans = traceSolution(domain.getMaze().getGoalPosition());
        return ans;
    }

    /**
     * Best first search uses priority queue based on a MazeState cost to decide which states to pop from queue.
     * @param domain - Searchable maze.
     */
    protected void scanSearchableMaze(SearchableMaze domain){
        MazeState currentState = new MazeState(domain.getMaze().getStartPosition());    //Starting position state.
        numOfEvaluatedNodes = 1;
        Position currentPosition, mazeStartingPosition = domain.getMaze().getStartPosition();
        MazeState[] possibleStates;
        visited[mazeStartingPosition.getRowIndex()][mazeStartingPosition.getColumnIndex()] = true;
        bestQueue.add(currentState);
        while(!bestQueue.isEmpty()){     //As long as we have nodes to check.
            currentState = bestQueue.remove();
            possibleStates = domain.getAllPossibleStates(currentState);
            if(possibleStates == null){
                continue;
            }
            for (MazeState aPossibleState: possibleStates) {   //Foreach node from currentNode's neighbors
                currentPosition = aPossibleState.getStatePosition();
                if(!visited[currentPosition.getRowIndex()][currentPosition.getColumnIndex()]){      //If we visited this position already
                    numOfEvaluatedNodes++;
                    visited[currentPosition.getRowIndex()][currentPosition.getColumnIndex()] = true;    //Mark as visited.
                    solutionGrid[currentPosition.getRowIndex()][currentPosition.getColumnIndex()] = currentState.getStatePosition();  //Set path - the cell that led to this one
                    bestQueue.add(aPossibleState);                                                                                      //was the current state we're in.
                    if(currentPosition.equals(domain.getMaze().getGoalPosition())){  //If we've reached goal position.
                        return; //quit the search.
                    }//if
                }//if
            }//for
        }//while
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

    /**
     * return name of search
     * @return
     */
    @Override
    public String getName() {
        return "Best First Search.";
    }
}
