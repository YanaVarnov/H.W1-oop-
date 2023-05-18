public class Tile {
    private int value;

    public Tile(int value){
        this.value = value;
    }

    /**
     * gives access to the object's attribute: value
     * @return a number representing the tile's value
     */
    public int getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Tile)) {
            return false;
        }
        Tile tile = (Tile) other;
        return value == tile.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }
}