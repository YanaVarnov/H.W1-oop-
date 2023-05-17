public class Node {

    private Action action;
    private State state;
    private Node parent;

    public Node(String state){
        this.state = new State(state);
    }
    public Node(int tileValue, Direction dir, String state, Node father){
        this.action = new Action(tileValue,dir);
        this.state = new State(state);
        this.parent = new Node(father.action.getTileValue(), father.action.getDirection(),
                father.state.board.getBoardString(), father.parent);
    }
    public Node getParent(){
        if(this.parent == null)
            return null;
        Node parent = new Node(this.parent.action.getTileValue(), this.parent.action.getDirection(),
                this.parent.state.board.getBoardString(), this.parent.parent);
        return parent;
    }
    public State getState(){
        String stateStr = state.board.getBoardString();
        State currState = new State(stateStr);
        return currState;
    }

    public Action getAction(){
        if(this.action == null)
            return null;
        int value = action.getTileValue();
        Direction dir = action.getDirection();
        Action currAction = new Action(value, dir);
        return currAction;
    }

    public int heuristicValue(){return 1;}

    public Node[] expand(){
        Node father;
        if(this.action == null && this.parent == null)
            father = new Node(state.board.getBoardString());
        else
            father = new Node(action.getTileValue(), action.getDirection(), state.board.getBoardString(), parent.parent);
        State possState;
        Action[] possAct = state.actions();
        Node[] childNodes = new Node[possAct.length] ;
        for(int i=0;i<possAct.length;i++){
            possState = state.result(possAct[i]);
            childNodes[i] = new Node(possAct[i].getTileValue(),possAct[i].getDirection(),possState.board.getBoardString(),father);
        }
        return childNodes;
    }

}
