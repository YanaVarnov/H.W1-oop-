public class Action {
    private Tile tile;
    private Direction direction;

    public Action(Tile tile, Direction dir){
        this.tile = tile;
        this.direction = dir;
    }

    //public Tile getTile(){return this.tile;}
    //public Direction getDirection(){return this.direction;}

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
     * @return a Tile object of the tile that needs to be moved
     */
    public static Tile tileToMove(Board board, Direction dir, int rowLoc, int colLoc){
        Tile[][] tiles = board.getTiles();
        Tile tile = new Tile();
        switch(dir){
            case UP:
                tile = new Tile(tiles[rowLoc + 1][colLoc].getValue());
                break;
            case DOWN:
                tile = new Tile(tiles[rowLoc - 1][colLoc].getValue());
                break;
            case RIGHT:
                tile = new Tile(tiles[rowLoc][colLoc - 1].getValue());
                break;
            case LEFT:
                tile = new Tile(tiles[rowLoc][colLoc + 1].getValue());
                break;
        }
        return tile;
    }

    public static void makeMove(Board board, Action action){
        int rowNum = board.getTiles().length, colNum = board.getTiles()[0].length;
        Tile[][] nextTiles = new Tile[rowNum][colNum];
        System.arraycopy(board.getTiles(), 0, nextTiles, 0, rowNum);
        int[] location = board.freeLocation();
        int rowLoc = location[0], colLoc = location[1];
        Tile nextTile = action.tile;
        Direction nextDir = action.direction;
        switch (nextDir){
            case UP:
                nextTiles[rowLoc][colLoc] = nextTile;
                nextTiles[rowLoc + 1][colLoc] = null;
                break;
            case DOWN:
                nextTiles[rowLoc][colLoc] = nextTile;
                nextTiles[rowLoc - 1][colLoc] = null;
                break;
            case RIGHT:
                nextTiles[rowLoc][colLoc] = nextTile;
                nextTiles[rowLoc][colLoc - 1] = null;
                break;
            case LEFT:
                nextTiles[rowLoc][colLoc] = nextTile;
                nextTiles[rowLoc][colLoc + 1] = null;
                break;
        }
        board.setTiles(nextTiles);
    }

    /**
     * represents the action as a string
     * @return a string the represents the action
     */
    public String toString(){
        return "Move " + this.tile + " " + this.direction;
    }

}
