package fr.craftinglabs.pi.apa102.matrix;

public class Size {
    private final int nbColumn;
    private final int nbLines;

    public Size(int nbColumn, int nbLines) {
        this.nbColumn = nbColumn;
        this.nbLines = nbLines;
    }

    public int nbColumns() {
        return nbColumn;
    }

    public int nbLines() {
        return nbLines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Size size = (Size) o;

        if (nbColumn != size.nbColumn) return false;
        return nbLines == size.nbLines;
    }

    @Override
    public int hashCode() {
        int result = nbColumn;
        result = 31 * result + nbLines;
        return result;
    }
}
