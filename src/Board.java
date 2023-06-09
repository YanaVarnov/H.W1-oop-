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
        String subCollCheck;
        for(i=0;i < arrBoard.length();i++){
            if (arrBoard.charAt(i) == '|')
                rowlen++;

            if (rowlen == 1 && colLen == 0){
                subCollCheck = arrBoard.substring(0,i);
                colLen = subCollCheck.split(" ").length;
            }
        }
        if(rowlen==0)
            colLen = arrBoard.split(" ").length;

        rowNum = rowlen + 1;
        colNum = colLen;
    }


    /**
     * a builder functions that builds the game board
     * @param arrBoard the given board string
     */
    public Board(String arrBoard){
        int i,rowCount=0,colCount=0;
        getBoardSize(arrBoard);
        this.tiles = new Tile[rowNum][colNum];
        this.boardString = arrBoard;
        String []arrStr = arrBoard.split("[| ]");

        for(i=0; i <arrStr.length; i++)
        {
            tiles[rowCount][colCount] = new Tile(extractNumber(arrStr[i]));

            colCount++;

            if(colCount == colNum) {
                colCount = 0;
                rowCount++;
            }

        }
    }

    /**
     * extracts and converts the value of the string into integer
     * @param tileStr the string that holds the value of the Tile
     * @return the value of the string converted to integer
     */
    private int extractNumber(String tileStr){
        int tileChar=0;
        if(tileStr.charAt(0)=='_')
            return 0;

        tileChar = Integer.parseInt(tileStr);
        return tileChar;
    }

    /**
     * gives access to the object's attribute: boardString
     * @return a String representing the board
     */
    public String getBoardString(){return this.boardString;}

    /**
     * gives access to the object attribute: tiles
     * @return a 2D array of Tile representing the board
     */
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

    /**
     * gives access to the object attribute: rowNum, colNum
     * @return an array in which cell "0" is for number of rows, anc cell "1" is for number of columns
     */
    public int[] getRowCol(){
        int[] rowCol = {this.rowNum, this.colNum};
        return rowCol;
    }

    /**
     * takes a matrix of tiles and represents it as a string
     * @param tiles 2D array of Tiles representing the board
     * @return a String that represents the board
     */
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
            if(i == rowNum - 1){
                if(tiles[i][colNum - 1].getValue() != 0)
                    updateString += tiles[i][colNum - 1].getValue();
                else
                    updateString += "_";
            }
            else{
                if(tiles[i][colNum - 1].getValue() != 0)
                    updateString += tiles[i][colNum - 1].getValue() + "|";
                else
                    updateString += "_|";
            }
        }
        return updateString;
    }

    /**
     * finds the indexes of the tile with a specific value
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
    /**
     * finds the value of a tile by being give its coordinates on the board
     * @param i row index
     * @param j column  index
     * @return the value of the tile
     */
    public int findValueByLoc(int i, int j){return this.tiles[i][j].getValue();}

    /**
     * finds the goal board and represents it as a String
     * @return a String representing the goal board
     */
    public String findGoalString(){
        int rowNum = this.rowNum, colNum = this.colNum, value = 1;
        String goalTiles = "";
        for(int i = 0; i < rowNum; i++){
            for(int j = 0; j < colNum - 1; j++) {
                goalTiles += value + " ";
                value++;
            }
            if(i == rowNum - 1)
                goalTiles += "_";
            else {
                goalTiles += value + "|";
                value++;
            }
        }
        return goalTiles;
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
