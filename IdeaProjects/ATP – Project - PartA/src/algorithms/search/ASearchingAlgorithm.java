package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.LinkedList;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{

    Position[][] solutionGrid;  //Each cell will contain position of the previous cell visited.
    int numOfEvaluatedNodes;

    /**
     * Default constructor
     */
    public ASearchingAlgorithm(){}

    protected abstract void scanSearchableMaze(SearchableMaze domain);

    @Override
    public abstract Solution solve(ISearchable domain);

    @Override
    public abstract int getNumberOfNodesEvaluated();

    @Override
    public abstract String getName();

    /**
     * This function traces the solution steps back from the goal position.
     * @param goalPos - Goal position
     * @return - Solution
     */
    protected Solution traceSolution(Position goalPos) {
//        testPrintSolutionGrid(solutionGrid);
        Position currentNode = goalPos;
        LinkedList<AState> listSolutionStates = new LinkedList<>();
        listSolutionStates.add(new MazeState(currentNode)); //Adding to list the goal position.
        while(!solutionGrid[currentNode.getRowIndex()][currentNode.getColumnIndex()].equals(new Position())){
            currentNode = solutionGrid[currentNode.getRowIndex()][currentNode.getColumnIndex()];    //Next node in path
            listSolutionStates.add(new MazeState(currentNode));     //Add that node to list.
        }
        return new Solution(fixSolutionDoubleSpaces(reverseLinkedList(listSolutionStates)));
    }

    /**
     * Since our maze uses prims algorithm, we jump in steps of 2, so we're gonna want to use the steps in between in the solution path.
     * @param givenPartialPath - path with jumps of 2
     * @return - path with jumps of 1.
     */
    private LinkedList<AState> fixSolutionDoubleSpaces(LinkedList<AState> givenPartialPath){
        LinkedList<AState> listSolutionStates = new LinkedList<>();
        Position currPos, nextPos;
        MazeState currMazeState = null, nextMazeState = null;
        for(int i=0; i < givenPartialPath.size() - 1; i++){
            currMazeState = (MazeState) givenPartialPath.get(i);
            nextMazeState = (MazeState) givenPartialPath.get(i+1);
            currPos = currMazeState.getStatePosition();
            nextPos = nextMazeState.getStatePosition();
            listSolutionStates.add(currMazeState);
            if((currPos.getRowIndex() - nextPos.getRowIndex()) == 2 || (currPos.getRowIndex() - nextPos.getRowIndex()) == -2){
                listSolutionStates.add(new MazeState(new Position((currPos.getRowIndex() - nextPos.getRowIndex())/2 + nextPos.getRowIndex() , currPos.getColumnIndex())));
            }
            if((currPos.getColumnIndex() - nextPos.getColumnIndex() == 2) || (currPos.getColumnIndex() - nextPos.getColumnIndex() == -2)){
                listSolutionStates.add(new MazeState(new Position(currPos.getRowIndex(), (currPos.getColumnIndex() - nextPos.getColumnIndex())/2 + nextPos.getColumnIndex())));
            }
        }
        listSolutionStates.add(new MazeState(nextMazeState));
        return listSolutionStates;

//        LinkedList<AState> givenSolutionPath = new LinkedList<AState>(otherSol.getSolutionPath());
//
//        for(int i=0; i < givenSolutionPath.size() - 1; i++){
//            currMazeState = (MazeState)givenSolutionPath.get(i);
//            nextMazeState = (MazeState)givenSolutionPath.get(i+1);
//            currPos = currMazeState.getStatePosition();
//            nextPos = nextMazeState.getStatePosition();
//            listSolutionStates.add(currMazeState);
//            if((currPos.getRowIndex() - nextPos.getRowIndex()) == 2 || (currPos.getRowIndex() - nextPos.getRowIndex()) == -2){
//                 listSolutionStates.add(new MazeState(new Position((currPos.getRowIndex() - nextPos.getRowIndex()) + nextPos.getRowIndex() , currPos.getColumnIndex())));
//            }
//            if((currPos.getColumnIndex() - nextPos.getColumnIndex() == 2) || (currPos.getColumnIndex() - nextPos.getColumnIndex() == -2)){
//                listSolutionStates.add(new MazeState(new Position(currPos.getRowIndex(), (currPos.getColumnIndex() - nextPos.getColumnIndex()) + nextPos.getColumnIndex())));
//            }
////            listSolutionStates.add(nextMazeState);
//        }
    }

    /**
     * Since we trace the solution from goal to start we need to reverse the state list.
     * @param reverseMe - LinkedList to reverse.
     * @return - reversed LinkedList.
     */
    protected LinkedList<AState> reverseLinkedList(LinkedList<AState> reverseMe){
        LinkedList<AState> ans = new LinkedList<>();
        for(int i = reverseMe.size() - 1; i >= 0; i--){
            ans.add(reverseMe.get(i));
        }
        return ans;
    }

    private void testPrintSolutionGrid(Position[][] grid){
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                System.out.print(grid[i][j]);
            }
            System.out.println("");
        }
    }


}
