public class Node {

    private Action action;
    private State state;
    private Node parent;

    public Node(Action action, String state, Node father){
        if(father != null)
            this.parent = father;
        if(action != null)
            this.action = action;
        this.state = new State(state);

    }

    /**
     * gives access to the object attribute: parent
     * @return a Node object that represents the parent of the current object
     */
    public Node getParent(){
        if(this.parent == null)
            return null;
        return this.parent;
    }

    /**
     * gives access to the object attribute: state
     * @return a State object that represents the state of the current object
     */
    public State getState(){
        return this.state;
    }

    /**
     * gives access to the object attribute: action that led to the current state from the previous one
     * @return an Action object that represents the action that led to the current state from the previous one
     */
    public Action getAction(){
        if(this.action == null)
            return null;
        return action;
    }

    /**
     * calculates the heuristic value by checking the manhattan distance of each tile from its goal position
     * @return a number that represents the heuristic value
     */
    public int heuristicValue(){
        int distSum = 0;
        String goal = state.getGoalStateString();
        Board goalBoard = new Board(goal);
        Board currBoard = new Board(this.state.getStateString());
        Tile[][] goalTiles = goalBoard.getTiles(), tiles = currBoard.getTiles();
        int rowNum = goalTiles.length, colNum = goalTiles[0].length;
        for(int val = 1; val < rowNum*colNum; val++){
            int[] currLoc = currBoard.findTileByValue(val);
            int[] goalLoc = goalBoard.findTileByValue(val);
            distSum += manhattanDistance(currLoc[0], currLoc[1], goalLoc[0], goalLoc[1]);
        }
        return distSum;
    }

    /**
     * calculates the manhattan distance of a tile from a goal location
     * @param currRow row index of the current position
     * @param currCol column index of the current position
     * @param goalRow row index of the goal position
     * @param goalCol column index of the goal position
     * @return the manhattan distance of the tile from its goal position
     */
    private int manhattanDistance(int currRow, int currCol, int goalRow, int goalCol){
        int mDist = 0;
        if(currRow > goalRow)
            mDist += currRow - goalRow;
        else
            mDist += goalRow - currRow;
        if(currCol > goalCol)
            mDist += currCol - goalCol;
        else
            mDist += goalCol - currCol;
        return mDist;
    }

    /**
     * finds the next nodes according to the possible actions that can be made from the current state
     * @return an array of the next nodes
     */
    public Node[] expand(){
        State possState;
        Action[] possAct = state.actions();
        Node[] childNodes = new Node[possAct.length] ;
        for(int i=0; i < possAct.length; i++){
            possState = state.result(possAct[i]);
            childNodes[i] = new Node(possAct[i], possState.getStateString(), this);
        }
        return childNodes;
    }

}
