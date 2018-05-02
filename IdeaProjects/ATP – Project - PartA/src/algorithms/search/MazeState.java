package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import javafx.geometry.Pos;

public class MazeState extends AState {
    Position currentState;
    int stateCost;


    /**
     * Constructor
     * @param aState
     */
    public MazeState(Position aState){
        currentState = aState;
    }

    /**
     * Copy constructor
     * @param other
     */
    public MazeState(MazeState other){
        currentState = new Position(other.getStatePosition());
    }

    /**
     * Getter for cost
     * @return Cost
     */
    public int getStateCost() {
        return stateCost;
    }

    /**
     * equals for Position
     * @param other - other object to compare to.
     * @return - true if equals or false otherwise.
     */
    @Override
    public boolean equals(Object other){
        if(other == null){return false;}
        if(other == this){return true;}
        if(!(other instanceof MazeState)) {return false;}
        MazeState otherMazeState = (MazeState) other;
        return ((otherMazeState.getStatePosition().getRowIndex() == this.currentState.getRowIndex()) && (otherMazeState.getStatePosition().getColumnIndex() == this.currentState.getColumnIndex()));
    }

    /**
     * setter for cost
     * @param stateCost
     */
    public void setStateCost(int stateCost) {
        this.stateCost = stateCost;
    }


    @Override
    public String toString() {
        return currentState.toString();
    }

    /**
     * getter for position
     * @return
     */
    public Position getStatePosition(){
        return currentState;
    }
}
