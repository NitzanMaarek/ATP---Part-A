package algorithms.mazeGenerators;

public class Position {
    private int m_Row;
    private int m_Height;

    public Position(int x, int y){
        m_Height = y;
        m_Row = x;
    }

    public int getRow() {
        return m_Row;
    }

    public void setRow(int m_Row) {
        this.m_Row = m_Row;
    }

    public int getHeight() {
        return m_Height;
    }

    public void setHeight(int m_Height) {
        this.m_Height = m_Height;
    }
}
