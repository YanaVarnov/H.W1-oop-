public class Action {
    private Tile tile;
    private Direction direction;

    public Action(int tileValue, Direction dir){
        this.tile = new Tile(tileValue);
        this.direction = dir;
    }

    public int getTileValue(){return this.tile.getValue();}
    public Direction getDirection(){return this.direction;}

    /**
     * Makes a copy of the current object
     * @return a new object with the same attributes values as the current object
     */
    public Action copyAction(){
        int value = this.tile.getValue();
        Direction dir = this.direction;
        Action copy = new Action(value, dir);
        return copy;
    }

    /**
     * checks whether it's possible to move a tile in a given direction
     * @param rowNum number of rows of the board
     * @param colNum number of columns of the board
     * @param dir the direction being checked
     * @param rowLoc row index of the free space on the board
     * @param colLoc column index of the free space on the board
     * @return true if it's possible to move a tile in the given direction and false otherwise
     */
    public static boolean isActionValid(int rowNum, int colNum, Direction dir, int rowLoc, int colLoc){
        boolean valid = false;
        switch(dir){
            case UP:
                if(rowLoc < rowNum - 1)
                    valid = true;
                break;
            case DOWN:
                if(rowLoc > 0)
                    valid = true;
                break;
            case RIGHT:
                if(colLoc > 0)
                    valid = true;
                break;
            case LEFT:
                if(colLoc < colNum - 1)
                    valid = true;
                break;
        }
        return valid;
    }

    /**
     * finds the tile that needs to be moved in the given direction in order to make the action
     * @param board an object representing the game board
     * @param dir the direction the tile needs to be moved to
     * @param rowLoc row index of the free space on the board
     * @param colLoc column index of the free space on the board
     * @return the value of the tile that needs to be moved
     */
    public static int tileToMove(Board board, Direction dir, int rowLoc, int colLoc){
        Tile[][] tiles = board.getTiles();
        int tileValue = 0;
        switch(dir){
            case UP:
                tileValue = tiles[rowLoc + 1][colLoc].getValue();
                break;
            case DOWN:
                tileValue = tiles[rowLoc - 1][colLoc].getValue();
                break;
            case RIGHT:
                tileValue = tiles[rowLoc][colLoc - 1].getValue();
                break;
            case LEFT:
                tileValue = tiles[rowLoc][colLoc + 1].getValue();
                break;
        }
        return tileValue;
    }

    public static int makeMove(Board board, Action action){
        int rowNum = board.rowNum, colNum = board.colNum;
        Tile [][] nextTiles = board.getTiles();
        int[] location = board.findTile(0);
        int rowLoc = location[0], colLoc = location[1];
        int nextTile = 0;
        Direction nextDir = action.direction;
        switch (nextDir){
            case UP:
                nextTile = nextTiles[rowLoc + 1][colLoc].getValue();
                break;
            case DOWN:
                nextTile = nextTiles[rowLoc - 1][colLoc].getValue();
                break;
            case RIGHT:
                nextTile = nextTiles[rowLoc][colLoc - 1].getValue();
                break;
            case LEFT:
                nextTile = nextTiles[rowLoc][colLoc + 1].getValue();
                break;
        }
        return nextTile;
    }

    /**
     * represents the action as a string
     * @return a string the represents the action
     */
    @Override
    public String toString(){
        String str = "Move " + this.tile.getValue() + " ";
        switch(this.direction){
            case UP:
                str += "up";
                break;
            case DOWN:
                str += "down";
                break;
            case RIGHT:
                str += "right";
                break;
            case LEFT:
                str += "left";
                break;
        }
        return str;
    }

}
