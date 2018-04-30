package algorithms.search;

import algorithms.mazeGenerators.Position;

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
     * @param domain
     * @return
     */
    @Override
    public Solution solve(ISearchable domain) {
        return null;
    }

    @Override
    public String getName() {
        return "Best First Search.";
    }
}
