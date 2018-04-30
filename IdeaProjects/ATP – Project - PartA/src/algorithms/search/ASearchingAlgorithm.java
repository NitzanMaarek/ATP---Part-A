package algorithms.search;

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
        return new Solution(reverseLinkedList(listSolutionStates));
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
