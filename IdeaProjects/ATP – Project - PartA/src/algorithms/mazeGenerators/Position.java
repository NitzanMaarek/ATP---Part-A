package algorithms.mazeGenerators;

public class Position {
    private int m_Row;
    private int m_Column;

    public Position(int x, int y){
        m_Column = y;
        m_Row = x;
    }

    public int getRowIndex() {
        return m_Row;
    }

    public String toString(){
        return ("(" + m_Row + ", " + m_Column + ")");
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
