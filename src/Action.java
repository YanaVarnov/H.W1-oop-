public class Action {
    private Tile tile;
    private Direction direction;

    public Action(Tile tile, Direction dir){
        this.tile = new Tile(tile.getValue());
        this.direction = dir;
    }

    /**
     * gives access to the object attribute: tile (value)
     * @return a number representing the action's tile value
     */
    public int getTileValue(){return this.tile.getValue();}

    /**
     * gives access to the object attribute: direction
     * @return a Direction object representing the action's direction
     */
    public Direction getDirection(){return this.direction;}

    /**
     * Makes a copy of the current object
     * @return a new object with the same attributes values as the current object
     */
    public Action copyAction(){
        int value = this.tile.getValue();
        Direction dir = this.direction;
        Action copy = new Action(new Tile(value), dir);
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
        int tileValue = 0;
        switch(dir){
            case UP:
                tileValue = board.findValueByLoc(rowLoc + 1, colLoc);
                break;
            case DOWN:
                tileValue = board.findValueByLoc(rowLoc - 1, colLoc);
                break;
            case RIGHT:
                tileValue = board.findValueByLoc(rowLoc, colLoc - 1);
                break;
            case LEFT:
                tileValue = board.findValueByLoc(rowLoc, colLoc + 1);
                break;
        }
        return tileValue;
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
