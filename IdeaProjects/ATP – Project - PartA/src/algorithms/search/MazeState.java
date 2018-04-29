package algorithms.search;

import algorithms.mazeGenerators.Position;
import javafx.geometry.Pos;

public class MazeState extends AState {
    Position currentState;

    public MazeState(Position aState){
        currentState = aState;
    }

    @Override
    public String toString() {
        return currentState.toString();
    }

    public Position getStatePosition(){
        return currentState;
    }
}
