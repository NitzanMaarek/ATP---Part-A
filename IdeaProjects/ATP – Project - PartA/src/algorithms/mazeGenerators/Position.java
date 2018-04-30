package algorithms.mazeGenerators;

public class Position {
    private int m_Row;
    private int m_Column;

    /**
     * Contstructor
     * @param x - row first
     * @param y - column second
     */
    public Position(int x, int y){
        m_Column = y;
        m_Row = x;
    }

    public Position(Position other){
        m_Row = other.getRowIndex();
        m_Column = other.getColumnIndex();
    }

    /**
     * Default constructor
     */
    public Position(){
        m_Row = -1;
        m_Column = -1;
    }

    /**
     * getter for Row
     * @return
     */
    public int getRowIndex() {
        return m_Row;
    }

    /**
     * toString!
     * @return string!
     */
    public String toString(){
        return ("(" + m_Row + ", " + m_Column + ")");
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
        if(!(other instanceof Position)) {return false;}
        Position otherPosition = (Position)other;
        return ((otherPosition.getRowIndex() == this.m_Row) && (otherPosition.getColumnIndex() == this.m_Column));
    }

    public void setRowIndex(int m_Row) {
        this.m_Row = m_Row;
    }

    public int getColumnIndex() {
        return m_Column;
    }

    public void setColumnIndex(int m_Height) {
        this.m_Column = m_Height;
    }
}
