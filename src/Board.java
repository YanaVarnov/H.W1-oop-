import java.util.Arrays;

public class Board {

    /**
     * finds the indexes of the free space on the board
     * @return an array with the indexes: cell "0" for the row index and cell "1" for the column index
     */
    public int[] freeLocation(){
        int rowNum = this.tiles.length, colNum = this.tiles[0].length;
        int[] location = new int[2];
        boolean flag = false;
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum; j++) {
                if(this.tiles[i][j] == null){
                    location[0] = i;
                    location[1] = j;
                    flag = true;
                    break;
                }
            }
            if(flag)
                break;;
        }
        return  location;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Board)) {
            return false;
        }
        Board board = (Board) other;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }
}
