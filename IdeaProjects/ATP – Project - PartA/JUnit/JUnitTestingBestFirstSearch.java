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
    void getNumberOfNodesEvaluated() {

    }

    @org.junit.jupiter.api.Test
    public void solvingPrimMazeWithBreadthAlgoTest() {
        Random rnd = new Random();
        Boolean result = true;
        for (int i = 0; i < 10000; i ++) {
            int mazeWidth = rnd.nextInt(100) + 50;
            int mazeLength = rnd.nextInt(100) + 50;
            System.out.println("Testing solving of a maze in a size of " + mazeLength + "x" + mazeWidth + " with Breadth-FS");
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

    @org.junit.jupiter.api.Test
    public void solvingSimpleMazeWithBreadthAlgoTest() {
        Random rnd = new Random();
        Boolean result = true;
        for (int i = 0; i < 10000; i ++) {
            int mazeWidth = rnd.nextInt(100) + 50;
            int mazeLength = rnd.nextInt(100) + 50;
            System.out.println("Testing solving of a simple maze in a size of " + mazeLength + "x" + mazeWidth + " with Breadth-FS");
            SimpleMazeGenerator mazeGenerator = new SimpleMazeGenerator();
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


    @org.junit.jupiter.api.Test
    public boolean testNodesAndStatesNum() {
        for (int i = 100; i < 500; i++) {
            MyMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(i,i);
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            BreadthFirstSearch bfs = new BreadthFirstSearch();
            bfs.solve(searchableMaze);
            MazeState startingState = new MazeState(maze.getStartPosition());
            if (bfs.getNumberOfNodesEvaluated() > searchableMaze.getAdjacencyList().size()) {
                System.out.println("Nodes evaluated = " + bfs.getNumberOfNodesEvaluated());
                System.out.println("Nodes number =  " + searchableMaze.getAdjacencyList().size());
                return false;
            }
        }
        return true;
    } // Failed

    @org.junit.jupiter.api.Test
    public boolean solve() {
        for (int i = 100; i < 500; i++) {
            MyMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(i,i);
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            BreadthFirstSearch bfs = new BreadthFirstSearch();
            Position endPosition = maze.getGoalPosition();
            Solution solution = bfs.solve(searchableMaze);
            ArrayList<AState> path = solution.getSolutionPath();
            MazeState lastState = (MazeState) path.get(path.size() - 1);
            Position finalSolutionPosition = lastState.getStatePosition();
            if (finalSolutionPosition.getColumnIndex() != endPosition.getColumnIndex() || finalSolutionPosition.getRowIndex() != endPosition.getRowIndex()) {
                System.out.println("failed in maze size of: " + i);
                return false;
            }
        }
        return true;
    } // Failed

    @org.junit.jupiter.api.Test
    public boolean mazeCreation(){
        for (int i = 100; i < 500; i++) {
            MyMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(i,i);
        }
        return true;
    } // Passed

    @org.junit.jupiter.api.Test
    public boolean searchableMazeCreation(){
        for (int i = 100; i < 500; i++) {
            MyMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(i,i);
            SearchableMaze searchableMaze = new SearchableMaze(maze);

        }
        return true;
    } // Passed

    @org.junit.jupiter.api.Test
    public boolean bfsCreation(){
        for (int i = 100; i < 500; i++) {
            MyMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(i,i);
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            BreadthFirstSearch bfs = new BreadthFirstSearch();

        }
        return true;
    } // Passed

    @org.junit.jupiter.api.Test
    public boolean solvingTest(){
        for (int i = 100; i < 500; i++) {
            MyMazeGenerator mazeGenerator = new MyMazeGenerator();
            Maze maze = mazeGenerator.generate(i,i);
            SearchableMaze searchableMaze = new SearchableMaze(maze);
            BreadthFirstSearch bfs = new BreadthFirstSearch();
            bfs.solve(searchableMaze);
        }
        return true;
    } // Failed!!!


    @org.junit.jupiter.api.Test
    void scanSearchableMaze() {

    }

    @org.junit.jupiter.api.Test
    void getName() {

    }

}