package algorithms.mazeGenerators;

public interface IMazeGenerator {

    public Maze generate(int x, int y);
    public long measureAlgorithmTimeMillis(int x, int y);
}
