import java.util.Arrays;
import java.util.StringTokenizer;

public class Board {
private Tile [][] tiles;
private String boardString;
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
                break;
        }
        return  location;
    }

    /**
     * gets the size of the board from the string
     * @param arrBoard the given board string
     * @return the size of the board ,as a string, for the builder
     */
    private int[] getBoardSize(String arrBoard){
        int i,rowlen=0,colLen=0;
        int []arrBoardSize = new int[2];
        String tempStr;
        for(i=0;i < arrBoard.length();i++){
            if (arrBoard.charAt(i) == '|')
                 rowlen++;
            if (rowlen == 1) {
                 tempStr = arrBoard.substring(i);
                 colLen = (tempStr.length() / 2) + 1;
            }
        }
        arrBoardSize[0]=rowlen;
        arrBoardSize[1]=colLen;
        return arrBoardSize;
    }

    /**
     * a builder functions that builds the game board
     * @param arrBoard the given board string
     */
    public Board(String arrBoard){
        int i,j,counter=0;
        int []boardSize = getBoardSize(arrBoard);
        this.tiles = new Tile[boardSize[0]][boardSize[1]];
        this.boardString = arrBoard;

        for(i=0;i<boardSize[0];i++){
            for (j=0;j<boardSize[1];j++){
                counter=+2;
                if(isDigit(arrBoard.charAt(counter)))
                    tiles[i][j] =new Tile(Character.getNumericValue(arrBoard.charAt(counter)));
                else
                    tiles[i][j] = new Tile(0);
            }
        }
    }

    public Tile[][] getTiles() {
        int i, j;
        int rowNum = this.tiles.length, colNum = this.tiles[0].length;
        Tile[][] tempTiles = new Tile[rowNum][colNum];
        for (i = 0; i < rowNum; i++) {
            for (j = 0; j < colNum; j++) {
                tempTiles[i][j] = new Tile(this.tiles[i][j].getValue());
            }
        }
        return tempTiles;
    }
    public String getBoardString(){
        return this.boardString;
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
