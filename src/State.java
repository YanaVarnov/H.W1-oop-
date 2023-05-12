public class State {
    private Board board;

    public State(Board currBoard){
        this.board = currBoard;
    }

    /**
     * checks whether the current state of the board is the goal state
     * @return true if the current state of the board is the goal state and false otherwise
     */
    public boolean isGoal(){
        int rowNum = this.board.getTiles().length, colNum = this.board.getTiles()[0].length, value = 1;
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
        Board goalBoard = new Board(goalTiles);
        if(this.board.equals(goalBoard))
            return true;
        else
            return false;
    }

    /**
     * checks which actions can be made at the current state of the board
     * @return an array of valid actions
     */
    public Action[] actions(){
        Action[] temp = new Action[4];
        int rowNum = this.board.getTiles().length, colNum = this.board.getTiles()[0].length;
        int[] location = this.board.freeLocation();
        int rowLoc = location[0], colLoc = location[1], count = 0;
        Direction[] directions = {Direction.UP, Direction.DOWN, Direction.RIGHT, Direction.LEFT};
        for(int dir = 0; dir < directions.length; dir++){
            if(Action.isActionValid(rowNum, colNum, directions[dir], rowLoc, colLoc)) {
                Tile tile = Action.tileToMove(this.board, directions[dir], rowLoc, colLoc);
                temp[count] = new Action(tile, directions[dir]);
                count++;
            }
        }
        Action[] actions = new Action[count];
        System.arraycopy(temp, 0, actions, 0, count);
        return actions;
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
