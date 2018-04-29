package algorithms.search;

import algorithms.mazeGenerators.Position;

public class BestFirstSearch extends ASearchingAlgorithm implements  ISearchingAlgorithm {

    @Override
    protected void scanSearchableMaze(ISearchable domain) {

    }

    @Override
    protected Solution traceSolution(Position goalPos) {
        return null;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return numOfEvaluatedNodes;
    }

    @Override
    public Solution solve(ISearchable domain) {
        return null;
    }

    @Override
    public String getName() {
        return "Best First Search.";
    }
}
