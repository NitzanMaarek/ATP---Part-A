package algorithms.mazeGenerators;

abstract public class AMazeGenerator
    implements IMazeGenerator {

    @Override
    abstract public Maze generate(int x, int y);

    @Override
    public long measureAlgorithmTimeMillis(int x, int y) {
        long a = System.currentTimeMillis();
        generate(x, y);
        long b = System.currentTimeMillis();
        return b - a;
    }
}
