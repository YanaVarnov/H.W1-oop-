import java.util.Arrays;
import java.util.StringTokenizer;

public class Board {
    private Tile [][] tiles;
    private String boardString;
    private int rowNum;
    private int colNum;

    /**
     * gets the size of the board from the string
     * @param arrBoard the given board string
     * @return the size of the board ,as a string, for the builder
     */
    private void getBoardSize(String arrBoard){
        int i, rowlen=0, colLen=0;
        int []arrBoardSize = new int[2];
        for(i=0;i < arrBoard.length();i++){
            if (arrBoard.charAt(i) == '|')
                rowlen++;

            if (rowlen == 1 && colLen==0){
                colLen = (i / 2) + 1;
            }
        }
        if(rowlen == 0)
            colLen = (arrBoard.length() / 2) + 1;
        rowNum = rowlen + 1;
        colNum= colLen;
    }

    /**
     * a builder functions that builds the game board
     * @param arrBoard the given board string
     */
    public Board(String arrBoard){
        int i,j,counter=0;
        getBoardSize(arrBoard);
        this.tiles = new Tile[rowNum][colNum];
        this.boardString = arrBoard;

        for(i=0; i < rowNum; i++){
            for (j=0; j < colNum; j++){
                if(Character.isDigit(arrBoard.charAt(counter)))
                    tiles[i][j] =new Tile(Character.getNumericValue(arrBoard.charAt(counter)));
                else
                    tiles[i][j] = new Tile(0);
                counter +=2;
            }
        }
    }
    public Board(Tile[][] tiles){
        this.boardString = tilesToString(tiles);
        this.rowNum = tiles.length;
        this.colNum = tiles[0].length;
        this.tiles = new Tile[rowNum][colNum];
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum; j++){
                this.tiles[i][j] = new Tile(tiles[i][j].getValue());
            }
        }
    }

    public static String tilesToString(Tile[][] tiles){
        String updateString = "";
        int rowNum = tiles.length, colNum = tiles[0].length;
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum - 1; j++){
                if(tiles[i][j].getValue() != 0)
                    updateString += tiles[i][j].getValue() + " ";
                else
                    updateString += "_ ";
            }
            if(tiles[i][colNum - 1].getValue() != 0)
                updateString += tiles[i][colNum - 1].getValue() + "|";
            else
                updateString += "_|";
        }
        return updateString;
    }

    /**
     * finds the indexes of the free space on the board
     * @return an array with the indexes: cell "0" for the row index and cell "1" for the column index
     */
    public int[] findTileByValue(int value){
        int[] location = new int[2];
        boolean flag = false;
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum; j++) {
                if(this.tiles[i][j].getValue() == value){
                    location[0] = i;
                    location[1] = j;
                    flag = true;
                    break;
                }
            }
            if(flag)
                break;
        }
        return location;
    }

    public int findValueByLoc(int i, int j){return this.tiles[i][j].getValue();}

    public Tile[][] getTiles() {
        int i, j;
        Tile[][] tempTiles = new Tile[rowNum][colNum];
        for (i = 0; i < rowNum; i++) {
            for (j = 0; j < colNum; j++) {
                tempTiles[i][j] = new Tile(this.tiles[i][j].getValue());
            }
        }
        return tempTiles;
    }

    public int[] getRowCol(){
        int[] rowCol = {this.rowNum, this.colNum};
        return rowCol;
    }

    public void setSpecificTile(int i, int j, int value){
        char currDigit = (char) (this.tiles[i][j].getValue() + 48), nextDigit;
        if(value == 0)
            nextDigit = '_';
        else
            nextDigit = (char)(value + 48);
        this.tiles[i][j].setValue(value);
        int index = 0;
        for(int k = 0; k < this.boardString.length(); k++){
           if(this.boardString.charAt(k) == currDigit){
               index = k;
               break;
           }
        }
        this.boardString = this.boardString.substring(0,index)+ nextDigit +this.boardString.substring(5);
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
