public class State {
    protected Board board;

    public State(String currBoard){
        this.board = new Board(currBoard);
    }

    /**
     * checks whether the current state of the board is the goal state
     * @return true if the current state of the board is the goal state and false otherwise
     */
    public boolean isGoal(){
        int rowNum = this.board.rowNum, colNum = this.board.colNum, value = 1;
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
        int rowNum = this.board.rowNum, colNum = this.board.colNum;
        int[] location = this.board.findTile(0);
        int rowLoc = location[0], colLoc = location[1], count = 0;
        Direction[] directions = {Direction.UP, Direction.DOWN, Direction.RIGHT, Direction.LEFT};
        for(int dir = 0; dir < directions.length; dir++){
            if(Action.isActionValid(rowNum, colNum, directions[dir], rowLoc, colLoc)) {
                int tileValue = Action.tileToMove(this.board, directions[dir], rowLoc, colLoc);
                temp[count] = new Action(tileValue, directions[dir]);
                count++;
            }
        }
        Action[] actions = new Action[count];
        for(int i = 0; i < count; i++)
            actions[i] = temp[i].copyAction();
        return actions;
    }

    public State result(Action action){
        String nextBoardString = this.board.getBoardString();
        State nextState = new State(nextBoardString);
        int[] tileLoc = nextState.board.findTile(action.getTileValue()), freeLoc = nextState.board.findTile(0);
        /*switch (action.getDirection()){
            case UP:
                freeLoc[0] = tileLoc[0] - 1;
                freeLoc[1] = tileLoc[1];
                break;
            case DOWN:
                freeLoc[0] = tileLoc[0] + 1;
                freeLoc[1] = tileLoc[1];
                break;
            case RIGHT:
                freeLoc[0] = tileLoc[0];
                freeLoc[1] = tileLoc[1] + 1;
                break;
            case LEFT:
                freeLoc[0] = tileLoc[0];
                freeLoc[1] = tileLoc[1] - 1;
                break;
        }*/
        nextState.board.setSpecificTile(tileLoc[0], tileLoc[1], 0);
        nextState.board.setSpecificTile(freeLoc[0], freeLoc[1], action.getTileValue());
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
