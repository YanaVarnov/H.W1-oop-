public class Node {

    private Action action;
    private State state;
    private Node parent;

    public Node(Action action, String state, Node father){
        if(father != null)
            this.parent = new Node(father.action, father.state.board.getBoardString(), father.parent);
        if(action != null)
            this.action = new Action(action.getTileValue(), action.getDirection());
        this.state = new State(state);

    }
    public Node getParent(){
        if(this.parent == null)
            return null;
        Node parent = new Node(this.parent.action, this.parent.state.board.getBoardString(), this.parent.parent);
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
            father = new Node(null, state.board.getBoardString(), null);
        else
            father = new Node(action,  state.board.getBoardString(), parent.parent);
        State possState;
        Action[] possAct = state.actions();
        Node[] childNodes = new Node[possAct.length] ;
        for(int i=0; i < possAct.length; i++){
            possState = state.result(possAct[i]);
            childNodes[i] = new Node(possAct[i], possState.board.getBoardString(), father);
        }
        return childNodes;
    }

}
