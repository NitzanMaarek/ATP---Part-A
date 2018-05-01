package algorithms.JUnit;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.search.BreadthFirstSearch;
import algorithms.search.SearchableMaze;

import static org.junit.jupiter.api.Assertions.*;

public class JUnitTestingBestFirstSearch {



    @org.junit.jupiter.api.Test
    void getNumberOfNodesEvaluated() {

    }

    public boolean testNodesAndStatesNum() {
        for (int i = 10; i < 5000; i++) {
            MyMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(i,i);
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            BreadthFirstSearch bfs = new BreadthFirstSearch();
            bfs.solve(searchableMaze);
            int statesNum = searchableMaze.getAllPossibleStates().length;
            if (statesNum > searchableMaze.getAdjacencyList().size() / 4) {
                return false;
            }
        }
        return true;
    }

    @org.junit.jupiter.api.Test
    void solve() {

    }

    @org.junit.jupiter.api.Test
    void scanSearchableMaze() {

    }

    @org.junit.jupiter.api.Test
    void getName() {

    }

}