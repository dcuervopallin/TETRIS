package utils.treeStaff;

public class transitionGame {
    final private int column;
    final private int rotation;

    public transitionGame(int column, int rotation) {
        this.column = column;
        this.rotation = rotation;
    }

    public int getRotation() {
        return rotation;
    }

    public int getColumna() {
        return column;
    }

    @Override
    public String toString() {
        return "transitionGame{" +
                "columna=" + column +
                ", rotation=" + rotation +
                '}';
    }
}
