package algorithms.search;

import algorithms.mazeGenerators.Position;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm{

    Position[][] solutionGrid;  //Each cell will contain position of the previous cell visited.
    int numOfEvaluatedNodes;

    public ASearchingAlgorithm(){}

    protected abstract Solution traceSolution(Position goalPos);

    protected abstract void scanSearchableMaze(ISearchable domain);

    @Override
    public abstract Solution solve(ISearchable domain);

    @Override
    public abstract int getNumberOfNodesEvaluated();

    @Override
    public abstract String getName();


}
