import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.MazeState;
import algorithms.search.SearchableMaze;
import org.junit.jupiter.api.TestInfo;

import java.lang.annotation.Repeatable;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BreadthFirstSearchTest {

    @org.junit.jupiter.api.Test
    public void testNodesAndStatesNumRandom() {
        Random rnd = new Random();
        Boolean result = true;
        for (int i = 0; i < 1000; i ++) {
            int mazeWidth = rnd.nextInt(990) + 10;
            int mazeLength = rnd.nextInt(990) + 10;
            System.out.println("Testing maze solving in the with size of " + mazeLength + "x" + mazeWidth);
            MyMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(mazeWidth, mazeLength);
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            BreadthFirstSearch bfs = new BreadthFirstSearch();
            bfs.solve(searchableMaze);
            MazeState startingState = new MazeState(maze.getStartPosition());
            if (bfs.getNumberOfNodesEvaluated() > searchableMaze.getAdjacencyList().size()) {
                result = false;
            }
            else {
                System.out.println("Maze of size " + mazeLength + "x" + mazeWidth + " was solved correctly");
            }
        }
        assertTrue(result);
    }
}