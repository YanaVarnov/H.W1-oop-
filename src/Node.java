public class Node {

    private Action action;
    private State state;
    private Node parent;

    public Node(int tileValue,Direction dir,String possState,Node father){
        this.action = new Action(tileValue,dir);
        this.state = new State(possState);
        if(father != NULL)
            this.parent = new Node(tileValue,dir,possState,parent);

    }

    public Node(int tileValue,Direction dir,String possState){
        this.action = new Action(tileValue,dir);
        this.state = new State(possState);
    }

    public State getState(){
        String stateStr = state.board.getBoardString();
        State currState = new State(stateStr);
        return currState;
    }

    public Action getAction(){
        int value = action.getTileValue();
        Direction dir = action.getDirection();
        Action currAction = new Action(value, dir);
        return currAction;
    }

    private int heuristicValue(){


    }

    private Node[] expand(){
        Node father = null;
        State possState;
        Action []possAct = state.actions();
        Node []nodes = new Node[possAct.length] ;
        for(int i=0;i<possAct.length;i++){

            possState = state.result(possAct[i]);
            nodes[i] = new Node(possAct[i].getTileValue(),possAct[i].getDirection(),possState.board.getBoardString(),parentNode);
        }
    }

}
