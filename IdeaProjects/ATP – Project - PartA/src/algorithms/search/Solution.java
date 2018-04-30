package algorithms.search;
import java.util.ArrayList;
import java.util.LinkedList;

public class Solution {

    LinkedList<AState> m_SolutionStates;

    public Solution(LinkedList<AState> solutionList){
        m_SolutionStates = solutionList;
    }

    public Solution(){
        m_SolutionStates = new LinkedList<AState>();
    }

    public ArrayList<AState> getSolutionPath(){
        return new ArrayList<AState>(m_SolutionStates);
    }

    public AState get(int numOfState){
        return null;
    }
}
