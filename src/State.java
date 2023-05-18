public class State {
    private Board board;

    public State(String currBoard){
        this.board = new Board(currBoard);
    }

    /**
     * gives access to the object attribute: boardString
     * @return a String that represents the board
     */
    public String getStateString(){return this.board.getBoardString();}

    /**
     * finds the String that represents the goal board
     * @return a String that represents the goal board
     */
    public String getGoalStateString(){return this.board.findGoalString();}

    /**
     * checks whether the current state of the board is the goal state
     * @return true if the current state of the board is the goal state and false otherwise
     */
    public boolean isGoal(){
        String goalTiles = this.board.findGoalString();
        if(this.board.getBoardString().equals(goalTiles))
            return true;
        else
            return false;
    }

    /**
     * checks which actions can be made from the current state of the board
     * @return an array of valid actions
     */
    public Action[] actions(){
        Action[] temp = new Action[4];
        int[] rowCol = this.board.getRowCol();
        int rowNum = rowCol[0], colNum = rowCol[1], value = 1;
        int[] location = this.board.findTileByValue(0);
        int rowLoc = location[0], colLoc = location[1], count = 0;
        Direction[] directions = {Direction.UP, Direction.DOWN, Direction.RIGHT, Direction.LEFT};
        for(int dir = 0; dir < directions.length; dir++){
            if(Action.isActionValid(rowNum, colNum, directions[dir], rowLoc, colLoc)) {
                int tileValue = Action.tileToMove(this.board, directions[dir], rowLoc, colLoc);
                temp[count] = new Action(new Tile(tileValue), directions[dir]);
                count++;
            }
        }
        Action[] actions = new Action[count];
        for(int i = 0; i < count; i++)
            actions[i] = temp[i].copyAction();
        return actions;
    }

    /**
     * finds the new state according to the action that is being made rom the current state
     * @param action an Action object that represents an action that can be made from the current state
     * @return a State object representing the new state
     */

    public State result(Action action){
        int value = action.getTileValue();
        Tile[][] tiles = this.board.getTiles();
        int[] tileLoc = this.board.findTileByValue(value);
        int[] freeLoc = this.board.findTileByValue(0);
        tiles[tileLoc[0]][tileLoc[1]] = new Tile(0);
        tiles[freeLoc[0]][freeLoc[1]] = new Tile(value);
        String nextBoardString = Board.tilesToString(tiles);
        State nextState = new State(nextBoardString);
        return nextState;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof State)) {
            return false;
        }
        State otherState = (State) other;
        return board.equals(otherState.board);
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }
}
