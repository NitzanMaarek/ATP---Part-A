package JUnit.JUnitTestingBestFirstSearch;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.lang.annotation.Repeatable;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class JUnitTestingBestFirstSearch {

    @BeforeEach
    void beforeEachTest() {
        System.out.println("Starting Test");
    }

    @AfterEach
    void afterEachTest() {
        System.out.println("Test Passed");
        System.out.println("=====================");
    }

    @org.junit.jupiter.api.Test
    public void nodesAssesmentPrimMazeWithBesthAlgoTest() {
        Random rnd = new Random();
        for (int i = 0; i < 500; i ++) {
            int mazeWidth = rnd.nextInt(100) + 50;
            int mazeLength = rnd.nextInt(100) + 50;
            System.out.println("Testing solving of a maze in a size of " + mazeLength + "x" + mazeWidth + " with Best-FS");
            MyMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(mazeWidth, mazeLength);
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            BestFirstSearch bfs = new BestFirstSearch();
            bfs.solve(searchableMaze);
            MazeState startingState = new MazeState(maze.getStartPosition());
            assertTrue(bfs.getNumberOfNodesEvaluated() < searchableMaze.getAdjacencyList().size());
            System.out.println("Maze of size " + mazeLength + "x" + mazeWidth + " was solved correctly");
        }
    }

    @org.junit.jupiter.api.Test
    public void solvingPrimMazeWithBest() {
        Random rnd = new Random();
        for (int i = 0; i < 500; i ++) {
            int mazeWidth = rnd.nextInt(100) + 50;
            int mazeLength = rnd.nextInt(100) + 50;
            System.out.println("Testing solving of a maze in a size of " + mazeLength + "x" + mazeWidth + " with Best-FS");
            MyMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(mazeWidth, mazeLength);
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            BestFirstSearch bfs = new BestFirstSearch();

            Position endPosition = maze.getGoalPosition();
            Solution solution = bfs.solve(searchableMaze);
            ArrayList<AState> path = solution.getSolutionPath();
            MazeState lastState = (MazeState) path.get(path.size() - 1);
            Position finalSolutionPosition = lastState.getStatePosition();

            assertTrue(endPosition.getRowIndex() == finalSolutionPosition.getRowIndex() &&
                    endPosition.getColumnIndex() == finalSolutionPosition.getColumnIndex());

            System.out.println("Maze of size " + mazeLength + "x" + mazeWidth + " was solved correctly");
        }
    }

    @org.junit.jupiter.api.Test
    public void simpleMazeBestSolving() {
        Random rnd = new Random();
        for (int i = 0; i < 500; i ++) {
            int mazeWidth = rnd.nextInt(10) + 50;
            int mazeLength = rnd.nextInt(10) + 50;
            System.out.println("Testing solving of a maze in a size of " + mazeLength + "x" + mazeWidth + " with Best-FS");
            SimpleMazeGenerator mazeGenerator = new SimpleMazeGenerator();
            Maze maze = mazeGenerator.generate(mazeWidth, mazeLength);
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            BreadthFirstSearch breadth = new BreadthFirstSearch();
            BestFirstSearch bfs = new BestFirstSearch();

            Position endPosition = maze.getGoalPosition();
            breadth.solve(searchableMaze);
            Solution solution = bfs.solve(searchableMaze);
            ArrayList<AState> path = solution.getSolutionPath();
            MazeState lastState = (MazeState) path.get(path.size() - 1);
            Position finalSolutionPosition = lastState.getStatePosition();

            assertTrue(endPosition.getRowIndex() == finalSolutionPosition.getRowIndex() &&
                    endPosition.getColumnIndex() == finalSolutionPosition.getColumnIndex());

            System.out.println("Maze of size " + mazeLength + "x" + mazeWidth + " was solved correctly");
        }
    }


    @org.junit.jupiter.api.Test
    public void smallSizeMazeSolving() {
        // Creating generators
        MyMazeGenerator mazeGenerator = new MyMazeGenerator();
        SimpleMazeGenerator simpleMazeGenerator = new SimpleMazeGenerator();

        BestFirstSearch bfs = new BestFirstSearch();

        Maze maze = mazeGenerator.generate(1, 1);
        Maze simpleMaze = simpleMazeGenerator.generate(1, 1);
        SearchableMaze searchableMaze = new SearchableMaze(maze);
        SearchableMaze simpleSearchable = new SearchableMaze(simpleMaze);

        // Checking maze width and length control
        assertTrue(searchableMaze.getWidth() > 1);
        assertTrue(simpleSearchable.getWidth() > 1);

        // Trying to solve prim maze
        Position endPosition = maze.getGoalPosition();
        Solution solution = bfs.solve(searchableMaze);
        ArrayList<AState> path = solution.getSolutionPath();
        MazeState lastState = (MazeState) path.get(path.size() - 1);
        Position finalSolutionPosition = lastState.getStatePosition();

        assertTrue(endPosition.getRowIndex() == finalSolutionPosition.getRowIndex() &&
                endPosition.getColumnIndex() == finalSolutionPosition.getColumnIndex());

        // Trying to solve simple maze
        endPosition = simpleMaze.getGoalPosition();
        solution = bfs.solve(simpleSearchable);
        path = solution.getSolutionPath();
        lastState = (MazeState) path.get(path.size() - 1);
        finalSolutionPosition = lastState.getStatePosition();

        assertTrue(endPosition.getRowIndex() == finalSolutionPosition.getRowIndex() &&
                endPosition.getColumnIndex() == finalSolutionPosition.getColumnIndex());
    }


}